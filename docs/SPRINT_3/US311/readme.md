# US 311 

## 1. Context

This task as the objective of concluding the requirements of the us311 of sprint2, where it is asked to develop a new functionality to the system. The team will now focus on completing the implementation and testing of this functionality as well as integrating it with the rest of the system.

### 1.1 List of Issues

- **Analysis**: Done  
- **Design**: Done  
- **Implementation**: To Do 
- **Testing**: Doing  

---

## 2. Requirements

**As** a CRM Collaborator,  
**I want** to configure the list of drone models (number of drones and model) of a show proposal.
**So that** I can configure the list of the drones and the model to be used.

### Acceptance Criteria

- **AC01**: The drones in the proposal must be compatible with the drones in the Shodrone’s inventory.
- **AC02**: There is no need to verify if these drones are used in another show on the same date.

### Dependencies

- This requirement depends on [US310](../../SPRINT_3/US310/readme.md), as a show proposal must be created before drones can be added to it.
- This requirement depends on [US240](../../SPRINT_2/US240/readme.md), as a drone model must be registered in the system before it can be added to a show proposal.
- This requirement depends on [US241](../../SPRINT_2/US241/readme.md), as a drone must be registered in the system before it can be added to a show proposal.

---

### Client Clarifications:

> **Empty**

---

## 3. Analysis

### Show Proposal Aggregate

The ShowProposal aggregate is responsible for representing a complete show proposal within the system. It includes the show’s configuration, proposal status, feedback, and various linked entities required for a successful proposal.

It is important that we are able to configure the list of drone models (number of drones and model) of a show proposal. As we have a one-to-many relationship between the show proposal and the drone models, we can easily configure the list of drone models for a show proposal.

The `Show Proposal` aggregate  includes:

- **ShowProposalStatus** – The current status of the proposal (e.g., pending, approved, rejected).  
- **ShowProposalTemplate** – The template used as a base for configuring the proposal.
- **ShowProposalNumber** – Unique identifier number for the proposal.
- **Insurance** – Insurance details related to the show.
- **CustomerFeedback** – Feedback provided by the customer about the proposal.
- **ShowConfiguration** – Detailed configuration of the show, including assigned drones.
- **Video** –  Preview or demo video related to the show proposal.
- **ShowRequest** – The original request on which this proposal is based.
- **TemplateValidate** – A domain service that validates the proposal template.
- **GenerateShowDSL** – A domain service that generates a DSL (domain-specific language) description of the show.
- **ShowDSLDescription** – A DSL representation of the show’s technical configuration.

The principal element of show proposal for this US is the **ShowConfiguration**, with a list of all the drones needed for the show proposal, for each **DroneModel**.

### Drone Aggregate
The Drone aggregate is responsible for representing an individual drone in the system. This aggregate ensures the integrity and consistency of a drone throughout its lifecycle, including its unique identifiers, operational status, and language settings.

The `Drone` aggregate includes:

- **SerialNumber** – Unique identifier for each drone.
- **DroneStatus** – Current status of the drone (e.g., active, under maintenance, removed).
- **RemovalReason** – Reason for the drone’s removal, if applicable.
- **DroneLanguage** – Configuration or operational language of the drone.
- **DroneLanguageValidate** – Domain service that validates the selected language for a drone.

For this US, the important elemente from **Drone** is the **SerialNumber**, to indentify the drone in the proposal.

### ModelOfDrone  Aggregate
The Model aggregate represents a generic drone model, including its technical specifications, name, and configuration. It is directly related to physical drones (Drone entities) that adopt this model.

The `ModelOfDrone ` aggregate includes:

- **ModelName** – Identifier for the model’s name.
- **Configuration** – Technical configuration and specifications of the drone model.
- **WindSpeed** – Maximum wind speed tolerances defined for the model.
- **PositionTolerance** – Accepted deviation or precision in positioning.
- **SafetyStatus** – Current safety certification of the model (e.g., approved, rejected, under review).

