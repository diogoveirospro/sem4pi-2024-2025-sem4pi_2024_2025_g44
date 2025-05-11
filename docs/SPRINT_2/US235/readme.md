# US 235

## 1. Context
\
This task is being developed in sprint 2.\
The CRM Manager or the CRM Collaborator should be able to list all show requests of a client.\
The show request should also provide the show request status information.

### 1.1 List of issues
\
Requirements: Done

Analysis: Done

Design: Done

Implement: In Progress

Test: In Progress


## 2. Requirements
\
US235 - As CRM Manager or CRM Collaborator, I want to list all show requests of a client. \
The show request status information should be provided.

\
**Acceptance Criteria:**

- AC01 - The CRM Manager must be a user in the system
- AC02 - The CRM Collaborator must be a user in the system
- AC03 - The Client must be a user in the system
- AC04 - The Show Request status information must be provided

---
**Dependencies/References:**

- US211 - As Administrator, I want to be able to register users of the backoffice.\
  The CRM Manager and CRM Collaborator must be registered before this functionality is executed


- US220 - As a CRM Collaborator, I want to register a customer, 
  and that the system automatically creates a customer representative for that customer.\
  The client must be registered before this functionality is executed
 

- US230 - As a CRM Collaborator. I want to register (create) a show request.\
  In order to list any Show Request, it should be created before this functionality is executed


- If there is no clients, the functionality can not show anything


- If there is a client but no show request for that client, the functionality can not show anything rather than the list of clients

---

**Client Clarifications:**

> **[Topic: Status do Show Request e Show Proposal](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35337)**\
> The Show Request status information should be either created or accepted

> **[Topic: Identificação do show request](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35337)**\
> The Show Request is identified by a Customer, a CRM Collaborator and a Timestamp

> **[Topic: US 235](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35706)**\
> When listing a Show Request, it should have a date, a time, a location, a status and a description


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

![US235 Domain Model](images/domain_model_us235.svg "US235 Domain Model")



## 4. Design
\
In this section, the design for **US235 - List all Show Request of a Client** is presented.\
It shows the separation between user interface, controller, domain model and persistence layers.

### 4.1. Realization

![US235 Class Diagram](images/class_diagram_us235.svg "US235 Class Diagram")

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

**Test 2:** Ensure the CRM Manager is a user in the system\
**Refers to Acceptance Criteria:** AC02

```
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
	...
}
````

---

**Test 3:** Ensure the Customer is a user in the system\
**Refers to Acceptance Criteria:** AC03

```
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
	...
}
````

---

**Test 4:** Ensure the Show Request status information is provided\
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