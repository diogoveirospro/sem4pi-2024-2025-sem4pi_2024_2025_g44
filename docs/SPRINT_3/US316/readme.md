# US 316

## 1. Context

This user story is being developed as part of Sprint 3. It introduces the functionality that allows a CRM Collaborator to
send a completed show proposal to a customer. A proposal includes the configured figures, drones, duration, the
associated simulation video, and a formatted document generated from a previously validated template (see US318).

Sending the proposal is only possible once the proposal has passed simulation, includes a video (US315), and has been
formatted with a valid template. The goal is to provide a clear, professional proposal document to the customer, using
a standardised format with personalised content.

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
**So that** the customer can review and approve the planned drone show.

### Acceptance Criteria:

* **US316.1** The proposal must have passed simulation validation.
* **US316.2** The proposal must have an associated video (see US315).
* **US316.3** A valid proposal template must be configured for the proposal (see US318).
* **US316.4** The final document must be generated using the template and data.
* **US316.5** The system must successfully deliver the document and video to the customer.

### Dependencies/References:

* ***US315 – Generate Simulation Video***: A video must exist before sending the proposal.
* ***US318 – Configure Proposal Template***: A valid template must be available and applied.
* ***US347 – Proposal Generation***: Involves document generation using the template.

## 3. Analysis

This user story focuses on the final step of the proposal process: delivering the complete proposal package to the
customer.

The domain includes a `ShowProposal` entity, which aggregates:

* Figures and configuration (ShowConfiguration)
* Simulation video (Video VO)
* Template (ShowProposalTemplate VO)

The proposal must be in a valid state (e.g., approved simulation, associated video). The content from `ShowProposalTemplate`
is combined with the data from the proposal to produce a formatted document (e.g. TXT or PDF). The system then packages 
this document along with the video and sends it to the customer.

The following diagram shows the current domain model for the `ShowProposal` aggregate:

![Domain Model - Show Proposal Aggregate](../../global_artifacts/analysis/images/domain_model_show_proposal.svg)

## 4. Design

This section presents the design adopted for implementing **US316 – Send Show Proposal to Customer**.  
The sequence diagram below illustrates the interaction between the CRM Collaborator, the UI, the controller, the 
proposal repository, and the delivery service.

### 4.1 Realisation

The process begins when the CRM Collaborator requests to send a show proposal from the user interface. The 
`SendProposalUI` calls the `SendProposalController`, which starts a new `PersistenceContext` to obtain access to the 
necessary repositories via the `RepositoryFactory`.

The controller then retrieves the `ShowProposalRepository` and queries it using `findProposalsReadyToSend()` to obtain 
only those proposals that meet all the conditions for sending (i.e., passed simulation, video available, and valid 
template configured).

Once the list of valid proposals is received, it is shown to the user via the UI. After the CRM Collaborator selects a 
proposal, the UI sends the corresponding proposal ID back to the controller.

The controller loads the selected proposal from the repository using its identifier. If the proposal is valid, it invokes 
the `ProposalDelivery` service to handle the actual delivery. This service is responsible for sending the formatted 
document and associated video to the customer (e.g., via email).

If the delivery is successful, the UI displays a success message. Otherwise, an appropriate error message is shown to 
the user, indicating that the proposal could not be sent.

![Sequence Diagram for US316](images/sequence_diagram_us316.svg)

### 4.2. Acceptance Tests

## 5. Implementation

## 6. Integration/Demonstration

## 7. Observations
