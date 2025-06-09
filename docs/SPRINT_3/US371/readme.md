# US 371

## 1. Context

This user story is part of Sprint 3 and introduces the functionality that allows a **Customer** to accept or reject a 
show proposal and optionally provide feedback on it.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implementation: 🧪 Testing

Testing: ⚪ Not Applicable
 

## 2. Requirements

**As a Customer,**  
<br>
**I want** to accept or reject a proposal,  
<br>
**So that** I can express whether I agree with the show plan and optionally share my opinion.

### Acceptance Criteria:

* **US371.1** The customer must be able to accept or reject a proposal.
* **US371.2** The customer should be able to optionally provide textual feedback.
* **US371.3** The system must update the proposal status and store any given feedback.
* **US371.4** This action must be available only for proposals currently pending decision.

### Dependencies/References

* **US370 – Analyse a proposal**: to view the proposal before taking action.

## 3. Analysis

This user story focuses on enabling the customer to accept or reject a previously delivered proposal, and optionally 
provide feedback.

All required domain elements are already in place:

- `ShowProposal` entity includes:
    - `proposalStatus` (of type `ShowProposalStatus`)
    - `customerFeedback` (of type `CustomerFeedback`)
- The proposal is linked to the customer via the `ProposalDeliveryInfo` aggregate.

The system must ensure that:
- Only proposals with status `WAITING FOR RESPONSE` can be accepted or rejected.
- The customer's response is persisted in the `ShowProposal` instance.
- Optional feedback is stored in the associated `CustomerFeedback` value object.

There is **no need to modify the domain model**, only to apply its existing operations correctly.

### Responsibilities per component

- **Customer App**: UI element to choose between accept/reject and optionally submit feedback.
- **Customer App Protocol Proxy**: constructs and sends an `AcceptRejectProposalRequest` over the socket.
- **Customer App Server**:
    - Parses and validates the request.
    - Verifies the current proposal status.
    - Applies the decision (updates status and feedback).
    - Returns success/failure response.

![Domain Model - Show Proposal Aggregate](../../global_artifacts/analysis/images/domain_model_show_proposal.svg)

## 4. Design

This section presents the design adopted for implementing **US371 – Accept/Reject Proposal**.

### 4.1 Realisation

The following sequence diagram illustrates the interaction flow between the user interface, controller, network proxy, 
socket layer, server components, and repositories:

![Sequence Diagram for US371](images/sequence_diagram_us371.svg)

The process begins in the `SendFeedbackProposalUI`, where the **Customer** requests to view all show proposals that are 
pending a decision. The UI triggers the `SendFeedbackProposalController`, which initiates a multistep lookup process 
to determine the authenticated user and fetch the proposals addressed to their customer account.

First, the controller retrieves the authenticated `ShodroneUser` by delegating to the `CustomerAppProtocolProxy`. The 
proxy constructs a `GetShodroneUserRequest` and sends it via TCP using the `ClientSocket`. The `CustomerAppServer` 
receives and parses the request through the `CustomerAppMessageParser`, which delegates the operation to the 
`UserAppServerController`. The user repository (`ShodroneUserRepository`) is queried, and the result is returned to the 
client, where the `MarshallerUnmarshaller` deserializes the response into a `ShodroneUserDTO`.

With the user data retrieved, the controller continues by requesting the `Customer` information and then the list of 
show proposals pending feedback. The proxy builds and sends respective requests (`GetCustomerOfRepresentativeRequest`, 
`GetProposalsOfCustomerRequest`) through the socket. On the server side, the `CustomerRepository` and the 
`DeliveryReportingRepository` are queried to retrieve the relevant data. The resulting proposals are returned and 
presented to the customer.

After reviewing the available proposals, the customer selects one and is prompted to accept or reject it, optionally 
providing textual feedback.

