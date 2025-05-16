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

void end()
{
  raise(SIGINT);
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
	}
	close(s.up[1]);				// parent doesnt need write side on up pipe

	s.down = down;
	s.childs_created = 1;
}

void repeat()
{

}

void do_report()
{

}


void terminate()
{
  int_free_matrix(s.down, s.num_drones);
  printf("Done\n");
  exit(0);
}

void start() {
  // general functions
  
  set_up_signals();

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
  else if (argc == 5)
  {
    s.inp_dir = argv[1];
    s.out_dir = argv[2];
    s.max_collisions = atoi(argv[3]);
    s.num_drones = atoi(argv[4]);
  }
  else
    end();

  fprintf(stderr, "inp_dir: %s, out_dir: %s, num_drones: %d, max_collisions: %d\n", s.inp_dir, s.out_dir, s.num_drones, s.max_collisions);

  start();
}

