# US 233

## 1. Context

This user story is being developed as part of Sprint 2. It introduces the functionality to add a new figure to the 
public catalogue, including its associated category and keywords. If the figure is marked as exclusive, it will not be 
public and can only be used in shows for the customer holding the exclusivity rights.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implement: 📝 To Do

Test: 📝 To Do


## 2. Requirements

**As a** Show Designer,
**I want** to add a figure to the public catalogue,
**So that** no figures are missing.

**Acceptance Criteria:**

- **US233.1** The figure must be classified with a valid category and a set of keywords.
- **US233.2** If a figure is exclusive to a customer, it must not be added to the public catalogue and should only be 
associated with that customer.
- **US233.3** The figure's DSL description and version must be stored in the system.
- **US233.4** The system must ensure that only authenticated Show Designers can add a figure to the catalogue.
- **US233.5** The figure name must be unique within the public catalogue.

**Dependencies/References:**

- **_US245 – Add figure category_**: A figure category must exist before it can be assigned to a new figure.

**Client Clarifications:**

> **[Topic: Questões de negócio](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35186)**
>
> All figures must be added to the catalogue, regardless of whether they are public or exclusive.  
> Private figures may appear in the catalogue, but can only be used in proposals made for the customer holding 
exclusivity rights.

> **[Topic: Confusão sobre Figuras e DSL](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35283)**
> 
> According to the client, "DSL code" and "DSL description" refer to the same thing — the high-level DSL file that 
defines a figure. This should not be confused with the figure's general textual description (e.g., "ISEP logo"), 
which is a separate attribute.

> **[Topic: Keyword e Category](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35336)**
>
> Categories exist within a catalogue and can be selected from it. Keywords are free-form values introduced by the user. 
Additionally, keyword and category comparisons must be case-insensitive and accent-insensitive.

> **[Topic: Informações — Figure](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35438)**
>
> The client confirmed that it is relevant to store the author (Show Designer) who created the figure, as a way of 
giving credit for their work.

> **[Topic: Figure category](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35571)**
>
> The client confirmed that both the category and the keywords of a figure can change when a new version of that figure 
is released.

> **[Topic: Figure Version](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35573)**
>
> The client clarified that each figure must have a unique identifier. This can either be a unique code per version or 
a combination of code and version to ensure uniqueness. What distinguishes one version from another is entirely up to 
the client's management of the catalogue.

> **[Topic: Figure](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35621)**
>
> The client confirmed that the *description* and *keywords* of a figure are distinct attributes.  
> Additionally, it is possible to add a **new version** of an existing figure to the public catalogue.

## 3. Analysis

The figure aggregate includes several attributes, but for this user story the most relevant ones are:

- `Code` and `Version`, which together uniquely identify each figure in the system.
- `Description`, used to provide meaningful information about the figure to users browsing the catalogue.
- `FigureStatus`, to define whether the figure is currently active in the system.
- `Keyword` and `Category`, which classify the figure and support future filtering and searching capabilities.
- `Exclusivity` and the associated `Customer`, which indicate if the figure is exclusive and, if so, to which customer.
- `ShowDesigner`, identifying the user responsible for creating the figure, which may be relevant for traceability or 
validation.

Other attributes such as the DSL description or simulation metadata are not relevant in the context of this 
functionality and were intentionally omitted from the diagram for clarity.

![Domain Model for US233](images/domain_model_us233.svg)

## 4. Design

In this section, we describe the design approach adopted for implementing **US233 – Add Figure to the Catalogue**. 
The class diagram defines the main components involved in the addition of a new figure, showing a clear separation of 
concerns between the UI, application logic, domain model, and persistence infrastructure.

### 4.1. Realization

The class diagram below illustrates the realization of **US233 – Add Figure to the Catalogue**. The UI component 
initiates the process by invoking the `addFigureToCatalogue(figure)` method in the controller. The controller is 
responsible for coordinating the operation: it retrieves the appropriate `FigureRepository` instance from the configured 
`RepositoryFactory`, and delegates the persistence of the figure.

Before adding a figure, the UI also calls `showCategories()` to present the user with a list of available categories 
to associate with the new figure. This action internally invokes `listCategories()` in the controller, which interacts 
with the `CategoryRepository` and converts the domain `Category` objects to `CategoryDTO` via the `CategoryMapper`.

Both the `FigureRepository` and `CategoryRepository` have two implementations (JPA and in-memory), selected dynamically 
via the `RepositoryFactory`.

The domain model includes relevant elements such as `Figure`, `Category`, `ShowDesigner`, and `Exclusivity`, as well as 
their supporting value objects (e.g., `Code`, `Version`, `CategoryName`, etc.), ensuring proper encapsulation and 
expressiveness of the business rules.

![Class Diagram US233](images/class_diagram_us233.svg)

### 4.2. Applied Patterns

The design of the implementation for **US233 – Add Figure to the Catalogue** applies several well-established design 
patterns, contributing to a robust and extensible architecture. Below are the key patterns identified in this solution:

#### 1. **DTO (Data Transfer Object) Pattern**
- **Class Involved:** `CategoryDTO`
- **Description:** Used to transfer category information from the domain to the UI without exposing the domain entity.

