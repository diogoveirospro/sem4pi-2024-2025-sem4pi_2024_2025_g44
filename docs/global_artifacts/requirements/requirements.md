# Requirements, technical constraints and concerns of the project.

## Use Case Diagram (UCD)

The following diagram represents the use cases of the system, in accordance with the user stories.
User stories can have multiple use cases.

![Use Case Diagram](images/use_case_diagram.svg)

| Main Use Cases   | Description                    |
|:-----------------|:-------------------------------|
| Admin            | Manages users                  |
| CRM Manager      | Manages clients and proposals  |
| CRM Collaborator | Schedules and configures shows |
| Show Designer    | Creates figures for shows      |
| Drone Tech       | Manages drones                 |
| Customer         | Requests and views shows       |

### Use Cases / User Stories

| US    | UC    | Description |                   
|:------|:------|:------------|
| USxx1 | UCXX1 |             |
| USxx1 | UCXX2 |             |
| USxx1 | UCXX3 |             |
| USxx2 | UCXX1 |             |
| USxx2 | UCXX2 |             |

## Supplementary Specification (FURPS+)

### Functionality

_Specifies functionalities that:  
&nbsp; &nbsp; (i) are common across several US/UC;  
&nbsp; &nbsp; (ii) are not related to US/UC, namely: Audit, Reporting and Security._

### Usability

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

1. A Portal in which parks and garden users can post comments and report faults and malfunctions of equipment.
2. A diary for planning the week’s work. Each entry in the diary
   defines a task. Comparatively, analysing the
   diary entries and the pending tasks. Allows the evaluation of the work.

### Reliability

_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are:
frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between
failures._

1. Business rules validation must be respected when recording and updating data.

### Performance

_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory
consumption, CPU usage, load capacity and application availability._

### Supportability

_The supportability requirements gather several characteristics,
for example, testability, adaptability, maintainability, compatibility,
configurability, instability, scalability, and more._

### +

#### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process,
mandatory standards/patterns, use of development tools, class library, etc._

1. The application must be developed in Java language using the IntelliJ IDE or
   NetBeans.
2. The application’s graphical interface is to be developed in JavaFX 11. Adopt the best practices for identifying
   requirements,
   and for OO software analysis and design.
3. Coding standard CamelCase is the one being implemented to the code.
4. The unit tests should be implemented using the JUnit 5 framework.
5. Javadoc will be used to generate useful documentation for Java code.

#### Implementation Constraints

_Specifies or constraints the code or construction of a system,
for example, mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

1. All those who wish to use the application must be authenticated with a password
   of seven alphanumeric characters, including three capital letters and two digits.

2. The application must support the English language.

3. The development team must implement unit tests for all methods, except for
   methods that implement Input/Output operations.

4. The application should use object serialization to ensure data persistence between
   two runs of the application.

5. The class structure must be designed to allow easy maintenance and the addition
   of new features, following the best OO practices.

#### Interface Constraints

_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

1. The JaCoCo plugin should be used to generate the coverage report.

#### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example,
material, shape, size or weight._

1. The program needs to be able to answer in the fastest time possible
2. The program should be compiled to improve the speed of the answer.