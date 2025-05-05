# US 246

## 1. Context

The objective of this task is to edit a figure category. This task is included in Sprint 2 and it's the first time it's being implemented.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implement: 📝 To Do

Test: 📝 To Do


## 2. Requirements

**As a** Show Designer,
<br>
**I want** to edit a existing figure category,
<br>
**So that** categories are correct and updated.

**Acceptance Criteria:**

- US246.1 The category name must be unique (not case-sensitive).
- US246.2 The functionality should only be accessible to authenticated Show Designer users.

**Dependencies/References:**

- **_US211 - Register users_**: This user story is a direct dependency. It is required to have a Show Designer registered in the system so he can edit a category.
- **_US245 - Add figure category to the catalogue_**: This user story is a direct dependency. It is required to have a figure category added to the catalogue so it can be edited.

## 3. Analysis

The core aggregate involved is `Category`, which includes the following value objects:

* `CategoryName`: Name by which the category is known.

* `CategoryDescription`: Describes the scope or purpose of the category.



## 4. Design

### 4.1. Realization

The class diagram depicts the high-level design used to implement category editing:

* UI delegates user input to the controller.
* the controller accesses the repository via the `PersistenceContext`, calling `editFigureCategory`.
* Repository implementations (JPA or in-memory) update the stored entity.
* Business validation (e.g., unique name enforcement or format constraints) can be validated within the repository.

![a class diagram](images/class-diagram-246.svg "A Class Diagram")


### 4.2. Acceptance Tests

**Test 1:** *Edit the category name and description*

**Refers to Acceptance Criteria:** US246.1

```
@Test
void ensureCategoryCanBeEdited() {
    // Setup: create and persist a category
    // Action: edit name and description, then persist, check if the name is unique
    // Assert: the updated category reflects the changes
}
````

## 5. Implementation

*In this section the team should present, if necessary, some evidencies that the implementation is according to the design. It should also describe and explain other important artifacts necessary to fully understand the implementation like, for instance, configuration files.*

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this requirement.*

## 6. Integration/Demonstration

*In this section the team should describe the efforts realized in order to integrate this functionality with the other parts/components of the system*

*It is also important to explain any scripts or instructions required to execute an demonstrate this functionality*

## 7. Observations

*This section should be used to include any content that does not fit any of the previous sections.*

*The team should present here, for instance, a critical prespective on the developed work including the analysis of alternative solutioons or related works*

*The team should include in this section statements/references regarding third party works that were used in the development this work.*