# US 328


## 1. Context

This task aims to fulfill the requirements of US328 from Sprint 3, which consists of listing all drones that require preventive maintenance, based on their usage time exceeding the predefined maintenance threshold of their model. The goal is to finalize the analysis, design, implementation, and testing of this functionality.
### 1.1 List of issues

- **Analysis**: Done
- **Design**: Done
- **Implementation**: Done
- **Testing**: Done

---

## 2. Requirements

**As** a Drone Tech,  
**I want** to list all drones requiring preventive maintenance based on time usage limits,  
**So that** I can proactively maintain drones before they degrade or fail.

### Acceptance Criteria:

- **AC01**: Only drones that are still active (in inventory) must be considered.
- **AC02**: A drone is considered to require preventive maintenance if its usage exceeds the threshold defined by its model.
- **AC03**: The maintenance threshold is defined per model and is expressed in usage time (HH:mm or hours).
- **AC04**: The UI must display relevant data for each drone: Serial Number, Model, Usage Limit, and Current Usage.
- **AC05**: If no drones require maintenance, an appropriate message must be displayed.

### Dependencies

- Depends on US241 for drone registration.
- Depends on drone usage time (US327).
- Depends on model registration and maintenance time limit (US240).
- Threshold comparison logic relies on attributes defined in the drone model.

---


### Client Clarifications:

> - The limit is defined per drone **model** and is available in the domain model and documentation.
> - The logic for preventive maintenance is based on **usage time**, typically expressed in **HH:mm**.
> - Only **active drones** (in inventory) should be considered — removed drones are excluded by logic.
> - Although alerts/notifications could be useful, they are **not** part of the scope for this sprint.
> - For each drone in the list, display: **Serial Number**, **Model**, **Limit from Model**, and **Current Usage**.

---
## 3. Analysis

### Drone Aggregate

The drone aggregate supports:
- `SerialNumber`
- `Model` – contains the time limit.
- `UsageTime` – total usage accumulated.
- `DroneStatus` – used to determine if drone is active.

### Model Aggregate

The drone model contains:
- `TimeLimit` – usage threshold for preventive maintenance, likely represented with `Duration`.

---

![Domain model](images/domain_model_us325.svg "Domain Model")

## 4. Design

The system follows a layered MVC-style architecture: UI → Controller → Repository → Domain.

---

### 👤 Actor

#### Drone Tech
- Requests a list of drones needing maintenance.
- Reviews information (Serial Number, Model, Threshold, Usage).

---

### 💻 UI Layer

#### `ListDronesOverTimeLimitUI`
- **Methods**:
    - `controller.listDronesOverTimeLimit()` – retrieves list.
    - Displays message if list is empty.
    - Shows drone list via `ListWidget<Drone>` with custom printer.

---

### 🎮 Application Layer

#### `ListDronesOverTimeLimitController`
- **Methods**:

listDronesOverTimeLimit();

### 🗃 Persistence Layer

#### :Persistence
- **Role:** Provides access to repositories and persistence infrastructure.
- **Main Method:**
    - `getRepositoryFactory() : RepositoryFactory`

---

### 🏗 Repository Layer

#### :RepositoryFactory
- **Role:** Abstract factory for repositories.
- **Main Methods:**
    - `findAllDronesInventory();`

#### droneRepository: DroneRepository
- **Main Method:**
    - `findDronesOverTimeLimit();`


### 🧠 Domain Layer

The domain layer includes entities and value objects with their business rules.

---

### 🔁 Process Flow Summary

1. **Drone Tech** accesses “List Drones Over Time Limit”.
2. UI requests list of drones via `controller.listDronesOverTimeLimit()`.
3. Controller calls `droneRepository.findDronesOverTimeLimit()`.
4. Repository queries drones whose `usageTime` exceeds `model.timeLimit` and are active.
5. Controller returns list to UI.
6. UI prints drone info or shows message if list is empty.


---
### 4.1. Realization

![US328 Sequence Diagram](images/sequence_diagram_328.svg "US328 Sequence Diagram")

### 4.3. Applied Patterns

### 4.4. Acceptance Tests

Include here the main tests used to validate the functionality. Focus on how they relate to the acceptance criteria. May be automated or manual tests.

**Test 1:** *Verifies that it is not possible to ...*

**Refers to Acceptance Criteria:** US101.1


```
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
	...
}
````

## 5. Implementation

*In this section the team should present, if necessary, some evidencies that the implementation is according to the design. It should also describe and explain other important artifacts necessary to fully understand the implementation like, for instance, configuration files.*

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this requirement.*

## 6. Integration/Demonstration

*In this section the team should describe the efforts realized in order to integrate this functionality with the other parts/components of the system*

*It is also important to explain any scripts or instructions required to execute an demonstrate this functionality*

## 7. Observations

*This section should be used to include any content that does not fit any of the previous sections.*

*The team should present here, for instance, a critical prespective on the developed work including the analysis of alternative solutioons or related works*

*The team should include in this section statements/references regarding third party works that were used in the development this work.*