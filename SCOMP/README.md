# SCOMP

This readme contains all the process and information related to the development of the uss related to SCOMP.

## Diagram with the components and processes for the solution.

// Put the images here and the other information here

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

// Put the process here

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

// Put the process here

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

// Put the process here

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

// Put the process here

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

// Put the process here

## Auto-Evaluation

The auto-evaluation is between 0 and 100.

Diogo Veiros = 100%
Diogo Pereira = 100%
Tiago Sampaio = 100%
Tiago Alves = 100%


