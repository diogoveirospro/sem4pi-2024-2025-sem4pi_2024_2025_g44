# US 233

## 1. Context

This user story is being developed as part of Sprint 2. It introduces the functionality to add a new figure to the 
public catalogue, including its associated category and keywords. If the figure is marked as exclusive, it will not be 
public and can only be used in shows for the customer holding the exclusivity rights.

### 1.1 List of issues

Analysis: ✅ Done

Design: ✅ Done

Implement: ✅ Done

Test: ✅ Done


## 2. Requirements

**As a** Show Designer,
<br>
**I want** to add a figure to the public catalogue,
<br>
**So that** no figures are missing.

**Acceptance Criteria:**

- **US233.1** A figure must include the following parameters: code, version, description, DSL description, keywords, 
categories and showDesigner.

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

The `Figure` aggregate includes multiple domain attributes, but only a subset is relevant to this user story, which 
focuses on adding a new figure to the catalogue.

- `Code` and `Version` – together, they uniquely identify each figure in the system.
- `Description` – provides a human-readable label to identify the figure in the catalogue.
- `DSLDescription` – contains the DSL code that defines the figure’s visual representation.
- `Keyword` – a set of labels that help describe the figure and may support future search or filtering functionalities.
- `Category` – represents the classification to which the figure belongs and is selected from a predefined catalogue.
- `Exclusivity` – indicates whether the figure is exclusive to a specific customer; if present, the figure should not 
be listed publicly.
- `ShowDesigner` – the user who created the figure, stored for traceability and attribution.

Other elements not essential to this functionality have been omitted from the diagram to maintain clarity and focus.

![Domain Model for US233](images/domain_model_us233.svg)

## 4. Design

This section presents the design adopted for implementing **US233 – Add Figure to the Catalogue**.
The class diagram highlights the main components involved in the process, demonstrating a clear separation of concerns 
between the user interface (UI), application logic (Controller), domain model (Domain), and persistence infrastructure 
(Persistence).

### 4.1 Realisation

The class diagram below illustrates the realisation of **US233 – Add Figure to the Catalogue**.
The user interface (`AddFigureToCatalogueUI`) initiates the process by requesting all necessary data from the user. 
It reads the DSL code from a `.txt` file line by line, collects the remaining figure attributes, and invokes one of the 
available controller methods—`addPublicFigureToCatalogue(...)` or `addExclusiveFigureToCatalogue(...)`— depending on 
whether the figure is public or exclusive.

The `AddFigureToCatalogueController` is responsible for orchestrating the creation and persistence of the 
`Figure` entity.
It retrieves the required repositories through the `PersistenceContext`, which provides the appropriate 
`RepositoryFactory` implementation (either JPA or In-Memory). With the collected data, the controller instantiates the 
new `Figure` and persists it by calling `save(figure)` on the `FigureRepository`.

Before creating the figure, the UI allows the user to select one or more `Category` objects via the 
`showCategoriesAndSelect()` method. If the figure is exclusive, the `showCustomersAndSelect()` method is also invoked 
to associate a `Customer` with an `Exclusivity` object. These methods interact with the controller, which exposes the 
necessary data through `listCategories()` and `listCustomers()`.

The persistence layer follows the Abstract Factory pattern. The `RepositoryFactory` interface has multiple concrete 
implementations (JPA and In-Memory), enabling flexibility in the underlying persistence strategy. Each repository 
interface (e.g., `FigureRepository`, `CategoryRepository`, `CustomerRepository`) has corresponding implementations 
compatible with the selected storage backend.

![Class Diagram for US233](images/class_diagram_us233.svg)

### 4.2. Acceptance Tests

The following tests validate the acceptance criteria defined for **US233 – Add Figure to the Catalogue**. They ensure 
that figures are correctly structured and validated, that exclusivity is respected, that access control is enforced, 
and that authorship is properly recorded.

---

