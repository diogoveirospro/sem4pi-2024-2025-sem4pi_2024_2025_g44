#include "header.h"

DroneData s;
SharedMemoryDrone *shm = NULL;
sem_t *sem_d2p = NULL;  // drone-to-parent
sem_t *sem_p2d = NULL;  // parent-to-drone
char sem_name[64];
size_t shm_size = 0;
int shm_fd = 0;

// Ends the program, closes shared memory and semaphore, and frees allocated memory
// Receives the SIGUSR2
void end()
{
    if (sem_d2p) sem_close(sem_d2p);
    if (sem_p2d) sem_close(sem_p2d);
    if (shm) detach_shared_memory(shm, shm_size);
    free(s.filename);
    exit(0);
}

// sends SIGUSR2 to parent to kill everything
void handler_sigusr2(int sig)
{
  (void)sig;
  end();
}


// parent sends SIGUSR1 to notify collision
void handler_sigusr1(int sig)
{
  (void)sig;
}

// setup signal handling functions
void set_up_signals()
{
  struct sigaction sa_usr1;
  struct sigaction sa_usr2;

  // ----- SIGUSR1: collision -----
  memset(&sa_usr1, 0, sizeof(sa_usr1));
  sa_usr1.sa_handler = handler_sigusr1;
  sigfillset(&sa_usr1.sa_mask);
  sigaction(SIGUSR1, &sa_usr1, NULL);

  // ----- SIGUSR2: terminate program -----
  memset(&sa_usr2, 0, sizeof(sa_usr2));
  sa_usr2.sa_handler = handler_sigusr2;
  sigfillset(&sa_usr2.sa_mask);
  sigaction(SIGUSR2, &sa_usr2, NULL);
}

// waits for green flag from parent to keep going
void sync_drones()
{
  post_semaphore(sem_d2p);  // Notify parent
  wait_semaphore(sem_p2d);  // Wait for green light
}


// Updates the position in the shared memory
void update_position_in_shm(Position pos)
{
  shm->drones[s.id - 1].pos = pos;
  shm->drones[s.id - 1].active = 1;
}

// Marks the drone as finished in shared memory
void mark_finished_in_shm()
{
  shm->drones[s.id - 1].active = 0;

  // Notify parent that drone is finished
  post_semaphore(sem_d2p);

  // Still wait for parent's green flag so sync stays consistent
  //wait_semaphore(sem_p2d);
}


FILE *open_file(char *filename)
{
  FILE *f = fopen(filename, "r");

  if (!f) {
    perror("Drone: Error opening input file");
    end();
  }
  return f;
}

void sum_coordinates(void *s, void *d)
{
  Position *src = (Position *)s;
  Position *dst = (Position *)d;

  dst->x += src->x;
  dst->y += src->y;
  dst->z += src->z;
}

void copy_coordinates(void *s, void *d)
{
  Position *src = (Position *)s;
  Position *dst = (Position *)d;

  dst->x = src->x;
  dst->y = src->y;
  dst->z = src->z;
}

// Validates a position
// returns 1 if valid
// returns 0 if not valid
int valid_position(Position p)
{
  if (p.x < 0 || p.y < 0 || p.z < 0)
    return 0;
  if (p.x >= s.max_x || p.y >= s.max_y || p.z >= s.max_z) {
    return 0;
  }
    
  return 1;
}

void run_script(char *filename)
{
  FILE *f = open_file(filename);

  // current position
  Position c;

  // last position
  Position l;

  // new vector
  Vector v;

  int valid_flag = 1;

  // initially x,y,z save the initial position
  if (fscanf(f, "%d,%d,%d", &c.x, &c.y, &c.z) == 3) {

    valid_flag = valid_position(c);

    // only continue if position valid
    if (valid_flag) {

      // update shared memory with initial position
      update_position_in_shm(c);

      // save starting position in message
      copy_coordinates(&c, &l);

      // sync
      sync_drones();
    }
  }

  // now x,y,z save the vectors
  // c x,y,z are the current position
  while (fscanf(f, "%d,%d,%d", &v.x, &v.y, &v.z) == 3 && valid_flag) {

    // add vectors to positions to get new position
    sum_coordinates(&v, &c);

    valid_flag = valid_position(c);

    // if position not valid, break
    if (!valid_flag)
      break;

    // update shared memory with new position
    update_position_in_shm(c);

    // save new position into the last position
    copy_coordinates(&c, &l);

    // sync
    sync_drones();
  }

  fclose(f);


  mark_finished_in_shm();
}

char *build_filename(int id, char *inp_dir)
{
  char *filename;

  filename = (char *) malloc(sizeof(char) * FILENAME_SIZE);

  if (filename == NULL)
    end();

	snprintf(filename, FILENAME_SIZE, "%s/%s%d%s", inp_dir, INPUT_FILENAME, id, INPUT_FILE_EXTENSION);

  s.filename = filename;

  return filename;
}

void start_working()
{
  char *filename = build_filename(s.id, s.inp_dir);

  run_script(filename);
}

// Starts shared memory and semaphore
void set_up_shared_memory_and_semaphore()
{
  shm_fd = open_shared_memory("/shm_drones");

  shm_size = sizeof(SharedMemoryDrone);
  shm = attach_shared_memory(shm_fd, shm_size);

  char name[64];
  snprintf(name, sizeof(name), "/sem_drone_%d", s.id);
  sem_d2p = open_semaphore(name);  // Drone to parent

  snprintf(name, sizeof(name), "/sem_parent_%d", s.id);
  sem_p2d = open_semaphore(name);  // Parent to drone
}


void terminate()
{
  free(s.filename);
  exit(0);
}

// argv[1] input_directory
// argv[2] index of drone in arr of parent + 1
// argv[3] max_x
// argv[4] max_y
// argv[5] max_z

int main(int argc, char **argv)
{
  if (argc != 6)
    end();

  s.inp_dir = argv[1];
  s.id = atoi(argv[2]);
  s.max_x = atoi(argv[3]);
  s.max_y = atoi(argv[4]);
  s.max_z = atoi(argv[5]);

  set_up_signals();
  set_up_shared_memory_and_semaphore();
  start_working();
  end();
}
