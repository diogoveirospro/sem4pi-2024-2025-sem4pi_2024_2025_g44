# US240 - Register Drone Model

## 1. Context

This task concerns the implementation of **US240**, part of Sprint 3, which introduces a new system feature: the ability to define and store different drone models. This functionality is essential to support the future creation and tracking of physical drone units (US241), and is a foundational step toward drone fleet management.

The system must ensure that each drone model includes specific physical and operational characteristics such as wind tolerance, maximum wind speed, position tolerance, and safety status. This information will guide validation and operation constraints in later features, including inventory and mission execution.

### 1.1 List of Issues

- **Analysis**: Done
- **Design**: Done
- **Implement**: To do
- **Test**: To do

---

## 2. Requirements

**As** a Drone Tech,  
**I want** to create a drone model in the system,  
**So that** a new model of drones can start being used.

### Acceptance Criteria

- **AC01**: This must also be achieved by a bootstrap process.

### Dependencies

This user story does not depend on any previous functionality.

---

### Client Clarifications

> **[Topic: Validação do modelo de drone](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35790#p45210)**  
> Sim, esses parâmetros são obrigatórios para garantir a segurança operacional dos drones. Um modelo mal definido pode causar falhas em voo.

---

## 3. Analysis

### Model Aggregate

The core domain concept for this user story is the **Model** aggregate, which captures all essential characteristics of a drone type.

#### Domain Attributes:

- **ModelName** – A unique identifier for internal referencing.
- **Configuration** – A collection of attributes defining the model's operational limits and safety status. Composed of:
  - **WindSpeed** – Defines the wind speed range (min, max) under which the drone can operate.
  - **PositionTolerance** –  Defines the positional deviation tolerated by the drone under specific wind conditions.
- **SafetyStatus** –  Enum indicating whether the configuration is deemed safe (SAFE, UNSAFE).

These attributes must be validated during model creation. Invalid data should prevent the persistence of the model.

### Value Objects

- **ModelName** – Immutable value object representing the unique identity of a drone model.
- **WindSpeed** -  A value object representing minimum and maximum wind velocity. Must be a valid range (min ≤ max).
- **PositionTolerance** –  A decimal-based tolerance (e.g., 0.5m) that defines how much deviation is accepted in drone navigation under wind effects.
- **SafetyStatus** – Enum-like value object (e.g., SAFE, UNSAFE) that defines whether the model meets safety requirements.

### Domain Model

![Model Aggregate](images/domain_model_us240.svg "Domain Model")

---

## 4. Design

This section describes the design process for **US240**, which enables the creation of drone models. The interaction is initiated by a Drone Tech through a user interface and handled through a series of layered responsibilities, culminating in the creation and validation of a new `Model` entity.

### 4.1 Realization

The following diagram shows the flow of the drone model creation process:

![US240 Sequence Diagram](images/sequence_diagram_us240.svg)

---
## 5. Tests

---

#### Test: `testConstructorAndGetters`

**Refers to:** Model creation validation  
**Description:** Verifies that the constructor correctly initializes mandatory attributes and that the methods `getConfiguration()` and `identity()` return the expected values.

```java
@Test
void testConstructorAndGetters() {
    assertNotNull(model);
    assertEquals(modelName, model.identity());
    assertEquals(configuration, model.getConfiguration());
}

```

---

#### Test: `testConstructorThrowsOnNullArgs`

**Refers to:** Mandatory data validation (AC02)
**Description** Ensures that creating a model with null arguments throws exceptions, preventing the persistence of invalid data.

```java
@Test
void testConstructorThrowsOnNullArgs() {
    assertThrows(IllegalArgumentException.class, () -> new Model(null, configuration));
    assertThrows(IllegalArgumentException.class, () -> new Model(modelName, null));
}
```

---

#### Test: `testSetters`

**Refers to:** Model attribute modification
**Description** Checks if the setModelName and setConfiguration methods properly update the internal state of the entity.

```java
@Test
void testSetters() {
    ModelName newName = new ModelName("NewModelName");
    model.setModelName(newName);
    assertEquals(newName, model.identity());

    WindSpeed ws2 = new WindSpeed(10, 20);
    PositionTolerance pt2 = new PositionTolerance(1.0);
    Configuration newConfig = new Configuration(Map.of(ws2, pt2), SafetyStatus.UNSAFE);
    model.setConfiguration(newConfig);
    assertEquals(newConfig, model.getConfiguration());
}
```

---

#### Test: `testSameAs`

**Refers to:** Semantic comparison of models
**Description** Verifies if the sameAs method recognizes equivalent objects based on the model name, rejecting null and incompatible types.

```java
@Test
void testSameAs() {
    Model sameModel = new Model(modelName, configuration);
    Model differentModel = new Model(new ModelName("OtherModel"), configuration);

    assertTrue(model.sameAs(sameModel));
    assertFalse(model.sameAs(differentModel));
    assertFalse(model.sameAs(null));
    assertFalse(model.sameAs("Some String"));
}
```

---

#### Test: `testEqualsAndHashCode`

**Refers to:**  Entity identity (DDD design)
**Description** Ensures that equals and hashCode methods are implemented consistently with the entity’s identity (ModelName).

```java
@Test
void testEqualsAndHashCode() {
    Model sameModel = new Model(modelName, configuration);
    Model differentModel = new Model(new ModelName("OtherModel"), configuration);

    assertEquals(model, sameModel);
    assertEquals(model.hashCode(), sameModel.hashCode());

    assertNotEquals(model, differentModel);
    assertNotEquals(model.hashCode(), differentModel.hashCode());
}
```

---

#### Test: `testToStringContainsModelNameAndConfiguration`

**Refers to:** Inspection and debugging
**Description** Checks if the textual representation of the entity includes essential attributes (ModelName and Configuration), facilitating tracing in logs and interfaces.

```java
@Test
void testToStringContainsModelNameAndConfiguration() {
    String toString = model.toString();
    assertTrue(toString.contains(modelName.toString()));
    assertTrue(toString.contains(configuration.toString()));
}
```

---

## 6. Implementation

The implementation of US221 is based on the design and analysis presented in the previous sections. The code is organized into packages that reflect the domain model, application logic, and user interface.
We included the necessary classes and methods to support the registration of a new model of drone. And didn't diverge from the design.

## 7. Integration/Demonstration

To integrate the new functionality with the existing system, we followed these steps:

1. **Persistence Layer**: To connect the new functionality with the database, we used the existing repository pattern. The `ModelRepository` were updated to include the necessary methods for the new functionality.
2. **Controller Layer**: The controller was updated to include methods for creating models.
3. **UI Layer**: The user interface was updated to include forms and views for managing the model configuration. This includes input validation and error handling.
4. **Testing**: We ran the unit tests to ensure that the new functionality works as expected. The tests cover all acceptance criteria and other important scenarios.

To run the project, follow the instructions in the [README.md](../../../readme.md) file located in the root directory of the project. This file contains detailed instructions on how to set up the development environment, run the application, and execute the tests.

### Demonstration Instructions

To demonstrate the functionality, follow these steps:

1. **Launch the application**.
2. **Log in as a DroneTech**.
3. Navigate to the **Drones** section.
4. Select the corresponding option to create a model.
5. Follow the instructions in the UI.

---

## 8. Observations

For the implementation of this project, we used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools of our department(ISEP).
- **ECafetaria project**: A project developed by our department that serves as a reference and source for implementing similar functionalities and as a guide for best practices.
- **Jpa Hibernate**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.
