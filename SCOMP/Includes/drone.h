#ifndef DRONE_H
#define DRONE_H

#include "position.h" 
# include <sys/types.h>

typedef struct {
    pid_t id;
    char continueFlag;
    float Velocity;
    Position currPos;
    Position *positions;
    int numPositions;
} drone;

#endif