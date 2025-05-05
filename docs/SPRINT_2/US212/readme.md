# US 212

*This is an example template*

## 1. Context

*This task aims to conclude the requirements for US212 in Sprint 2, which consists of developing the Disable/enable users functionality for the system. The team will focus on completing the implementation of Disable/enable user functionality. Additionally, the team will integrate this feature with the rest of the system to ensure seamless security for all users.*

### 1.1 List of issues

Analysis:
- Determine the criteria for disabling or enabling users.
- Define the permissions and effects on users when they are disabled or enabled.
Design:
- Implement the logic to change the user status between active and disabled.
Implement:
- Create methods to enable/disable users in the user repository.
Test:
- Ensure that users can be enabled or disabled correctly and that their status is reflected in the system.

## 2. Requirements

**US G212** As Administrator I Want to be able to disable/enable users in the system.

**Acceptance Criteria:**

- US212 The system should allow the administrator to disable or enable users.
- US212 The system should ensure that disabled users cannot access the system.
- US212 The system should allow the administrator to view the status of users (active/disabled).
- US212 The system should provide a confirmation prompt before disabling/enabling a user.

**Dependencies/References:**
- **US210 Authentication and Authorization**: The system should support authentication and authorization for all its users and functionalities before the registration process can proceed.
- **US211 Register Users**: The functionality for disabling/enabling users will rely on the existence of registered users.

*Regarding this requirement we understand that it relates to...*

## 3. Analysis

Disabling users is crucial to ensure that only authorized users access the system. The user status will be managed by a field in the database, which can be changed to reflect whether the user is active or disabled.

The interaction with the user repository will be managed through the **UserRepository** and will be handled by the **AuthenticationController**. The disable/enable process will be executed through appropriate methods in the service layer.

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
public void deactivateUserSuccessfully() {
    UserRepository userRepository = repositoryFactory.userRepository();
    SystemUser user = new SystemUser("user1", "password", Role.CRM_MANAGER);
    userRepository.save(user);
    user.deactivate();
    assertFalse(user.isActive());
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