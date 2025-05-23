# US 341

## 1. Context

The purpose of this task is to validate the syntax of a figure's high-level description (DSL), ensuring it complies
with the defined grammar and can therefore be safely registered in the system. This task is included in Sprint 3 and
is being implemented for the first time. The grammar used for validation was previously designed in Sprint 2, as part
of US251 – Specification of the language for figure and show description.

### 1.1 List of issues

Analysis: 🧪 Testing  

Design: 🧪 Testing  

Implementation: 📝 To Do  

Testing: 📝 To Do

## 2. Requirements

**As a** Show Designer,  
**I want** to validate the syntax of the figure description written in DSL,  
**So that** I can register the figure in the system.

### Acceptance Criteria

- **_US341.1_** The system must verify that the figure description follows the DSL grammar rules.
- **_US341.2_** The validation process must use the ANTLR parser configured with the current version of the DSL grammar.

### Dependencies/References

- **_US251 – Specification of the language for figure and show description_**: The grammar for the DSL was defined in 
this user story, and it is essential for the validation process.
- **_US233 – Add Figure to the Catalogue_**: The validation must occur before a figure can be added to the catalogue.

## 3. Analysis

The grammar used for this user story was designed in [US251](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#3-analysis) to describe drone shows and figures in a modular and 
extensible way.

In the scope of US341, this grammar is now applied to validate DSL input files before figures are registered in the 
system. The validation process ensures that each DSL file is syntactically correct and matches the expected structure.

The grammar distinguishes between two main domains:

1. **Show Definition:** Describes the overall structure of a show, composed of multiple figure references.
2. **Figure Definition:** Encapsulates all the declarations and movement instructions that define a single figure.

The grammar relies on terminal rules (e.g., `DOUBLENUMBER`, `IDENTIFIER`) to enforce type correctness and uses recursive 
productions to support expression nesting, command blocks (`before`, `after`, `group`) and action sequences.

Validation will be performed using ANTLR, which will generate the lexer and parser to be used in this validation process.
This user story focuses on verifying that the grammar works as intended and detecting syntax errors, but not on 
integrating the parser into the system as a reusable plugin.


## 4. Design

The grammar was fully designed and documented in [US251](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#4-design). In this user story, the focus is on using that grammar to 
generate the ANTLR parser and apply it to figure description files.

The ANTLR grammar will serve as the basis for generating a parser capable of validating DSL inputs. The parser will 
detect syntax errors and confirm whether a DSL description can be safely registered.

This section includes a copy of the relevant grammar rules to support validation. For the full design rationale, see 
the documentation of US251.

[Full Grammar](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#full-grammar)


## 5. Implementation


## 6. Integration/Demonstration


### Demonstration Instructions


## 7. Observations
