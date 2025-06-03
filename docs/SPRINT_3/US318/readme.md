# US 318 – Templates for Show Proposals

## 1. Context

This user story is part of Sprint 3 and introduces the functionality that allows a CRM Manager to configure the 
**template** used to format the **proposal document** sent to the customer. The template defines the structure and 
content of the final document generated during the proposal process.

To ensure that the document follows the expected structure, the system must validate the selected template before it 
is accepted. This validation is performed using a plugin, which must already be registered in the system (as described 
in US317).

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implementation: 📝 To Do

Testing: 📝 To Do

## 2. Requirements

**As a** CRM Manager,  
<br>
**I want** to be able to configure the template that formats the document to be sent to the customer,  
<br>
**So that** the proposal document follows a standard structure and can be generated and sent correctly.

### Acceptance Criteria:

- **US318.1** The proposal must contain a valid configuration, including assigned drones and figures, before a document 
template can be configured.
- **US318.2** The plugin used to validate the proposal document must be previously registered in the system.
- **US318.3** In case of invalid formatting, a descriptive error must be returned to the user.
- **US318.4** Only predefined and valid templates can be used for document configuration. Any attempt to use an 
unrecognised template must be rejected.

### Dependencies/References:

- **_US347 – Proposal Generation_**: The document configured in this user story will be used to generate the final 
proposal file. It must be valid for US347 to execute successfully.
- **_US317 – Plugin Registration_**: The plugin responsible for validation must be registered beforehand.

## 3. Analysis

This user story focuses on allowing the CRM Manager to configure and validate the template that defines the structure 
of the proposal document. The document is later generated based on this template and sent to the customer.

The domain model includes a `ShowProposalDocument` value object, which encapsulates the raw content of the template. 
The validation is performed by the `DocumentValidate` domain service, which delegates the task to a plugin 
(`DocumentValidationPlugin`) registered in the system.

Validation is required before a document template can be associated with a proposal. If the template is invalid, the 
system returns a validation error and prevents its use.

The following diagram shows the relevant portion of the domain model within the `ShowProposal` aggregate, including 
`ShowProposalDocument` and `DocumentValidate`.

![Domain Model - Show Proposal Aggregate](../../global_artifacts/analysis/images/domain_model_show_proposal.svg)

## 4. Design

This section presents the design adopted for implementing **US318 – Configure Proposal Document**.  
The following sequence diagram and explanation detail the interaction between the user interface, controller, 
validation service, plugin, domain model, and repository.

### 4.1 Realisation

The process begins in the UI (`ConfigureProposalDocumentUI`), where the CRM Manager initiates the configuration of a 
proposal document. The UI requests from the controller the list of proposals that are still eligible for configuration.

The `ConfigureProposalDocumentController` accesses the `ShowProposalRepository` to retrieve proposals that are in a 
configurable state. The CRM Manager selects one of them and a predefined template type (e.g., "Portuguese", 
"English VIP", etc.).

The controller loads the corresponding `ShowProposal` and the template content. The selected content is then validated 
through the `DocumentValidate` domain service. The validation logic is delegated to the `DocumentValidationPlugin`, 
which ensures that the content is syntactically and structurally correct.

If validation is successful, the template content is stored as a `ShowProposalDocument` and assigned to the proposal. 
The proposal is saved in the repository. If validation fails, the CRM Manager is notified, and the operation is aborted.

This design separates responsibilities clearly:

- The **UI** manages user input and feedback.
- The **controller** orchestrates the interaction between domain logic and persistence.
- The **domain service** encapsulates the validation logic.
- The **plugin** allows pluggable validation strategies, supporting future extensibility.

![Sequence Diagram for US318](images/sequence_diagram_us318.svg)

### 4.2. Acceptance Tests

The following tests validate the acceptance criteria defined for **US318 – Configure Proposal Document**. They ensure 
that a document template can only be configured and accepted when all required conditions are met: a complete proposal, 
a valid plugin, and a properly formatted template.

---

#### **Test 1: Proposal is not ready for document configuration if required data is missing**
**Refers to Acceptance Criteria:** _US318.1_  
**Description:** Ensures that a proposal without necessary data (e.g., configuration, video, etc.) cannot proceed 
with document configuration.

```java
@Test
void ensureProposalCannotConfigureDocumentWithoutRequiredFields() {
    // Setup: create a ShowProposal with missing configuration or customer information
    // Action: call isReadyToConfigureDocument() on the proposal
    // Assert: verify the method returns false
}
```

---

#### **Test 2: Valid proposal configures document correctly**

**Refers to Acceptance Criteria:** *US318.1 + US318.2*
**Description:** Confirms that a complete and valid proposal successfully configures a document template, with all 
placeholders replaced and validation completed.

```java
@Test
    void ensureDocumentIsConfiguredCorrectly() {
    // Setup: create a ShowProposal with configuration, video and template content
    // Action: call proposal.configureDocument(template, manager)
    // Assert: resulting document contains replaced values (e.g., customer name, model info)
}
```

---

#### **Test 3: Only predefined templates can be used**
**Refers to Acceptance Criteria:** _US318.4_  
**Description:** Ensures that if an unrecognised template identifier is used (i.e., not one of the predefined valid options), the system throws an error and blocks document configuration.

```java
@Test
    void ensureInvalidTemplateThrowsException() {
        // Setup: create a valid ShowProposal with configuration and video
        // Use an invalid template name (e.g., "french", which is not configured)
        // Action: call proposal.configureDocument("french", manager)
        // Assert: IllegalArgumentException is thrown due to unrecognised template
    }

```

## 5. Implementation

## 6. Integration/Demonstration

## 7. Observations