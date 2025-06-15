# Lprog User Stories documentation

Too see the images in the documentation go to the docs package folder in the project.

# US 340

This task as the objective of concluding the requirements of the us340 of sprint3, where it is asked to develop a new
functionality to the system. The team will now focus on completing the implementation and testing of this functionality
as well as integrating it with the rest of the system.

## 1. Context

The purpose of this task is to deploy and configure a plugin so it can be used by the system to analyze the figure
high-level description.
This task is included in Sprint 3 and is being implemented for the first time.
This plugin is supposed to use the antlr code developed in US341 - Validate Figure Description, And connect it to the
system so that it can be used to validate the figure description.

### 1.1 List of issues

Analysis: Done

Design: Done

Implement: Done

Test: Done

## 2. Requirements

**As** a Drone Tech,
<br>
**I want** to deploy and configure a plugin,
<br>
**So that** it can be used by the system to analyze the figure high-level description.

### Acceptance Criteria

- **AC01** The system should be able to call the plugin to validate the figure description.

**Dependencies/References:**

- **US341 – Validate Figure Description**: The plugin will use the code developed in this user story to validate the
  figure description.

## 3. Analysis

The grammar used for this user story was designed
in [US251](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#3-analysis) to describe drone
shows and figures in a modular and
extensible way. And is implemented in the plugin developed in [US341 - Validate Figure Description](../US341/readme.md).

To implement this plugin it was necessary to create a `PluginInitializer` that has the function of calling the plugin
and registering it on the `DSLValidatorPluginFactory` that would simply serve as a way to register the plugin in the
system,
and then the `DSLValidatorPlugin` interface was implemented on the `ANTLRDSLValidatorPlugin` the plugin that uses the
ANTLR parser to validate the DSL syntax, to fully connect the plugin to the system. As afterwards the class
`DSLValidate` would be called as it would contain the logic to call the ANTLR plugin that validates the DSL syntax using
the ANTLR parser. Then the validation would go through.

## 4. Design

View the design of the plugin in [US341 - Validate Figure Description](../US341/readme.md#4-design).

For these to work it was necessary to `PluginInitializer.initialize()` as it would then register the instance on
the `DSLValidatorPluginFactory` and then the method `getInstance()` that returns the instance of the format
that would be `DSLValidatorPlugin` that would be implemented by the `ANTLRDSLValidatorPlugin` class, which would then be
used to validate the DSL syntax, that is possible as the interface as the
method `DSLValidationResult validateDSL(String code);`
that would be implemented in the antlr plugin. But the acess would be only possible via the `DSLValidate` class that
would get the factory instance and then call the `validateDSL` method on the plugin instance.

### 4.1. Acceptance Tests

See the [US341 - Validate Figure Description](../US341/readme.md) for more information on the acceptance tests for the
plugin.

## 5. Implementation

See the [US341 - Validate Figure Description](../US341/readme.md) and the information above for more information.

Relevant commit messages:

- [Made some implementation related to the figure plugin](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/78115a139edc8680b9f4aad2a2f8e2fb0b52a30a)
- [Was able to correctly call the plugin](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/c5e0f57d5b2e08d51b60bcb5db986bc984e31315)

## 6. Integration/Demonstration

The running of the plugin is the same as the one
in [US341 - Validate Figure Description](../US341/readme.md#6-integrationdemonstration), as it is the same plugin that
is being used to validate the DSL syntax.

## 7. Observations

Go to user story [US341 - Validate Figure Description](../US341/readme.md) for more information on the plugin and how it
works.

# US 341

## 1. Context

The purpose of this task is to validate the syntax of a figure's high-level description (DSL), ensuring it complies
with the defined grammar and can therefore be safely registered in the system. This task is included in Sprint 3 and
is being implemented for the first time. The grammar used for validation was previously designed in Sprint 2, as part
of US251 – Specification of the language for figure and show description.

The validation logic will be implemented using the ANTLR parser generator. The actual integration of the parser into
the system as a reusable plugin is defined in US340. In this user story, the focus is on implementing the syntax
validation
logic and exposing it through the DSL validation service in the domain layer (`DSLValidate`), which is invoked when a
Figure is being registered.

### 1.1 List of issues

Analysis: ✅ Done

Design: ✅ Done

Implementation: ✅ Done

Testing: ⚪ Not Applicable

## 2. Requirements

**As a** Show Designer,  
<br>
**I want** to validate the syntax of the figure description written in DSL,  
<br>
**So that** I can register the figure in the system.

### Acceptance Criteria

- **_US341.1_** The system must verify that the figure description follows the DSL grammar rules.
- **_US341.2_** The validation process must use the ANTLR parser configured with the current version of the DSL grammar.
- **_US341.3_** The result of the validation must indicate whether the DSL is valid and, if not, provide error details
  (line, message, etc.).

### Dependencies/References

- **_US251 – Specification of the language for figure and show description_**: The grammar for the DSL was defined in
  this user story, and it is essential for the validation process.
- **_US340 – DSL Plugin_**: The validation logic implemented in this US will be executed inside the plugin registered in
  this user story.
- **_US233 – Add Figure to the Catalogue_**: The validation must occur before a figure can be added to the catalogue.

## 3. Analysis

The grammar used for this user story was designed
in [US251](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#3-analysis) to describe drone
shows and figures in a modular and
extensible way.

In the scope of US341, this grammar is now applied to validate DSL input files before figures are registered in the
system. The validation process ensures that each DSL file is syntactically correct and matches the expected structure.

The `Figure` aggregate encapsulates all the relevant information required to define a figure in the system. For this
user story, the focus is on the `DSLDescription` value object, which contains the high-level description of a figure
using the DSL. This description must be validated syntactically before the figure can be stored in the catalogue.

The validation is performed through the domain service `DSLValidate`, which receives the `DSLDescription` and delegates
the actual validation logic to the plugin structure defined in **US340**. If the validation fails, the system prevents
the figure from being added or updated.

The following domain model represents all the relevant entities and value objects involved in figure definition and DSL
validation:

![Domain Model - Figure Aggregate](../../global_artifacts/analysis/images/domain_model_figure.svg)

## 4. Design

This section outlines the design adopted for implementing **US341 – Validate Figure Description**. The system allows a
Show Designer to enter a figure's DSL description, which must be validated before the figure can be added to the
catalogue.

### 4.1 Realisation

The sequence diagram below presents the full validation flow, highlighting the separation of responsibilities between
the
user interface, domain service, plugin infrastructure, and ANTLR-generated components.

The validation process is initiated by the Show Designer through the `AddFigureToCatalogueUI`, where the DSL code and
other
figure-related metadata are entered. The UI component delegates validation to the domain service `DSLValidate`, which
interacts with the plugin registered via **US340**.

The plugin exposes a `validateDSL(code)` method and internally leverages ANTLR-generated components, specifically
`FigureLexer` and `FigureParser`, to perform lexical and syntactic analysis. The process includes:

- Lexical analysis performed by `FigureLexer`
- Syntax parsing through `FigureParser`, starting from the root rule
- Collection of validation results and reporting of syntax errors (if any)

Upon successful validation, the UI proceeds to call the controller to persist the figure. Otherwise, it displays the
validation errors to the user and halts the process.

This design ensures that **only syntactically valid DSL descriptions are accepted**, promoting data integrity and
consistency
across the system.

[Full Grammar](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#full-grammar)

![Sequence Diagram US341](images/sequence_diagram_us341.svg)

### 4.2. Acceptance Tests

Given that the validation logic is invoked directly from the user interface layer — and not from within the domain
model or a dedicated application service — no unit tests are provided for this user story.

The `DSLValidate` service is designed to be used externally by the UI to check whether the input DSL code is
syntactically valid. Since the responsibility of triggering validation and handling the result lies entirely within the
user interface flow, this validation will be verified manually or as part of the figure registration process during
full-system testing.

Unit testing the `DSLValidate` service in isolation is not required in this context, as it acts as a pass-through to the
plugin implemented in **US340**, which is where the core parsing and validation logic resides and may be tested directly
if needed.

## 5. Implementation

The implementation of **US341** focused on enabling the syntactic validation of a figure’s DSL description before it is
registered in the system.

A new application service, `DSLValidate`, was created to encapsulate the validation logic. This service delegates the
validation to the plugin `ANTLRDSLValidatorPlugin`, which uses the ANTLR-generated components `FigureLexer` and
`FigureParser` to perform syntax checks over the input DSL code. The result is returned as a structured object
containing success or failure status and error details (if applicable).

Validation is triggered directly from the user interface (`AddFigureToCatalogueUI`) before a figure is created. If the
DSL is valid, the process continues; otherwise, the figure is not registered and the error messages are shown to the
user.

No changes were required to the `Figure` entity itself. All logic was isolated in the application layer and plugin,
maintaining separation of concerns.

Relevant commit messages:

* [Grammar implementation in ANTLR to test DSL Description.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/ac8e50203bb9db2b8860084a3363740454781268)
* [Minor grammar corrections and creation of the DSLValidate service for validating the DSL file in the US233 UI.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/de39ab3e638252e89fc3e8b7cca8337d2ef03694)

## 6. Integration/Demonstration

The functionality developed in **US341** was successfully integrated into the process of figure registration through
the `AddFigureToCatalogueUI`.

When the Show Designer inputs the DSL code during figure creation, the system performs a validation step by calling the
`DSLValidate` service. This ensures that only syntactically correct DSL descriptions are accepted, preserving system
integrity and preventing runtime errors in future show execution.

The validation result (success or detailed errors) is presented to the user in the UI. If valid, the figure can be
created as usual.

### Demonstration Instructions

To demonstrate the functionality, follow these steps:

1. **Launch the application** (via the main class or script, as defined in the [readme.md](../../../readme.md).
2. **Log in as a Show Designer**.
3. Navigate to the **Figures** section.
4. Choose **Add Figure to the Catalogue**.
5. Enter all the necessary data to create the figure.
6. Select a **DSL File** from the list or the **Other** option to enter your own path to a file.
7. If the DSL is valid, you can continue creating the figure; otherwise, error messages (with line and message) are
   displayed, and you are asked to make a new selection.

## 7. Observations

* The validation process was implemented using **ANTLR 4**, a powerful parser generator for building DSLs.
* The grammar used is defined and documented
  in [US251](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md).
* No unit tests were implemented for this user story, as validation is triggered exclusively from the user interface
  and is indirectly verified through the figure registration flow.
* The `DSLValidate` service and the plugin were designed for reuse, ensuring that future use cases can rely on
  centralized validation logic.
* The `eCafeteria` project and **EAPLI Framework** served as references for structural and architectural alignment.

# US 345

## 1. Context

The objective of this task is to implement and deploy a plugin responsible for analyzing and validating drone programs
written in the Drone Language (DSL). This functionality is part of **Sprint 3**, aligned with the domain of drone
programming and simulation.

### 1.1 List of Issues

Analysis :        Doing

Design:           Doing

Implement:        To do

Test:             ⚪ Not Applicable (UI-driven)

---

## 2. Requirements

**As a** Drone Tech,  
**I want** to deploy and configure a plugin to be used by the system to analyze/validate a drone program,  
**So that** we can ensure the programs are syntactically correct and ready to be used in drone simulations.

### Acceptance Criteria:

- **US345.1:** There must be a plugin for each different drone language.

### Dependencies/References:

- **US253 - Drone Programming Language Configuration:** This user story is a direct dependency. It is required to have
  the drone programming language defined in the system so it can be used by the plugin.

---

## 3. Analysis

The Drone Language is a Domain-Specific Language (DSL) used to program individual drone behaviors in a show. The
language includes instructions such as `takeOff`, `land`, `move`, `blink`, etc., and allows mathematical expressions and
structured types such as `Position`, `Vector`, and arrays.

Two versions of the language currently exist:

- **DroneOne DSL** (v0.86)
- **DroneTwo DSL** (v0.666), which adds new types and instructions

Both versions share much of the core syntax but diverge in instruction sets and type names.

---

## 4. Design

The plugin is implemented using **ANTLR4**. A shared Antlr (`DroneLexer.g4 and DroneParser.g4`) was created to support
both versions by
generalizing keywords and types.

### 4.1 Architecture Overview

- `DroneParser.g4`: ANTLR grammar defining syntactic structure of the language
- `DroneLexer.g4`: ANTLR lexer defining tokens for the language
- `ANTLRDroneValidationPlugin`: Main Java class that uses the parser to check program validity

The plugin exposes a method:

```java
public DroneValidationResult validateTemplate(String code) {
}
```

Which returns a success status or a list of syntax errors with line/column info.

### 4.2 Sequence Diagram

![Sequence Diagram](images/sequence_diagram_us345.svg "Sequence Diagram")

### 4.3 Acceptance Tests

Given that the validation logic is invoked directly from the user interface layer — and not from within the domain
model or a dedicated application service — no unit tests are provided for this user story.

The `DroneValidationPlugin` is designed to be used externally by the UI to check whether the input DSL code is
syntactically valid. Since the responsibility of triggering validation and handling the result lies entirely within the
user interface flow, this validation will be verified manually or as part of the drone program registration process
during
full-system testing.

## 5. Implementation

The implementation of **US345** focused on enabling the syntactic validation of a drone program's code.
A new plugin, `ANTLRDroneValidationPlugin`, was created to encapsulate the validation logic. This plugin uses the
ANTLR-generated components `DroneLexer` and `DroneParser` to perform syntax checks over the input code. The result
is returned as a structured object containing success or failure status and error details (if applicable).

Validation is triggered directly from the user interface (`ValidateDroneProgramUI`) before a drone program is created.

## 6. Integration/Demonstration

To demonstrate the functionality, follow these steps:

1. **Launch the application** (via the main class or script, as defined in the [readme.md](../../../readme.md)).
2. **Log in as a Drone Tech**.
3. Navigate to the **Drone Programs** section.
4. Choose **Validate Drone Program**.
5. Choose a drone.
6. The program will validate the drone program code and display the result.

## 7. Observations

* The validation process was implemented using **ANTLR 4**, a powerful parser generator for building DSLs.
* The grammar used is defined and documented
  in [US253](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US253/US253.md).
* No unit tests were implemented for this user story, as validation is triggered exclusively from the user interface
  and is indirectly verified through the drone registration flow.
* The `DroneValidate` service and the plugin were designed for reuse, ensuring that future use cases can rely on
  centralized validation logic.
* The `eCafeteria` project and **EAPLI Framework** served as references for structural and architectural alignment.

# US 347

## 1. Context

The objective of this task is to implement and deploy a plugin responsible for validating and generating proposal
documents for show proposals. This functionality is part of Sprint 3, aligned with the domain of CRM and customer
proposal management.

### 1.1 List of Issues

Analysis :        Doing

Design:           Doing

Implement:        Done

Test:             ⚪ Not Applicable (UI-driven)

---

## 2. Requirements

**As a** CRM Collaborator,  
**I want** the system to validate the proposal template and to generate the proposal document for the show proposal,
**So that** we can prepare the proposal for the customer.

### Acceptance Criteria:

- **US347.1:** There must be a plugin to validate the proposal template.
- **US347.2:** The system must generate the proposal document based on the selected template and show proposal data.

### Dependencies/References:

- **US255 - Show Proposal Template Configuration:** This user story is a direct dependency. It is required to have
  the show proposal template defined in the system so it can be used by the plugin.

---

## 3. Analysis

The proposal document generation process involves validating the selected template and generating a document based on
the show proposal data. The validation ensures that the template follows the defined grammar rules, while the generation
process combines the template with the proposal data to produce a complete document.

The plugin uses ANTLR 4 to validate the template syntax. The grammar for the proposal template is defined
in `ProposalLexer.g4` and `ProposalParser.g4`.
The validation process ensures that the template is syntactically correct before generating the document.

The `ConfigureProposalDocumentController` orchestrates the process, interacting with the plugin and the repository to
validate
the template and persist the generated document.

## 4. Design

The plugin is implemented using **ANTLR4**. A shared Antlr (`ProposalLexer.g4 and ProposalParser.g4`) was created to
support all versions by generalizing keywords and types.

### 4.1 Architecture Overview

* `ProposalLexer.g4`: Defines the tokens for the proposal template grammar.
* `ProposalParser.g4`: Defines the syntactic structure of the proposal template grammar.
* `ANTLRDocumentGenerationPlugin`: Validates the template and generates the document.
* `ConfigureProposalDocumentController`: Coordinates the validation and generation process.
* `ConfigureProposalDocumentUI`: Provides the user interface for selecting templates and generating documents.

## 5. Implementation

The implementation of **US347** focused on enabling the validation and generation of proposal documents. The following
components were developed:

* `ProposalLexer.g4`: Defines tokens for keywords, symbols, and basic structures in the proposal template.
* `ProposalParser.g4`: Defines the rules for parsing the proposal template syntax.

* `ANTLRDocumentGenerationPlugin`: Implements the `DocumentGeneratorPlugin` interface. It validates the template syntax
  using the ANTLR-generated lexer and parser and generates the document content.

* `ConfigureProposalDocumentController`: Coordinates the validation and generation process. It interacts with the plugin
  and repository to validate the template and persist the generated document.

* `ConfigureProposalDocumentUI`: Provides the user interface for selecting templates and generating documents. It
  interacts with the controller to execute the process.

## 6. Integration/Demonstration

The functionality developed in **US 347** was successfully integrated into the CRM Collaborator’s workflow via the
`ConfigureProposalDocumentUI`.

To demonstrate the functionality, follow these steps:

1. **Launch the application** (via the main class or script, as defined in the [readme.md](../../../readme.md)).
2. **Log in as a CRM Collaborator**.
3. Navigate to the **Show Proposals** section.
4. Select a proposal and choose the **Configure Proposal Document** option.
5. Choose a proposal template from the list.
6. If the template is valid, the system generates the proposal document and displays a success message; otherwise,
   validation errors are shown to the user for correction.

## 7. Observations

* The validation process was implemented using **ANTLR 4**, a powerful parser generator for building DSLs.
* The grammar used is defined and documented
  in [US255](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US255/US255.md).
* No unit tests were implemented for this user story, as validation is triggered exclusively from the user interface
  and is indirectly verified through the drone registration flow.
* The `DocumentGeneratorPlugin` interface and `ANTLRDocumentGenerationPlugin` were designed for reuse, allowing future
  extensions for other document types.
* The `eCafeteria` project and **EAPLI Framework** served as references for structural and architectural alignment.


# US 348

## 1. Context

This user story is part of Sprint 3 and introduces the ability to generate a high-level description of a drone show
based on its configuration. The generated description serves as a DSL (Domain-Specific Language) representation of the
show, from which the low-level code for drones can later be derived.

This generation is intended for Drone Techs, who are responsible for converting the high-level DSL into actual
instructions per drone. The generation process transforms the set of figures and drones selected in the show proposal
into a structured textual representation using a predefined grammar.

### 1.1 List of issues

Analysis: ✅ Done

Design: ✅ Done

Implementation: ✅ Done

Testing: ✅ Done

## 2. Requirements

**As a** Drone Tech,
<br>
**I want** the system to generate the show high-level description from its set of figures,
<br>
**So that** I can later generate the actual code for each drone.

### Acceptance Criteria:

* **US348.1** The show description must be generated based on the figures in the configuration.
* **US348.2** The generation must follow the predefined DSL grammar.
* **US348.3** The generated description must be associated with the `ShowConfiguration`.
* **US348.4** The description must be stored in the system for future access.

### Dependencies/References:

* **US310 – Create Show Proposal**: The proposal must already exist and contain a valid configuration.
* **US312 – Add figures to a proposal**: The figures must be defined and selected in the proposal.

## 3. Analysis

This user story focuses on producing a DSL-based textual description of a drone show. The data source is the
`ShowConfiguration` entity, which contains the ordered set of `Figure` elements selected for the show, along with the
assigned `Drone` instances.

The generation process applies a predefined grammar to this configuration to produce a syntactically valid
`ShowDSLDescription` (a value object). This object is then associated with the `ShowConfiguration` for future retrieval,
export, or compilation into drone-specific code.

The generation is performed by the domain service `GenerateShowDSL`, which consumes a `ShowConfiguration` and returns a
`ShowDSLDescription`. The grammar is defined externally and should be maintained consistently to ensure that all figures
are valid and properly formatted.

The following diagram shows the domain model for the `ShowProposal` aggregate, highlighting the entities and value
objects involved in this user story:

![Domain Model - Show Proposal Aggregate](../../global_artifacts/analysis/images/domain_model_show_proposal.svg)

## 4. Design

This section presents the design adopted for implementing **US348 – Generate Show DSL Description**.

### 4.1 Realisation

The process begins when the Drone Tech initiates a request to generate the DSL description for a specific show proposal
via the user interface. The UI delegates the request to the `GenerateShowDSLController`, which coordinates the
operation.

The controller creates a new `PersistenceContext` and obtains access to the `ShowProposalRepository` through the
`RepositoryFactory`. It then retrieves the selected `ShowProposal` using its identifier and accesses the associated
`ShowConfiguration`.

The generation of the DSL description is delegated to the `GenerateShowDSL` domain service. This service applies the
predefined DSL grammar to the set of `Figure` elements included in the configuration. Internally, the service uses
ANTLR-generated components, namely `ShowLexer` and `ShowParser`, to perform lexical and syntactic analysis
over the configuration.

The final result is encapsulated in a `ShowDSLDescription` value object, which represents the textual description of
the show. This value object is then set on the `ShowConfiguration`. The updated `ShowProposal` is persisted via the
repository.

This design ensures that:

- The **user interface** is responsible only for interaction and presentation.
- The **controller** orchestrates the process and handles persistence.
- The **domain service** encapsulates the grammar-based logic using ANTLR components.
- The **value object** guarantees immutability and correctness of the generated DSL.
- The **proposal configuration** stores the result for future compilation or inspection.

![Sequence Diagram for US348](images/sequence_diagram_us348.svg)

## 5. Implementation

The implementation of **US348** focused on enabling the generation of a DSL-based high-level description for a drone
show based on its configured figures.

A new application service, `GenerateShowDSL`, was created to encapsulate the generation logic. This service obtains the
list of figures from the `ShowConfiguration` and generates a DSL representation using predefined grammar rules. The DSL
content is then validated using the plugin `ANTLRShowDSLValidatorPlugin`, which uses ANTLR-generated components
(`ShowLexer`, `ShowParser`) to ensure syntactic correctness.

If validation succeeds, a new `ShowDSLDescription` value object is created and associated with the show proposal. The
proposal is then persisted through the repository.

The entire logic is isolated in the application layer and service, preserving the domain model integrity and ensuring
modular design.

Relevant commit messages:

* [Possible Implementation of US348](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/dbc26f2dc870e325a25a2180bd620333b330df34)

## 6. Integration/Demonstration

The functionality developed in **US348** was successfully integrated into the Drone Tech’s workflow via the
`GenerateShowDSLUI`.

When the Drone Tech selects a proposal marked as ready, the system fetches its configuration and invokes the
`GenerateShowDSL` service. The generated DSL is validated and, if successful, saved in the proposal. Otherwise, the
validation errors are shown to the user for correction.

This ensures that only valid DSL descriptions are persisted, protecting the consistency of the domain and preparing
the show for downstream processing such as simulation and code generation.

### Demonstration Instructions

To demonstrate the functionality, follow these steps:

1. **Launch the application** (via the main class or script, as defined in the [readme.md](../../../readme.md).
2. **Log in as a Drone Tech**.
3. Navigate to the **Show Proposals** section.
4. Select the **Generate Show DSL** option.
5. Choose a proposal marked as ready for generation.
6. If the generation and validation succeed, a success message is shown; otherwise, validation errors are displayed
7. and the DSL is not saved.

## 7. Observations

* The validation process was implemented using **ANTLR 4**, a powerful parser generator for building DSLs.
* The grammar used is defined and documented
  in [US251](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md).
* No unit tests were implemented for this user story, as validation is triggered exclusively from the user interface
  and is indirectly verified through the figure registration flow.
* The `DSLValidate` service and the plugin were designed for reuse, ensuring that future use cases can rely on
  centralized validation logic.
* The `eCafeteria` project and **EAPLI Framework** served as references for structural and architectural alignment.

