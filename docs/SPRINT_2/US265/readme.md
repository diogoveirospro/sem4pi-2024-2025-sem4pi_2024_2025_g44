# US265 - Generate a simulation report

## 1. Context

This task aims to conclude the requirements for **US265** of **Sprint 2**, which consists of developing a new functionality for the system. The team will now focus on completing the implementation and testing of this feature, as well as integrating it with the rest of the system.

### 1.1 List of Issues

- **Analysis**: Done
- **Design**: Done
- **Implementation**: To do
- **Testing**: To do

---

## 2. Requirements

**As** a system user,
<br>
**I want** to receive a summary of the simulation results,
<br>
**So that** I can determine if the figure is safe to use.

### Acceptance Criteria

**AC01:** The system must generate a report and store it in a file.

**AC02:** The report should include the total number of drones and their execution status.

**AC03:** If collisions occur, the report must list timestamps and positions.

**AC04:** The report should indicate whether the figure passed or failed validation.

### Dependencies

This requirement depends on **US261**, as the simulation must be able to start before generating a report.

---

## 3. Analysis

It is important that we are able to generate a report of the simulation results. As we have a simulation process that tracks the positions of drones, we need to ensure that we can accurately simulate real-world execution. And this will allow us be sure that the figure is safe to use.

This report will include the collisions that occurred during the simulation, as well as the timestamps and positions of the drones involved. This information will be crucial for determining whether the figure passed or failed validation.

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


