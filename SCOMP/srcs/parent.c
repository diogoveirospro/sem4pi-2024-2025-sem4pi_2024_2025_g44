#include "header.h"

/**
 *
 * Parent starts
 * Reads config file
 * Setups initial configs
 * Creates Childs
 * Setups pipes and signal handlers
 * Distributes script files for all kids
 * (Files must be valid? or checked in program?)
 * Each kid has a script to follow
 * Each step the parent checks for collisions
 * If none, next step
 * Else notify kids involved (can be more than 1 collision
 * and maybe more than 2 drones in each collision)
 * If collision number higher than the limit
 * Terminate kids and end
 * Else keep going
 * When all steps over, create report
 *
 */

ConfigData c;
ParentData s;

SpaceCell ***space;
int **collision_state = NULL; // 0: no collision, 1: collision
SharedMemoryDrone *shm_drones = NULL;
SharedMemoryParent *shm_parent = NULL; // Global pointer to shared memory

size_t shm_size = 0;
int shm_fd = 0;
sem_t **semaphores_drones = NULL;
sem_t **semaphores_parent = NULL;

void end()
{
  raise(SIGUSR2);
}

void handler_sigusr2(int sig)
{
	(void) sig;

	if (s.childs_created)
	{
		for (int i = 0; i < c.num_drones; i++)
			kill(s.pids[i], SIGKILL);

		for (int i = 0; i < c.num_drones; i++)
			wait(NULL);
	}
  exit(1);
}

void set_up_signals()
{
  struct sigaction sa;

	memset(&sa, 0, sizeof(sa));
	sigfillset(&sa.sa_mask);
	sa.sa_handler = handler_sigusr2;
	sigaction(SIGUSR2, &sa, NULL);
}

void parse_data(char *str)
{
	char d[] = "=\n";

	strtok(str, d);
	c.inp_dir = strdup(strtok(NULL, d));

	strtok(NULL, d);
	c.out_dir = strdup(strtok(NULL, d));

	strtok(NULL, d);
	c.max_collisions = atoi(strtok(NULL, d));

	strtok(NULL, d);
	c.num_drones = atoi(strtok(NULL, d));

  strtok(NULL, d);
  c.drone_radius = atoi(strtok(NULL, d));

	strtok(NULL, d);
	c.max_X = atoi(strtok(NULL, d));

	strtok(NULL, d);
	c.max_Y = atoi(strtok(NULL, d));

	strtok(NULL, d);
	c.max_Z = atoi(strtok(NULL, d));

	strtok(NULL, d);
	c.timestep = atof(strtok(NULL, d));
}

void process_config_file()
{
	FILE *fd = fopen(CONFIG_FILE, "r");

	int size = get_file_size(fd);

	if (size > CONFIG_FILE_MAX_SIZE)
		end();

	char *str = (char *) malloc(sizeof(char) * size + 1);

	if (str == NULL)
		end();

  int n = fread(str, sizeof(char), size, fd);

  str[size] = '\0';

  if (n <= 0)
    end();

	fclose(fd);

	parse_data(str);

	free(str);
}

void set_up_childs()
{
    s.pids = (int *) malloc(sizeof(int) * c.num_drones);
    if (s.pids == NULL)
        end();
    memset(s.pids, 0, sizeof(int) * c.num_drones);

    for (int i = 0; i < c.num_drones; i++)
    {
        pid_t pid = fork();
        if (pid == 0)
        {
            char str_i[10], str_x[10], str_y[10], str_z[10];
            snprintf(str_i, sizeof(str_i), "%d", i + 1);
            snprintf(str_x, sizeof(str_x), "%d", c.max_X);
            snprintf(str_y, sizeof(str_y), "%d", c.max_Y);
            snprintf(str_z, sizeof(str_z), "%d", c.max_Z);
            execl(DRONE_FILE, DRONE_FILE, c.inp_dir, str_i, str_x, str_y, str_z, NULL);
            exit(1);
        }
        s.pids[i] = pid;
    }
    s.childs_created = 1;
}

void add_position_timestamp(DroneHistory *h, Position pos, float timestamp)
{
  if (h->count == h->capacity)
  {
    int new_capacity = h->capacity * 2;
    Position *new_positions = realloc(h->positions, new_capacity * sizeof(Position));
    float *new_timestamps = realloc(h->timestamps, new_capacity * sizeof(float));

    if (!new_positions || !new_timestamps)
    {
      perror("realloc");
      // In the event of an error, we can continue with the old memory to avoid losses
      free(new_positions);
      free(new_timestamps);
      return;
    }

    h->positions = new_positions;
    h->timestamps = new_timestamps;
    h->capacity = new_capacity;
  }

  h->positions[h->count] = pos;
  h->timestamps[h->count] = timestamp;
  h->count++;
}


