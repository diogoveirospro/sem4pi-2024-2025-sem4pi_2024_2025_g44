# US 340

*This is an example template*

## 1. Context

The purpose of this task is to deploy and configure a plugin so it can be used by the system to analyze the figure high-level description.
This task is included in Sprint 3 and is being implemented for the first time. 
This plugin is supposed to use the antlr code developed in US341 - Validate Figure Description, And connect it to the system so that it can be used to validate the figure description.

### 1.1 List of issues

Analysis: To Do

Design: To Do
 
Implement: To Do

Test: To Do


## 2. Requirements

**As** a Drone Tech,
<br>
**I want** to deploy and configure a plugin,
<br>
**So that** it can be used by the system to analyze the figure high-level description.

### Acceptance Criteria

- **_US341.1_** The system should be able to call the plugin to validate the figure description.

**Dependencies/References:**

- **US341 – Validate Figure Description**: The plugin will use the code developed in this user story to validate the figure description.

## 3. Analysis

The grammar used for this user story was designed in [US251](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#3-analysis) to describe drone shows and figures in a modular and
extensible way. And is implemented in the plugin developed in [US341 - Validate Figure Description](../US341/readme.md).

## 4. Design

View the design of the plugin in [US341 - Validate Figure Description](../US341/readme.md#4-design).

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


## 6. Integration/Demonstration


## 7. Observations

