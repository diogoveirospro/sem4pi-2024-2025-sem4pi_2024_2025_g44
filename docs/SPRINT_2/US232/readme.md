# US 232

## 1. Context

This user story is being developed as part of the Sprint 2. It introduces a search feature to the figure catalogue, 
allowing CRM Collaborators to filter figures based on categories and keywords.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implement: 🧪 Testing

Test: 🧪 Testing


## 2. Requirements

**As a** CRM Collaborator,
<br>
**I want** to search the figure catalogue by category and/or keyword,
<br>
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

> **[Topic: US232](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35804)**
>
> The client confirmed that both public and exclusive figures may be shown in the search results. However, exclusive 
figures must be clearly marked to indicate their restricted usage.

## 3. Analysis

The `Figure` aggregate contains several domain attributes, but for this user story the most relevant ones are:

- `Code` and `Version` – uniquely identify each figure in the search results.
- `Description` – provides meaningful information to display in the user interface.
- `FigureStatus` – ensures that only active figures are included in the search.
- `Keyword` and `Category` – serve as filtering criteria in the search functionality.
- `Exclusivity` – indicates whether a figure is exclusive; if so, it references a `Customer` entity.
- `Customer` and its `Name` – allow exclusive figures to be clearly marked and associated with the correct client when 
shown in the results.

Attributes such as the DSL description or figure author are not relevant to this functionality and were intentionally 
omitted from the diagram for simplicity.

![Domain Model for US232](images/domain_model_us232.svg)

## 4. Design

In this section, we describe the design approach adopted for implementing **US232 – Search Figure Catalogue**. The 
class diagram defines the main components involved in the search operation, showcasing a clear separation of concerns 
between the UI, application logic, domain model, and persistence infrastructure.

### 4.1. Realization

The class diagram below illustrates the realization of **US232 – Search Figure Catalogue**. The `SearchCatalogueUI` 
component allows the user to initiate a search by providing a category and/or keyword. This request is forwarded to 
the `SearchCatalogueController`, which orchestrates the use case.

The controller retrieves the appropriate `FigureRepository` from the configured `RepositoryFactory` (resolved via the 
`PersistenceContext`) and delegates the operation using the method `searchCatalogue(category, keyword)`. This method 
accepts both parameters as optional:
- If both category and keyword are provided, the results are filtered by both.
- If only one is provided, the results are filtered accordingly.
- If a parameter should be ignored, `null` is passed for that argument.

The actual filtering logic is encapsulated in the domain model, particularly within the `Figure` class, which includes 
methods such as `matchesCategory(category)` and `matchesKeyword(term)` to support expressive and reusable criteria-based 
filtering.

The persistence layer supports two interchangeable implementations—JPA and in-memory—through the use of 
`JpaFigureRepository` and `InMemoryFigureRepository`, respectively. These implementations conform to the 
`FigureRepository` interface and are instantiated through their respective factories.

The model also includes domain concepts like `Category`, `Keyword`, `Exclusivity`, and `Customer`, as well as several 
value objects such as `Code`, `Version`, and `FigureStatus`, ensuring that the domain logic is expressive, consistent, 
and encapsulated.

![Class Diagram US232](images/class_diagram_us232.svg)

### 4.2. Acceptance Tests

The following tests validate the acceptance criteria defined for **US232 – Search Figure Catalogue**. They ensure that 
the search functionality supports flexible filtering (by category and/or keyword), returns only active figures, and 
handles input in a case-insensitive and accent-insensitive manner.

---

#### **Test 1: Only active figures are returned**
**Refers to Acceptance Criteria:** _US232.3_  
**Description:** Ensures that inactive figures are excluded from the search results, even if they match the keyword or 
category.

```java
@Test
void ensureOnlyActiveFiguresAreReturned() {
    // setup: create two figures, one active and one inactive
    // action: call matchesCategory(category) and matchesKeyword(term)
    // assert: only active figure are true
}
```

---

#### **Test 2: Search by keyword only**
**Refers to Acceptance Criteria:** _US232.2_  
**Description:** Verifies that the search works correctly when filtering only by keyword.

