PARENT_FILE=parent.c
DRONE_FILE=drone.c
UTILS_FILE=utils.c

PARENT_EXEC=parent
DRONE_EXEC=drone

REPORT_FOLDER=./ReportFolder

CC=gcc
#FLAGS=-pthread -lrt -lm
FLAGS=

RM=rm -f

all: run

run: $(PARENT_EXEC) $(DRONE_EXEC) 
	@./$<

$(PARENT_EXEC): $(PARENT_FILE) $(UTILS_FILE)
	@$(CC) $(FLAGS) $^ -o $@

$(DRONE_EXEC): $(DRONE_FILE) $(UTILS_FILE)
	@$(CC) $(FLAGS) $^ -o $@

clean:
	@$(RM) $(PARENT_EXEC) $(DRONE_EXEC)
	@$(RM) $(REPORT_FOLDER)/*

re: clean all

.PHONY: run clean re
