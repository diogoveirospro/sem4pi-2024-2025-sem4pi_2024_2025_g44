# US 231

## 1. Context

The purpose of this task is to provide a list of all active public figures, enabling the appropriate selection during 
the design of show request process. This task is included in Sprint 2 and is being implemented for the first time.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implement: 📝 To Do

Test: 📝 To Do


## 2. Requirements

**As a** CRM Collaborator, 
<br>
**I want** to list all active public figures in the catalogue, 
<br>
**So that** I can select them during the design of a show request proposal.

**Acceptance Criteria:**

- **_US231.1_** Only active public figures must be listed.
- **_US231.2_** The list must include at least the figure's code, version and description.
- **_US231.3_** The functionality should only be accessible to authenticated CRM Collaborator users.

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