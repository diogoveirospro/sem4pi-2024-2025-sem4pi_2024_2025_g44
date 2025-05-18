# SCOMP

This readme contains all the process and information related to the development of the uss related to SCOMP.

## Diagram with the components and processes for the solution.

![SCOMP Diagram](../SCOMP/images/Solution.svg)

## Drone Script

### Template for the Drone Script

The X, Y, and Z correspond to the coordinates in a 3D space, representing the initial position of the drone.
And the VX, VY, and VZ correspond to the vector of the drone in the X, Y, and Z directions respectively.
Each line corresponds to a time step of 1 second.

```
X, Y, Z
VX1, VY1, VZ1
VX2, VY2, VZ2
...
VXN, VYN, VZN
```

### Example of a Drone Script

```
1,2,3
1,1,1
1,1,2
```


## Process for each US

### US261 – Initiate Simulation for a Figure

**As** a System User,
<br>
**I want** to start a simulation process for a figure,
<br>
**So that** I can check for any mistake before approving it.

#### Acceptance Criteria

- **AC01:** This component must be implemented in C and must utilize processes, pipes, and signals.
- **AC02:** The system should fork a new process for each drone in the figure.
- **AC03:** Each drone process should execute its designated movement script.
- **AC04:** Pipes should facilitate communication between the main process and each drone process.
- **AC05:** The main process should track drone positions over time using an appropriate data structure.

### Process:

For this US, we set up the signal handling, the child creation, the fork and pipes setup, and the config file and struct initialization, in order to setup for the other USs.


### US262 - Capture and Process Drone Movements

**As** a Simulation Process,
<br>
**I want** to receive movement commands from drone scripts,
<br>
**So that** I can track drone positions over time.

#### Acceptance Criteria

- **AC01:** Each drone process must send position updates to the main process via a pipe.
- **AC02:** The main process should maintain a time-indexed 3D matrix to track drone positions.
- **AC03:** The system must store past positions to anticipate and detect potential collisions.

### Process:

### Process:

To implement this user story, the simulation process was designed to act as the central controller, coordinating drone 
activity and tracking their movements over time. Each drone is launched as a child process using `fork()` and executes 
a script via `execl()` that reads a predefined input file containing a sequence of 3D coordinates.

Communication between the simulation process and each drone is established using anonymous pipes. The simulation sets up 
a unidirectional pipe (`up`) for receiving data from all drones, and individual pipes (`down[i]`) for sending control 
signals and simulation timestamps to each drone. The child processes use `dup2()` to redirect their standard input/output 
to the respective pipes, enabling message passing through standard file descriptors.

In accordance with AC01, drones send structured messages (`Message` struct) containing their ID and current position at 
each simulation timestep. These messages are read sequentially by the main process and parsed to extract spatial data.

To satisfy AC02, a 3D matrix `space[x][y][z]` was allocated to represent the current state of the simulation space. 
Additionally, to fulfil AC03, a dynamic structure (`DroneHistory`) was created for each drone to log all past positions 
and timestamps. This historical data is essential for future collision detection (handled in US263), as it allows the 
system to reconstruct movement paths and identify spatial overlaps between drones over time.

The simulation advances in discrete time steps, each separated by a configurable delay (`s.timestamp`). At each step, 
the simulation collects the current positions from all active drones, updates their states in the shared space matrix, 
and logs their movements. This process continues until all drones complete their scripts or a termination condition is met.

### US263 - Detect Drone Collisions in Real Time

**As** a Simulation System,
<br>
**I want** to continuously monitor drone positions for overlaps,
<br>
**So that** I can identify and report collisions.

#### Acceptance Criteria

- **AC01:** The system must detect when two or more drones occupy the same position at the same time.
- **AC02:** Upon detecting a collision, the system should log the event and notify the involved drones via signals.
- **AC03:** Each drone must handle the received signal and notify the system user with a message.
- **AC04:** When a drone receives a SIGUSR1 (collision detected), it should block other signals while handling it.
- **AC05:** The system should allow early termination if collisions exceed a predefined threshold by sending termination signals to drones.