When the feedback is submitted, the controller calls `sendFeedbackProposal(...)` on the proxy. A 
`SendFeedbackProposalRequest` is constructed and sent via the `ClientSocket`. On the server side, the 
`handleProposalFeedback(...)` method in the `UserAppServerController` processes the request. This method accesses the 
`ShowProposalRepository`, where the matching proposal is located and its state updated according to the customer's 
decision and feedback. The proposal is then persisted.

A `SendFeedbackProposalResponse` is created and returned to the client, where it is parsed and interpreted. The 
controller then informs the UI of the outcome, and a confirmation message is shown to the customer.

This design ensures a clear separation of concerns:

- The **UI** manages user interaction and decision capture.
- The **controller** coordinates between user actions and the domain logic.
- The **network proxy** handles message preparation and socket communication.
- The **socket layer** abstracts low-level network transport.
- The **message parser** interprets incoming requests and responses.
- The **server controller** executes the domain logic and updates aggregate state.
- The **repositories** provide persistent access to both customer-related information (`DeliveryReportingRepository`, 
`CustomerRepository`) and proposal lifecycle (`ShowProposalRepository`).

## 5. Implementation

The implementation of **US371** focused on enabling customers to submit feedback by accepting or rejecting a delivered 
show proposal, optionally providing a comment.

A new request/response pair was created: `SendFeedbackProposalRequest` and `SendFeedbackProposalResponse`, responsible 
for encapsulating the customer's decision and the response from the server.

The interaction starts in the `SendFeedbackProposalUI`, where the customer selects a proposal and submits a decision 
along with optional feedback. The `SendFeedbackProposalController` coordinates this action and delegates to the 
`CustomerAppProtocolProxy`.

The proxy builds the request and sends it through a TCP socket using the `ClientSocket`. On the server side, the 
request is parsed by the `CustomerAppMessageParser`, which delegates its handling to the `UserAppServerController`.

The method `handleProposalFeedback(...)` in the server controller is responsible for processing the request. It accesses
the `ShowProposalRepository`, locates the relevant proposal using its number, and applies the decision (either 
`accept(...)` or `reject(...)`) with the feedback provided.

The proposal is then persisted, and a `SendFeedbackProposalResponse` is returned to the client. The client-side 
`MarshallerUnmarshaller` handles response parsing and communicates the result back to the controller and UI.

The implementation respects the DDD principles, ensuring the domain model (`ShowProposal`) remains in control of its 
own state and transitions.

Relevant commit messages:

* [US371 - Accept/Reject Proposal – Feedback flow](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/placeholder-commit-hash)

---

## 6. Integration/Demonstration

The functionality developed in **US371** was integrated into the customer-side application via the `SendFeedbackProposalUI`.

When the customer opens the feedback screen, the application queries for all proposals delivered to their account and 
presents them in a selectable list. Upon selecting one, the system prompts for a decision (accept/reject) and optional 
feedback. Once submitted, the proposal's status is updated and persisted accordingly.

This process closes the proposal loop, providing essential feedback to the CRM team and preparing the proposal for 
archival or revision depending on the customer's response.

### Demonstration Instructions

To demonstrate the functionality, follow these steps:

1. **Launch the application** (via the main class or script, as defined in the [readme.md](../../../readme.md)).
2. **Log in as a Customer**.
3. Navigate the **Send Feedback Proposal** option.
4. Select one of the available proposals.
5. Provide a decision (Accept or Reject) and optional feedback.
6. Submit the form.
7. A confirmation message will appear, indicating the operation was successful.

---

## 7. Observations

* The request/response logic is based on a TCP socket communication layer.
* The feedback is processed in the server's domain model via `accept(feedback)` or `reject(feedback)` methods on 
`ShowProposal`.
* The list of proposals is obtained via `DeliveryReportingRepository`, ensuring read-only access to pre-filtered data.
* The response parsing is handled in a centralized manner using the `MarshallerUnmarshaller`.
* The integration follows the CQRS principle: querying through a reporting repository, and writing via the aggregate 
repository.
* This implementation contributes directly to the closing of the proposal lifecycle, supporting future reporting and 
archival logic.