#### 2. **Mapper Pattern**
- **Class Involved:** `CategoryMapper`
- **Description:** Converts `Category` domain entities into `CategoryDTO` objects. Centralizes the transformation logic, 
enhancing separation of concerns.

#### 3. **Repository Pattern**
- **Classes Involved:** `FigureRepository`, `CategoryRepository`, `JpaFigureRepository`, `InMemoryFigureRepository`, etc.
- **Description:** Abstracts data access logic behind clear interfaces, allowing seamless switching between persistence 
strategies.

#### 4. **Factory Pattern**
- **Classes Involved:** `RepositoryFactory`, `JpaRepositoryFactory`, `InMemoryRepositoryFactory`
- **Description:** Provides repository instances according to the runtime context (JPA or in-memory), supporting 
flexibility and configurability.

#### 5. **Singleton Pattern**
- **Classes Involved:** `JpaRepositoryFactory`, `InMemoryRepositoryFactory`
- **Description:** Ensures a single instance of the repository factory is used, centralizing access to persistence 
mechanisms.

#### 6. **Controller Pattern**
- **Class Involved:** `AddFigureToCatalogueController`
- **Description:** Handles the orchestration of figure addition and category listing by coordinating the repositories 
and mappers involved.

#### 7. **Value Object Pattern**
- **Classes Involved:** `Code`, `Version`, `Description`, `Keyword`, `CategoryName`, `FigureStatus`, etc.
- **Description:** These immutable types encapsulate domain-specific logic and validation, contributing to a rich and 
expressive model.

---

These design patterns reinforce a clear separation of concerns and support long-term maintainability, testability, and 
reusability of the system. By leveraging the combination of **Repository + Factory + Mapper + DTO**, the solution 
ensures that the domain logic remains decoupled from infrastructure details and UI concerns.

### 4.3. Acceptance Tests

The following tests validate the acceptance criteria defined for **US233 – Add Figure to the Catalogue**. They ensure 
that figures are correctly classified, validated, and stored, and that access control and uniqueness constraints are 
properly enforced.

---

#### **Test 1: Figure must be classified with a valid category and keywords**
**Refers to Acceptance Criteria:** _US233.1_  
**Description:** Ensures that a figure cannot be added without a category or keywords.

```java
@Test
void ensureFigureIsClassifiedWithCategoryAndKeywords() {
    // setup: create a figure without category or keywords
    // action: attempt to add the figure to the catalogue
    // assert: expect InvalidFigureException to be thrown
}

```

---

#### **Test 2: Exclusive figure cannot be added to the public catalogue**
**Refers to Acceptance Criteria:** _US233.2_  
**Description:** Ensures that exclusive figures are only associated with the respective customer and not made public.

```java
@Test
void ensureExclusiveFiguresAreNotAddedToPublicCatalogue() {
    // setup: create an exclusive figure for a customer
    // action: add the figure to the catalogue
    // assert: public catalogue must not contain the exclusive figure
}

```

---

#### **Test 3: DSL description and version are stored**
**Refers to Acceptance Criteria:** _US233.3_  
**Description:** Verifies that when a figure is added, its DSL description and version are correctly saved.

```java
@Test
void ensureDSLDescriptionAndVersionAreStored() {
    // setup: create a figure with DSL description and version
    // action: add the figure to the catalogue
    // assert: verify DSL description and version are stored correctly
}

```

---

#### **Test 4: Only authenticated Show Designers can add figures**
**Refers to Acceptance Criteria:** _US233.4_  
**Description:** Ensures that only users with the Show Designer role can add figures.

```java
@Test
void ensureOnlyAuthenticatedShowDesignersCanAddFigures() {
    // setup: authenticate as a Show Designer
    // action: attempt to add a valid figure
    // assert: addition succeeds

    // setup: authenticate as unauthorized user
    // action: attempt to add a valid figure
    // assert: expect AccessDeniedException to be thrown
}

```

---

#### **Test 5: Figure name must be unique in the public catalogue**
**Refers to Acceptance Criteria:** _US233.5_  
**Description:** Ensures that figures with duplicate names (descriptions) are not allowed in the public catalogue.

```java
@Test
void ensureFigureNameIsUniqueInPublicCatalogue() {
    // setup: add a figure with a specific name to the public catalogue
    // action: attempt to add another figure with the same name
    // assert: expect DuplicateFigureException to be thrown
}


```

## 5. Implementation

*In this section the team should present, if necessary, some evidencies that the implementation is according to the
design. It should also describe and explain other important artifacts necessary to fully understand the implementation
like, for instance, configuration files.*

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this requirement.*


## 6. Integration/Demonstration

*In this section the team should describe the efforts realized in order to integrate this functionality with the other
parts/components of the system*

*It is also important to explain any scripts or instructions required to execute an demonstrate this functionality*


## 7. Observations

*This section should be used to include any content that does not fit any of the previous sections.*

*The team should present here, for instance, a critical prespective on the developed work including the analysis of
alternative solutioons or related works*

*The team should include in this section statements/references regarding third party works that were used in the
development this work.*