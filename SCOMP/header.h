#ifndef HEADER_H
#define HEADER_H

// Includes
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <signal.h>
#include <stdbool.h>
#include <time.h>

// Defines
#define CONFIG_FILE "./config.txt"
#define DRONE_FILE "./drone"
#define INPUT_FILENAME "file_drone_"
#define INPUT_FILE_EXTENSION ".txt"

#define CONFIG_FILE_MAX_SIZE 10000

typedef struct config {
} Config;

typedef struct data {

  //config file info
  int max_collisions;
  int num_drones;
  char *inp_dir;
  char *out_dir;

  //signals
  struct sigaction sa;

  //pipes
  int up[2];
  int **down;

  //childs info
  char *state;
  int *pids;

  //childs created flag
  int childs_created;

} Data;

// Structs
typedef struct position {
  int x;
  int y;
  int z;
} Position;

typedef struct message {
  int id;
  char *msg;
} Message;


// Function declarations
void* safe_malloc(size_t size);
char *read_line(const char *prompt);
int read_int(const char *prompt);
double read_double(const char *prompt);
bool confirm(const char *message);
void show_list(const char *list[], size_t size, const char *header);
int select_index(const char *list[], size_t size, const char *header);
void goBackAndWait();
void trim_trailing_spaces(char* str);


#endif
