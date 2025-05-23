# US 234

## 1. Context

This user story is being developed as part of Sprint 2. It introduces the functionality to decommission a figure from 
the catalogue. Once a figure is decommissioned, it becomes inactive and can no longer be used in new shows or appear in 
catalogue search results. This allows CRM Managers to control and retire figures that are no longer relevant or approved 
for use.

### 1.1 List of issues

Analysis: ✅ Done

Design: ✅ Done

Implement: ✅ Done

Test: ✅ Done


## 2. Requirements

**As a** CRM Manager,
<br>
**I want** to decommission a figure from the catalogue,
<br>
**So that** it will not be used anymore.

**Acceptance Criteria:**

- **_US234.1_** Only active figures can be decommissioned
- **_US234.2_** Decommissioning a figure sets its status to disable

**Dependencies/References:**

- **_US233 – Add Figure to the catalogue_**: Figures are needed for these to be decommissioned.

**Client Clarifications:**

> **[Topic: Descomissionamento de uma figura](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35559)** 
> 
> The client clarified that only a specific **version** of a figure is to be decommissioned. Versions are what truly 
matter in proposals, while the figure entity itself serves only as a cataloguing structure.

## 3. Analysis

The figure aggregate includes multiple attributes, but for this user story the most relevant one is the `FigureStatus`. 
This value object represents the current state of the figure — for instance, whether it is **active** or **disable**.

Decommissioning a figure involves changing its status to disable. Once disable, the figure should be excluded from all 
search results, listings, and proposals. Since, according to the client, each **version** of a figure is treated 
independently, the decommissioning operation applies only to the specific version being handled, not to all versions of 
a figure.

Other attributes such as `Code`, `Description`, `Keywords`, or `Exclusivity` are not directly impacted by this 
functionality and were omitted from the diagram for clarity.

![Domain Model for US234](images/domain_model_us234.svg)

## 4. Design

In this section, we describe the design approach adopted for implementing **US234 – Decommission Figure**. The class 
diagram defines the main components involved in the decommissioning operation, illustrating a clear separation of 
concerns between the UI, application logic, domain model, and persistence infrastructure.

### 4.1. Realization

The class diagram below illustrates the realization of **US234 – Decommission Figure**. The `DecommissionFigureUI` 
component allows a CRM Manager to select a figure from the catalogue and initiate the decommissioning process via the 
`decommissionFigure(figure)` method. This action is forwarded to the `DecommissionFigureController`, which coordinates 
the use case.

The controller retrieves the configured `FigureRepository` through the `RepositoryFactory`, made available via the 
`PersistenceContext`. It then delegates the operation by calling the domain method `decommission()` on the selected 
`Figure`.

The domain logic is encapsulated in the `Figure` aggregate, which includes the method `decommission()` responsible for 
changing the `FigureStatus` to a disable state. This ensures that the deactivation logic is kept within the domain 
layer, enforcing the necessary business rules.

The `FigureRepository` interface provides access to the current catalogue of figures through the method 
`findAll()`, which supports listing and selection. Two repository implementations are supported: `JpaFigureRepository` 
and `InMemoryFigureRepository`, both of which conform to the `FigureRepository` interface and are instantiated via their 
respective factories.

This design ensures that the decommissioning use case remains modular, testable, and consistent with the overall 
architecture of the system.

![Class Diagram US234](images/class_diagram_us234.svg)

### 4.2. Acceptance Tests

The following tests validate the acceptance criteria defined for **US234 – Decommission Figure**. They ensure that only 
active figures can be decommissioned, that decommissioned figures become inactive and are excluded from listings, and 
that the operation is restricted to authorized users.

---

#### **Test 1: Only active figures can be decommissioned**
**Refers to Acceptance Criteria:** _US234.1_  
**Description:** Ensures that the system does not allow a figure to be decommissioned more than once.

```java
@Test
void ensureOnlyActiveFiguresCanBeDecommissioned() {
    // setup: create an active figure and decommission it
    // action: attempt to decommission the same figure again
    // assert: expect IllegalStateException to be thrown
}

```

---

#### **Test 2: Decommissioning sets figure as disable**
**Refers to Acceptance Criteria:** _US234.2_  
**Description:** Verifies that the status of a figure is updated to disable after decommissioning.

```java
@Test
void ensureDecommissionedFigureIsMarkedAsDisable() {
    // setup: create an active figure
    // action: decommission the figure
    // assert: verify that the figure status is set to DISABLE
}

```

## 5. Implementation

The implementation of **US234** required the extension of the `Figure` aggregate with the domain method `decommission()`, 
which changes the `FigureStatus` to `DISABLE`. This ensures that figures are excluded from future listings and searches 
while preserving their historical data.

A new UI (`DecommissionFigureUI`) and controller (`DecommissionFigureController`) were created to allow CRM Managers to 
perform this operation. The controller interacts with the repository to retrieve available figures and delegates the 
business logic to the domain model.

The `FigureRepository` continues to provide access to all figures, but only those with an `ACTIVE` status are allowed to
be decommissioned. Proper validation and exception handling are enforced to prevent re-decommissioning.

Relevant commit messages:

- [Correction of method names and the variable name of figure categories in the design of some USs](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/64c740bfbfb2f21b63ab700395b1d24eeac5160d)
- [Correction of the Value Object Description of the Maintenance, Category, Figure and ShowRequest classes in the domain model, as well as the Name of the Category. Correction of the domain and class models of USs 231, 232, 233, 234.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/05f67a8c27becdce7b5aedaa69c97ff3021cf5e0)
- [Addition of the Value Object DateInterval for the link with Exclusivity.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/6db2f1f0c4b02cf515bce84e0b437c6c3a39d81b)
- [Possible completion of the US234 implementation, already properly tested with JUnit.](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/dd2e75092222e8d5f18a05350355902a6726e555)

## 6. Integration/Demonstration

The implementation of **US234 – Decommission Figure** was integrated seamlessly into the existing figure catalogue 
module. This functionality enables **CRM Managers** to manage the lifecycle of figures by deactivating those that are 
no longer in use or approved.

The user interacts with the `DecommissionFigureUI`, which allows selecting an existing figure from the catalogue. Once 
a figure is selected, the UI delegates the action to the `DecommissionFigureController`, which retrieves the correct 
`FigureRepository` via the `PersistenceContext` and invokes the domain method `decommission()` on the selected figure.

This operation modifies the `FigureStatus` to `DISABLE`, making the figure effectively inactive. The system consistently 
enforces this status throughout the application:

* Inactive figures are excluded from the **search results** (as defined in **US232**).
* Inactive figures are not eligible for use in any new functionality (e.g., show planning).
* Attempting to decommission an already disabled figure results in a controlled exception (`IllegalStateException`), 
ensuring domain consistency.

### Demonstration Instructions

To demonstrate this functionality:

1. **Launch the application** (either via the provided script, as explained in the [readme.md](readme.md) file).
2. **Log in as a CRM Manager**.
3. Navigate to the **Figures** section.
4. Navigate to the **Decommission Figure** option.
5. Select a figure from the list of active figures.
6. Verify:
    * The figure no longer appears in active catalogue listings or search.
    * Attempting to decommission the same figure again is not allowed.
    * The figure’s status is correctly updated to `DISABLE` in the persistence layer.

This use case depends on the successful completion of **US233 – Add Figure to Catalogue**, as only existing (and active) figures can be decommissioned.

## 7. Observations

For the implementation of this project, I used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools developed by our department (ISEP).
- **eCafeteria Project**: A reference project developed by our department, used as a source of inspiration for similar
  functionalities and a guide for best practices.
- **JPA (Hibernate)**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.
