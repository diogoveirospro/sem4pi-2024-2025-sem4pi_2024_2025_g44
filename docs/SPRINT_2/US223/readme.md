# US 223

## 1. Context

This task as the objective of concluding the requirements of the us223 of sprint2, where it is asked to develop a new functionality to the system. The team will now focus on completing the implementation and testing of this functionality as well as integrating it with the rest of the system.

### 1.1 List of issues

- **Analysis**: Done
- **Design**: Done
- **Implementation**: To do
- **Testing**: To do

---


## 2. Requirements

**As** a CRM Collaborator,
**I want** to edit the information (email and phone number) of a customer representative.
**So that** the given information is always up to date.

**Acceptance Criteria:**

**AC01:** The system should allow the user to edit the email and phone number of a customer representative. All other information cannot be changed.
**AC02:** The system should validate the input to ensure that it is valid.

**Dependencies:**

*Regarding this requirement we understand that it relates to [US221](../../SPRINT_2/US221/readme.md), as there needs to be a customer representative registered in the system before editing his information.*

## 3. Analysis

The system should allow the user to edit the information of a customer representative. This is important because the information of a customer representative may change over time, and it is important to keep this information up to date.

The system should allow the user to edit the email and phone number of a customer representative. The user should be able to edit this information through the user interface, and the system should validate the input to ensure that it is valid.

Check the [US221](../../SPRINT_2/US221/readme.md) for more information about the customer representative and the customer.

![Relation representative and his updatable information](images/domain_model_us223.svg "Domain Model")

## 4. Design

*In this section we are going to present the design of the system. We will focus on the design of the new functionality, but we will also include other parts of the system that are important to understand the implementation.*

### 4.1. Realization

The class diagram as a similar structure to the one presented in [US221](../../SPRINT_2/US221/readme.md), as the only difference are the names of the ui and controller and the used functions.

### 5. Tests

The following tests were designed to validate the acceptance criteria defined for US223. These tests focus on verifying
that the information edited is correct, that the expected data is correctly returned to the UI, and that proper
access control is enforced.

---

#### **Test 1: Editing the email and phone number of a customer representative**
**Refers to Acceptance Criteria:** _AC01_  
**Description:** Ensures that customers representatives information can be edited successfully.

```java
@Test
void ensureCustomerRepresentativeInformationCanBeEdited() {
    CustomerDTO dto = controller.registerNewRepresentativeOfCustomer();
    Email oldEmail = dto.getEmail();
    PhoneNumber oldPhoneNumber = dto.getPhoneNumber();
    controller.editInformationOfCustomerRepresentative(dto.getEmail(), dto.getPhoneNumber());
    assertNotEquals(oldEmail, dto.getEmail());
    assertNotEquals(oldPhoneNumber, dto.getPhoneNumber());
}
```

---

#### **Test 2: The newly edited data is valid**
**Refers to Acceptance Criteria:** _AC02_  
**Description:** Validates that the edited email and phone number are valid.

```java
@Test
void ensureRepresentativeRepresentsACustomer() {
    // setup: create and persist a representative.
    // action: call controller.editInformationOfCustomerRepresentative() with invalid data and valid data
    // assert: the first call throws an exception and the second one does not
}
```

## 6. Implementation

As said during the design phase, the implementation of this functionality is similar to the one presented in [US221](../../SPRINT_2/US221/readme.md).
And for these case we are going to use the same thing we said in [US221](../../SPRINT_2/US221/readme.md), as the commits are also the same.

## 7. Integration/Demonstration

Please go to the [US221](../../SPRINT_2/US221/readme.md) for the integration and demonstration of the system.

## 8. Observations

Please go to the [US221](../../SPRINT_2/US221/readme.md) for the observations of the system.