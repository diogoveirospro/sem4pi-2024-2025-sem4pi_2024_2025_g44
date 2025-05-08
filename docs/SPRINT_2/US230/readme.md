# US 230

## 1. Context
\
This task is being developed in sprint 2.\
The customer will contact the CRM Collaborator to submit a request for a show.\
The CRM Collaborator will create the show request into the system.

### 1.1 List of issues
\
Requirements: Done

Analysis: Done

Design: Done

Implement: In Progress

Test: In Progress


## 2. Requirements
\
US230 - As a CRM Collaborator, I want to register (create) a show request

\
**Acceptance Criteria:**

- AC01 - The CRM Collaborator must be a user in the system
- AC02 - The Show Request must have a Customer associated
- AC03 - The Show Request must have a Show Description, a Request Status and a Location 
- AC04 - The Show Request must also have a timestamp and the quantity of drones used
---
**Dependencies/References:**
 
- US211 - As Administrator, I want to be able to register users of the backoffice.\
The CRM Collaborator must be registered before a show request is created


- US220 - As a CRM Collaborator, I want to register a customer, and that the system automatically creates a customer representative for that customer.\
To create a Show Request, a customer is needed


- This US needs the customer is already in the system before starting to execute

---

**Client Clarifications:**

> **[Topic: Show Request Drones](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35126)**\
> The number of drones used is the total amount specified

> **[Topic: Conceito de um Show Request](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35286)**\
> The Show Request's show description can be a document with sequence of figures from the catalogue and/or new figures, as well as customer's exclusivity requirements

> **[Topic: Show Request Time](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35349)**\
> The customer should provide to the CRM Collaborator, the date and time for the show

> **[Topic: Status do Show Request e Show Proposal](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35337)**\
> The Show Request is created in a valid state, and not in an incomplete state waiting for information

> **[Topic: Identificão do show request](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35376)**\
> The Show Request is identified by a Customer, a CRM Collaborator and a Timestamp

> **[Topic: Show Request - Place](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35653)**\
> The Show Request place is defined by a location and an altitude, because it can affect the drone's performance

> **[Topic: Identificação do show request](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35649)**\
> The Customer can place multiple Show Request at the same time.\
> Each Show Request must have a number or a code to identify it

> **[Topic: Show Request - Q&A](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35671)**\
> The Show duration should be expressed in minutes

 
## 3. Analysis
\
The `Show Request` aggregate includes:

- Time - Time for the Show
- Date - Date for the Show
- QuantityOfDrones - Number of drones to be used in the Show
- Location - Location for the Show
- ShowRequestStatus - Status for the Show Request (created, accepted, etc.)
- ShowDescription - List of figures to be used in the show (i.e. a document)

\
The `CRM Collaborator` aggregate includes:

- Name - Name of the CRM Collaborator
- Email - Email of the Collaborator
- PhoneNumber - Phone Number of the Collaborator

\
The `Customer` aggregate includes:

- Name - Name of the customer (name of the company)
- Address - Address of the customer
- VatNumber - Value-added tax identification number
- CustomerStatus - Status of the Customer (active, inactive, etc.)
- CustomerType - Type of customer (individual, company, etc.)
- CustomerRepresentative - Representative from the company

  <br>
  <br>

![US230 Domain Model](images/domain_model_us230.svg "US230 Domain Model")


## 4. Design
\
In this section, the design for **US230 - Register a Show Request** is presented.
It shows the separation between user interface, controller, domain model and persistence layers.

### 4.1. Realization

![US230 Class Diagram](images/class_diagram_us230.svg "US230 Class Diagram")

### 4.2. Acceptance Tests

**Test 1:** Ensure the CRM Collaborator is a user in the system\
**Refers to Acceptance Criteria:** AC01

```
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
	...
}
````

---

**Test 2:** Ensure the Show Request has a Customer associated\
**Refers to Acceptance Criteria:** AC02

```
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
	...
}
````

---

**Test 3:** Ensure the Show Request has a Show Description, a Request Status and a Location\
**Refers to Acceptance Criteria:** AC03

```
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
	...
}
````

---

**Test 4:** Ensure the Show Request has a timestamp and the quantity of drones used\
**Refers to Acceptance Criteria:** AC04


```
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
	...
}
```


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