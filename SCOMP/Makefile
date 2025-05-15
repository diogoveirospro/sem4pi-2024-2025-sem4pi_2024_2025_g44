PARENT_FILE=parent.c
DRONE_FILE=drone.c

PARENT_EXEC=parent
DRONE_EXEC=drone

CC=gcc
FLAGS=

RM=rm

all: parent drone run

run:
	@./$(PARENT_EXEC)

parent: $(PARENT_FILE) 
	@$(CC) $(FLAGS) -o $(PARENT_EXEC) $^

drone: $(DRONE_FILE) 
	@$(CC) $(FLAGS) -o $(DRONE_EXEC) $^

clean:
	@$(RM) $(PARENT_EXEC) $(DRONE_EXEC)

.PHONY: run parent drone clean
