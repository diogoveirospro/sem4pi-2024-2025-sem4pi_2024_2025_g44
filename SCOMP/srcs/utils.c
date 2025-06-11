#include "header.h"

// Safely allocate memory
void* safe_malloc(size_t size) {
    void* ptr = malloc(size);
    if (!ptr) {
        perror(RED "Error allocating memory" RESET);
        exit(EXIT_FAILURE);
    }
    return ptr;
}

// Allocates a 3D array for the space (sizeX x sizeY x sizeZ)
SpaceCell*** alloc_space(int sizeX, int sizeY, int sizeZ) {
    SpaceCell ***space = (SpaceCell ***)safe_malloc(sizeX * sizeof(SpaceCell **));
    for (int x = 0; x < sizeX; x++) {
        space[x] = (SpaceCell **)safe_malloc(sizeY * sizeof(SpaceCell *));
        for (int y = 0; y < sizeY; y++) {
            space[x][y] = (SpaceCell *)safe_malloc(sizeZ * sizeof(SpaceCell));
            for (int z = 0; z < sizeZ; z++) {
                space[x][y][z].drone_id = -1; // empty
            }
        }
    }
    return space;
}

// Frees the 3D space array
void free_space(SpaceCell ***space, int sizeX, int sizeY) {
    for (int x = 0; x < sizeX; x++) {
        for (int y = 0; y < sizeY; y++) {
            free(space[x][y]);
        }
        free(space[x]);
    }
    free(space);
}

// Allocates a matrix to map drones to positions
DronePosition* alloc_drone_positions(int num_drones) {
    DronePosition *matrix = (DronePosition *)safe_malloc(num_drones * sizeof(DronePosition));
    for (int i = 0; i < num_drones; i++) {
        matrix[i].drone_id = i;
        matrix[i].pos.x = matrix[i].pos.y = matrix[i].pos.z = -1; // Not placed yet
    }
    return matrix;
}

// Set a drone at a position in the 3D space
void set_drone_in_space(SpaceCell ***space, int x, int y, int z, int drone_id) {
    space[x][y][z].drone_id = drone_id;
}

// Remove a drone from a position in the 3D space
void remove_drone_from_space(SpaceCell ***space, int x, int y, int z) {
    space[x][y][z].drone_id = -1;
}

// Check if a cell is empty
bool is_cell_empty(SpaceCell ***space, int x, int y, int z) {
    return space[x][y][z].drone_id == -1;
}

// Move a drone in the 3D space (updates both space and drone_positions)
void move_drone(SpaceCell ***space, SharedDroneState *drone_positions, int drone_idx, Position new_pos, int sizeX, int sizeY, int sizeZ) {
    Position old_pos = drone_positions[drone_idx].pos;
    // Remove from old position if valid
    if (old_pos.x >= 0 && old_pos.x < sizeX && old_pos.y >= 0 && old_pos.y < sizeY && old_pos.z >= 0 && old_pos.z < sizeZ) {
        remove_drone_from_space(space, old_pos.x, old_pos.y, old_pos.z);
    }
    // Set in new position
    if(new_pos.x < 0 || new_pos.x >= sizeX || new_pos.y < 0 || new_pos.y >= sizeY || new_pos.z < 0 || new_pos.z >= sizeZ) {
        fprintf(stderr, RED "Error: New position out of bounds.\n" RESET);
        return;
    }
    if (is_cell_empty(space, new_pos.x, new_pos.y, new_pos.z)) {
        set_drone_in_space(space, new_pos.x, new_pos.y, new_pos.z, drone_idx);
    } 
    drone_positions[drone_idx].pos = new_pos;
}

// Find drone at a given position, returns drone_id or -1
int get_drone_at(SpaceCell ***space, int x, int y, int z) {
    return space[x][y][z].drone_id;
}













// calculate the distance between two drones
double calculate_distance(Position p1, Position p2) {
    return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2) + pow(p1.z - p2.z, 2));
}

int get_file_size(FILE *fd)
{
	int size = 0;
	int cursor;

	cursor = ftell(fd);
	fseek(fd, 0, SEEK_END);
	size = ftell(fd);
	fseek(fd, 0, cursor);

	return size;
}

int **int_malloc_matrix(int row, int col)
{
	int **arr = (int **) malloc(sizeof(int *) * row);

	if (arr == NULL)
		end();

	for (int i = 0; i < row; i++)
	{
		arr[i] = (int *) malloc(sizeof(int) * col);

		if (arr[i] == NULL)
			end();
	}
	return arr;
}

void int_free_matrix(int **arr, int row)
{
	for (int i = 0; i < row; i++)
		free(arr[i]);
	free(arr);
}

DroneHistory **alloc_history(int n, int c)
{
  DroneHistory **list;

  list = (DroneHistory**) malloc(sizeof(DroneHistory *) * n);

  for (int i = 0; i < n; i++) {
    list[i] = (DroneHistory *) malloc(sizeof(DroneHistory));

    list[i]->positions = malloc(sizeof(Position) * c);
    list[i]->timestamps = malloc(sizeof(float) * c);

    list[i]->count = 0;
    list[i]->capacity = c;
    list[i]->drone_id = i + 1;
    list[i]->collision_count = 0;
  }

  return list;
}

