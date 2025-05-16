#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define N_CHILDS 5

int main() {
  int pid;
  int up_pipe[2];      // childs -> parent
  int down_pipe[2];    // parent -> childs
  int fd[N_CHILDS][2]; // parent -> child

  for (int i = 0; i < N_CHILDS; i++) {
    pid = fork();

    if (pid == 0) {
    }
  }
}

/*
 * ------------------------------------------------------
 *
 * Example pipe setup
 *
 * pipe(up_pipe)
 * pipe(down_pipe)
 *
 * (dup para o filho qnd nascer ficar ja pronto)
 * (para o pai, faz se depois do for loop)
 * dup2(down_pipe[0], 0)
 * dup2(up_pipe[1], 1)
 *
 * close(down_pipe[0])
 * close(up_pipe[1])
 *
 *
 *
 * for (int i = 0; i < N_CHILDS; i++)
 *
 * pipe(fd[i])
 * fork()
 *
 * if (pid == 0)
 * {
 *   close(down_pipe[1])
 *   close(up_pipe[0])
 *
 *   dup2(fd[i][0], 33) (33 fd escolhido)
 *
 *   close(fd[i][0])
 *   close(fd[i][1])
 *
 *   (algures aqui exec)
 *   perror
 *   exit
 * }
 *
 * if (pid > 0)
 * {
 *   close(fd[i][0])
 * }
 *
 *
 * (after for loop)
 *
 *  dup2(up_pipe[0], 0)
 *  dup2(donw_pipe[1], 1)
 *
 *  close(up_pipe[0])
 *  close(down_pipe[1])
 *
 *
 * ------------------------------------------------------
 *
 * File Descriptor table timeline
 *
 * P                          fork                    after loop FINAL 0 stdin
 * dup             down_pipe[0]    dup     up_pipe[0] up_pipe[0] 1 stdout   dup
 * up_pipe[1]      dup     down_pipe[1]                      down_pipe[1] 2
 * stderr stderr 3          down_pipe[0]    close         pipe to child 0
 * (fd[0][1])                  fd[0][1] 4          down_pipe[1] close empty 5
 * up_pipe[0]                    close empty 6          up_pipe[1]      close
 * pipe to child 1 (fd[1][1])                  fd[1][1] 7 pipe to child 2
 * (fd[2][1]) fd[2][1]
 *
 * C
 * 0 stdin    down_pipe[0] down_pipe[0] 1 stdout   up_pipe[1] up_pipe[1] 2
 * stderr stderr 3 empty 4          down_pipe[1]    close empty 5 up_pipe[0]
 * close                                                                   empty
 * ...
 * 33                         dup       pipe to child i (fd[i][0])  sadasdas as das das das asdasdasdasd a s das a :w
 *
 * "dup2(fd[i][0], 33)" fd[i][0]
 *
 *
 * ------------------------------------------------------
 *
 */
