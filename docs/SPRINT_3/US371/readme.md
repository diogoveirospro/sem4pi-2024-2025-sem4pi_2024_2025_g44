# US 371

## 1. Context

This user story is part of Sprint 3 and introduces the functionality that allows a **Customer** to accept or reject a 
show proposal and optionally provide feedback on it.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implementation: 🚧 Doing

Testing: 📝 To Do
 

## 2. Requirements

**As a Customer,**  
<br>
**I want** to accept or reject a proposal,  
<br>
**So that** I can express whether I agree with the show plan and optionally share my opinion.

### 2.1 Acceptance Criteria

* **US371.1** The customer must be able to accept or reject a proposal.
* **US371.2** The customer should be able to optionally provide textual feedback.
* **US371.3** The system must update the proposal status and store any given feedback.
* **US371.4** This action must be available only for proposals currently pending decision.

### 2.2 Dependencies and References

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

The following sequence diagram illustrates the interaction flow between the user interface, controller, proxy, server, 
and repositories:

![Sequence Diagram for US371](images/sequence_diagram_us371.svg)

The process begins in the `SendFeedbackProposalUI`, where the **Customer** requests to view all show proposals that are 
pending a decision. The UI triggers the `SendFeedbackProposalController`, which initiates a multistep lookup process to 
retrieve the customer identity and corresponding pending proposals.

The controller first requests the associated `ShodroneUser` using the authenticated user credentials. This request is 
serialized and sent through the network via the `CustomerAppProtocolProxy`, using a TCP socket. On the server side, the 
`CustomerAppServer` receives the request, which is parsed and processed by the `CustomerAppMessageParser` and delegated 
to the `UserAppServerController`. The controller queries the `ShodroneUserRepository` and returns the user information, 
which is sent back through the same communication chain and deserialized by the `MarshallerUnmarshaller`.

Once the user information is available, the controller proceeds to retrieve the `Customer` entity and finally the list 
of proposals pending decision using the `DeliveryReportingRepository`.

After the list of pending proposals is displayed, the customer selects one and is prompted to accept or reject it, 
optionally providing textual feedback.

Upon submission, the controller invokes the `sendFeedbackProposal(...)` method, which constructs a 
`SendFeedbackProposalRequest` and sends it through the proxy using a TCP socket. The request is parsed on the server 
side and routed to the `handleFeedbackProposal()` method of the `UserAppServerController`.

This method uses the `ShowProposalRepository` to locate and update the selected proposal with the customer’s decision 
and feedback. The result is wrapped in a `SendFeedbackProposalResponse`, which is sent back through the network, 
deserialized, and returned to the controller. The UI then shows a confirmation message to the user.

This design ensures a clear separation of concerns:

- The **UI** handles user input and interaction.
- The **controller** coordinates between user interaction, proxy communication, and business logic.
- The **proxy** is responsible for request formatting and communication via sockets.
- The **parser** interprets messages and delegates them to the appropriate server-side logic.
- The **server controller** encapsulates the domain logic and updates the aggregate state.
- The **repositories** are used for reading (`DeliveryReportingRepository`) and writing (`ShowProposalRepository`) 
domain data.

### 4.2. Acceptance Tests

## 5. Implementation

## 6. Integration/Demonstration

## 7. Observations