### Process:

To implement this user story, the simulation system was designed to monitor drone positions in real time and detect collisions based on proximity. 

The following steps outline the process:  
Collision Detection Algorithm: At each simulation timestep, the system calculates the distance between all pairs of drones using their current positions. 
If the distance between two drones is less than or equal to twice the drone radius (`collision_distance`), a collision is detected.  

Collision Logging: 
When a collision is detected:  
The system logs the event by updating the collision state matrix (`collision_state`) and incrementing the collision count for the involved drones.
The system sends a SIGUSR1 signal to the drones involved in the collision. The drones handle this signal.
Signal Handling in Drones: Each drone process has a signal handler for SIGUSR1. Upon receiving the signal, other signals are blocked to ensure proper processing.

Collision Limit Check: 
The system tracks the total number of collisions. If the number of collisions exceeds the predefined threshold (`s.max_collisions`), the simulation terminates early:  
All drones are notified to terminate using SIGKILL.
The system generates a simulation report summarizing the results.

Real-Time Execution: 
The collision detection and notification process is integrated into the main simulation loop. After processing all drone positions for a timestep, the system checks for collisions before advancing to the next step.

### US264 - Synchronize drone execution with a time step

**As** a simulation engine,
<br>
**I want** to synchronize drone movements based on time steps,
<br>
**So that** I can accurately simulate real-world execution.

#### Acceptance Criteria

**AC01:** The simulation must progress step by step.

**AC02:** Each drone process should send position updates at defined intervals.

**AC03:** The main process must ensure all updates for a given time step are processed before advancing to the next step

### Process:

For this us, we had to make all the drone processes wait for the main process to send a signal to them, indicating that they can proceed with the next step. 
This was done using a pipe to send a boolean value to the drone processes.

How did we do that? By putting each drone into a state where they are waiting to read from the pipe a boolean value, this happens after each process writes the new position to a pipe.
In the main process we make all the necessary procedures like moving the drone to a new position and verifying if there were any collisions.
After that, we send the boolean value to the drone processes, indicating that they can proceed with the next step.

All of this is done in a loop, until all the drone processes send a last message saying that they have finished executing their scripts.

### US265 - Generate a simulation report

**As** a system user,
<br>
**I want** to receive a summary of the simulation results,
<br>
**So that** I can determine if the figure is safe to use.

#### Acceptance Criteria

**AC01:** The system must generate a report and store it in a file.

**AC02:** The report should include the total number of drones and their execution status.

**AC03:** If collisions occur, the report must list timestamps and positions.

**AC04:** The report should indicate whether the figure passed or failed validation.

### Process:

To implement this user story, a reporting module was integrated into the final phase of the simulation process, executed 
after all drones have completed their scripts or the simulation was terminated due to excessive collisions.

A dedicated function, `do_report()`, is responsible for generating a detailed simulation report. In compliance with AC01, 
this function creates a uniquely named text file in the specified output directory, including a timestamp in the filename 
to ensure traceability and prevent overwriting previous reports.

To meet AC02, the report includes the total number of drones, their execution duration (in steps), and the number of 
collisions each drone was involved in. This data is retrieved from a dynamically allocated `DroneHistory` structure 
associated with each drone, which logs all positions and timestamps throughout the simulation.

Regarding AC03, if collisions were detected during the simulation (using a proximity-based algorithm from US263), the 
report explicitly lists the total number of collisions. The report includes collision counts for each drone, indirectly 
reflecting when and how frequently collisions occurred.

Finally, to address AC04, the report concludes with a validation result: if any collisions occurred, the output marks 
the simulation as "FAILED"; otherwise, it is marked "VALIDATED". This simple pass/fail logic provides the user with a 
clear indication of whether the tested figure is considered safe for use under the given simulation conditions.

## Auto-Evaluation

The auto-evaluation is between 0 and 100 %.

Diogo Veiros = 100%
<br>
Diogo Pereira = 100%
<br>
Tiago Sampaio = 100%
<br>
Tiago Alves = 100%


