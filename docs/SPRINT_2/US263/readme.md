# US263 - Detect Drone Collisions in Real Time

## 1. Context

This task aims to conclude the requirements for **US263** of **Sprint 2**, which consists of developing a new functionality for the system. The team will now focus on completing the implementation and testing of this feature, as well as integrating it with the rest of the system.

### 1.1 List of Issues

- **Analysis**: Done
- **Design**: Done
- **Implementation**: To do
- **Testing**: To do

---

## 2. Requirements

**As** a Simulation System,
<br>
**I want** to continuously monitor drone positions for overlaps,
<br>
**So that** I can identify and report collisions.

### Acceptance Criteria

- **AC01:** The system must detect when two or more drones occupy the same position at the same time.
- **AC02:** Upon detecting a collision, the system should log the event and notify the involved drones via signals.
- **AC03:** Each drone must handle the received signal and notify the system user with a message.
- **AC04:** When a drone receives a SIGUSR1 (collision detected), it should block other signals while handling it.
- **AC05:** The system should allow early termination if collisions exceed a predefined threshold by sending termination signals to drones.

### Dependencies

This requirement depends on **US262**, as we need to capture and process drone movements to detect collisions.

---

## 3. Analysis

It is important that we are able to detect drone collisions in real time. As we have a simulation process that tracks the positions of drones, we need to ensure that we can identify when two or more drones occupy the same position at the same time.

This will allow us to take appropriate actions to prevent collisions and ensure the safety of the drones.

Check the US261 for more information about the simulation process and the figure.

---

## 4. Design

In this section, we will present the design of the system, including the class diagram and any other relevant diagrams.

### 4.1 Realization

The class diagram as a similar structure to the one presented in US261, as the only difference is at the functionality level.

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


