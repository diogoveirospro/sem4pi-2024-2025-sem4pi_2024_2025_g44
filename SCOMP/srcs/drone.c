#include "header.h"

DroneData s;

// sends SIGUSR2 to itself to terminate
void end()
{
  raise(SIGUSR2);
}

// sends SIGUSR2 to parent to kill everything
void handler_sigusr2(int sig)
{
	(void) sig;

	kill(getppid(), SIGUSR2);
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
void sync_drones() {
  int n;

  do {
    n = read(0, (char[1]){0}, sizeof(char));
  } while (n == -1 && errno == EINTR);
}

// sends special message to alert the end of script
void send_terminate_message(Message m)
{
  m.pos.x = -1;
  m.pos.y = -1;
  m.pos.z = -1;

  write(1, &m, sizeof(Message));
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
  if (p.x >= s.max_x || p.y >= s.max_y || p.z >= s.max_z)
    return 0;
  return 1;
}

void run_script(char *filename, int drone_id)
{
  FILE *f = open_file(filename);

  // current position
  Position c;

  // last position
  Position l;

  // new vector
  Vector v;

  Message m;

  int valid_flag = 1;

  m.id = drone_id;

  // initially x,y,z save the initial position
  if (fscanf(f, "%d,%d,%d", &c.x, &c.y, &c.z) == 3) {

    valid_flag = valid_position(c);

    // only continue if position valid
    if (valid_flag) {

      // save starting position in message
      copy_coordinates(&c, &m.pos);

      // send message to parent
      write(1, &m, sizeof(Message));

      // save current position to start the loop
      copy_coordinates(&c, &l);

      // sync
      sync_drones();
    }
  }

  // now x,y,z save the vectors 
  // m.pos.x,y,z save the new position
  while (fscanf(f, "%d,%d,%d", &v.x, &v.y, &v.z) == 3 && valid_flag) {

    // add vectors to positions to get new position
    sum_coordinates(&v, &l);

    valid_flag = valid_position(l);

    // if position not valid, break
    if (!valid_flag)
      break;

    // save new position to message
    copy_coordinates(&l, &m.pos);

    // send new position to parent
    write(1, &m, sizeof(Message));

    // save new position for next iteration
    copy_coordinates(&m.pos, &l);
    
    // sync
    sync_drones();
  }

  fclose(f);
  
  send_terminate_message(m);
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
  char *filename;

  filename = build_filename(s.id, s.inp_dir);

  run_script(filename, s.id);
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

  start_working();

  terminate();
}
