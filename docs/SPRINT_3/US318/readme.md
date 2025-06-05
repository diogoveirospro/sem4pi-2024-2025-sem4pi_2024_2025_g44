# US 318

## 1. Context

This user story is part of Sprint 3 and introduces the functionality that allows a CRM Manager to configure the
**template** used to format the **proposal document** sent to the customer. The template defines the structure and
content of the final document generated during the proposal process.

To ensure that the document follows the expected structure, the system must validate and process the selected template
before it is accepted. This processing is performed using a plugin, which must already be registered in the system

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implementation: 🧪 Testing

Testing: 🧪 Testing

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

## 3. Analysis

This user story focuses on allowing the CRM Manager to configure the proposal document using a predefined template.
The template is loaded, validated, and processed to produce a properly formatted document that will later be sent to
the customer.

The domain model includes a `ShowProposalDocument` value object, which encapsulates the content of the generated
document. The generation is handled by the `ProposalDocumentGenerator` domain service, which delegates the logic to
a plugin implementing `DocumentGenerationPlugin`.

The process includes:
- Verifying that the proposal is in a valid state (configuration, video, etc.)
- Replacing placeholders with real proposal data
- Validating the final result

The following diagram shows the relevant portion of the domain model within the `ShowProposal` aggregate.

![Domain Model - Show Proposal Aggregate](../../global_artifacts/analysis/images/domain_model_show_proposal.svg)

## 4. Design

This section presents the design adopted for implementing **US318 – Configure Proposal Document**.

### 4.1 Realisation

The following sequence diagram illustrates the interaction flow between the user interface, controller, domain service,
plugin, and repository:

![Sequence Diagram for US318](images/sequence_diagram_us318.svg)

The process starts in the `ConfigureProposalDocumentUI`, where the **CRM Manager** initiates the document configuration
process. The UI requests the list of proposals eligible for document configuration by calling the controller. The
`ConfigureProposalDocumentController` uses the `PersistenceContext` and the `RepositoryFactory` to access the
`ShowProposalRepository`, from which it retrieves all proposals still in a configurable state.

Once the CRM Manager selects a proposal and a predefined template type (e.g., "Portuguese", "English VIP", etc.), the
controller invokes the method `configureDocument(...)` on the selected proposal. The proposal returns the corresponding
raw document template content.

This content is passed to the domain service `ProposalDocumentGenerator`, which is responsible for generating the final
document. The generator calls the `DocumentGenerationPlugin`, which performs the actual template processing and returns
a `ShowProposalDocument` containing the filled content and document path.

If the generation is successful, the controller sets the document on the proposal using `addDocument(...)`, then persists
the updated proposal via the repository.

If any step fails (e.g., due to an invalid template or plugin failure), an appropriate error is returned and shown to
the user.

This design ensures a clean separation of responsibilities:

- The **UI** handles user interaction and selection.
- The **controller** orchestrates the coordination between UI, domain logic, and persistence.
- The **domain service** encapsulates the logic for template processing.
- The **plugin** contains the actual logic for transforming the template into a complete proposal document.
- The **repository** manages data retrieval and persistence.

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

The implementation of **US318** is distributed across the domain, application, and presentation layers.

* **Domain layer**:

   * `ShowProposal.configureDocument()` handles the configuration process, performing placeholder replacement using
     data from the proposal and customer.
   * The `ProposalDocumentGenerator` service delegates to the plugin to generate and validate the content.

* **Application layer**:

   * `ConfigureProposalDocumentController` orchestrates the process.

* **Infrastructure layer**:

   * The plugin implements the logic to validate the structure of the final document.
   * `TemplateLoaderService` reads the template content from the file system based on user selection.

Relevant commit messages:

* [Implementation of US318](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/26691413a48476381972731b4b1e5ceba7ee3796)

## 6. Integration/Demonstration

This functionality was successfully integrated into the **Shodrone** system.

### Demonstration Instructions

1. User logs in as a **CRM Manager**
2. Navigates to **Show Proposals → Configure Proposal Document**
3. Selects a proposal from the list of eligible proposals
4. Selects one of the predefined templates:

   * Portuguese
   * English (Regular)
   * English (VIP)
5. If successful:

   * A document is generated and saved in the proposal
   * A success message is shown
6. If unsuccessful:

   * An error is shown and no changes are persisted

### Notes

* The configured document will later be delivered to the customer in **US316**
* All templates are preloaded from `application.properties`

## 7. Observations

For the implementation of this project, I used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools developed by our department (ISEP).
- **eCafeteria Project**: A reference project developed by our department, used as a source of inspiration for similar
  functionalities and a guide for best practices.
- **JPA (Hibernate)**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.