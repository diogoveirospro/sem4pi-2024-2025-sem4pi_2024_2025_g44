prog: main.o
	gcc -z noexecstack ex.o -o prog

main.o: main.c
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o -lrt

clean:
	rm -vf *.o prog

run: prog
	./prog

debug: prog
	gdb -tui ./prog

vg: prog
	valgrind ./prog

ddd: prog
	ddd ./prog

.PHONY: clean run debug vg ddd
