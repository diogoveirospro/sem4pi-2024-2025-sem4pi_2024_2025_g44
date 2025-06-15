#ifndef HEADER_H
#define HEADER_H

// ------------ Includes -----------------

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <signal.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>
#include <errno.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <semaphore.h>
#include <pthread.h>
#include <pthread.h>


// ------------ Defines -----------------

//#define LOG_FILE "./log.txt"
#define CONFIG_FILE "./config.txt"
#define DRONE_FILE "./drone"
#define INPUT_FILENAME "file_drone_"
#define INPUT_FILE_EXTENSION ".txt"

#define CONFIG_FILE_MAX_SIZE 10000
#define MAX_COLLISIONS_LOG 1000
#define FILENAME_SIZE 500
#define HISTORY_INIT_CAPACITY 100
#define MAX_DRONES 100

#define PARENT_SHM_NAME "/shm_parent"
#define DRONES_SHM_NAME "/shm_drones"

//#define DRONE_SEMAPHORE_PREFIX "/sem_drone_"
//#define PARENT_SEMAPHORE_PREFIX "/sem_parent_"

#define RESET "\033[0m"
#define BOLD "\033[1m"
#define RED "\033[31m"
#define GREEN "\033[32m"
#define YELLOW "\033[33m"
#define BLUE "\033[34m"
#define CYAN "\033[36m"

// ------------ Structs -----------------

typedef struct vector {

  int x;
  int y;
  int z;

} Vector;


typedef struct position {

  int x;
  int y;
  int z;

} Position;


typedef struct drone_history {

    Position *positions;  // dynamic array of drone positions
                          //
    float *timestamps;    // parallel array of timestamps for each position
                          //
    int count;            // number of stored positions
    int capacity;         // current array capacity (for resizing)
    int drone_id;         // drone id
    int collision_count;  // number of collisions

} DroneHistory;


typedef struct collision_log {

    int drone1;
    int drone2;

    Position pos1;
    Position pos2;

    float timestamp;

} CollisionLog;


typedef struct space_cell {

    int drone_id; // -1 if empty, else drone id
                  
} SpaceCell;


typedef struct config_file_data {

  //config file info
  int max_collisions;
  int num_drones;
  int drone_radius;
  char *inp_dir;
  char *out_dir;
  int max_X;
  int max_Y;
  int max_Z;
  float timestep;

} ConfigData;


typedef struct parent_data {

  //childs pids
  int *pids;

  //save which drones ended
  int *finished;

  //number of drones active
  int active_drones;

  //number of total collisions
  int collisions;

  //childs created flag
  int childs_created;

} ParentData;


typedef struct drone_data {

  //arguments
  char *inp_dir;
  int id;
  int max_x;
  int max_y;
  int max_z;

  //script filename
  char *filename;

} DroneData;

// ------------ Possible Structs to use for shared memory -----------------

typedef struct {
  int drone_id;
  Position pos;
  int active;
  int collision_count;
} SharedDroneState;

typedef struct {
    SharedDroneState drones[MAX_DRONES]; // They are pointers but the values will be defined by the parent
} SharedMemoryDrone;


typedef struct {
    DroneHistory history[HISTORY_INIT_CAPACITY];
    CollisionLog collision_log[MAX_COLLISIONS_LOG]; // Log of collisions
} SharedMemoryParent;



typedef struct {
    size_t size; // size of the shared memory
    int fd; // file descriptor for the shared memory
} SharedMemoryFileInfo; // Information about the shared memory file

typedef struct {
    pthread_t *threads;
    int num_threads;
} ParentThreads;

typedef struct {
    pthread_mutex_t mutex;
    pthread_cond_t cond_collision;
} ParentMutex;

// ------------ Possible functions to use for shared memory -----------------

// Shared Memory Functions
int create_shared_memory(const char *name, size_t size);
int open_shared_memory(const char *name);
SharedMemoryDrone* attach_shared_memory(int fd, size_t size);
void detach_shared_memory(void* shmaddr, size_t size);
void clear_shared_memory(const char *name);
void resize_shared_memory(int fd, size_t new_size);
void change_drone_state(SharedMemoryDrone *shm, int idx, SharedDroneState value);
void update_collision_log(SharedMemoryParent *shm, CollisionLog *log, int count);
void close_shared_memory(int fd);

// Mutex and Condition Variable Functions
void init_mutex(pthread_mutex_t* mutex);
void init_cond(pthread_cond_t* cond);
void lock_mutex(pthread_mutex_t *mutex);
void unlock_mutex(pthread_mutex_t *mutex);
void wait_cond(pthread_cond_t *cond, pthread_mutex_t *mutex);
void signal_cond(pthread_cond_t *cond);
void clear_mutex(pthread_mutex_t* mutex);
void clear_cond(pthread_cond_t* cond);

// Semaphore Functions
sem_t* init_semaphore(const char *name, int value);
sem_t* open_semaphore(const char *name);
void post_semaphore(sem_t *sem);
void wait_semaphore(sem_t *sem);
void clear_semaphore(const char *name, sem_t *sem);
//int get_semaphore_value(sem_t *sem);

// Thread Functions
void create_threads(pthread_t *threads, int n, void *(*start_routine)(void *), void **args);
void join_threads(pthread_t *threads, int n);
void end_thread(); // optional has it might be better to use join


// ------------ Functions -----------------

void end();
void terminate();
void do_report();


int get_file_size(FILE *fd);

int **int_malloc_matrix(int row, int col);
void int_free_matrix(int **arr, int row);


DroneHistory **alloc_history(int n, int c);
void free_history(DroneHistory **h, int n);

void* safe_malloc(size_t size);
void set_drone_in_space(SpaceCell ***space, int x, int y, int z, int drone_id);
void remove_drone_from_space(SpaceCell ***space, int x, int y, int z);
bool is_cell_empty(SpaceCell ***space, int x, int y, int z);
void move_drone(SpaceCell ***space, SharedDroneState *drone_positions, int drone_idx, Position new_pos, int sizeX, int sizeY, int sizeZ);
int get_drone_at(SpaceCell ***space, int x, int y, int z);
SpaceCell*** alloc_space(int sizeX, int sizeY, int sizeZ);
void free_space(SpaceCell ***space, int sizeX, int sizeY);
double calculate_distance(Position p1, Position p2);

#endif