#### **Test 1: Figure must contain all required parameters**
**Refers to Acceptance Criteria:** _US233.1_  
**Description:** Ensures that a figure cannot be added if any of the mandatory attributes (code, version, description, 
DSL description, keywords or category) are missing.

```java
@Test
void ensureFigureIncludesAllRequiredParameters() {
    // setup: create figures with missing attributes
    // action: attempt to add the figure to the catalogue
    // assert: expect AssertionError to be thrown
}
```

## 5. Implementation

The implementation of **US233** involved the creation of all necessary components to support the addition of both 
public and exclusive figures to the catalogue. The `Figure` aggregate was extended with two constructors to differentiate 
between public and exclusive figures.

The DSL description and keywords were handled as value objects, while `Exclusivity` was introduced as a domain entity 
associated with a `Customer` and a `DateInterval`. The `AddFigureToCatalogueController` was responsible for orchestrating 
the process, and its methods were exposed through a dedicated UI.

All persistence-related configurations were updated to support the creation of figures with their respective relationships. 
Unit tests were created to validate the creation of both figure types.

Relevant commit messages:

- [Correction of method names and the variable name of figure categories in the design of some USs](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/64c740bfbfb2f21b63ab700395b1d24eeac5160d)
- [Correction of the Value Object Description of the Maintenance, Category, Figure and ShowRequest classes in the domain model, as well as the Name of the Category. Correction of the domain and class models of USs 231, 232, 233, 234.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/05f67a8c27becdce7b5aedaa69c97ff3021cf5e0)
- [Addition of the Value Object DateInterval for the link with Exclusivity.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/6db2f1f0c4b02cf515bce84e0b437c6c3a39d81b)
- [Start of implementation of US233](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/c652d3ded025f84fa4968f718b89b4bc01a963b5)
- [Start of implementation of US233](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/fb9252f7e50da05dfc85e1f217841a8b732e9c60)
- [Addition of the possibility of choosing the customer when creating the figure if it is exclusive.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/eabaef6074a1b6f26d2d22fab5fdd6804c9be245)
- [Small fixes to the add figure us](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/ddde1124c959859b119086f059abfa0931c6553a)
- [Possible completion of the US233 implementation, already properly tested with JUnit.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/dd35b6925f0881e685c05899091affb37b9058e5)
- [Minor corrections to designs, tests and UIs](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/5b3ac69021584fc281c3a9f8fa07c818765e5ef2)
- [Addition of the ShowDesigner save in the AddFigureToCatalogueUI, still in commentary because the Authentication implementation is missing.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/d6e1fdca0be33359efb961f7f2c805a782d1c811)

## 6. Integration/Demonstration

The functionality implemented in **US233** was successfully integrated into the system, enabling **Show Designers** to 
add new figures to the catalogue, with optional exclusivity for a specific customer.

The `AddFigureToCatalogueUI` provides an interactive interface to collect all required data, including DSL code, 
description, keywords, and associated categories. For exclusive figures, the UI also allows selection of the customer 
who will hold the exclusivity rights.

The `AddFigureToCatalogueController` orchestrates the process, handling domain validation and delegating persistence to 
the `FigureRepository`, which is dynamically obtained via the `PersistenceContext`. Both JPA and in-memory implementations 
of the repository are supported, ensuring compatibility across production and testing environments.

### Demonstration Instructions

To demonstrate the functionality, follow these steps:

1. **Launch the application** (either via the provided script, as explained in the [readme.md](readme.md) file).
2. **Log in as a Show Designer**.
3. Navigate to the **Figures** section.
4. Choose **Add Figure to the Catalogue**.
5. Provide the required data (code, version, description, DSL code, etc.).
6. Select one or more categories from the list.
7. (Optional) Indicate the customer for exclusivity.
8. Upon confirmation, the figure will be persisted and available for use in show proposals.

## 7. Observations

For the implementation of this project, I used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools developed by our department (ISEP).
- **eCafeteria Project**: A reference project developed by our department, used as a source of inspiration for similar
  functionalities and a guide for best practices.
- **JPA (Hibernate)**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.