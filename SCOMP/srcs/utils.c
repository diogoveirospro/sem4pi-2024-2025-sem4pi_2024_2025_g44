#include "header.h"

// Safely allocate memory
void* safe_malloc(size_t size) {
    void* ptr = malloc(size);
    if (!ptr) {
        perror(RED "Error allocating memory" RESET);
        exit(EXIT_FAILURE);
    }
    return ptr;
}

// Allocates a 3D array for the space (sizeX x sizeY x sizeZ)
SpaceCell*** alloc_space(int sizeX, int sizeY, int sizeZ) {
    SpaceCell ***space = (SpaceCell ***)safe_malloc(sizeX * sizeof(SpaceCell **));
    for (int x = 0; x < sizeX; x++) {
        space[x] = (SpaceCell **)safe_malloc(sizeY * sizeof(SpaceCell *));
        for (int y = 0; y < sizeY; y++) {
            space[x][y] = (SpaceCell *)safe_malloc(sizeZ * sizeof(SpaceCell));
            for (int z = 0; z < sizeZ; z++) {
                space[x][y][z].drone_id = -1; // empty
            }
        }
    }
    return space;
}

// Frees the 3D space array
void free_space(SpaceCell ***space, int sizeX, int sizeY) {
    for (int x = 0; x < sizeX; x++) {
        for (int y = 0; y < sizeY; y++) {
            free(space[x][y]);
        }
        free(space[x]);
    }
    free(space);
}

// Allocates a matrix to map drones to positions
DronePosition* alloc_drone_positions(int num_drones) {
    DronePosition *matrix = (DronePosition *)safe_malloc(num_drones * sizeof(DronePosition));
    for (int i = 0; i < num_drones; i++) {
        matrix[i].drone_id = i;
        matrix[i].pos.x = matrix[i].pos.y = matrix[i].pos.z = -1; // Not placed yet
    }
    return matrix;
}

// Set a drone at a position in the 3D space
void set_drone_in_space(SpaceCell ***space, int x, int y, int z, int drone_id) {
    space[x][y][z].drone_id = drone_id;
}

// Remove a drone from a position in the 3D space
void remove_drone_from_space(SpaceCell ***space, int x, int y, int z) {
    space[x][y][z].drone_id = -1;
}

// Check if a cell is empty
bool is_cell_empty(SpaceCell ***space, int x, int y, int z) {
    return space[x][y][z].drone_id == -1;
}

// Move a drone in the 3D space (updates both space and drone_positions)
void move_drone(SpaceCell ***space, DronePosition *drone_positions, int drone_idx, Position new_pos, int sizeX, int sizeY, int sizeZ) {
    Position old_pos = drone_positions[drone_idx].pos;
    // Remove from old position if valid
    if (old_pos.x >= 0 && old_pos.x < sizeX && old_pos.y >= 0 && old_pos.y < sizeY && old_pos.z >= 0 && old_pos.z < sizeZ) {
        remove_drone_from_space(space, old_pos.x, old_pos.y, old_pos.z);
    }
    // Set in new position
    if(new_pos.x < 0 || new_pos.x >= sizeX || new_pos.y < 0 || new_pos.y >= sizeY || new_pos.z < 0 || new_pos.z >= sizeZ) {
        fprintf(stderr, RED "Error: New position out of bounds.\n" RESET);
        return;
    }
    if (is_cell_empty(space, new_pos.x, new_pos.y, new_pos.z)) {
        set_drone_in_space(space, new_pos.x, new_pos.y, new_pos.z, drone_idx);
    } 
    drone_positions[drone_idx].pos = new_pos;
}

// Find drone at a given position, returns drone_id or -1
int get_drone_at(SpaceCell ***space, int x, int y, int z) {
    return space[x][y][z].drone_id;
}













// calculate the distance between two drones
double calculate_distance(Position p1, Position p2) {
    return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2) + pow(p1.z - p2.z, 2));
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

DroneHistory **alloc_history(int n, int c)
{
  DroneHistory **list;

  list = (DroneHistory**) malloc(sizeof(DroneHistory *) * n);

  for (int i = 0; i < n; i++) {
    list[i] = (DroneHistory *) malloc(sizeof(DroneHistory));

    list[i]->positions = malloc(sizeof(Position) * c);
    list[i]->timestamps = malloc(sizeof(float) * c);

    list[i]->count = 0;
    list[i]->capacity = c;
    list[i]->drone_id = i + 1;
    list[i]->collision_count = 0;
  }

  return list;
}

void free_history(DroneHistory **h, int n)
{
  for (int i = 0; i < n; i++)
  {
    free(h[i]->positions);
    free(h[i]->timestamps);
    free(h[i]);
  }
  free(h);
}

