# US 231

## 1. Context

The purpose of this task is to provide a list of all active public figures, enabling the appropriate selection during 
the design of show request process. This task is included in Sprint 2 and is being implemented for the first time.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implement: 🧪 Testing

Test: 🧪 Testing


## 2. Requirements

**As a** CRM Collaborator, 
<br>
**I want** to list all active public figures in the catalogue, 
<br>
**So that** I can select them during the design of a show request proposal.

**Acceptance Criteria:**

- **_US231.1_** Only active public figures must be listed.

**Dependencies/References:**

- **_US233 – Add Figure to the Catalogue_**: This user story is a direct dependency. It is required to have figures 
added to the catalogue before listing them.

## 3. Analysis

The `Figure` aggregate includes multiple domain attributes, but only a subset is relevant for this user story, which 
focuses on listing public figures. The attributes considered are:

- `Code` and `Version` – used to uniquely identify and distinguish each figure.
- `Description` – provides a human-readable label for display in the user interface.
- `FigureStatus` – used to filter out inactive figures; only active ones should be listed.
- `Exclusivity` – determines whether a figure is exclusive to a customer and therefore must be excluded from the public 
listing.

Other attributes such as the `Keyword`, `Category`, `DSLDescription` or `ShowDesigner` reference are not required for 
this use case and were excluded from the diagram for clarity.

![Domain Model for US231](images/domain_model_us231.svg)

## 4. Design

This section outlines the design adopted for implementing **US231**. The class diagram presents the essential components 
involved in listing public figures, with a clear separation between the user interface, application logic, domain model, 
and persistence layer.

### 4.1 Realisation

The class diagram below represents the realisation of **US231 — Figure Catalogue**. It shows how the UI interacts with 
the controller, which accesses the configured repository (either JPA or in-memory) via the `PersistenceContext`. 
The repository returns domain entities representing public figures.

Filtering for public and active figures is encapsulated within the `Figure` entity, through the `isPublic()` and 
`isActive()` methods. This ensures that business logic remains within the domain layer.

Only the relevant domain elements are included in the diagram, such as `Figure`, its value objects 
(i.e., `Code`, `Version`, `Description`). The diagram omits unrelated components to maintain clarity and focus on the 
functionality.

![Class Diagram US231](images/class_diagram_us231.svg)

### 4.2. Acceptance Tests

The following tests were designed to validate the acceptance criteria defined for **US231**. These focus on ensuring 
that only valid public figures are listed, that the necessary data is correctly returned to the UI, and that access is 
appropriately restricted to authorised users.

---

#### **Test 1: Only active public figures are listed**  
**Refers to Acceptance Criteria:** _US231.1_  
**Description:** Ensures that figures marked as inactive or exclusive are excluded from the results returned by 
`listPublicCatalogue()`.

```java
@Test
public void testIsExclusive(){
    // Setup: create an exclusive figure
    // Action: invoke figure.isExclusive()
    // Assert: verify the result is true
}
```

---

#### **Test 2: Only active public figures are listed**
**Refers to Acceptance Criteria:** _US231.1_  
**Description:** Ensures that figures marked as inactive or exclusive are excluded from the results returned by
`listPublicCatalogue()`.

```java
@Test
public void testIsActive(){
    // Setup: create an active figure
    // Action: invoke figure.isActive()
    // Assert: verify the result is true
}
```

## 5. Implementation

The implementation of **US231** is based on the design and analysis presented in the previous sections. The code is
organized into packages that reflect the domain model, application logic, and user interface layers.

All necessary classes and methods were implemented to support the functionality of listing all active public figures
in the catalogue. The implementation follows the architectural and coding standards defined for the project, without 
deviations.

Below are the relevant commit messages associated with this user story:

- [Correction of method names and the variable name of figure categories in the design of some USs](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/64c740bfbfb2f21b63ab700395b1d24eeac5160d)
- [Correction of the Value Object Description of the Maintenance, Category, Figure and ShowRequest classes in the domain model, as well as the Name of the Category. Correction of the domain and class models of USs 231, 232, 233, 234.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/05f67a8c27becdce7b5aedaa69c97ff3021cf5e0)
- [Addition of the Value Object DateInterval for the link with Exclusivity.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/6db2f1f0c4b02cf515bce84e0b437c6c3a39d81b)
- [Possible completion of the US231 implementation, already properly tested with JUnit.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/de5b8479681f78271c39379beb40d29b28301d90)

## 6. Integration/Demonstration

The functionality developed in **US231** was seamlessly integrated into the existing system, specifically within the
**Show Request** workflow where CRM Collaborators are required to select public figures. The `listPublicCatalogue()`
method was exposed through the UI layer and connected to the application service responsible for retrieving active
and non-exclusive public figures from the repository.

To ensure compatibility with both JPA and in-memory repositories (used for testing purposes), the `FigureRepository`
interface was properly abstracted, and the corresponding implementations were registered in the `PersistenceContext`.

### Demonstration Instructions

To demonstrate the functionality, follow these steps:

1. **Launch the application** (either via the provided script, as explained in the [readme.md](readme.md) file).
2. **Log in as a CRM Collaborator**.
3. Navigate to the **Figures** section.
4. Choose the **List Public Catalogue** option.
5. A list should appear containing only **active and non-exclusive** public figures.
6. Public figures marked as `inactive` or `exclusive` will be **excluded** from the list.

## 7. Observations

For the implementation of this project, I used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools developed by our department (ISEP).
- **eCafeteria Project**: A reference project developed by our department, used as a source of inspiration for similar 
functionalities and a guide for best practices.
- **JPA (Hibernate)**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.