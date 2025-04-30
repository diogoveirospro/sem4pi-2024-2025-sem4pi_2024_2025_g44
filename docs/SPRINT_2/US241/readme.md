# US 221

## 1. Context

This task as the objective of concluding the requirements of the us221 of sprint2, where it is asked to develop a new functionality to the system. The team will now focus on completing the implementation and testing of this functionality as well as integrating it with the rest of the system.

### 1.1 List of issues

Analysis: testing

Design: testing

Implement: To do

Test: To do


## 2. Requirements

**As** a CRM Collaborator,
**I want** to register a new representative of a customer,
**So that** the customer can be represented by another person.

**Acceptance Criteria:**

**AC01:** The customer representative will be a user of the system (Costumer core.Persitence.AppSettings).

**Dependencies:**

*Regarding this requirement we understand that it relates to US220, as there needs to be a customer registered in the system before registering a customer representative.*

**Note:** There is no need to verify that customer representative’s email in the customer’s domain.

## 3. Analysis

The team decided that the best approach to implement this functionality is to have the `Customer Representative` that will be responsible for representing the customer in the system. Having a one-to-many relationship with the `Customer`, as a customer can have multiple representatives.

The customer won't be able to have the same representative as another customer, so the system will not allow that.

The customer won't have an account in the system, so the customer representative will be the way to access the system.

![Relation customer and representative](images/domain_model_us221.svg "Domain Model")

## 4. Design

*In this section we are going to present the design of the system. We will focus on the design of the new functionality, but we will also include other parts of the system that are important to understand the implementation.*

### 4.1. Realization

![US221 Class Diagram](images/class_diagram_us221.svg "US221 Class Diagram")

### 4.3. Applied Patterns

The system design applies several well-established design patterns, promoting a clean, cohesive, and maintainable architecture. Below are the main patterns identified:

#### 1. **DTO (Data Transfer Object) Pattern**
- **Class Involved:** `CustomerDTO`
- **Description:** Used to transfer data between layers, especially from the domain to the user interface. Ensures that only the necessary data is exposed, protecting domain entities.

#### 2. **Mapper Pattern**
- **Class Involved:** `CustomerListMapper`
- **Description:** Responsible for converting domain objects (`Customer`) into DTO objects (`CustomerDTO`) and vice versa. Centralizes transformation logic, promoting reuse and separation of concerns.

#### 3. **Repository Pattern**
- **Classes Involved:** `CustomerRepository`, `CustomerRepresentativeRepository`, `Repositories`
- **Description:** Abstracts the data persistence layer. Defines a clear interface for operations on domain entities, allowing the data source to be replaced or modified without impacting business logic.

#### 4. **Singleton Pattern**
- **Class Involved:** `Repositories`
- **Description:** Ensures that only one instance of the repository aggregator exists in the system. The `getInstance()` method implements the Singleton pattern, providing a controlled, global access point to the repositories.

#### 5. **Controller Pattern**
- **Class Involved:** `AddCustomerRepresentativeController`
- **Description:** Acts as an intermediary between the UI layer and the domain. Encapsulates the orchestration logic of use cases, such as registering representatives and listing customers.

#### 6. **Value Object Pattern**
- **Classes Involved:** `Address`, `VatNumber`, `CustomerStatus`, `CustomerType`, `Position`, `Name`, `Email`, `PhoneNumber`
- **Description:** Represent immutable objects that encapsulate values and validation rules. Used to compose entities, ensuring consistency and expressiveness in the domain model.

---

These patterns contribute to the modular organization of the code and help maintain a clear separation of concerns across the various layers of the application.


### 5. Tests

Include here the main tests used to validate the functionality. Focus on how they relate to the acceptance criteria. May be automated or manual tests.

**Test 1:** *Verifies that it is not possible to ...*

**Refers to Acceptance Criteria:** US101.1


```
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
	...
}
````

## 6. Implementation

*In this section the team should present, if necessary, some evidencies that the implementation is according to the design. It should also describe and explain other important artifacts necessary to fully understand the implementation like, for instance, configuration files.*

*It is also a best practice to include a listing (with a brief summary) of the major commits regarding this requirement.*

## 7. Integration/Demonstration

*In this section the team should describe the efforts realized in order to integrate this functionality with the other parts/components of the system*

*It is also important to explain any scripts or instructions required to execute an demonstrate this functionality*

## 8. Observations

*This section should be used to include any content that does not fit any of the previous sections.*

*The team should present here, for instance, a critical prespective on the developed work including the analysis of alternative solutioons or related works*

*The team should include in this section statements/references regarding third party works that were used in the development this work.*