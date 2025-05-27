# US315 - Add Video of Simulation to the Proposal

## 1. Context

This task as the objective of concluding the requirements of the us315 of sprint3, where it is asked to develop a new functionality to the system. The team will now focus on completing the implementation and testing of this functionality as well as integrating it with the rest of the system.

### 1.1 List of Issues

- **Analysis**: Done
- **Design**: DOne
- **Implementation**: In Progress
- **Testing**: To do

---

## 2. Requirements

**As** a CRM Collaborator,
<br>
**I want** to add a video of the simulated show,
<br>
**So that** the customer can have a preview of the show.


### Acceptance Criteria

- **AC01**: There is no need to generate the video, but we can use any suitable file.
- **AC02**: The video must be available via a link.
- **AC03**: The video must be available in the analyse proposal us.

### Dependencies

- This requirement depends on [US310](../../SPRINT_3/US310/readme.md), as a proposal must be created before we can add the video of that proposal simulation.
- This requirement depends on [US370](../../SPRINT_3/US370/readme.md), as the objective of adding the video is soo that the customer can visualise it.

---

### Client Clarifications:

> **[Topic: US315](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=36158)**  
> The video is just a link.

## 3. Analysis

It is important that the customer can visualise their own proposal simulation, as they request for that show and have all the motives to want to see how much progress was made into implementing his show.

This is possible because in terms of domain the proposal as a relation of many-to-one with the request and the request as a many-to-one relation with customer.

Other elements not relevant to this functionality are omitted for simplicity.

![Relation customer with proposals](images/domain_model_us315.svg "Domain Model")

## 4. Design

In this section, we describe the design approach adopted for implementing **US315 - Add Video of Simulation to the Proposal**. The class diagram defines the main components involved in the addition of a video to the show proposal, showing a clear separation of concerns between the UI, application logic, domain model, and persistence layer.

### 4.1. Realization



## 5. Tests


## 6. Implementation

List of videos that can be used:

1. https://www.youtube.com/watch?v=G4-2bls6-Z0&ab_channel=DroneSolutionProvider

## 7. Integration/Demonstration

To integrate the new functionality with the existing system, we followed these steps:

1. **Persistence Layer**: To connect the new functionality with the database, we used the existing repository pattern. The `ShowProposalRepository` was updated to include the necessary methods for the new functionality.
2. **Controller Layer**: The controller was updated to include methods for handling requests related to visualising the simulation of the proposal. 
3. **UI Layer**: The user interface was updated to include forms and views for analysing the proposal via the video. This includes input validation and error handling.
4. **Testing**: We ran the unit tests to ensure that the new functionality works as expected. The tests cover all acceptance criteria and other important scenarios.

To run the project, follow the instructions in the [README.md](../../../readme.md) file located in the root directory of the project. This file contains detailed instructions on how to set up the development environment, run the application, and execute the tests.

### Demonstration Instructions

To demonstrate the functionality, follow these steps:

1. **Launch the application via the user app**.
2. **Log in as a Customer**.
3. Navigate to the **Proposal** section.
4. Select the corresponding option to what you want to do.
5. Follow the instructions in the UI.
6. And click the link to visualise the video

## 8. Observations

For the implementation of this project, we used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools of our department(ISEP).
- **ECafetaria project**: A project developed by our department that serves as a reference and source for implementing similar functionalities and as a guide for best practices.
- **Jpa Hibernate**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.
