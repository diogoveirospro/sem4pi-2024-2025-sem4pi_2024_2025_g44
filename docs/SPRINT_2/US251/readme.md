# US251 - Specification of the language for figure and show description

## 1. Context

This task aims to conclude the requirements for **US221** of **Sprint 2**, which consists of developing a new functionality for the system. The team will now focus on completing the implementation and testing of this feature, as well as integrating it with the rest of the system.

### 1.1 List of Issues

- **Analysis**: Done
- **Design**: Done
- **Implementation**: To do
- **Testing**: To do

---

## 2. Requirements

**As** a CRM Collaborator,  
**I want** to register a new representative for a customer,  
**So that** the customer can be represented by another person.

### Acceptance Criteria

- **AC01**: The customer representative must be a system user (restricted to the Customer App). Each representative must be a distinct user.
- **AC02**: The representative must be associated with a customer.
- **AC03**: The representative must have a name, email, phone number, position, and status.
- **AC04**: The data must be retrieved using a dedicated DTO to decouple the internal domain model.

### Dependencies

This requirement depends on **US220**, as a customer must be registered in the system before a customer representative can be registered.

---

### Client Clarifications:

> **[Topic: Representantes de um cliente](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35120)**  
> Os representantes não são partilhados entre clientes.

> **[Topic: Dúvidas sobre regras de negócio](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35121)**  
> Não faz qualquer sentido atribuir um email da Shodrone a representantes dos clientes.  
> Um cliente não precisa de ter um domínio próprio e, caso o tenha, não quer dizer que seja igual ao nome, etc.  
> O cliente é a empresa, natural que todos os representantes vejam as propostas da empresa.

> **[Topic: Identificador de clientes e collaboradores](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=35235)**  
> Todos os utilizadores devem ter um ID.  
> O que deve ser esse ID é outra questão. (o id pode ser qualquer elemento que consiga identificar o user)

---

## 3. Analysis

### Customer Aggregate

The `Customer` aggregate contains multiple domain attributes, but only a subset is relevant for this functionality:

- **Name** – Customer name (for identification)
- **Address** – Customer address
- **VatNumber** – Tax identification number
- **CustomerStatus** – Current status of the customer (active, inactive, etc.)
- **CustomerType** – Type of customer (individual, company, etc.)
- **Representatives** – A list of associated representatives

Non-essential elements were omitted to maintain clarity and focus.

### Customer Representative Aggregate

The `CustomerRepresentative` aggregate includes:

- **Name** – Representative's name
- **Position** – Job title or role
- **Email** – Representative’s email address
- **PhoneNumber** – Contact number
- **CustomerRepresentativeStatus** – Current status (active, inactive, etc.)
- **Customer** – Associated customer

Other elements not relevant to this functionality are omitted for simplicity.

![Relation customer and representative](images/domain_model_us221.svg "Domain Model")

---

## 4. Design

In this section, we describe the design approach adopted for implementing **US221 – Add a Customer Representative**. The class diagram defines the main components involved in the addition of a new representative, showing a clear separation of concerns between the UI, application logic, domain model, and persistence layer.

### 4.1 Realization

![US221 Class Diagram](images/class_diagram_us221.svg "US221 Class Diagram")

---

## 5. Tests

The following tests validate the acceptance criteria defined for US221. They ensure that only valid customer representatives are created, that the data is correctly returned to the UI, and that DTOs are used properly.

---

### Test 1: Customer is a user of the system

**Refers to Acceptance Criteria:** AC01  
**Description:** Ensures that customer representatives are valid system users.

```java
@Test
void ensureCustomerIsAUser() {
    // setup: create and persist a customer representative
    // action: call controller.registerNewRepresentativeOfCustomer() and get the users list
    // assert: customer representative is in the list of users
}
```

---

### Test 2: The representative is associated with a customer

**Refers to Acceptance Criteria:** AC02  
**Description:** Validates that the representative is associated with a customer.

```java
@Test
void ensureRepresentativeRepresentsACustomer() {
    CustomerDTO dto = controller.registerNewRepresentativeOfCustomer();
    assertNotNull(dto.getCustomer());
}
```

---

### Test 3: Customer representative's information is correct

**Refers to Acceptance Criteria:** AC03  
**Description:** Verifies the correctness of name, email, phone number, position, and status.

```java
@Test
void ensureCustomerInformationIsCorrect() {
    CustomerDTO dto = controller.registerNewRepresentativeOfCustomer();
    assertNotNull(dto.getName());
    assertNotNull(dto.getEmail());
    assertNotNull(dto.getPhoneNumber());
    assertNotNull(dto.getPosition());
    assertNotNull(dto.getStatus());
}
```

---

### Test 4: Use of DTOs to decouple domain and UI

**Refers to Acceptance Criteria:** AC04  
**Description:** Ensures that domain entities are not directly exposed.

```java
@Test
void ensureDomainEntitiesAreNotLeaked() {
    var result = controller.listAllCustomers();
    assertTrue(result.stream().allMatch(dto -> dto instanceof CustomerDTO));
}
```

---

## 6. Implementation

This section should include evidence that the implementation aligns with the proposed design. Additional artifacts such as configuration files may also be included to help understand the implementation.

### Major Commits (Sample Format)

- `feat(us221): add CustomerRepresentative entity and repository`
- `feat(us221): implement DTO mapping for representative registration`
- `test(us221): add unit tests for representative creation and validation`
- `refactor: adjust Customer aggregate to support representatives`

---

## 7. Integration / Demonstration

This section describes how the functionality was integrated with the system. It should also provide instructions for running or demonstrating the feature.

### Example:

1. Start the application.
2. Log in with a CRM Collaborator account.
3. Navigate to the Customer page.
4. Click on "Add Representative".
5. Fill in the representative's details and submit.
6. Verify the representative is added and listed correctly.

---

## 8. Observations

- The solution follows a clean architecture separating domain, application, and infrastructure layers.
- The use of DTOs effectively prevents domain leakage.
- Alternatives considered included merging representative data into the customer aggregate directly, but this was dismissed to preserve modularity.
- All third-party libraries used (e.g., validation frameworks, mapping tools) are properly documented in the project repository.

---


