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

The process begins when the CRM Collaborator opens the interface to send a proposal. The UI invokes the 
`SendProposalController`, which queries the `ShowProposalRepository` for proposals that are ready to be sent — meaning 
they have passed simulation, include a valid video (US315), and have a validated template (US318).

Once the list of ready proposals is presented, the CRM Collaborator selects the one to be sent. The controller then 
retrieves the selected `ShowProposal` from the repository using its identifier.

The controller invokes the `ProposalDelivery` service, passing the proposal object. This service is responsible for 
accessing the associated document and video, and delivering them to the customer.

If the delivery is successful, the UI notifies the user accordingly. If the delivery fails — for example, due to missing 
data — the UI presents an appropriate error message and the process is aborted.

This design follows clean separation of responsibilities:
- The **UI** handles user interaction and feedback.
- The **controller** manages orchestration and delegates to the domain and infrastructure layers.
- The **repository** provides access to persistent proposals.
- The **delivery service** encapsulates the communication logic, allowing future extensibility.

![Sequence Diagram for US316](images/sequence_diagram_us316.svg)

### 4.2. Acceptance Tests

## 5. Implementation

## 6. Integration/Demonstration

## 7. Observations
