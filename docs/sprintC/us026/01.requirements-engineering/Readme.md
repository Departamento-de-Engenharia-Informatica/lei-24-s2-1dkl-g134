# US026 - Assign Vehicles to an Entry


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to assign one or more vehicles to an entry in the Agenda.
### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The Agenda is made
up of entries that relate to a task (which was previously in the To-Do List),
the team that will carry out the task, the vehicles/equipment assigned to
the task, expected duration, and the status (Planned, Postponed, Canceled,
Done).

**From the client clarifications:**

> **Question:** should each GSM only be able to assign vehicles to its own entries or every GSM can assign vehicles to every entry, even if the green space associated with the task is not registered with their email?
>
> **Answer:** For the sake of simplicity, you can assume that GSM will only manage its Agenda Entries.

### 1.3. Acceptance Criteria

* **AC1:** None.

### 1.4. Found out Dependencies

* There is a dependency on the US22 -Add New Entry in the Agenda and on the US006 - Register a Vehicle

### 1.5 Input and Output Data

**Input Data:**

* Selected Data:
    * Vehicle
    * Task

**Output Data:**

* Success or failure message indicating the outcome of the assignment operation.
### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us026-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* None