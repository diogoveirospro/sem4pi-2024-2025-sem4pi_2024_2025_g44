# US 371

## 1. Context

This user story is part of Sprint 3 and introduces the functionality that allows a **CustomerRepresentative** to get the file of a chosen show proposal to analyse.

### 1.1 List of issues

Analysis: Done

Design: Done

Implementation: Done

Testing: Done

--- 

## 2. Requirements

**As a Customer RepresentaƟve,**  
<br>
**I want**  to have access to a show proposal of mine in the App. 
<br>
**So that** I received a link/code to download the file.

### Acceptance Criteria:

* **AC01** The customer must receive a link/code.
* **AC02** The customer can't use a code from a show proposal from another customer.

### Dependencies/References

* **US370 – Analyse a proposal**: to view the proposal before taking action.

---

## 3. Analysis

This user story focuses on allowing a customer to receive a specific show proposal file, given a valid delivery code, and ensuring that the customer is authorized to download it.


All required domain elements are already in place:

- `ShowProposal` entity includes:
    - `document` (of type `ShowProposalDocument`) that includes
      - `file` (binary content encoded)
      
- `ProposalDeliveryInfo` entity includes:
    - `deliveryCode` (of type `ProposalDeliveryInfoCode`)
    - `proposal` (of type `ShowProposal`)

- The proposal is linked to the customer via the `ProposalDeliveryInfo` aggregate.

The system must ensure that:
- Only authorized customer representatives can download proposals linked to their customer.
- The file content is correctly decoded and saved to a local file.

There is **no need to modify the domain model**, only to apply its existing operations correctly.

### Responsibilities per component

- **Customer App**: UI element to send the delivery code and receive the file.
- **Customer App Protocol Proxy**: constructs and sends several `Request` over the socket.
- **Customer App Server**:
    - Parses and validates the request.
    - Returns the showProposal from the response.

![Domain Model - Show Proposal Aggregate](../../global_artifacts/analysis/images/domain_model_show_proposal.svg)

---

## 4. Design

This section presents the design adopted for implementing **US370 – Analyze a Proposal**.

### 4.1 Realisation

The following sequence diagram illustrates the interaction flow between the user interface, controller, network proxy, 
socket layer, server components, repositories and DTOS:

![Sequence Diagram for US370](images/sequence_diagram_us370.svg)

This design ensures a clear separation of concerns:

- The **UI** manages user interaction and decision capture.
- The **controller** coordinates between user actions and the domain logic.
- The **network proxy** handles message preparation and socket communication.
- The **socket layer** abstracts low-level network transport.
- The **message parser** interprets incoming requests and responses.
- The **server controller** executes the domain logic and updates aggregate state.
- The **repositories** provide persistent access to both customer-related information (`DeliveryReportingRepository`, `CustomerRepository`) and proposal lifecycle (`ShowProposalRepository`).
- The **DTOS** encapsulate the data exchanged between client and server, ensuring type safety and clarity in communication.

---
## 6. Implementation

---

### 👤 Actor

#### Customer Representative
- **Role:** The end-user who initiates the configuration of drone models for a show proposal.
- **Interaction:** Requests to analyze a show proposal by providing a delivery code, views the proposal information, and downloads the proposal file.

---

### 💻 UI Layer

#### AnalyseProposalUI
   - The UI starts by asking the user to input the delivery code for the proposal.
   - Once the code is provided, it invokes `AnalyseProposalController.findProposalByDeliveryCode(code)`.
   - After receiving the proposal data, it shows the proposal details to the user.
   - If the user confirms, it requests the decoding and creation of the physical file from the controller and notifies the user upon successful file creation.
---

### 🎮 Application Layer

#### :AnalyseProposalController
- Receives the delivery code from the UI.
- Retrieves the authenticated user via `AuthorizationService`.
- Contacts the remote server via `CustomerAppProtocolProxy` to:
- Obtain the **ShodroneUserDTO** of the authenticated user.
- Retrieve the corresponding **CustomerDTO** for that user.
- Request the **ShowProposalDTO** using the delivery code.
- It then validates whether the proposal belongs to the corresponding customer.
- If validation is successful, it passes the proposal data back to the UI.
- Responsible for decoding the Base64 encoded file (decodeFile) and creating the physical file (createFile).


---

### 🌐 Communication Layer

#### CustomerAppProtocolProxy
  - Acts as a proxy between the application and the remote server.
  - Handles the creation of requests and communication over TCP sockets.
  - Serializes and deserializes requests and responses using MarshallerUnmarshaller.
  - Main Remote Calls:
    - `getShodroneUser(currentUser.username)`:

      - Uses **GetShodroneUserRequest** to retrieve user data.

    - `getCustomerOfRepresentative(email)`:

      - Uses **GetCustomerOfRepresentativeRequest** to retrieve customer data.

    - `getProposalByCode(code)`:

      - Uses **GetProposalByCodeRequest** to retrieve proposal data.

---

### 🌐  Server Side (CustomerAppServer)

#### CustomerAppMessageParse
- Parses incoming requests and dispatches them to the correct handler.
- Request Handlers:
  - **GetShodroneUserRequest**:
    - Retrieves ShodroneUser data from ShodroneUserRepository.
  - **GetCustomerRequest**:
    - Retrieves Customer data from CustomerRepository.
  - **GetPropByCodeRequest**:
    - Retrieves the proposal data from DeliveryReportingRepository using the delivery code.

#### UserAppServerController
- The server-side controller layer that interacts with repositories.

---

### 🗄️  Persistence Layer
#### Repositories
- ShodroneUserRepository: Retrieves authenticated user data.
- CustomerRepository: Retrieves customer data based on representative's email.
- DeliveryReportingRepository: Retrieves proposal delivery data.

---

### 🧠 Domain Layer

#### DTOs involved
- `ShodroneUserDTO`: Holds data related to the authenticated user.

- `CustomerDTO`: Holds data related to the customer.

- `ShowProposalDTO` : Holds all information related to the show proposal.

---

## 6. Integration/Demonstration

The functionality developed in **US370** was integrated into the customer-side application via the `AnalyseProposalUI`.

### Demonstration Instructions

1. **Launch the application**.
2. **Log in as a Customer**.
3. Select **Analyse Proposal** option.
4. Enter a valid delivery code.
5. Shows the proposal from the delivery code and asks to confirm download.
6. The file will be saved locally, and the file path will be shown.

---

## 7. Observations

- Full client-server interaction via TCP socket.
- Proxy pattern on the client side to decouple the controller logic from low-level socket communication.
- DTO pattern to transport data between layers and across network boundaries.
- Repository pattern on server side for database operations.
- Marshaller/Unmarshaller responsible for serializing/deserializing requests/responses.

