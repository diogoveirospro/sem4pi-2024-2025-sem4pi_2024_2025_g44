# US 316

## 1. Context

This user story is being developed as part of Sprint 3. It introduces the functionality that allows a **CRM Collaborator**  
to send a completed **show proposal** to a customer. A proposal includes the configured figures, assigned drones,  
the duration of the event, a simulation video (US315), and a document generated using a validated template (US318).

Sending the proposal is only possible when the proposal is complete and ready, meaning:

- It passed the simulation phase.
- It contains a valid video (see US315).
- It includes a configured and validated document (see US318).

Instead of sending an actual email, the system generates a unique delivery code that can later be used by the customer  
to access the proposal through the client-side application.

### 1.1 List of issues

Analysis: 🧪 Testing  

Design: 🧪 Testing  

Implementation: 📝 To Do  

Testing: 📝 To Do

## 2. Requirements

**As a** CRM Collaborator,  
<br>  
**I want** to send a show proposal to the customer,  
<br>  
**So that** the customer can review and approve the proposed drone show.

### Acceptance Criteria:

- **US316.1** A proposal must contain a valid document before it can be sent.
- **US316.2** The system must display the Show Proposal identification code so that the customer can subsequently 
access the proposal.
- **US316.3** If the proposal is incomplete, the system must prevent the sending operation and notify the user.

### Dependencies/References:

- **US318 – Templates for Show Proposals**: The document must have been generated and validated beforehand.
- **US315 – Generate Simulation Video**: A video must already be associated with the proposal.
- **US347 – Proposal Generation**: Builds upon this configuration to deliver the final file to the customer.

## 3. Analysis

This user story finalises the proposal workflow by making the completed proposal available to the customer.

The delivery logic is delegated to the `ProposalDelivery` domain service. This service creates a new `ProposalDeliveryInfo`  
entity containing:

- A reference to the `ShowProposal`.
- A reference to the `Customer`.
- A `ProposalDeliveryCode` (a unique code or token).
- A delivery status.

This entity is persisted in the system and will later be retrieved by the customer application (e.g., via sockets) using 
the delivery code.

No email is sent in this version. Instead, the generated code is shown to the CRM Collaborator, who must communicate it 
to the customer.

## 4. Design

This section outlines the design for implementing **US316 – Send Show Proposal to Customer**.

### 4.1 Realisation

The process begins when the CRM Collaborator opens the **Send Proposal** interface.

1. The `SendProposalUI` triggers the `SendProposalController`.
2. The controller opens a `PersistenceContext` and uses the `RepositoryFactory` to retrieve:
    - The `ShowProposalRepository`
    - The `ProposalDeliveryInfoRepository`
3. The controller calls `findProposalsReadyToSend()` to filter only completed proposals.
4. The UI shows the list to the user, who selects one.
5. The selected proposal is passed to the controller.
6. The controller calls the `ProposalDelivery` service.
7. This service creates a `ProposalDeliveryInfo` instance, including a unique `ProposalDeliveryCode`.
8. The entity is saved via `ProposalDeliveryInfoRepository`.
9. If successful, the UI shows the code to the user.
10. If not, an error is displayed.

![Sequence Diagram for US316](images/sequence_diagram_us316.svg)

## 5. Implementation

The implementation of **US316** is distributed across the domain, application, and presentation layers, following the 
principles of layered architecture.

### Domain Layer

- A new aggregate root `ProposalDeliveryInfo` was introduced in the `ProposalDelivery` aggregate. This entity stores:
   * The associated `ShowProposal`.
   * The `Customer`.
   * A unique `ProposalDeliveryCode`.
   * A `ProposalDeliveryStatus`.

- The domain service `ProposalDelivery` is responsible for validating the proposal state and creating the 
- `ProposalDeliveryInfo` instance.

### Application Layer

- The `SendProposalController` orchestrates the process, interacting with the domain service and repositories.

### Infrastructure Layer

- The `ProposalDeliveryInfoRepository` handles persistence of delivery records, allowing future retrieval by the customer.
- A code generation utility may be used internally to ensure uniqueness of the delivery code.

This approach ensures a clear separation of concerns and supports future evolution — such as integrating with the 
customer application via sockets or HTTP to retrieve proposals using the code.

Relevant commit messages:

- [Possible Implementation of US316](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/abca01fd983324f3f82bac6eab37776fdc416a9e)

## 6. Integration/Demonstration

The functionality developed in **US316** was successfully integrated into the Shodrone system.

### Demonstration Instructions

1. **Launch the application** (via the main class or script, as defined in the [readme.md](../../../readme.md).
2. Log in as a CRM Collaborator.
3. Navigate to the **Send Proposal** menu.
4. Select a valid proposal (must have video and document).
5. Observe the delivery code generated and shown.

### Notes

- This feature depends on prior completion of **US315 (video)** and **US318 (document)**.
- No real email or message is sent; only a code is shown.
- The customer app will later use this code to retrieve the proposal.

## 7. Observations

For the implementation of this project, I used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools developed by our department (ISEP).
- **eCafeteria Project**: A reference project developed by our department, used as a source of inspiration for similar
  functionalities and a guide for best practices.
- **JPA (Hibernate)**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.