// Check number of collisions between drones
int check_collisions(int iter)
{
    int new_collisions = 0;
    int collision_distance = c.drone_radius * 2;
    float current_timestamp = iter * c.timestep;

    double distance;

    for (int i = 0; i < c.num_drones; i++)
    {
        for (int j = i + 1; j < c.num_drones; j++)
        {
            distance = calculate_distance(shm_drones->drones[i].pos, shm_drones->drones[j].pos);

            if (distance <= collision_distance)
            {
                if (collision_state[i][j] == 0)
                {
                    // New collision!
                    kill(s.pids[i], SIGUSR1);
                    kill(s.pids[j], SIGUSR1);

                    shm_parent->history[i].collision_count++;
                    shm_parent->history[j].collision_count++;
                    shm_drones->drones[i].collision_count++;
                    shm_drones->drones[j].collision_count++;

                    new_collisions++;

                    // Log shared
                    shm_parent->collision_log[s.collisions].drone1 = i + 1;
                    shm_parent->collision_log[s.collisions].drone2 = j + 1;
                    shm_parent->collision_log[s.collisions].pos1 = shm_drones->drones[i].pos;
                    shm_parent->collision_log[s.collisions].pos2 = shm_drones->drones[j].pos;
                    shm_parent->collision_log[s.collisions].timestamp = current_timestamp;

                    s.collisions++;

                    collision_state[i][j] = 1;
                    collision_state[j][i] = 1;
                }
            }
            else
            {
                // Not in collision state reset
                collision_state[i][j] = 0;
                collision_state[j][i] = 0;
            }
        }
    }
    return new_collisions;
}

// updates position in all needed places
void update_position(int i, int iter, SharedDroneState *drone)
{
  if (shm_drones->drones[i].active == 0) {
    s.finished[i] = 1;
    s.active_drones--;
  } else {
    add_position_timestamp(&shm_parent->history[i], drone->pos, iter * c.timestep);
    move_drone(space, shm_drones->drones, i, drone->pos, c.max_X, c.max_Y, c.max_Z);
  }
}

// reads all childs messages
// saves each one to specific places
// if drone ended, remove him
//
// return 0 to continue program
// return -1 in case no more drones
int manage_drones(int iter)
{
  int msg_received_cnt = 0;

  for (int i = 0; i < c.num_drones; i++) {
    if (s.finished[i])
      continue;

    wait_semaphore(semaphores_drones[i]);  // sem_drone_%d

    SharedDroneState *drone = &shm_drones->drones[i];
    update_position(i, iter, drone);

    msg_received_cnt++;
  }

  if (s.active_drones == 0)
    return -1;
  return 0;
}

// checks for collision limit
int process_position(int iter)
{
  static int collisions = 0;

  collisions += check_collisions(iter);

  if (collisions >= c.max_collisions)
    return -1;
  return 0;
}

// send green flag to specific child
void send_green_flag(int i)
{
  post_semaphore(semaphores_parent[i]); // sem_parent_%d
}

void sync_drones()
{
  for (int i = 0; i < c.num_drones; i++) {
    if (!s.finished[i]) {
      send_green_flag(i);
    }
  }
}


// repeats process of reading position
// checking collision
// updating position
// syncing drones
// repeat
//
// if manage_drones returns -1
// means no more active drones
//
// if process_position returns -1
// means collision limit exceeded
void start_loop()
{
  int n_iter = 0;

  while (1)
  {
    if (manage_drones(n_iter) == -1)
      break;

    if (process_position(n_iter) == -1)
      break;

    sync_drones();

    n_iter++;
  }
}

