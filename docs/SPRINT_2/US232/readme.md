# US 232

## 1. Context

This user story is being developed as part of the Sprint 2. It introduces a search feature to the figure catalogue, 
allowing CRM Collaborators to filter figures based on categories and keywords.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implement: 📝 To Do

Test: 📝 To Do


## 2. Requirements

**As a** CRM Collaborator,
**I want** to search the figure catalogue by category and/or keyword,
**So that** it is easier to navigate through the catalogue.

**Acceptance Criteria:**

- **_US232.1_** The search should ignore accents and shouldn't be case-sensitive.
- **_US232.2_** The functionality must allow filtering by category only, keyword only, or both simultaneously.
- **_US232.3_** The results must include only active figures.

**Dependencies/References:**

- **_US233 – Add Figure to Catalogue_**: Figures must be present in the catalogue to be searchable.
- **_US234 - Decommission Figure_**: Inactive figures should not appear in the search results, even if they match the 
keyword or category.

**Client Clarification:**

> **[Topic: Versões de figuras](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35125)**
>
> Figures may have multiple versions. According to the client, this has no impact on the backend implementation and 
should only affect the UI, where different versions may appear when searching for a figure.

## 3. Analysis

The figure aggregate includes several attributes, but for this user story the most relevant ones are:

- `Code` and `Version`, to uniquely identify each figure in the results.
- `Description`, to display contextual information about the figure in the UI.
- `FigureStatus`, to ensure only active figures are searchable.
- `Keyword` and `Category`, which are the filtering criteria used in the search functionality.
- `Exclusivity` and `Customer`, to indicate that a figure is exclusive and to whom it is exclusive, when applicable.

Other attributes such as the DSL description, Show Designer, or validation services are not relevant in the context of 
this functionality and were omitted from the diagram for clarity.

![Domain Model for US232](images/domain_model_us232.svg)

## 4. Design

In this section, we describe the design approach adopted for implementing **US232**. The class diagram defines the main 
components involved in the search operation, showing a clear separation of concerns between the UI, application logic, 
domain model, and persistence infrastructure.

### 4.1. Realization

The class diagram below illustrates the realization of **US232  – Search Figure Catalogue**. The UI component invokes 
the controller, which delegates the operation to the `FigureRepository` through the configured `RepositoryFactory`. 
The repository implementation (JPA or in-memory) performs the actual data access, returning domain entities that are 
then mapped to DTOs via the `CatalogueMapper`.

The `FigureRepository` defines a single method, `searchCatalogue(category, keyword)`, that supports flexible search 
logic:
- If only one criterion is needed, the other is passed as `null`.
- If both are `null`, an exception is thrown.
- The search should ignore accents and shouldn’t be case-sensitive.

This approach enables reusability and simplifies the interface, while maintaining alignment with the user story's 
requirements.

The diagram also includes the relevant domain classes such as `Figure`, `Category`, `Keyword`, and their value objects, 
ensuring a rich and expressive domain model.

![Class Diagram US232](images/class_diagram_us232.svg)

### 4.2. Applied Patterns

The design of the implementation for **US232 – Search Figure Catalogue** applies several well-established design 
patterns, contributing to a modular, maintainable, and extensible architecture. Below are the key patterns identified 
in this solution:

#### 1. **DTO (Data Transfer Object) Pattern**
- **Class Involved:** `FigureDTO`
- **Description:** Encapsulates the data to be transferred from the domain layer to the UI. It abstracts the internal 
domain structure and prevents leakage of business logic to the presentation layer.

#### 2. **Mapper Pattern**
- **Class Involved:** `CatalogueMapper`
- **Description:** Converts `Figure` domain entities to `FigureDTO` objects. Centralizes transformation logic, 
facilitating reuse and reducing duplication between layers.

#### 3. **Repository Pattern**
- **Classes Involved:** `FigureRepository`, `JpaFigureRepository`, `InMemoryFigureRepository`
- **Description:** Abstracts the data access layer by exposing a clean interface for querying figures. Enables 
flexibility in how data is stored and retrieved.

#### 4. **Factory Pattern**
- **Classes Involved:** `RepositoryFactory`, `JpaRepositoryFactory`, `InMemoryRepositoryFactory`
- **Description:** Provides repository instances based on the chosen persistence strategy. Allows switching between 
in-memory and JPA implementations without changing the business logic.