For this US, the important elemente from **Model** is the **ModelName**, to indentify the model to add the drones in the proposal.

---
![Domain model](images/domain_model_us311.svg "Domain Model")

---

## 4. Design

In this section, we describe the design approach adopted for implementing **US311 – Add drones to a proposal**. The sequence diagram defines the main components involved in the addition of a new drone to the inventory, showing a clear separation of concerns between the UI, application logic, domain model, and persistence layer.

### 4.1 Realization

![US311 Sequence Diagram](images/sequence_diagram_us311.svg "US221 Class Diagram")

---

## 5. Tests

The following tests validate the acceptance criteria defined for US221. They ensure that only valid customer representatives are created, that the data is correctly returned to the UI.

---

### Test 1: Customer is a user of the system

**Refers to Acceptance Criteria:** AC01  
**Description:** Ensures that customer representatives are valid system users.

```java
@Test
void ensureCustomerRepresentativeIsAUser() {
    // setup: create and persist a customer representative
    // action: call controller.registerNewRepresentativeOfCustomer() and get the users list
    // assert: customer representative is in the list of users
}
```

---

### Test 2: The representative is associated with a customer

**Refers to Acceptance Criteria:** AC02  
**Description:** Validates that the representative is associated with a customer.

```java
@Test
void ensureRepresentativeRepresentsACustomer() {
    CustomerDTO dto = controller.registerNewRepresentativeOfCustomer();
    assertNotNull(dto.getCustomer());
}
```

---

### Test 3: Customer representative's information is correct

**Refers to Acceptance Criteria:** AC03  
**Description:** Verifies the correctness of name, email, phone number, position, and status.

```java
@Test
void ensureCustomerInformationIsCorrect() {
    Customer customer = controller.registerNewRepresentativeOfCustomer();
    assertNotNull(customer.getName());
    assertNotNull(customer.getEmail());
    assertNotNull(customer.getPhoneNumber());
    assertNotNull(customer.getPosition());
    assertNotNull(customer.getStatus());
}
```

## 6. Implementation

The implementation of US221 is based on the design and analysis presented in the previous sections. The code is organized into packages that reflect the domain model, application logic, and user interface.
We included the necessary classes and methods to support the registration of a new customer representative. And didn't diverge from the design.

The coding Commit messages related to this requirement are as follows:

- [Added the unit tests for the classes that make the us221 us222 us223 and us224](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/8c673b5543cfdc98a1faad132e06541cc48147cb)

- [Added the implementation of the classes that make the us221 us222 us223 and us224](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/c649bbf87b8d7c21c9dd30540338cba4c656bbf1)

## 7. Integration/Demonstration

To integrate the new functionality with the existing system, we followed these steps:

1. **Persistence Layer**: To connect the new functionality with the database, we used the existing repository pattern. The `CustomerRepository` were updated to include the necessary methods for the new functionality.
2. **Controller Layer**: The controller was updated to include methods for handling requests related to customer representatives. This includes methods for adding, updating, and retrieving representatives.
3. **UI Layer**: The user interface was updated to include forms and views for managing customer representatives. This includes input validation and error handling.
4. **Testing**: We ran the unit tests to ensure that the new functionality works as expected. The tests cover all acceptance criteria and other important scenarios.

To run the project, follow the instructions in the [README.md](../../../readme.md) file located in the root directory of the project. This file contains detailed instructions on how to set up the development environment, run the application, and execute the tests.

### Demonstration Instructions

To demonstrate the functionality, follow these steps:

1. **Launch the application via the backoffice application**. 
2. **Log in as a CRM Collaborator**.
3. Navigate to the **Customers** section.
4. Select the corresponding option to what you want to do.
5. Follow the instructions in the UI.

## 8. Observations

For the implementation of this project, we used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools of our department(ISEP).
- **ECafetaria project**: A project developed by our department that serves as a reference and source for implementing similar functionalities and as a guide for best practices.
- **Jpa Hibernate**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.
