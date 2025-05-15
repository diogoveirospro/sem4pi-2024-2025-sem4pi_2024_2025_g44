#include "header.h"

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
	//write(1, "INT\n", 4);
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

void handler_sigusr1(int sig)
{
	(void) sig;
//	write(1, "SIG1\n", 5);
  
  // code for sigusr1
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
	sa.sa_handler = handler_sigusr1;
	sigaction(SIGUSR1, &sa, NULL);
	sa.sa_handler = handler_sigint;
	sigaction(SIGINT, &sa, NULL);
}

void set_up_childs()
{

}

void repeat()
{

}

void do_report()
{

}


void terminate()
{
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

int main(int argc, char **argv){
  if (argc == 1)
    process_config_file();
  else if (argc == 3)
  {
    s.max_collisions = atoi(argv[1]);
    s.num_drones = atoi(argv[2]);
  }
  else
    end();

  printf("num_drones: %d, max_collisions: %d\n", s.num_drones, s.max_collisions);

  start();
}
