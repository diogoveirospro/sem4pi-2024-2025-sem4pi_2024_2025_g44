#include "header.h"

void sigint_handler(int sig)
{
	(void) sig;

	kill(getppid(), SIGINT);
}

void handler_sigusr1(int sig)
{
  (void) sig;
  
  //sigusr1 code

}

void set_up_signals()
{
	struct sigaction sa;

	memset(&sa, 0, sizeof(sa));
	sigfillset(&sa.sa_mask);
	sigdelset(&sa.sa_mask, SIGINT);
	sa.sa_handler = handler_sigusr1;
	sigaction(SIGUSR1, &sa, NULL);
	sa.sa_handler = sigint_handler;
	sigaction(SIGINT, &sa, NULL);
}

// argv[1] index of drone in arr of parent + 1
// argv[2] input_directory

int main(int argc, char **argv)
{
	if (argc != 2)
		kill(getppid(), SIGINT);

	set_up_signals();

  // drone code here
	
  fprintf(stderr, "index: %d, input_dir: %s\n", atoi(argv[1]), argv[2]);

  exit(0);
}
