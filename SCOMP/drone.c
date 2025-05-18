#include "header.h"

void sigint_handler(int sig)
{
	(void) sig;

	kill(getppid(), SIGINT);
}

void handler_sigusr1(int sig)
{
    (void)sig;

    // Notifies the user that this drone has suffered a collision
    fprintf(stderr, "🚨 Drone %d received collision signal (SIGUSR1)!\n", getpid());
    fflush(stderr); // Ensures that the message is written immediately
}

void set_up_signals()
{
	struct sigaction sa_usr1, sa_int;

    // ----- SIGUSR1: collision -----
    memset(&sa_usr1, 0, sizeof(sa_usr1));
    sa_usr1.sa_handler = handler_sigusr1;
    sigfillset(&sa_usr1.sa_mask);  // Blocks all signals during treatment
    sigaction(SIGUSR1, &sa_usr1, NULL);

    // ----- SIGINT: end -----
    memset(&sa_int, 0, sizeof(sa_int));
    sa_int.sa_handler = sigint_handler;
    sigfillset(&sa_int.sa_mask);  // Blocks all signals during treatment
    sigaction(SIGINT, &sa_int, NULL);

}

// Build the file name based on the drone number
void build_filename(char *buffer, size_t size, const char *inp_dir, const char *id)
{
	snprintf(buffer, size, "%s/%s%s%s", inp_dir, INPUT_FILENAME, id, INPUT_FILE_EXTENSION);
}

// Helper function to handle Continue_Flag logic

void wait_for_continue_flag() {
    bool c;
    ssize_t n;
    do {
        n = read(0, &c, sizeof(bool));
    } while (n == -1 && errno == EINTR);

    if (n != sizeof(bool)) {
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

    m.finished = 0;
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
    m.finished = 1;
    write(1, &m, sizeof(Message));
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
