#ifndef HEADER_H
#define HEADER_H

// Includes
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

// Defines
#define CONFIG_FILE "./config.txt"
#define DRONE_FILE "./drone"
#define INPUT_FILENAME "file_drone_"
#define INPUT_FILE_EXTENSION ".txt"


#define CONFIG_FILE_MAX_SIZE 10000

#define RESET "\033[0m"
#define BOLD "\033[1m"
#define RED "\033[31m"
#define GREEN "\033[32m"
#define YELLOW "\033[33m"
#define BLUE "\033[34m"
#define CYAN "\033[36m"

typedef struct data {

  //config file info
  int max_collisions;
  int num_drones;
  int drone_radius;
  char *inp_dir;
  char *out_dir;
  int max_X;
  int max_Y;
  int max_Z;
  float timestamp;

  //signals
  struct sigaction sa;

  //pipes
  int up[2];
  int **down;

  //childs info
  char *state;
  int *pids;

  //childs created flag
  int childs_created;

} Data;

// Structs
typedef struct position {
  int x;
  int y;
  int z;
} Position;

typedef struct {
  int id;
  Position pos;
  int finished; // 0 = normal message, 1 = drone finished
} Message;

typedef struct {
    Position *positions;  // dynamic array of drone positions
    float *timestamps;    // parallel array of timestamps for each position
    int count;            // number of stored positions
    int capacity;         // current array capacity (for resizing)
    int drone_id;         // drone id
    int collision_count;  // number of collisions
} DroneHistory;

typedef struct space_cell {
    int drone_id; // -1 if empty, else drone id
} SpaceCell;

// Matrix to map drone index to its Position
typedef struct drone_position {
    int drone_id;
    Position pos;
} DronePosition;

typedef struct drone{
  int id;
  Position Curr_pos;
  Position *vector;
  int num_positions;
  bool continue_flag;
} Drone;


// Function declarations
void* safe_malloc(size_t size);
char *read_line(const char *prompt);
int read_int(const char *prompt);
double read_double(const char *prompt);
bool confirm(const char *message);
void show_list(const char *list[], size_t size, const char *header);
int select_index(const char *list[], size_t size, const char *header);
void goBackAndWait();
void trim_trailing_spaces(char* str);
void set_drone_in_space(SpaceCell ***space, int x, int y, int z, int drone_id);
void remove_drone_from_space(SpaceCell ***space, int x, int y, int z);
bool is_cell_empty(SpaceCell ***space, int x, int y, int z);
void move_drone(SpaceCell ***space, DronePosition *drone_positions, int drone_idx, Position new_pos, int sizeX, int sizeY, int sizeZ);
int get_drone_at(SpaceCell ***space, int x, int y, int z);
SpaceCell*** alloc_space(int sizeX, int sizeY, int sizeZ);
void free_space(SpaceCell ***space, int sizeX, int sizeY);
DronePosition* alloc_drone_positions(int num_drones);
double calculate_distance(Position p1, Position p2);


#endif
