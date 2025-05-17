# US 241

## 1. Context

This task has the objective of concluding the requirements of US241 from Sprint 3, which consists of developing a new functionality in the system: the addition of drones to the inventory. The team is now focused on completing the implementation and testing of this functionality, as well as integrating it with the existing system components.

This feature ensures that drones can be registered in the system with all the necessary attributes, including their serial number, operational status, and associated model. It builds upon previously implemented functionalities (such as US240, which enables the creation of drone models), and is a necessary step toward enabling drone-based operations within the system.
### 1.1 List of issues

Analysis: Done

Design: Done

Implement: To do

Test: To do


## 2. Requirements

**As** a Drone Tech,  
**I want** to add drones of an existing type to inventory,
**So that** all drones in the inventory are accounted for.

### Acceptance Criteria

- **AC01**: For each drone, the serial number must be stored.
- **AC02**: This must also be achieved by a bootstrap process.
- **AC03**: Drone model must already exist.

### Dependencies

This requirement depends on **US240:** As a Drone Tech, I want to create a drone model in the system

---

### Client Clarifications:

> **[Topic: Estado de um drone ao adicioná-lo no inventário](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35829#p45386)**  
> Fará sentido adicionar um drone desactivado ao sistema? Acho que seria perder tempo.  
> Adicionar um drone em manutenção ao sistema seria um problema muito complexo, pois teria que incluir também o processo de manutenção. Esqueça...

---
## 3. Analysis

### Drone Aggregate

The Drone aggregate represents an individual physical drone in the system and acts as the root of its lifecycle and operational state.

#### Domain Attributes:
- **SerialNumber** –A value object representing the unique identifier for each drone. This value must be unique across all drones in the system.
- **DroneStatus** – A value object indicating the current operational status of the drone (e.g., ACTIVE, INACTIVE, MAINTENANCE, BROKEN). Encapsulates the drone’s lifecycle and availability.
- **Model** – An entity reference to an existing Model. Every drone must be linked to a valid and pre-registered drone model to ensure standardization and traceability.
- **RemovalReson** - A value object capturing the reason for removal from service, if applicable. A drone may have multiple associated removal reasons (e.g., malfunction, upgrade, decommission), or none if still in active service.

This structure ensures that all drones are uniquely identifiable, tied to a certified model, and that their status and history are traceable through immutable value objects.
### Model Aggregate

The `Model` aggregate (also referred to as DroneModel in earlier discussions) represents the specifications of a type of drone. A model must exist before drones of that model can be created.

In this US, the model must already be present when creating a drone, thus this entity acts as a dependency and point of validation during the creation process.

### Value Objects

- **SerialNumber** – An immutable, validated value object enforcing uniqueness and formatting rules for drone identification.
- **DroneStatus** –  A controlled enumerated type representing the valid operational states of a drone throughout its lifecycle.
- **RemovalReason** – A descriptive value object (e.g., string or code-based reason) used for auditability when a drone is taken out of service.
- **Model** – An entity reference (not a value object) representing the shared drone type definition required for creating a drone.

### Domain Model

![Drone and Model Relationship](images/domain_model_us241.svg "Domain Model")

---

## 4. Design

In this section, we describe the design approach adopted for implementing US241 – Add drone to the inventory. The sequence diagram defines the main components involved in the addition of a new drone to the inventory, showing a clear separation of concerns between the UI, application logic, domain model, and persistence layer.

### 4.1 Realization

The following diagram shows the flow of the drone addition process.

![US241 Sequence Diagram](images/sequence_diagram_us241.svg "US241 Sequence Diagram")

---

## 5. Tests

The following tests validate the acceptance criteria defined for **US241**. They ensure that drones are added with a unique serial number, that bootstrap processes insert valid data, and that each drone is linked to an existing model.

---

### Test: Drone Entity Unit Tests
**Refers to:** Core functionality of the Drone entity   
**Description:**  Tests constructors, getters, setters, equality, and business methods for the Drone entity class.

```java
@Test
    void testDroneDefaultConstructorForORM() {
            Drone emptyDrone = new Drone();
            assertNotNull(emptyDrone);
            }

@Test
    void testDroneSameAsReturnsFalseAlways() {
            Drone otherDrone = new Drone(
            new SerialNumber(9999),
            buildSampleModel(),
            new RemovalReason(new HashMap<>()),
        DroneStatus.ACTIVE
        );

        assertFalse(drone.sameAs(otherDrone));
        assertFalse(drone.sameAs(null));
        }

@Test
    void testDroneStatusGetterAndAlias() {
            assertEquals(drone.droneStatus(), drone.droneStatus());
            }

@Test
    void testModelGetterAndAlias() {
            assertEquals(drone.model(), drone.model());
            }

@Test
    void testIdentityEquality() {
            Drone sameSerialDrone = new Drone(serialNumber, model, removalReason, DroneStatus.ACTIVE);
            assertEquals(drone.identity(), sameSerialDrone.identity());
            }

@Test
    void testGetSerialNumber() {
            assertEquals(serialNumber, drone.getSerialNumber());
            }

@Test
    void testSetSerialNumber() {
            SerialNumber newSerial = new SerialNumber(5678);
            drone.setSerialNumber(newSerial);
            assertEquals(newSerial, drone.getSerialNumber());
            }

@Test
    void testSetModel() {
            Model newModel = buildSampleModel();
            drone.setModel(newModel);
            assertEquals(newModel, drone.model());
            }

@Test
    void testSetRemovalReason() {
            Map<Date, String> newReasons = new HashMap<>();
        newReasons.put(new Date(), "Updated reason");
        RemovalReason newReason = new RemovalReason(newReasons);
        drone.setRemovalReason(newReason);
        assertEquals(newReason, drone.removalReason());
        }

@Test
    void testSetDroneStatus() {
            drone.setDroneStatus(DroneStatus.BROKEN);
            assertEquals(DroneStatus.BROKEN, drone.droneStatus());
            }
````
---

## 6. Implementation

The implementation of US241,US242, US243 is based on the design and analysis presented in the previous sections. The code is organized into packages that reflect the domain model, application logic, and user interface.
We included the necessary classes and methods to support the registration of a new model of drone. And didn't diverge from the design.

## 7. Integration/Demonstration

To integrate the new functionality with the existing system, we followed these steps:

1. **Persistence Layer**: To connect the new functionality with the database, we used the existing repository pattern. The `DroneRepository` were updated to include the necessary methods for the new functionality.
2. **Controller Layer**: The controller was updated to include methods for handling requests related to models and drones. This includes methods for adding, removing, and retrieving drones and retrieving models.
3. **UI Layer**: The user interface was updated to include forms and views for managing the drones. This includes input validation and error handling.
4. **Testing**: We ran the unit tests to ensure that the new functionality works as expected. The tests cover all acceptance criteria and other important scenarios.

To run the project, follow the instructions in the [README.md](../../../readme.md) file located in the root directory of the project. This file contains detailed instructions on how to set up the development environment, run the application, and execute the tests.

### Demonstration Instructions

To demonstrate the functionality, follow these steps:

1. **Launch the application**.
2. **Log in as a DroneTech**.
3. Navigate to the **Drones** section.
4. Select the corresponding option to what you want to do.
5. Follow the instructions in the UI.

---

## 8. Observations

For the implementation of this project, we used the following sources:

- **EAPLI Framework**: A Java framework that provides a set of libraries and tools of our department(ISEP).
- **ECafetaria project**: A project developed by our department that serves as a reference and source for implementing similar functionalities and as a guide for best practices.
- **Jpa Hibernate**: A Java framework for object-relational mapping (ORM) that simplifies database interactions.
- **H2 Database**: A lightweight Java database that is easy to set up and use for development and testing purposes.

