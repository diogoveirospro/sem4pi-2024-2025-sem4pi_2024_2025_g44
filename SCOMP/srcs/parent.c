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
DronePosition *drone_positions;
CollisionLog collision_log[MAX_COLLISIONS_LOG];
int **collision_state = NULL; // 0: no collision, 1: collision

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
  int **down = int_malloc_matrix(c.num_drones, 2);

	char str_i[10];
	char str_x[10];
	char str_y[10];
	char str_z[10];

	pid_t pid;

	s.pids = (int *) malloc(sizeof(int) * c.num_drones);

	if (s.pids == NULL)
		end();
	memset(s.pids, 0, sizeof(int) * c.num_drones);

	if (pipe(s.up))
		end();

	for (int i = 0; i < c.num_drones; i++)
	{
		if (pipe(down[i]))
			end();

		pid = fork();

		if (pid == 0)
		{
			close(s.up[0]);		    // child doesnt need read side on up pipe

			dup2(down[i][0], 0);	// child reads from fd 0 to get info from pipe down

			dup2(s.up[1], 1);	    // child writes to fd 1 to write info to pipe up

			close(down[i][1]);	  // child doesnt need write side on down pipe

			snprintf(str_i, sizeof(str_i), "%d", i + 1);
			snprintf(str_x, sizeof(str_x), "%d", c.max_X);
			snprintf(str_y, sizeof(str_y), "%d", c.max_Y);
			snprintf(str_z, sizeof(str_z), "%d", c.max_Z);
			execl(DRONE_FILE, DRONE_FILE, c.inp_dir, str_i, str_x, str_y, str_z, NULL);

			kill(getppid(), SIGUSR2);
		}
		close(down[i][0]);		  // parent doesnt need read side on down pipe

		s.pids[i] = pid;

		drone_positions[i].drone_id = i + 1;
	}
	close(s.up[1]);				    // parent doesnt need write side on up pipe

	s.down = down;
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
  int current_timestamp = iter * c.timestep;

  double distance;

  for (int i = 0; i < c.num_drones; i++)
    for (int j = i + 1; j < c.num_drones; j++)
    {
      distance = calculate_distance(drone_positions[i].pos, drone_positions[j].pos);

      if (distance <= collision_distance)
      {
        if (collision_state[i][j] == 0)
        {
          // New Collision!
          kill(s.pids[i], SIGUSR1);
          kill(s.pids[j], SIGUSR1);

          s.h_list[i]->collision_count++;
          s.h_list[j]->collision_count++;

          new_collisions++;

          collision_log[s.collisions].drone1 = i + 1;
          collision_log[s.collisions].drone2 = j + 1;
          collision_log[s.collisions].pos1 = drone_positions[i].pos;
          collision_log[s.collisions].pos2 = drone_positions[j].pos;
          collision_log[s.collisions].timestamp = current_timestamp;

          s.collisions++;

          collision_state[i][j] = 1;
          collision_state[j][i] = 1;
        }
      } 
      else
      {
        // They are no longer in collision, reset
        collision_state[i][j] = 0;
        collision_state[j][i] = 0;
      }
    }
  return new_collisions;
}

// verify if drone terminated
// returns 1 if terminated
// returns 0 if not terminated
int verify_drone_termination(Message m)
{
  if (m.pos.x == -1 || m.pos.y == -1 || m.pos.z == -1)
    return 1;
  return 0;
}

// updates position in all needed places
void update_position(Message m, int iter)
{
  // TODO: improve this condition. Supposed to check if invalid position.
  // if so, remove this drone from the matrix
  if (verify_drone_termination(m))
  {
    ;
  }
  else
  {
    add_position_timestamp(s.h_list[m.id - 1], m.pos, iter * c.timestep);
    move_drone(space, drone_positions, m.id - 1, m.pos, c.max_X, c.max_Y, c.max_Z);
  }
}