// generates report file
void do_report()
{
  time_t now = time(NULL);
  struct tm *t = localtime(&now);

  char filename[100];
  snprintf(filename, sizeof(filename),
           "simulation_report_%02d_%02d_%02d_%02d_%02d_%02d.txt",
           t->tm_mday, t->tm_mon + 1, t->tm_year % 100,
           t->tm_hour, t->tm_min, t->tm_sec);

  char path[256];
  snprintf(path, sizeof(path), "%s/%s", c.out_dir, filename);

  if (mkdir(c.out_dir, 0777) == -1 && errno != EEXIST) {
    perror("Parent: Error creating output directory");
    return;
  }

  FILE *f = fopen(path, "w");
  if (!f) {
    perror("Parent: Error creating report file");
    return;
  }

  fprintf(f, "Simulation Report - Shodrone\n\n");
  fprintf(f, "Total drones: %d\n\n", c.num_drones);

  // Status of drones
  fprintf(f, "\nDrone Execution Summary:\n\n");
  for (int i = 0; i < c.num_drones; i++) {
    fprintf(f, "Drone %d: %d steps, %d collisions\n",
            shm_parent->history[i].drone_id,
            shm_parent->history[i].count,
            shm_parent->history[i].collision_count);
  }

  // Position history
  fprintf(f, "\n\nDrone Position History:\n\n");
  for (int i = 0; i < c.num_drones; i++) {
    fprintf(f, "Drone %d:\n", shm_parent->history[i].drone_id);
    for (int j = 0; j < shm_parent->history[i].count; j++) {
      fprintf(f, "  [t = %.2f] (%d, %d, %d)\n",
              shm_parent->history[i].timestamps[j],
              shm_parent->history[i].positions[j].x,
              shm_parent->history[i].positions[j].y,
              shm_parent->history[i].positions[j].z);
    }
    fprintf(f, "\n");
  }

  // Log collisions
  fprintf(f, "\nCollision Log:\n");
  if (s.collisions == 0)
    fprintf(f, "None\n");
  else {
    for (int i = 0; i < s.collisions; i++) {
      fprintf(f, "[t = %.2f] Drones %d (%d, %d, %d) and %d (%d, %d, %d) collided\n",
              shm_parent->collision_log[i].timestamp,
              shm_parent->collision_log[i].drone1,
              shm_parent->collision_log[i].pos1.x,
              shm_parent->collision_log[i].pos1.y,
              shm_parent->collision_log[i].pos1.z,
              shm_parent->collision_log[i].drone2,
              shm_parent->collision_log[i].pos2.x,
              shm_parent->collision_log[i].pos2.y,
              shm_parent->collision_log[i].pos2.z);
    }
  }

  fprintf(f, "\nValidation Result: %s\n",
          s.collisions >= c.max_collisions ? "FAILED" : "VALIDATED");

  fclose(f);
  fprintf(stderr, GREEN "\nSimulation report saved to %s\n" RESET, path);
}

void terminate()
{
  // Free allocated memory
  int_free_matrix(collision_state, c.num_drones);
  free_space(space, c.max_X, c.max_Y);

  free(s.pids);
  free(s.finished);

  // Free shared memory for drones
  if (shm_drones) {
    detach_shared_memory(shm_drones, sizeof(SharedMemoryDrone));
    shm_drones = NULL;
  }
  // Free shared memory for parent
  if (shm_parent) {
    detach_shared_memory(shm_parent, sizeof(SharedMemoryParent));
    shm_parent = NULL;
  }

  // Unlink shared memory
  clear_shared_memory("/shm_drones");
  clear_shared_memory("/shm_parent");

  // Clear semaphores
  if (semaphores_drones) {
    for (int i = 0; i < c.num_drones; i++) {
      char sem_name[64];
      snprintf(sem_name, sizeof(sem_name), "/sem_drone_%d", i+1);
      if (semaphores_drones[i]) {
        clear_semaphore(sem_name, NULL); // Unlink
      }
    }
    free(semaphores_drones);
    semaphores_drones = NULL;
  }
  if (semaphores_parent) {
    for (int i = 0; i < c.num_drones; i++) {
      char sem_name[64];
      snprintf(sem_name, sizeof(sem_name), "/sem_parent_%d", i+1);
      if (semaphores_parent[i]) {
        clear_semaphore(sem_name, NULL); // Unlink
      }
    }
    free(semaphores_parent);
    semaphores_parent = NULL;
  }


  fprintf(stderr, GREEN "\nDone!\n" RESET);
  exit(0);
}

