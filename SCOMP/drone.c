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

// Build the file name based on the drone number
void build_filename(char *buffer, size_t size, const char *inp_dir, const char *id)
{
	snprintf(buffer, size, "%s/%s%s%s", inp_dir, INPUT_FILENAME, id, INPUT_FILE_EXTENSION);
}

// Reads the drone file line by line and sends the positions to the parent
void run_script(const char *filename, int drone_id)
{
	FILE *f = fopen(filename, "r");
	if (!f) {
		perror("Error opening input file");
		exit(1);
	}

	Message m;
	m.id = drone_id;

	while (fscanf(f, "%d,%d,%d", &m.pos.x, &m.pos.y, &m.pos.z) == 3)
	{
		write(1, &m, sizeof(Message));  // write to stdout -> pipe to parent
	}

	fclose(f);
}

// ---------------------
// MAIN
// ---------------------

// argv[1] index of drone in arr of parent + 1
// argv[2] input_directory

int main(int argc, char **argv)
{

	if (argc != 3)
		kill(getppid(), SIGINT);

	set_up_signals();

	int drone_id = atoi(argv[1]);
	char filename[256];
	build_filename(filename, sizeof(filename), argv[2], argv[1]);

	// ---- drone code here ----
	run_script(filename, drone_id);

	exit(0);
}