// reads message from up pipe
Message read_msg()
{
  Message m;

  read(s.up[0], &m, sizeof(Message));

  return m;
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
  int msg_expected_cnt = s.active_drones;

  Message m;

  while (msg_received_cnt < msg_expected_cnt)
  {
    m = read_msg();

    if (verify_drone_termination(m))
    {
      s.finished[m.id - 1] = 1;
      s.active_drones--;
    } 

    msg_received_cnt++;

    update_position(m, iter);
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
  write(s.down[i][1], (char []){'Z'}, sizeof(char));
}

// send msg to sync all drones
void sync_drones()
{
  for (int i = 0; i < c.num_drones; i++)
    if (!s.finished[i])
      send_green_flag(i);
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
  // File name with timestamp
  time_t now = time(NULL);
  struct tm *t = localtime(&now);

  char filename[100];

  snprintf(filename, sizeof(filename),
           "simulation_report_%02d_%02d_%02d_%02d_%02d_%02d.txt",
           t->tm_mday, t->tm_mon + 1, t->tm_year % 100,
           t->tm_hour, t->tm_min, t->tm_sec);

  char path[256];

  snprintf(path, sizeof(path), "%s/%s", c.out_dir, filename);

  // Create output directory if it doesn't exist
  if (mkdir(c.out_dir, 0777) == -1 && errno != EEXIST)
  {
    perror("Parent: Error creating output directory");
    return;
  }

  FILE *f = fopen(path, "w");
  if (!f)
  {
    perror("Parent: Error creating report file");
    return;
  }

  // Header
  fprintf(f, "Simulation Report - Shodrone\n\n");
  fprintf(f, "Total drones: %d\n\n", c.num_drones);

  DroneHistory *h;

  // Status of each drone
  fprintf(f, "\nDrone Execution Summary:\n\n");

  for (int i = 0; i < c.num_drones; i++)
  {
    h = s.h_list[i];
    fprintf(f, "Drone %d: %d steps, %d collisions\n",
            h->drone_id, h->count, h->collision_count);
  }

  // Drone Position History
  fprintf(f, "\n\nDrone Position History:\n\n");

  for (int i = 0; i < c.num_drones; i++)
  {
    h = s.h_list[i];
    fprintf(f, "Drone %d:\n", h->drone_id);

    for (int j = 0; j < h->count; j++)
    {
      fprintf(f, "  [t = %.2f] (%d, %d, %d)\n", 
              h->timestamps[j],
              h->positions[j].x,
              h->positions[j].y,
              h->positions[j].z);
    }
    fprintf(f, "\n");
  }

  // Collisions (detailed log)
  fprintf(f, "\nCollision Log:\n");

  if (s.collisions == 0) 
    fprintf(f, "None\n");
  else
  {
    for (int i = 0; i < s.collisions; i++)
    {
      CollisionLog *cl = &collision_log[i];

      fprintf(f, "[t = %.2f] Drones %d (%d, %d, %d) and %d (%d, %d, %d) collided\n", 
              cl->timestamp, cl->drone1, cl->pos1.x, cl->pos1.y, cl->pos1.z, cl->drone2, 
              cl->pos2.x, cl->pos2.y, cl->pos2.z);
    }
  }

  // Final result
  fprintf(f, "\nValidation Result: %s\n", 
          s.collisions >= c.max_collisions ? "FAILED" : "VALIDATED");

  fclose(f);
  fprintf(stderr, GREEN "\nSimulation report saved to %s\n" RESET, path);
}

void terminate()
{
  int_free_matrix(collision_state, c.num_drones);

  free_space(space, c.max_X, c.max_Y);
  free(drone_positions);



  free_history(s.h_list, c.num_drones);

  int_free_matrix(s.down, c.num_drones);

  free(s.pids);
  free(s.finished);

  printf(GREEN "\nDone!\n" RESET);

  exit(0);
}

void allocate_structs()
{
  collision_state = int_malloc_matrix(c.num_drones, c.num_drones);

  for (int i = 0; i < c.num_drones; i++)
    for (int j = 0; j < c.num_drones; j++)
      collision_state[i][j] = 0;

  space = alloc_space(c.max_X, c.max_Y, c.max_Z);

  drone_positions = alloc_drone_positions(c.num_drones);



  s.h_list = alloc_history(c.num_drones, HISTORY_INIT_CAPACITY);

  s.finished = (int *) calloc(c.num_drones, sizeof(int));
  s.active_drones = c.num_drones;
  s.collisions = 0;
}

void debug_config_file()
{
  printf(CYAN "\nSimulation Configurations:\n" RESET);
  printf("Input Directory: %s\n", c.inp_dir);
  printf("Output Directory: %s\n", c.out_dir);
  printf("Maximum collisions: %d\n", c.max_collisions);
  printf("Number of Drones: %d\n", c.num_drones);
  printf("Drone Radius: %d\n", c.drone_radius);
  printf("Maximum Coordinate X: %d\n", c.max_X);
  printf("Maximum Coordinate Y: %d\n", c.max_Y);
  printf("Maximum Coordinate Z: %d\n", c.max_Z);
  printf("Timestep: %f\n\n", c.timestep);
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

  //debug_config_file();
  
  set_up_signals();

  allocate_structs();

  set_up_childs();

  start_loop();

  do_report();

  terminate();
}
