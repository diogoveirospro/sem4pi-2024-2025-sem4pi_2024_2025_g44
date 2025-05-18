# US 245

## 1. Context

The objective of this task is to add a new figure category to the figure category catalogue. This task is included in Sprint 2 and it's the first time it's being implemented.

### 1.1 List of issues

Analysis: ✅ Done

Design: ✅ Done

Implement: ✅ Done

Test: ✅ Done


## 2. Requirements

**As a** Show Designer,
<br>
**I want** to add a new figure category to the catalogue,
<br>
**So that** we can filter figures by category.

**Acceptance Criteria:**

- US245.1: The category name must be unique (not case-sensitive).
- US245.2: The functionality should only be accessible to authenticated Show Designer users.

**Dependencies/References:**

- **_US211 - Register users_**: This user story is a direct dependency. It is required to have a Show Designer registered in the system so he can add a new category.

## 3. Analysis

The `Category` aggregate is central to this functionality. It includes the following value objects:

* `CategoryName`: Name by which the category is known.

* `CategoryDescription`: Describes the scope or purpose of the category.

* `CategoryStatus`: Represents the current status of a category, active or inactive.

## 4. Design

This section outlines the design adopted for implementing **US245**. The class diagram presents the essential components
involved in adding new figure categories, with a clear separation between the user interface, application logic, domain model,
and persistence layer.
 

### 4.1. Realization

The class diagram below illustrates the design for adding a new figure category:

* The UI (`AddFigureCategoryUI`) calls the controller to request a category creation.

* The controller (`AddFigureCategoryController`) accesses the repository factory via the `PersistenceContext`.

* The factory returns the configured `CategoryRepository instance, which persists the category.

* Both JPA and in-memory repository implementations are supported.

Business rules (e.g., validating uniqueness of category names, input constraints) are assumed to be enforced inside the domain layer or repository validation logic.


![a class diagram](images/class-diagram-245.svg "A Class Diagram")

### 4.2. Acceptance Tests

#### **Test 1:** *Verifies that a new category can be created*
**Refers to Acceptance Criteria:** _US245.1_

**Description:** Validates that the user can add a category with a name and description, the name must be unique.

```java
@Test
void ensureCategoryCanBeCreatedWithValidNameAndDescription() {
// Setup: create a category with a valid name and description
// Action: invoke controller.addFigureCategory(category)
// Assert: category is persisted and retrievable from repository
}

```

#### **Test 2:** *Verifies that only the Show Designer can add a category*
**Refers to Acceptance Criteria:** _US245.2_

**Description:** Validates that the functionality is restricted to authenticated Show Designer users.

```java
@Test
void ensureOnlyShowDesignerCanAddCategory() {
// Setup: create a non-Show Designer user
// Action: attempt to invoke controller.addFigureCategory(category)
// Assert: an exception is thrown indicating insufficient permissions
}
```


## 5. Implementation

* The implementation of US245 follows the design and analysis presented earlier. The Category aggregate was updated to ensure the uniqueness of the CategoryName value object, and the necessary controllers and repositories were implemented to support the functionality.  
* Key Implementation Details:
* Domain Layer: The Category aggregate ensures that the CategoryName is unique and immutable. The CategoryName value object validates the input and enforces constraints.
* Application Layer: The AddCategoryController handles the logic for adding a new category, ensuring that the name is unique before persisting the category.
* Persistence Layer: The CategoryRepository interface and its JPA implementation ensure that categories are stored and retrieved correctly.

# 6. Integration/Demonstration

* The functionality developed in US245 was integrated into the existing system. The AddCategoryController was exposed through the UI layer, allowing authenticated Show Designers to add new categories.
* Steps to Demonstrate:
* Launch the application: Start the system using the provided scripts or instructions in the main README.md.
* Log in as a Show Designer: Ensure the user has the appropriate role.
* Navigate to the Category Management Section: Select the option to add a new category.
* Add a Category: Enter a unique name and description for the category.
* Verify: Confirm that the category is added successfully and appears in the list of categories.

## 7. Observations

For the implementation of this project, I used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools developed by our department (ISEP).
- **eCafeteria Project**: A reference project developed by our department, used as a source of inspiration for similar
  functionalities and a guide for best practices.
- **JPA (Hibernate)**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.