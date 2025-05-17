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

Data s;
SpaceCell ***space;
DronePosition *drone_positions;
DroneHistory **histories;

void end()
{
  raise(SIGINT);
  exit(EXIT_FAILURE);
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

void parse_data(char *str)
{
	char d[] = "=\n";

	strtok(str, d);
	s.inp_dir = strdup(strtok(NULL, d));

	strtok(NULL, d);
	s.out_dir = strdup(strtok(NULL, d));

	strtok(NULL, d);
	s.max_collisions = atoi(strtok(NULL, d));

	strtok(NULL, d);
	s.num_drones = atoi(strtok(NULL, d));

    strtok(NULL, d);
    s.raio_drone = atoi(strtok(NULL, d));

	strtok(NULL, d);
	s.max_X = atoi(strtok(NULL, d));

	strtok(NULL, d);
	s.max_Y = atoi(strtok(NULL, d));

	strtok(NULL, d);
	s.max_Z = atoi(strtok(NULL, d));

	strtok(NULL, d);
	s.timestamp = atof(strtok(NULL, d));
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

	if (fread(str, sizeof(char), size + 1, fd) <= 0)
		end();

	fclose(fd);

	parse_data(str);

	free(str);
}

void handler_sigint(int sig)
{
	(void) sig;

	if (s.childs_created)
	{
		for (int i = 0; i < s.num_drones; i++)
			kill(s.pids[i], SIGKILL);

		for (int i = 0; i < s.num_drones; i++)
			wait(NULL);
	}

	exit(1);
}

void set_up_signals()
{
/*	sigset_t set;

	sigfillset(&set);
	sigdelset(&set, SIGUSR1);
	sigdelset(&set, SIGINT);
	sigprocmask(SIG_BLOCK, &set, NULL);
*/
	struct sigaction sa;

	memset(&sa, 0, sizeof(sa));
	sigfillset(&sa.sa_mask);
	sigdelset(&sa.sa_mask, SIGINT);
	sa.sa_handler = handler_sigint;
	sigaction(SIGINT, &sa, NULL);
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

void free_drone_history(DroneHistory *history) {
    if (!history) return;
    free(history->positions);
    free(history->timestamps);
    free(history);
}

void set_up_childs()
{
  int **down = int_malloc_matrix(s.num_drones, 2);

	char str_i[10];
	pid_t pid;

	s.state = (char *) malloc(sizeof(char) * s.num_drones);

	if (s.state == NULL)
		end();
	memset(s.state, 0, sizeof(char) * s.num_drones);

	s.pids = (int *) malloc(sizeof(int) * s.num_drones);

	if (s.pids == NULL)
		end();
	memset(s.pids, 0, sizeof(int) * s.num_drones);

	if (pipe(s.up))
		end();

	for (int i = 0; i < s.num_drones; i++)
	{
		if (pipe(down[i]))
			end();

		pid = fork();


		if (pid == 0)
		{
			close(s.up[0]);		// child doesnt need read side on up pipe

			dup2(down[i][0], 0);	// child reads from fd 0 to get info from pipe down

			dup2(s.up[1], 1);	// child writes to fd 1 to write info to pipe up

			close(down[i][1]);	// child doesnt need write side on down pipe

			snprintf(str_i, sizeof(str_i), "%d", i + 1);
			execl(DRONE_FILE, DRONE_FILE, str_i, s.inp_dir, NULL);

			kill(getppid(), SIGINT);
		}
		close(down[i][0]);		// parent doesnt need read side on down pipe
		s.pids[i] = pid;
		drone_positions[i].drone_id = i + 1;
	}
	close(s.up[1]);				// parent doesnt need write side on up pipe

	s.down = down;
	s.childs_created = 1;
}

DroneHistory* init_drone_history(int drone_id, int initial_capacity) {
    DroneHistory *history = malloc(sizeof(DroneHistory));
    if (!history) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    history->positions = malloc(initial_capacity * sizeof(Position));
    history->timestamps = malloc(initial_capacity * sizeof(float));
    if (!history->positions || !history->timestamps) {
        perror("malloc");
        free(history->positions);
        free(history->timestamps);
        free(history);
        exit(EXIT_FAILURE);
    }

    history->count = 0;
    history->capacity = initial_capacity;
    history->drone_id = drone_id;

    return history;
}

void add_position_timestamp(DroneHistory *history, Position pos, float timestamp) {
    if (history->count == history->capacity) {
        int new_capacity = history->capacity * 2;
        Position *new_positions = realloc(history->positions, new_capacity * sizeof(Position));
        float *new_timestamps = realloc(history->timestamps, new_capacity * sizeof(float));

        if (!new_positions || !new_timestamps) {
            perror("realloc");
            // In the event of an error, we can continue with the old memory to avoid losses
            free(new_positions);
            free(new_timestamps);
            return;
        }

        history->positions = new_positions;
        history->timestamps = new_timestamps;
        history->capacity = new_capacity;
    }

    history->positions[history->count] = pos;
    history->timestamps[history->count] = timestamp;
    history->count++;
}


// Check number of collisions between drones
int check_collisions(int COLLISION_DISTANCE, int *pids, DronePosition *drone_positions, int num_drones) {
    int collisions = 0;
    for (int i = 0; i < num_drones; i++) {
        for (int j = i + 1; j < num_drones; j++) {
            if (calculate_distance(drone_positions[i].pos, drone_positions[j].pos) <= COLLISION_DISTANCE) {
                // Notifica os drones envolvidos via sinal
                kill(pids[i], SIGUSR1);
                kill(pids[j], SIGUSR1);
                collisions++;
            }
        }
    }
    return collisions;
}

// Handle collision limit exceeded
void handle_collision_limit_exceeded(int *terminado) {
    fprintf(stderr, "%sCollision limit exceeded. Terminating...%s\n", RED, RESET);
    // Notify all drones to terminate
    for (int i = 0; i < s.num_drones; i++) {
        if (!terminado[i]) {
            kill(s.pids[i], SIGKILL);
        }
    }
    // Wait for all drones to terminate
    for (int i = 0; i < s.num_drones; i++) {
        waitpid(s.pids[i], NULL, 0);
    }
    free(terminado);
    do_report();
    terminate();
}

void send_continue_flag(int drone_idx, bool should_continue) {
    write(s.down[drone_idx][1], &should_continue, sizeof(bool));
}

void send_timestep(int drone_idx, int timestep) {
    write(s.down[drone_idx][1], &timestep, sizeof(int));
}

void repeat() {
    Message m;
    int t = 0;
    int drones_ativos = s.num_drones;
    int *terminado = calloc(s.num_drones, sizeof(int));
    int COLLISION_DISTANCE = s.raio_drone * 2;
    int collisions = 0;

    if (!terminado) {
        perror("calloc");
        exit(EXIT_FAILURE);
    }

    while (drones_ativos > 0) {
        // Send timestep to each drone at the start of the round
        for (int i = 0; i < s.num_drones; i++) {
            if (!terminado[i])
                send_timestep(i, t);
        }
        for (int i = 0; i < s.num_drones; i++) {
            if (terminado[i])
                continue;

            ssize_t n = read(s.up[0], &m, sizeof(Message));

            if (n == 0) {
                terminado[i] = 1;
                drones_ativos--;
                continue;
            } else if (n < 0) {
                perror("read");
                continue;
            }

            int x = m.pos.x;
            int y = m.pos.y;
            int z = m.pos.z;

            if (x < 0 || x >= s.max_X || y < 0 || y >= s.max_Y || z < 0 || z >= s.max_Z) {
                fprintf(stderr, RED "Invalid drone position %d: %d,%d,%d\n" RESET, m.id, x, y, z);
                continue;
            }

            add_position_timestamp(histories[m.id - 1], m.pos, t * s.timestamp);
            move_drone(space, drone_positions, m.id - 1, m.pos, s.max_X, s.max_Y, s.max_Z);
        }

        for (int i = 0; i < drones_ativos; i++) {
            send_continue_flag(i, true);
        }

        // Check for collisions
        collisions += check_collisions(COLLISION_DISTANCE, s.pids, drone_positions, s.num_drones);

        // debbug num colisoes
        fprintf(stderr, "Collisions detected: %d\n", collisions);

        // Check if the number of collisions exceeds the limit
        if (collisions >= s.max_collisions){
            handle_collision_limit_exceeded(terminado);
        }

        usleep((useconds_t)(s.timestamp * 1e6));
        t++;
    }

    free(terminado);
}


void do_report()
{

}


void terminate()
{
  int_free_matrix(s.down, s.num_drones);
  // Liberta os históricos
    for (int i = 0; i < s.num_drones; i++) {
        free_drone_history(histories[i]);
    }
    free(histories);
  free_space(space, s.max_X, s.max_Y);
  free(drone_positions);
  printf("Done\n");
  exit(0);
}

void start() {
    set_up_signals();

    space = alloc_space(s.max_X, s.max_Y, s.max_Z);

    drone_positions = alloc_drone_positions(s.num_drones);

    // Initialises the histories for each drone
    histories = malloc(s.num_drones * sizeof(DroneHistory*));
    for (int i = 0; i < s.num_drones; i++) {
        histories[i] = init_drone_history(i + 1, 100);  // initial capacity 100 positions
    }

    set_up_childs();

    repeat();

    do_report();

    terminate();
}


// argv[1] inp_dir
// argv[2] out_dir
// argv[3] max_collisions
// argv[4] num_drones

int main(int argc, char **argv){
  if (argc == 1)
    process_config_file();
  else if (argc == 9)
  {
    s.inp_dir = argv[1];
    s.out_dir = argv[2];
    s.max_collisions = atoi(argv[3]);
    s.num_drones = atoi(argv[4]);
    s.raio_drone = atoi(argv[5]);
	s.max_X = atoi(argv[6]);
	s.max_Y = atoi(argv[7]);
	s.max_Z = atoi(argv[8]);
	s.timestamp = atof(argv[9]);
  }
  else
    end();

  printf("1st arg: %s\n", s.inp_dir);
  printf("2nd arg: %s\n", s.out_dir);
  printf("3rd arg: %d\n", s.max_collisions);
  printf("4th arg: %d\n", s.num_drones);
  printf("5th arg: %d\n", s.raio_drone);
  printf("6th arg: %d\n", s.max_X);
  printf("7th arg: %d\n", s.max_Y);
  printf("8th arg: %d\n", s.max_Z);
  printf("9th arg: %f\n", s.timestamp);

  start();
}