```java
@Test
void ensureSearchByKeywordOnlyReturnsMatchingFigures() {
    // setup: create figure with the keyword "fire" and another with "water"
    // action: call matchesKeyword("fire") for both figures
    // assert: return true for the first and false for the second
}
```

---

#### **Test 3: Search by category only**
**Refers to Acceptance Criteria:** _US232.2_  
**Description:** Verifies that the search works when filtering only by category.

```java
@Test
void ensureSearchByCategoryOnlyReturnsMatchingFigures() {
    // setup: create figure with the category "mythology" and another with "nature"
    // action: call matchesCategory("mythology") for both figures
    // assert: return true for the first and false for the second
}
```

---

#### **Test 4: Search is case-insensitive and accent-insensitive**
**Refers to Acceptance Criteria:** _US232.1_  
**Description:** Ensures that keywords with different casing or accents still produce a match.

```java
@Test
void ensureSearchIgnoresCaseAndAccents() {
    // setup: create figure with the keyword "Fénix" and another with "Phoenix"
    // action: call matchesKeyword("fenix") and matchesKeyword("phoenix")
    // assert: return true for both
}
```

## 5. Implementation

The implementation of **US232** followed the previously defined design and analysis. The code is structured across the 
domain, application, and presentation layers, respecting the modular and layered architecture of the system.

Filtering logic based on `Category` and `Keyword` was encapsulated within the `Figure` aggregate through methods such as
`matchesCategory(...)` and `matchesKeyword(...)`. These methods were designed to be case-insensitive and 
accent-insensitive, using string normalization to improve the robustness of the search feature.

The `FigureRepository` interface was extended with the `searchCatalogue(...)` method, and its corresponding 
implementations (JPA and In-Memory) support filtering based on the provided parameters. The controller and UI were 
updated to enable optional filtering by category and/or keyword, in line with client expectations.

Relevant commit messages:

- [Correction of method names and the variable name of figure categories in the design of some USs](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/64c740bfbfb2f21b63ab700395b1d24eeac5160d)
- [Correction of the Value Object Description of the Maintenance, Category, Figure and ShowRequest classes in the domain model, as well as the Name of the Category. Correction of the domain and class models of USs 231, 232, 233, 234.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/05f67a8c27becdce7b5aedaa69c97ff3021cf5e0)
- [Addition of the Value Object DateInterval for the link with Exclusivity.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/6db2f1f0c4b02cf515bce84e0b437c6c3a39d81b)
- [Possible completion of the US232 implementation, already properly tested with JUnit.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/146acc67926093101a53b63a503c3496c421ccc2)

## 6. Integration/Demonstration

The functionality developed in **US232** was successfully integrated into the overall application, enabling 
**CRM Collaborators** to search the figure catalogue using flexible criteria such as **category** and **keyword**.

The `SearchCatalogueUI` provides an intuitive interface that prompts users to input a category, a keyword, or both. 
The UI forwards the request to the `SearchCatalogueController`, which coordinates the filtering logic using the `FigureRepository`.

The controller delegates the actual search operation to the repository via `searchCatalogue(category, keyword)`, 
which returns a list of active figures that match the criteria. Exclusive figures are included in the results, but are 
clearly identified in the UI, as clarified by the client.

This feature integrates seamlessly with the existing catalogue, which is populated through **US233 – Add Figure to Catalogue**. 
It also respects constraints introduced by **US234 – Decommission Figure**, ensuring that only active figures are 
included in the results.

### Demonstration Instructions

To test this functionality:

1. **Launch the application** (either via the provided script, as explained in the [readme.md](readme.md) file).
2. **Log in as a CRM Collaborator**.
3. Navigate to the **Figures** section.
4. Navigate to the **Search Figures in the Catalogue** option under the Figures section.
5. Enter a category name and/or keyword (case and accents do not matter).
6. View the results, which:
    * Only include **active** figures.
    * Display **exclusive figures** with a clear indicator and their associated customer.
7. Try different combinations:
    * Only category
    * Only keyword
    * Both

## 7. Observations

For the implementation of this project, I used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools developed by our department (ISEP).
- **eCafeteria Project**: A reference project developed by our department, used as a source of inspiration for similar
  functionalities and a guide for best practices.
- **JPA (Hibernate)**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.