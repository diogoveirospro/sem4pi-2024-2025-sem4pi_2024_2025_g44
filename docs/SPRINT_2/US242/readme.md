# US242 - Remove Drone from Inventory

## 1. Context

This task focuses on completing the functionality for **US242**, which enables the removal of a specific drone from the inventory. This complements **US241** (adding drones) by giving Drone Techs the ability to manage which drones are actively listed in the system.

The main objective is to allow operators to remove a drone when it's no longer needed or operational, while ensuring that the removal is recorded with a valid reason and timestamp for future auditing.

### 1.1 List of Issues

- **Analysis**: Done
- **Design**: Done
- **Implement**: To do
- **Test**: To do

---

## 2. Requirements

**As** a Drone Tech,  
**I want** to remove a specific drone from the inventory,  
**So that** only required drones are shown.

### Acceptance Criteria

- **AC01**: The reason for removal and the date must be stored.

### Dependencies

- **US241** – Adding drones to the inventory.

---

## 3. Analysis

### Drone Aggregate

The `Drone`aggregate represents a physical drone tracked within the system. In the scope of **US242**, the domain logic supports logical removal rather than physical deletion. This means a drone remains in the system but is flagged as removed, with accompanying metadata that includes:

- The **reason** for removal (e.g., "decommissioned", "damaged", "lost").
- The **date/time** the removal occurred.

This design ensures historical traceability and regulatory compliance, while also supporting business needs such as audit logs and asset lifecycle tracking.

#### Domain Attributes:
- **SerialNumber** –A value object representing the unique identifier for each drone. This value must be unique across all drones in the system.
- **DroneStatus** – A value object indicating the current operational status of the drone (e.g., ACTIVE, INACTIVE, MAINTENANCE, BROKEN). Encapsulates the drone’s lifecycle and availability.
- **RemovalReson** - A value object capturing the reason for removal from service, if applicable. A drone may have multiple associated removal reasons (e.g., malfunction, upgrade, decommission), or none if still in active service.

The removal reason is modeled as a 0..* relationship, supporting multiple entries for scenarios where a drone might be temporarily decommissioned and then reinstated.

### Value Objects

- **SerialNumber** – An immutable, validated value object enforcing uniqueness and formatting rules for drone identification.
- **DroneStatus** –  A controlled enumerated type representing the valid operational states of a drone throughout its lifecycle.
- **RemovalReason** – A descriptive value object (e.g., string or code-based reason) used for auditability when a drone is taken out of service.

### Domain Model

![Drone and Removal Relationship](images/domain_model_us242.svg "Domain Model")

---

## 4. Design

This section describes the design process for US242, which enables the removal of drones from the inventory. The interaction begins when a Drone Tech initiates a removal request through the user interface. The request flows through a structured application layer, which ensures proper validation, including the existence of the drone and the capture of a removal reason and timestamp. The process culminates in the logical removal of the drone from active inventory, while retaining historical data for auditing and traceability purposes.

### 4.1 Realization

The following diagram shows the flow of the drone removal process.

![US242 Sequence Diagram](images/sequence_diagram_us242.svg)

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