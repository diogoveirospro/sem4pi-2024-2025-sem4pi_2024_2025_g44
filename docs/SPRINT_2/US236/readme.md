# US 236

## 1. Context
\
This task is being developed in sprint 2.\
The CRM Collaborator should be able to edit show requests from a client.\
The show request's editing can only be done before a proposal is made.

### 1.1 List of issues
\
Requirements: In Progress

Analysis: TODO

Design: TODO

Implement: TODO

Test: TODO


## 2. Requirements
\
US236 - As CRM Collaborator, I want to edit a show request of a client.\
Only show requests without a proposal can be edited.

\
**Acceptance Criteria:**

- AC01 - The CRM Collaborator must be a user in the system
- AC02 - The Client must be a user in the system

---
**Dependencies/References:**

- US211 - As Administrator, I want to be able to register users of the backoffice.\
  The CRM Collaborator must be registered before this functionality is executed


- US220 - As a CRM Collaborator, I want to register a customer,
  and that the system automatically creates a customer representative for that customer.\
  The client must be registered before this functionality is executed


- US230 - As a CRM Collaborator. I want to register (create) a show request.\
  In order to edit any Show Request, it should be created before this functionality is executed


- US235 - As CRM Manager or CRM Collaborator, I want to list all show requests of a client.\
  In order to provide a list of Show Request for the CRM Collaborator edit, the US235 should be used
  

- If there is no clients, the functionality can not show anything


- If there is a client but no show request for that client, the functionality can not show anything rather than the list of clients


- If there is a client and show requests for that client, but the show request does already have an proposal made, it should not be allowed to edit

---

**Client Clarifications**

> **[Topic: ]()**\
> Hello There


## 3. Analysis
\
The

<br>
<br>

![US236 Domain Model](images/domain_model_us236.svg "US236 Domain Model")



## 4. Design

*In these sections, the team should present the solution design that was adopted to solve the requirement. This should include, at least, a diagram of the realization of the functionality (e.g., sequence diagram), a class diagram (presenting the classes that support the functionality), the identification and rational behind the applied design patterns and the specification of the main tests used to validade the functionality.*

### 4.1. Realization

![a class diagram](images/class-diagram-01.svg "A Class Diagram")

### 4.3. Applied Patterns

### 4.4. Acceptance Tests

Include here the main tests used to validate the functionality. Focus on how they relate to the acceptance criteria. May be automated or manual tests.

**Test 1:** *Verifies that it is not possible to ...*

**Refers to Acceptance Criteria:** US101.1


```
@Test(expected = IllegalArgumentException.class)
public void ensureXxxxYyyy() {
	...
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