#### 5. **Singleton Pattern**
- **Classes Involved:** `JpaRepositoryFactory`, `InMemoryRepositoryFactory`
- **Description:** Guarantees a single instance of each factory throughout the application. The `getInstance()` method 
ensures centralized access.

#### 6. **Controller Pattern**
- **Class Involved:** `SearchCatalogueController`
- **Description:** Coordinates the search use case by invoking the repository and converting the result into DTOs. Acts 
as the mediator between the UI and domain layers.

#### 7. **Value Object Pattern**
- **Classes Involved:** `Code`, `Version`, `Description`, `Keyword`, `CategoryName`, `FigureStatus`, etc.
- **Description:** These are immutable types that encapsulate value semantics and domain validation. They enhance 
expressiveness and enforce domain invariants.

---

These design patterns support a clean separation of concerns, promote testability, and provide a robust foundation for 
extending the system in future sprints. By applying the **Repository + Factory + DTO + Mapper** combination, the system 
remains decoupled, modular, and easy to maintain across multiple deployment contexts.

### 4.3. Acceptance Tests

The following tests validate the acceptance criteria defined for **US232 – Search Figure Catalogue**. They ensure that 
the search functionality behaves as expected when filtering by category and/or keyword, that it only returns active 
figures, and that case/accents are handled correctly.

---

#### **Test 1: Only active figures are returned**
**Refers to Acceptance Criteria:** _US232.3_  
**Description:** Ensures that inactive figures are not returned, even if they match the search criteria.

```java
@Test
void ensureOnlyActiveFiguresAreReturned() {
    // setup: add active and inactive figures to the repository
    // action: call controller.listSearchCatalogue() with matching keyword/category
    // assert: result only includes active figures
}
```

---

#### **Test 2: Search by keyword only**
**Refers to Acceptance Criteria:** _US232.2_  
**Description:** Validates that filtering by keyword alone returns only figures that match the keyword 
(case-insensitive and accent-insensitive).

```java
@Test
void ensureSearchByKeywordOnlyReturnsMatchingFigures() {
    // setup: create figures with various keywords (some matching, some not)
    // action: call controller.listSearchCatalogue(null, "fire")
    // assert: result only includes figures with keyword "fire", regardless of case or accents
}
```

---

#### **Test 3: Search by category only**
**Refers to Acceptance Criteria:** _US232.2_  
**Description:** Ensures that searching by category alone returns all figures within that category, and only active ones.

```java
@Test
void ensureSearchByCategoryOnlyReturnsMatchingFigures() {
    // setup: create figures assigned to specific categories
    // action: call controller.listSearchCatalogue(category, null)
    // assert: result only includes figures from the given category
}
```

---

#### **Test 4: Search by keyword and category combined**
**Refers to Acceptance Criteria:** _US232.2_  
**Description:** Ensures that when both filters are applied, only figures that match both the keyword and category are 
returned.

```java
@Test
void ensureSearchWithBothFiltersReturnsCorrectIntersection() {
    // setup: create figures matching category, keyword, both, and neither
    // action: call controller.listSearchCatalogue(category, "flames")
    // assert: result only includes figures matching both filters
}
```

---

#### **Test 5: Search is case-insensitive and accent-insensitive**
**Refers to Acceptance Criteria:** _US232.1_  
**Description:** Validates that the search works regardless of letter casing and accent marks.

```java
@Test
void ensureSearchIgnoresCaseAndAccents() {
    // setup: add a figure with keyword "Fénix"
    // action: call controller.listSearchCatalogue(null, "fenix")
    // assert: result includes the figure with "Fénix"
}
```

---

#### **Test 6: Exception thrown when both filters are null**
**Covers repository safeguard for invalid usage**  
**Description:** Ensures the method throws an exception when called without any search filters.

```java
@Test
void ensureExceptionIsThrownWhenBothFiltersAreNull() {
    assertThrows(IllegalArgumentException.class, () -> {
        controller.listSearchCatalogue(null, null);
    });
}
```

---

#### **Test 7: Returned results are DTOs**
**Ensures proper use of data transfer and encapsulation**  
**Description:** Confirms that the controller only returns `FigureDTO` objects, not domain entities.

```java
@Test
void ensureReturnedResultsAreDTOs() {
    List<?> result = controller.listSearchCatalogue(someCategory, "example");
    for (Object dto : result) {
        assertTrue(dto instanceof FigureDTO, "Element is not an instance of FigureDTO");
    }
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