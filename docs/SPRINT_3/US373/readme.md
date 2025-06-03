# US373 - Get Show Info

This task as the objective of concluding the requirements of the us373 of sprint3, where it is asked to develop a new functionality to the system. The team will now focus on completing the implementation and testing of this functionality as well as integrating it with the rest of the system.

## 1. Context

### 1.1 List of issues

Analysis: Done

Design: Done

Implement: To Do

Test: To Do


---

## 2. Requirements

**As** a Customer,
<br>
**I want** to get the details of a show (scheduled or in the past), including the drone models, figures, duration, etc.,
<br>
**So that** I know what I am going to watch.


### Acceptance Criteria

- **AC01**: There should be a server that will connect to the database and retrieve the show information.
- **AC02**: The returned information should have the most relevant details of the show.
- **AC03**: Sockets should be used to retrieve the show information.

### Dependencies

- This requirement depends on [US317](../../SPRINT_3/US317/readme.md), as a proposal must be accepted before we can get the show information. (The show becomes scheduled after wards).

---

## 3. Analysis

It is important that the customer can visualise the information of scheduled shows, as they request for that show and have all the motives to know when it will happen and how it will look like. (or also check for past shows).

This is possible because in terms of domain as the proposal as a relation of many-to-one with the request and the request as a many-to-one relation with customer.

Other elements not relevant to this functionality are omitted for simplicity.

![Relation show proposal](../../global_artifacts/analysis/images/domain_model_show_proposal.svg "Domain Model")

![Relation show request](../../global_artifacts/analysis/images/domain_model_show_request.svg "Domain Model")

## 4. Design

*In these sections, the team should present the solution design that was adopted to solve the requirement. This should include, at least, a diagram of the realization of the functionality (e.g., sequence diagram), a class diagram (presenting the classes that support the functionality), the identification and rational behind the applied design patterns and the specification of the main tests used to validade the functionality.*

### 4.1. Realization

![a sequence diagram](images/sequence_diagram_us373.svg "A Sequence Diagram")

## 5. Tests

The tests for this requirement are not required as the domain logic is already tested in previous requirements. 

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