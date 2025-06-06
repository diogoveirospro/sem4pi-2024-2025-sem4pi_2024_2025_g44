# US 348

## 1. Context

This user story is part of Sprint 3 and introduces the ability to generate a high-level description of a drone show 
based on its configuration. The generated description serves as a DSL (Domain-Specific Language) representation of the 
show, from which the low-level code for drones can later be derived.

This generation is intended for Drone Techs, who are responsible for converting the high-level DSL into actual 
instructions per drone. The generation process transforms the set of figures and drones selected in the show proposal 
into a structured textual representation using a predefined grammar.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implementation: 📝 To Do

Testing: 📝 To Do

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
* **US312 –  Add figures to a proposal**: The figures must be defined and selected in the proposal.

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
via the user interface. The UI delegates the request to the `GenerateShowDSLController`, which coordinates the operation.

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

### 4.2. Acceptance Tests

The following tests were designed to validate the acceptance criteria defined for **US348**. These focus on verifying 
the correctness of the DSL generation process and its integration within the domain layer, particularly the 
`GenerateShowDSL` service and the `ShowConfiguration` entity.

---

#### **Test 1: DSL is generated based on figures**
**Refers to Acceptance Criteria:** _US348.1_  
**Description:** Ensures that the generated DSL description reflects the figures included in the show configuration.

```java
@Test
public void testDSLGenerationReflectsFigures() {
    // Setup: create a ShowConfiguration with a list of Figure objects
    // Action: call GenerateShowDSL.generateFrom(configuration)
    // Assert: verify that the returned DSL string includes references to the configured figures
}
````

---

#### **Test 2: DSL is assigned to configuration**

**Refers to Acceptance Criteria:** *US348.3*
**Description:** Ensures that the DSL description is stored inside the configuration once generated.

```java
@Test
public void testDSLIsAssociatedWithConfiguration() {
    // Setup: create a ShowConfiguration without a DSL description
    // Action: call GenerateShowDSL.generateFrom(configuration) and assign it
    // Assert: verify that configuration.getDSLDescription() returns a non-null object
}
```

---

#### **Test 4: Description is persisted in proposal**

**Refers to Acceptance Criteria:** *US348.4*
**Description:** Ensures that once the DSL is generated and set on the configuration, the proposal is saved with the 
updated state.

```java
@Test
public void testProposalWithDSLIsPersisted() {
    // Setup: create a ShowProposal with valid configuration
    // Action: set the DSL description on configuration
    // Assert: verify that the configuration exists in the show proposal
}
```

---

#### **Test 5: DSL is not generated for empty configuration**

**Negative Case**
**Description:** Ensures that an error or empty result is returned if no figures are present in the configuration.

```java
@Test
public void testDSLGenerationFailsWithNoFigures() {
    // Setup: create a ShowConfiguration with no figures
    // Action: call GenerateShowDSL.generateFrom(configuration)
    // Assert: expect an empty DSL string or specific exception (depending on implementation)
}
```

## 5. Implementation

## 6. Integration/Demonstration

## 7. Observations