void free_history(DroneHistory **h, int n)
{
  for (int i = 0; i < n; i++)
  {
    free(h[i]->positions);
    free(h[i]->timestamps);
    free(h[i]);
  }
  free(h);
}

#include "header.h"
#include <sys/ipc.h>
#include <sys/shm.h>

// ---------------- Shared Memory Functions ----------------

// Creates a shared memory file and returns its file descriptor
int create_shared_memory(const char *name, size_t size) {
    int fd = shm_open(name, O_CREAT | O_EXCL | O_RDWR, S_IRUSR | S_IWUSR);
    if (fd == -1) {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }
    if (ftruncate(fd, size) == -1) {
        perror("ftruncate");
        close(fd);
        exit(EXIT_FAILURE);
    }
    return fd;
}

// Opens an existing shared memory file and returns its file descriptor
int open_shared_memory(const char *name) {
    int fd = shm_open(name, O_RDWR, S_IRUSR | S_IWUSR);
    if (fd == -1) {
        perror("shm_open");
        exit(EXIT_FAILURE);
    }
    return fd;
}

// Maps the shared memory to the process's address space and returns a pointer to it
SharedMemory* attach_shared_memory(int fd, size_t size) {
    void *addr = mmap(NULL, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (addr == MAP_FAILED) {
        perror("mmap");
        return NULL;
    }
    return addr;
}

// Detaches the shared memory from the process's address space
void detach_shared_memory(void* shmaddr, size_t size) {
    if (munmap(shmaddr, size) == -1) {
        perror("munmap");
    }
}

// Removes the shared memory file from the system
void clear_shared_memory(const char *name) {
    if (shm_unlink(name) == -1) {
        perror("shm_unlink");
    }
}

// Resizes the shared memory file to a new size
void resize_shared_memory(int fd, size_t new_size) {
    if (ftruncate(fd, new_size) == -1) {
        perror("ftruncate (resize)");
    }
}

// Updates a specific index in the shared memory with a new value
void change_drone_state(SharedMemory *shm, int idx, SharedDroneState value) {
    shm->drones[idx] = value;
}

// Updates the collision log in the shared memory
void update_collision_log(SharedMemory *shm, CollisionLog *log, int count) {
    shm->collision_log = log; // might be necessary to change
    shm->collision_count = count;
}

// Close file descriptor for shared memory
void close_shared_memory(int fd) {
    if (close(fd) == -1) {
        perror("close");
    }
}

// ---------------- Mutex and Condition Variables Functions ----------------

void init_mutex(pthread_mutex_t* mutex) {
    pthread_mutex_init(mutex, NULL);
}

void clear_mutex(pthread_mutex_t* mutex) {
    pthread_mutex_destroy(mutex);
}

void lock_mutex(pthread_mutex_t *mutex) {
    pthread_mutex_lock(mutex);
}

void unlock_mutex(pthread_mutex_t *mutex) {
    pthread_mutex_unlock(mutex);
}

void init_cond(pthread_cond_t* cond) {
    pthread_cond_init(cond, NULL);
}

void clear_cond(pthread_cond_t* cond) {
    pthread_cond_destroy(cond);
}

void wait_cond(pthread_cond_t *cond, pthread_mutex_t *mutex) {
    pthread_cond_wait(cond, mutex);
}

void signal_cond(pthread_cond_t *cond) {
    pthread_cond_signal(cond);
}

// ---------------- Semaphore Functions ----------------

sem_t* init_semaphore(const char *name, int value) {
    sem_t *sem = sem_open(name, O_CREAT | O_EXCL, S_IRUSR | S_IWUSR, value);
    if (sem == SEM_FAILED) {
        perror("Error while initializing semaphore");
        exit(EXIT_FAILURE);
    }
    return sem;
}

sem_t* open_semaphore(const char *name) {
    sem_t *sem = sem_open(name, 0);
    if (sem == SEM_FAILED) {
        perror("Error while opening semaphore");
        exit(EXIT_FAILURE);
    }
    return sem;
}

void clear_semaphore(const char *name, sem_t *sem) {
    if (sem != NULL) {
        sem_close(sem);
    }
    sem_unlink(name);
}

void post_semaphore(sem_t *sem) {
    sem_post(sem);
}

void wait_semaphore(sem_t *sem) {
    sem_wait(sem);
}

int get_semaphore_value(sem_t *sem) {
    int value;
    if (sem_getvalue(sem, &value) == -1) {
        perror("Error getting semaphore value");
        return -1;
    }
    return value;
}

// ---------------- Thread Functions ----------------

void create_threads(pthread_t *threads, int n, void *(*start_routine)(void *), void **args) {
    for (int i = 0; i < n; i++) {
        if (pthread_create(&threads[i], NULL, start_routine, args ? args[i] : NULL) != 0) {
            perror("Error while creating thread");
            exit(EXIT_FAILURE);
        }
    }
}

void join_threads(pthread_t *threads, int n) {
    for (int i = 0; i < n; i++) {
        pthread_join(threads[i], NULL);
    }
}

void end_thread() {
    pthread_exit(NULL);

}

