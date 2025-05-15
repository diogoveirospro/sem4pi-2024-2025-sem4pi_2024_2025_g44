#ifndef DRONE_H
#define DRONE_H

#include "position.h" 

typedef struct {
    float Velocity;
    Position currPos;
    Position *positions;
    int numPositions;
} drone;

#endif