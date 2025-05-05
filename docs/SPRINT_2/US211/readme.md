# US 211

## 1. Context

*This task aims to conclude the requirements for US211 in Sprint 2, which consists of developing the register users functionality for the system. The team will focus on completing the implementation of register user functionality. Additionally, the team will integrate this feature with the rest of the system to ensure seamless security for all users.*

### 1.1 List of issues

Analysis:
- Understanding the user management process and how administrator will interact with the back-office system.
- Defining the necessary roles and permissions to ensure proper access control.
Design:
- Developing a clear process for user registration, incorporating role assignments and validation.
Implement:
- Implementing a system to handle user registration, ensuring roles and statuses are set correctly.
Test:
- Verifying that users can be registered properly, and that roles are assigned and users are active after registration.

## 2. Requirements
**US G211** As Administrator I Want to register users in the system

**Acceptance Criteria:**

- US211 The system should allow the administrator to register new users.
- US211 The system should allow the administrator to assign roles to users during registration.
- US211 The system should ensure that the registered user is active by default.
- US211 The system should validate the user’s information before registration.
- 
**Dependencies/References:**
- **US210 Authentication and Authorization**: The system should support authentication and authorization for all its users and functionalities before the registration process can proceed.

## 3. Analysis

In order to support user registration, a bootstrap process will be created, which will pre-register an initial set of users. This process will ensure the back-office system has a functional set of users to begin with. The role management system will use the `Role` enum and will allow the administrator to assign appropriate roles to users.
A **UserRepository** will manage the persistence of user data, and this will be accessible through the **RepositoryFactory**. The **AuthenticationService** will ensure that users are authenticated after registration, which is important for accessing the back-office functionalities.
## 4. Design

*In this sections, the team should present the solution design that was adopted to solve the requirement. This should include, at least, a diagram of the realization of the functionality (e.g., sequence diagram), a class diagram (presenting the classes that support the functionality), the identification and rational behind the applied design patterns and the specification of the main tests used to validade the functionality.*

### 4.1. Realization

![US210 Class Diagram](images/class_diagram_us210.svg "US210 Class Diagram")

### 4.3. Applied Patterns

### 4.4. Acceptance Tests

**Test 1:** *Verifies that it is not possible to ...*

**Refers to Acceptance Criteria:** US101.1


```
@Test
public void registerUserSuccessfully() {
    UserRepository userRepository = repositoryFactory.userRepository();
    SystemUser user = new SystemUser("admin", "password", Role.ADMIN);
    userRepository.save(user);
    assertTrue(user.isActive());
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