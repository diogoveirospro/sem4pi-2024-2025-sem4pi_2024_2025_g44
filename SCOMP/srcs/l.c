report:


  while(true)
    wait(sem)
    save_collisions
    post(sem)


check_collisions:

  kill(sigusr1);

  post(sem)
  wait(sem)

