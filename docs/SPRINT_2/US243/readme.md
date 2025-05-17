# US 243 - List Active Drones of a Given Model in the Inventory

## 1. Context

This task is part of the larger effort to manage drones within the system. It focuses on enabling the **Drone Tech** to list all active drones associated with a specific drone model. This helps in tracking and managing the active drones in the inventory, which is crucial for operational decisions.

The feature builds upon the functionalities established in **US241**, where drones can be added to the system. In this feature, the system provides a way for the Drone Tech to query and view only those drones that are active and associated with a particular model.

### 1.1 List of Issues

- **Analysis**: Done
- **Design**: Done
- **Implementation**: To do
- **Test**: To do

## 2. Requirements

**As** a Drone Tech,  
**I want** to list active drones of a given model in the inventory,  
**So that** it helps in making decisions.

### Acceptance Criteria

- **AC01**: Drones must be filtered by model.

### Dependencies

This feature depends on **US241**: As a Drone Tech, I want to add drones of an existing type to the inventory.

---

## 3. Analysis

### Drone Aggregate

The `Drone` aggregate represents each physical drone tracked in the system. It holds a unique identity (`SerialNumber`), a current operational `DroneStatus`, and is associated with a specific `Model`.

For **US243**, the primary business requirement is to **filter and retrieve drones by their associated model**. This enables users to quickly query the system for all drones of a particular type.

#### Key Domain Element:

- **Model** – A required reference that each drone is associated with. This entity acts as the classification or type definition for a drone. Filtering by model means querying drones based on this association.

The aggregate also retains:

- **DroneStatus** – Indicates if the drone is currently ACTIVE, MAINTENANCE, BROKEN, etc., which can be combined with model filters for more refined searches.
- **SerialNumber** – A unique value object that identifies each drone.

### Value Objects

- **Model** – An entity representing the drone's configuration and type. It must already exist before any drone of that model can be created or retrieved.
- **SerialNumber** – Guarantees unique identification and immutability.
- **DroneStatus** – Encapsulates valid lifecycle states of the drone.

---

### Domain Model

![Drone List](images/domain_model_us243.svg "Domain Model")

---

## 4. Design

In this section, we describe the design approach adopted for implementing US243 – List Active Drones of a Given Model in the Inventory. The interaction is initiated by a Drone Tech through a user interface, and involves querying the system for active drones by model.

### 4.1 Realization

The following diagram shows the flow of the active drone listing process based on a selected model.

![US243 Sequence Diagram](images/sequence_diagram_us243.svg)

---


## 5. Tests

Please go to the [US241](../../SPRINT_2/US241/readme.md) for the tests of the system.

---

## 6. Implementation

As said during the design phase, the implementation of this functionality is similar to the one presented in [US241](../../SPRINT_2/US241/readme.md).
And for these case we are going to use the same thing we said in [US241](../../SPRINT_2/US241/readme.md), as the commits are also the same.

## 7. Integration/Demonstration

Please go to the [US241](../../SPRINT_2/US241/readme.md) for the integration and demonstration of the system.

## 8. Observations

Please go to the [US241](../../SPRINT_2/US241/readme.md) for the observations of the system.
