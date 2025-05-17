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

// Helper function to handle Continue_Flag logic

void wait_for_continue_flag() {
    bool should_continue;
    if (read(0, &should_continue, sizeof(bool)) != sizeof(bool)) {
        perror("Drone: Failed to read continue flag");
        raise(SIGINT);
    }
}

// Reads the drone file line by line and sends the positions to the parent
void run_script(const char *filename, int drone_id)
{
    FILE *f = fopen(filename, "r");
    if (!f) {
        perror("Error opening input file");
        raise(SIGINT);
    }

    Message m;
    m.id = drone_id;

    int dx, dy, dz;
    int lastx, lasty, lastz;
    int first = 1;

    while (fscanf(f, "%d,%d,%d", &dx, &dy, &dz) == 3) {
        int timestep;
        ssize_t n = read(0, &timestep, sizeof(int));
        if (n != sizeof(int)) {
            // Parent closed pipe or error
            break;
        }
        if (first) {
            m.pos.x = dx;
            m.pos.y = dy;
            m.pos.z = dz;
            lastx = dx;
            lasty = dy;
            lastz = dz;
            first = 0;
        } else {
            m.pos.x = lastx + dx;
            m.pos.y = lasty + dy;
            m.pos.z = lastz + dz;
            lastx = m.pos.x;
            lasty = m.pos.y;
            lastz = m.pos.z;
        }

        write(1, &m, sizeof(Message));
        wait_for_continue_flag();
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
