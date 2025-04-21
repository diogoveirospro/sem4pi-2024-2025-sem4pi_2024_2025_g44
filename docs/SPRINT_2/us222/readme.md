# US 222

## 1. Context

This task as the objective of concluding the requirements of the us222 of sprint2, where it is asked to develop a new functionality to the system. The team will now focus on completing the implementation and testing of this functionality as well as integrating it with the rest of the system.

### 1.1 List of issues

Analysis: Testing

Design: Testing

Implement: To do

Test: To do


## 2. Requirements

**As** a CRM Collaborator,
**I want** to list all customer representatives of a given customer,
**So that** decision-making by the CRM Collaborator is easier.

**Acceptance Criteria:**

**AC01:** It should not list disabled representatives.

**Dependencies:**

*Regarding this requirement we understand that it relates to US220, as there needs to be a customer registered in the system before listing a customer representative of a given customer.*
*Regarding this requirement we understand that it relates to US221, as there needs to be a customer representative registered in the system before listing them.*

## 3. Analysis

It is important that we are able to list all the representatives of a customer. As we have a one-to-many relationship between the customer and the representative, we can easily list all the representatives of a customer.

This method will be used in the UI to show the list of representatives of a customer.

![Relation customer and representative](images/domain_model_us222.svg "Domain Model")

## 4. Design

*In this section we are going to present the design of the system. We will focus on the design of the new functionality, but we will also include other parts of the system that are important to understand the implementation.*

### 4.1. Realization

![US222 Class Diagram](images/class_diagram_us222.svg "US222 Class Diagram")

### 4.3. Applied Patterns

A arquitetura do sistema continua aplicando diversos padrões de projeto reconhecidos, os quais garantem uma organização robusta e de fácil manutenção. A seguir, destacam-se os padrões aplicados para a funcionalidade de listagem de representantes de um cliente:

#### 1. **DTO (Data Transfer Object) Pattern**
- **Classes Envolvidas:** `CustomerDTO`, `CustomerRepresentativeDTO`
- **Descrição:** Os DTOs encapsulam os dados que serão transferidos da camada de domínio para a interface do usuário, evitando o acoplamento direto com as entidades e facilitando a serialização/deserialização.

#### 2. **Mapper Pattern**
- **Classes Envolvidas:** `CustomerListMapper`, `CustomerRepresentativeListMapper`
- **Descrição:** Realizam a conversão entre entidades do domínio e seus respectivos DTOs. A responsabilidade de transformação é isolada, promovendo reuso e clareza na estrutura da aplicação.

#### 3. **Repository Pattern**
- **Classes Envolvidas:** `CustomerRepository`, `CustomerRepresentativeRepository`, `Repositories`
- **Descrição:** Abstraem o acesso aos dados persistentes. Permitem a obtenção de clientes e seus representantes por meio de interfaces bem definidas, desacoplando o domínio da implementação concreta da persistência.

#### 4. **Singleton Pattern**
- **Classe Envolvida:** `Repositories`
- **Descrição:** A classe `Repositories` segue o padrão Singleton ao expor um método `getInstance()` que garante uma única instância acessível globalmente. Isso centraliza o acesso às instâncias de repositórios.

#### 5. **Controller Pattern**
- **Classe Envolvida:** `ListCustomerRepresentativesController`
- **Descrição:** O controller gerencia os fluxos de interação entre a interface de usuário e o domínio. Ele orquestra a listagem de clientes e de seus representantes, interagindo com os repositórios e os mappers para preparar os dados apresentados.

#### 6. **Value Object Pattern**
- **Classes Envolvidas:** `Address`, `VatNumber`, `CustomerStatus`, `CustomerType`, `Position`, `Name`, `Email`, `PhoneNumber`, `CustomerRepresentativeStatus`
- **Descrição:** Representam valores imutáveis e sem identidade própria, usados para compor entidades como `Customer` e `Representative`. Garantem expressividade e consistência no modelo de domínio.

---

Esses padrões contribuem para a organização modular do código e ajudam a manter uma separação clara entre responsabilidades nas diversas camadas da aplicação.


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