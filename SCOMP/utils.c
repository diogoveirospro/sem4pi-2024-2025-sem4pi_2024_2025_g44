#include "header.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Safely allocate memory
void* safe_malloc(size_t size) {
    void* ptr = malloc(size);
    if (!ptr) {
        perror(RED "Error allocating memory" RESET);
        exit(EXIT_FAILURE);
    }
    return ptr;
}

// Reads a line from the console
char *read_line(const char *prompt) {
    printf("%s", prompt);

    size_t buffer_size = 256;
    char *line = malloc(buffer_size);
    if (!line) {
        fprintf(stderr, RED "Memory allocation error.\n" RESET);
        exit(EXIT_FAILURE);
    }

    if (fgets(line, buffer_size, stdin) == NULL) {
        free(line);
        return NULL;
    }

    // Remove trailing newline
    line[strcspn(line, "\n")] = '\0';
    return line;
}

// Reads an integer from the console
int read_int(const char *prompt) {
    int value;
    while (true) {
        char *input = read_line(prompt);
        if (sscanf(input, "%d", &value) == 1) {
            free(input);
            return value;
        }
        printf(RED "Invalid input. Please enter an integer.\n" RESET);
        free(input);
    }
}

// Reads a double from the console
double read_double(const char *prompt) {
    double value;
    while (true) {
        char *input = read_line(prompt);
        if (sscanf(input, "%lf", &value) == 1) {
            free(input);
            return value;
        }
        printf(RED "Invalid input. Please enter a number.\n" RESET);
        free(input);
    }
}

// Asks for confirmation (Y/N)
bool confirm(const char *message) {
    char *input;
    while (true) {
        input = read_line(message);
        if (strcasecmp(input, "y") == 0) {
            free(input);
            return true;
        } else if (strcasecmp(input, "n") == 0) {
            free(input);
            return false;
        }
        printf(YELLOW "Please enter 'Y' or 'N'.\n" RESET);
        free(input);
    }
}

// Displays a list with a header
void show_list(const char *list[], size_t size, const char *header) {
    printf(BOLD "%s\n" RESET, header);
    for (size_t i = 0; i < size; ++i) {
        printf("  %zu - %s\n", i + 1, list[i]);
    }
    printf("  0 - Exit\n");
}

// Allows the user to select an index from a list
int select_index(const char *list[], size_t size, const char *header) {
    show_list(list, size, header);

    int index;
    while (true) {
        index = read_int("Type your option: ");
        if (index >= 0 && index <= (int)size) {
            return index - 1; // Return zero-based index
        }
        printf(RED "Invalid option. Please select a valid index.\n" RESET);
    }
}

void goBackAndWait() {
    char input[10]; // Set a maximum size for the input string
    do {
        // Clear the input buffer
        while (getchar() != '\n'); // Clear any characters in the buffer

        printf("\nPress \"0\" to go back: ");
        fgets(input, sizeof(input), stdin); // Read the line from the console

        // Remove the '\n' that is added by fgets
        input[strcspn(input, "\n")] = '\0';
    } while (strcmp(input, "0") != 0); // Continue until the input is ‘0’
}

void trim_trailing_spaces(char* str) {
    int len = strlen(str);
    while (len > 0 && (str[len - 1] == ' ' || str[len - 1] == '\t' || str[len - 1] == '\n')) {
        str[len - 1] = '\0';  // Replace the last character with '\0'
        len--;
    }
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


