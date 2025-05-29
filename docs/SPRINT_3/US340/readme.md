# US 340

This task as the objective of concluding the requirements of the us340 of sprint3, where it is asked to develop a new functionality to the system. The team will now focus on completing the implementation and testing of this functionality as well as integrating it with the rest of the system.

## 1. Context

The purpose of this task is to deploy and configure a plugin so it can be used by the system to analyze the figure high-level description.
This task is included in Sprint 3 and is being implemented for the first time. 
This plugin is supposed to use the antlr code developed in US341 - Validate Figure Description, And connect it to the system so that it can be used to validate the figure description.

### 1.1 List of issues

Analysis: Done

Design: Done
 
Implement: Done

Test: Done


## 2. Requirements

**As** a Drone Tech,
<br>
**I want** to deploy and configure a plugin,
<br>
**So that** it can be used by the system to analyze the figure high-level description.

### Acceptance Criteria

- **AC01** The system should be able to call the plugin to validate the figure description.

**Dependencies/References:**

- **US341 – Validate Figure Description**: The plugin will use the code developed in this user story to validate the figure description.

## 3. Analysis

The grammar used for this user story was designed in [US251](../../LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/US251.md#3-analysis) to describe drone shows and figures in a modular and
extensible way. And is implemented in the plugin developed in [US341 - Validate Figure Description](../US341/readme.md).

To implement this plugin it was necessary to create a `PluginInitializer` that has the function of calling the plugin and registering it on the `DSLValidatorPluginFactory` that would simply serve as a way to register the plugin in the system,
and then the `DSLValidatorPlugin` interface was implemented on the `ANTLRDSLValidatorPlugin` the plugin that uses the ANTLR parser to validate the DSL syntax, to fully connect the plugin to the system. As afterwards the class
`DSLValidate` would be called as it would contain the logic to call the ANTLR plugin that validates the DSL syntax using the ANTLR parser. Then the validation would go through.


## 4. Design

View the design of the plugin in [US341 - Validate Figure Description](../US341/readme.md#4-design).

For these to work it was necessary to `PluginInitializer.initialize()` as it would then register the instance on the `DSLValidatorPluginFactory` and then the method `getInstance()` that returns the instance of the format
that would be `DSLValidatorPlugin` that would be implemented by the `ANTLRDSLValidatorPlugin` class, which would then be used to validate the DSL syntax, that is possible as the interface as the method `DSLValidationResult validateDSL(String code);`
that would be implemented in the antlr plugin. But the acess would be only possible via the `DSLValidate` class that would get the factory instance and then call the `validateDSL` method on the plugin instance.

### 4.1. Acceptance Tests

See the [US341 - Validate Figure Description](../US341/readme.md) for more information on the acceptance tests for the plugin.


## 5. Implementation

See the [US341 - Validate Figure Description](../US341/readme.md) and the information above for more information.

Relevant commit messages:

- [Made some implementation related to the figure plugin](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/78115a139edc8680b9f4aad2a2f8e2fb0b52a30a)
- [Was able to correctly call the plugin](https://github.com/Departamento-de-Engenharia-Informatica/sem4pi-2024-2025-sem4pi_2024_2025_g44/commit/c5e0f57d5b2e08d51b60bcb5db986bc984e31315)

## 6. Integration/Demonstration

The running of the plugin is the same as the one in [US341 - Validate Figure Description](../US341/readme.md#6-integrationdemonstration), as it is the same plugin that is being used to validate the DSL syntax.

## 7. Observations

Go to user story [US341 - Validate Figure Description](../US341/readme.md) for more information on the plugin and how it works.
