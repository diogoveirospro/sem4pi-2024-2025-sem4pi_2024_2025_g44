# US 318

## 1. Context

This user story is being developed as part of Sprint 3. It introduces the functionality that allows a CRM Manager to  
configure and validate a proposal template before sending it to a customer. The template defines the structure and  
content of the document that will be delivered to the customer during the show proposal process.

The validation ensures that the template is compatible with the system and conforms to the expected structure. The  
plugin used for validation must already be registered in the system (as defined in US317), and it will be invoked to  
check the correctness of the template formatting.

### 1.1 List of issues

Analysis: 🧪 Testing  

Design: 📝 To Do  

Implementation: 📝 To Do  

Testing: 📝 To Do

## 2. Requirements

**As a** CRM Manager,  
<br>  
**I want** to be able to configure the template that formats the document,  
<br>  
**So that** it can be sent to the customer.

### Acceptance Criteria:

- **US318.1** The plugin used to validate the proposal template must be previously registered in the system.
- **US318.2** The configured template must be validated before it is accepted for use in proposals.
- **US318.3** In case of invalid formatting, an appropriate error must be returned to the user.

### Dependencies/References:

- **_US347 – Proposal Generation_**: This user story uses the configured template to validate and generate the proposal  
  document. The template defined in US318 must be available and valid for US347 to execute successfully.

## 3. Analysis

This user story focuses on validating the proposal template configured by the CRM Manager. The template will be used to  
generate documents sent to customers when proposing a drone show.

The domain includes a `ShowProposalTemplate` value object that encapsulates the raw content of the template to be validated.  
The validation is performed by the `TemplateValidate` domain service, which invokes a plugin registered in the system  
(see [US317](../US317/readme.md)). The validation plugin checks whether the template includes all required tags and 
follows the correct format.

Validation is mandatory: a template cannot be used in the system unless it passes validation. If the validation fails,  
the system returns a descriptive error message and prevents the use of the invalid template.

The following diagram shows the current domain model for the `ShowProposal` aggregate, including the newly introduced  
components `ShowProposalTemplate` and `TemplateValidate`:

![Domain Model - Show Proposal Aggregate](../../global_artifacts/analysis/images/domain_model_show_proposal.svg)

## 4. Design

This section presents the design adopted for implementing **US318 – Configure Proposal Template**.
The following diagram and explanation detail the interaction between the user interface, controller, validation service, 
plugin, domain model, and persistence layer.

### 4.1 Realisation

The sequence diagram below illustrates the realisation of **US318 – Configure Proposal Template**.

The process begins in the user interface (`ConfigureTemplateUI`), where the **CRM Manager** initiates the configuration 
of a proposal template. The UI requests a list of proposals eligible for template configuration by invoking the 
controller. The `ConfigureTemplateController` retrieves these proposals from the `ShowProposalRepository`, filtering 
those that can still be configured.

Once the CRM Manager selects a specific proposal and provides a template, the controller retrieves the corresponding 
`ShowProposal` from the repository using its identifier.

The selected template is passed to the domain service `TemplateValidate`, which is responsible for validating its 
syntax and structure. The validation logic is delegated to a plugin (`TemplateValidationPlugin`), previously registered 
in the system as part of **US317**. The plugin analyses the template and returns a `ValidationResult` indicating whether 
it is valid or contains errors.

If the validation succeeds, the template is set on the `ShowProposal`, and the updated proposal is persisted through 
the repository. If the validation fails, the UI displays the corresponding error messages to the user, and the 
configuration is aborted.

This design ensures a clear separation of concerns:

* The **UI** handles user input and feedback.
* The **controller** coordinates the flow and validation.
* The **domain** retains ownership of proposal data and template assignment.
* The **plugin** encapsulates validation logic, promoting modularity and reuse.

![Sequence Diagram for US318](images/sequence_diagram_us318.svg)

### 4.2. Acceptance Tests


## 5. Implementation


## 6. Integration/Demonstration



## 7. Observations