void allocate_structs()
{
  // Collision state matrix
  collision_state = int_malloc_matrix(c.num_drones, c.num_drones);
  for (int i = 0; i < c.num_drones; i++)
    for (int j = 0; j < c.num_drones; j++)
      collision_state[i][j] = 0;

  // Space and positions
  space = alloc_space(c.max_X, c.max_Y, c.max_Z);

  // State of each drone
  s.finished = (int *) calloc(c.num_drones, sizeof(int));
  s.active_drones = c.num_drones;
  s.collisions = 0;

  // Shared Memory for drones
  int fd_drones = create_shared_memory("/shm_drones", sizeof(SharedMemoryDrone));
  shm_drones = mmap(NULL, sizeof(SharedMemoryDrone), PROT_READ | PROT_WRITE, MAP_SHARED, fd_drones, 0);

  // Shared Memory for parent
  int fd_parent = create_shared_memory("/shm_parent", sizeof(SharedMemoryParent));
  shm_parent = mmap(NULL, sizeof(SharedMemoryParent), PROT_READ | PROT_WRITE, MAP_SHARED, fd_parent, 0);

  // Initialize SharedMemoryDrone
  for (int i = 0; i < MAX_DRONES; i++) {
    shm_drones->drones[i].drone_id = i + 1;
    shm_drones->drones[i].pos = (Position){-1, -1, -1};
    shm_drones->drones[i].active = 0;
    shm_drones->drones[i].collision_count = 0;
  }

  // Initialize SharedMemoryParent

  for (int i = 0; i < c.num_drones; i++) {
    shm_parent->history[i].positions = malloc(HISTORY_INIT_CAPACITY * sizeof(Position));
    shm_parent->history[i].timestamps = malloc(HISTORY_INIT_CAPACITY * sizeof(float));
    shm_parent->history[i].capacity = HISTORY_INIT_CAPACITY;
    shm_parent->history[i].count = 0;
    shm_parent->history[i].drone_id = i + 1;
    shm_parent->history[i].collision_count = 0;
  }

  for (int i = 0; i < MAX_COLLISIONS_LOG; i++) {
    shm_parent->collision_log[i].drone1 = -1;
    shm_parent->collision_log[i].drone2 = -1;
    shm_parent->collision_log[i].pos1 = (Position){-1, -1, -1};
    shm_parent->collision_log[i].pos2 = (Position){-1, -1, -1};
    shm_parent->collision_log[i].timestamp = -1.0f;
  }

  // Allocate semaphores for each drone
  semaphores_drones = malloc(sizeof(sem_t *) * c.num_drones);
  if (!semaphores_drones) {
    fprintf(stderr, "Error while allocating semaphores\n");
    end();
  }
  semaphores_parent = malloc(sizeof(sem_t *) * c.num_drones);
  if (!semaphores_parent) {
    fprintf(stderr, "Error while allocating semaphores for parent\n");
    end();
  }

  for (int i = 0; i < c.num_drones; i++) {
    char sem_name[64];
    snprintf(sem_name, sizeof(sem_name), "/sem_drone_%d", i+1);
    semaphores_drones[i] = init_semaphore(sem_name, 0);
  }

  for ( int i = 0; i < c.num_drones; i++) {
    char sem_name[64];
    snprintf(sem_name, sizeof(sem_name), "/sem_parent_%d", i+1);
    semaphores_parent[i] = init_semaphore(sem_name, 0);
  }
}

void debug_config_file()
{
  fprintf(stderr, CYAN "\nSimulation Configurations:\n" RESET);
  fprintf(stderr, "Input Directory: %s\n", c.inp_dir);
  fprintf(stderr, "Output Directory: %s\n", c.out_dir);
  fprintf(stderr, "Maximum collisions: %d\n", c.max_collisions);
  fprintf(stderr, "Number of Drones: %d\n", c.num_drones);
  fprintf(stderr, "Drone Radius: %d\n", c.drone_radius);
  fprintf(stderr, "Maximum Coordinate X: %d\n", c.max_X);
  fprintf(stderr, "Maximum Coordinate Y: %d\n", c.max_Y);
  fprintf(stderr, "Maximum Coordinate Z: %d\n", c.max_Z);
  fprintf(stderr, "Timestep: %f\n\n", c.timestep);
}

void process_comand_line_args(char **argv)
{
  c.inp_dir = argv[1];
  c.out_dir = argv[2];
  c.max_collisions = atoi(argv[3]);
  c.num_drones = atoi(argv[4]);
  c.drone_radius = atoi(argv[5]);
  c.max_X = atoi(argv[6]);
  c.max_Y = atoi(argv[7]);
  c.max_Z = atoi(argv[8]);
  c.timestep = atof(argv[9]);
}

//void set_up_log_file();

// argv[1] inp_dir
// argv[2] out_dir
// argv[3] max_collisions
// argv[4] num_drones
// argv[5] drone_radius
// argv[6] max_X
// argv[7] max_Y
// argv[8] max_Z
// argv[9] timestep

int main(int argc, char **argv) {

  // set_up_log_file();

  if (argc == 1)
    process_config_file();
  else if (argc == 9)
    process_comand_line_args(argv);
  else
    end();

  // Clear shared memory from previous runs
  clear_shared_memory("/shm_drones");
  clear_shared_memory("/shm_parent");

  // Clean up semaphores from previous runs
  for (int i = 0; i < c.num_drones; i++) {
    char sem_name[64];
    snprintf(sem_name, sizeof(sem_name), "/sem_drone_%d", i+1);
    clear_semaphore(sem_name, NULL); // Unlink semaphore
  }

  for ( int i = 0; i < c.num_drones; i++) {
    char sem_name[64];
    snprintf(sem_name, sizeof(sem_name), "/sem_parent_%d", i+1);
    clear_semaphore(sem_name, NULL); // Unlink semaphore
  }

  //debug_config_file();

  set_up_signals();

  allocate_structs();

  set_up_childs();

  //set_up_threads(); // Needs to be implemented

  start_loop();

  do_report();

  terminate();
}
