# US 247

## 1. Context

The objective of this task is to allow the listing of all figure categories stored in the system. 
This user story is included in Sprint 2 and represents the first implementation of the category listing functionality.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implement: 📝 To Do

Test: 📝 To Do

## 2. Requirements

**As a** Show Designer or CRM Collaborator,
<br>
**I want** to list all figure categories in the catalogue,
<br>
**So that** the user can see the categories available in the system.

**Acceptance Criteria:**

- US247.1. The category status information should be provided.
- US247.2. The functionality should only be accessible to authenticated Show Designer or CRM Collaborator users.

**Dependencies/References:**

- **_US211 - Register users_**: This user story is a direct dependency. It is required to have a Show Designer registered in the system, so he can edit a category.

- **_ US245 - Add figure category to the catalogue_**: This user story is a direct dependency. It is required to have a figure category added to the catalogue, so it can be listed.

## 3. Analysis

The Category aggregate encapsulates all figure category data. It comprises the following value objects:

* `CategoryName`: The name by which the category is known.
* `CategoryDescription`: Describes the scope or purpose of the category.
* `CategoryStatus`: Represents the current status of a category, either active or inactive.

## 4. Design

This section describes the class structure used to implement **US247**. It illustrates how the UI, controller, domain model, and persistence layers interact.

### 4.1. Realization

The class diagram below depicts the core design for retrieving figure categories:
* The UI (`ListFigureCategoryUI`) calls the controller to request a category listing.
* The controller (`ListFigureCategoryController`) accesses the repository factory via the `PersistenceContext`.
* The factory returns the configured `CategoryRepository` instance, which retrieves the list of categories.
* The controller then returns the list to the UI for display.

![a class diagram](images/class-diagram-247.svg "A Class Diagram")

### 4.2. Acceptance Tests

**Test 1:** *Verifies that the category status is provided in the output*

**Refers to Acceptance Criteria:** US247.1

**Description**: Ensures that each listed category includes its current status.
```
@Test
void ensureCategoryStatusIsIncludedInList() {
    // Setup: create categories with varying statuses (ACTIVE, INACTIVE)
    // Action: invoke controller.getCategoryList()
    // Assert: each category in the result contains a non-null status field
    //         and the status is either ACTIVE or INACTIVE
}
````

**Test 2:** *Verifies that the category status is provided in the output*

**Refers to Acceptance Criteria:** US247.2

**Description**: Ensures that only a Show Designer or CRM Collaborator can access the functionality.
```
@Test
void ensureOnlyAuthorizedUsersCanListCategories() {
    // Setup: authenticate as a user without the Show Designer or CRM Collaborator role
    // Action: attempt to invoke controller.getCategoryList()
    // Assert: an AccessDeniedException (or equivalent) is thrown
}

@Test
void ensureShowDesignerCanAccessCategoryList() {
    // Setup: authenticate as a Show Designer
    // Action: invoke controller.getCategoryList()
    // Assert: category list is returned successfully
}

@Test
void ensureCrmCollaboratorCanAccessCategoryList() {
    // Setup: authenticate as a CRM Collaborator
    // Action: invoke controller.getCategoryList()
    // Assert: category list is returned successfully
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