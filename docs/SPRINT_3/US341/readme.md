# US 341

## 1. Context

The purpose of this task is to validate the syntax of a figure's high-level description (DSL), ensuring it complies
with the defined grammar and can therefore be safely registered in the system. This task is included in Sprint 3 and
is being implemented for the first time. The grammar used for validation was previously designed in Sprint 2, as part
of US251 – Specification of the language for figure and show description.

The validation logic will be implemented using the ANTLR parser generator. The actual integration of the parser into
the system as a reusable plugin is defined in US340. In this user story, the focus is on implementing the syntax validation
logic and exposing it through the DSL validation service in the domain layer (`DSLValidate`), which is invoked when a
Figure is being registered.

### 1.1 List of issues

Analysis: 🧪 Testing  

Design: 🧪 Testing  

Implementation: 📝 To Do  

Testing: 📝 To Do

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

The grammar used for this user story was designed in [US251](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#3-analysis) to describe drone shows and figures in a modular and 
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

This section outlines the design adopted for implementing **US341**. The class diagram presents the essential components 
involved in validating a figure's DSL description, with a clear separation between the user interface, domain service, 
validation logic, and integration with the DSL plugin.

### 4.1 Realisation

The class diagram below represents the realisation of **US341 — Validate Figure Description**. The validation process 
is initiated in the user interface, where the DSL code entered by the Show Designer is validated before the figure is 
submitted to the system. The interface invokes the domain service `DSLValidate`, which delegates the validation to the 
plugin defined under **US340**.

The plugin exposes a method such as `validateDSL(String code)`, which internally uses ANTLR-generated components — 
namely `FigureLexer` and `FigureParser` — to perform syntactic analysis of the DSL input. If any syntax errors are 
detected, they are collected and returned in a structured format. If the input is syntactically correct, the validation 
is considered successful.

After validation succeeds, the UI proceeds to create the `DSLDescription` value object and the corresponding `Figure`. 
This ensures that only figures with valid DSL descriptions are allowed into the catalogue, maintaining system integrity.

Only the relevant domain elements are included in the diagram, such as `Figure`, `DSLDescription`, the `DSLValidate` 
service, and the plugin interface. The diagram omits unrelated components to maintain clarity and focus on the core 
functionality of DSL validation.

[Full Grammar](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#full-grammar)

![Class Diagram US341](images/sequence_diagram_us341.svg)

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


## 6. Integration/Demonstration


### Demonstration Instructions


## 7. Observations
