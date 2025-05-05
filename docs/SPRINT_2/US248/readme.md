# US 248

## 1. Context

The goal of this task is to allow authorized users to change the status of a figure category.
This task is included in Sprint 2 and represents the first implementation of the category status change functionality.

### 1.1 List of issues

Analysis: 🧪 Testing

Design: 🧪 Testing

Implement: 📝 To Do

Test: 📝 To Do


## 2. Requirements


*Example*

**As a** Show Designer 
**I Want** to change the status of an existing figure category in the figure category catalogue.
**So that** the status of the category is correct and updated.

**Acceptance Criteria:**

- US248.1 Inactive categories cannot be used in new figures.
- US248.2 The functionality should only be accessible to authenticated Show Designer users.

**Dependencies/References:**

- **_US211 - Register users_**: This user story is a direct dependency. It is required to have a Show Designer registered in the system, so he can change the status of a category.
- **_US245 - Add figure category to the catalogue_**: This user story is a direct dependency. It is required to have a figure category added to the catalogue, so it can be changed.

## 3. Analysis

The `Category` aggregate contains a `CategoryStatus value object which reflects whether a category is active or disabled.
Changing the category's status is a domain-level operation and must preserve business invariants (e.g., a disabled category should not be used to tag new figures).
Relevant components include:
- `Category`: The aggregate root representing a figure category.
- `CategoryStatus`: A value object representing the status of a category (active or disabled).
- `CategoryRepository`: The repository interface for accessing and modifying category data.

## 4. Design

The class diagram illustrates how the change of category status is supported across the layers of the system:

* The UI layer (`EditFigureCategoryUI`) interacts with the controller to initiate the change.

* The Controller (`EditFigureCategoryController`) queries the repository factory and invokes the domain-level change operation.

* The Domain layer performs the change, enforcing validations.

* Both JPA and in-memory persistence implementations are supported for flexibility and testing.


### 4.1. Realization

The class diagram below depicts the core design for changing the status of a figure category:
* The UI (`EditFigureCategoryUI`) calls the controller to request a status change.
* The controller (`EditFigureCategoryController`) accesses the repository factory via the `PersistenceContext`.
* The factory returns the configured `CategoryRepository` instance, which performs the status change.
* The controller then returns the result to the UI for display.

![a class diagram](images/class-diagram-248.svg "A Class Diagram")

### 4.2. Acceptance Tests

**Test 1:** *Validate that inactive categories cannot be used in new figures*

**Refers to Acceptance Criteria:** US248.1

**Description:** Ensures that when a category is marked as inactive, it cannot be used in the creation of new figures.

```
@Test
void ensureInactiveCategoriesCannotBeUsedInNewFigures() {
    // Setup: create and persist a category, then change its status to INACTIVE
    // Action: attempt to create a new figure with the inactive category
    // Assert: the system should throw an exception or return an error indicating the category is inactive
}
````

**Test 2:** *Validate that only authenticated Show Designer users can change the status*

**Refers to Acceptance Criteria:** US248.2

**Description:** Ensures that only users with the Show Designer role can access the functionality to change category status.

```
@Test
void ensureOnlyShowDesignerCanChangeCategoryStatus() {
    // Setup: authenticate as a user without the Show Designer role
    // Action: attempt to invoke controller.changeCategoryStatus()
    // Assert: the system should throw an exception or return an error indicating insufficient permissions
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