# US262 - Capture and Process Drone Movements

## 1. Context

This task aims to conclude the requirements for **US262** of **Sprint 2**, which consists of developing a new functionality for the system. The team will now focus on completing the implementation and testing of this feature, as well as integrating it with the rest of the system.

### 1.1 List of Issues

- **Analysis**: Done  
- **Design**: Done  
- **Implementation**: To do  
- **Testing**: To do  

---

## 2. Requirements

**As** a Simulation Process,
<br>
**I want** to receive movement commands from drone scripts,
<br>
**So that** I can track drone positions over time.

### Acceptance Criteria

- **AC01:** Each drone process must send position updates to the main process via a pipe.
- **AC02:** The main process should maintain a time-indexed 3D matrix to track drone positions.
- **AC03:** The system must store past positions to anticipate and detect potential collisions.

### Dependencies

This requirement depends on **US241**, as a drone must be in the inventory before it can be simulated.
This requirement depends on **US261**, as the simulation process must be initiated for a figure.

---

## 3. Analysis

It is important that we are able to capture and process drone movements. As we have a DSL description that describes how the figure will be presented in the show, we need to ensure that the movements of each drone are accurately captured and processed.

This will allow us to track the positions of the drones over time and ensure that they do not collide with each other.

Check the US261 for more information about the simulation process and the figure.

---

## 4. Design

In this section, we describe the design approach adopted for implementing **US262 – Capture and Process Drone Movements**. The class diagram defines the main components involved in capturing and processing drone movements, showing a clear separation of concerns between the simulation process, drone processes, and data structures used for tracking positions.

### 4.1 Realization

The class diagram as a similar structure to the one presented in US262, as the only difference is at the functionality level. 

---

## 5. Tests

In accordance with the non-functional requirements, there are no specific tests for this functionality. However, we will ensure that the implementation adheres to the principles of clean architecture and follows best practices in software development.

As we are going to follow all acceptance criteria, we will ensure that the implementation is correct and that the system is able to simulate a figure.

---

## 6. Implementation

This section should include evidence that the implementation aligns with the proposed design. Additional artifacts such as configuration files may also be included to help understand the implementation.

### Major Commits (Sample Format)

- `feat(us221): add CustomerRepresentative entity and repository`
- `feat(us221): implement DTO mapping for representative registration`
- `test(us221): add unit tests for representative creation and validation`
- `refactor: adjust Customer aggregate to support representatives`

---

## 7. Integration / Demonstration

This section describes how the functionality was integrated with the system. It should also provide instructions for running or demonstrating the feature.

### Example:

1. Start the application.
2. Log in with a CRM Collaborator account.
3. Navigate to the Customer page.
4. Click on "Add Representative".
5. Fill in the representative's details and submit.
6. Verify the representative is added and listed correctly.

---

## 8. Observations

- The solution follows a clean architecture separating domain, application, and infrastructure layers.
- The use of DTOs effectively prevents domain leakage.
- Alternatives considered included merging representative data into the customer aggregate directly, but this was dismissed to preserve modularity.
- All third-party libraries used (e.g., validation frameworks, mapping tools) are properly documented in the project repository.

---


