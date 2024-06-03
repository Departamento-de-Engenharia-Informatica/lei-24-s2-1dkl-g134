# US028 - Consult Tasks Assigned to Collaborator


## 1. Requirements Engineering

### 1.1. User Story Description

As a Collaborator, I wish to consult the tasks assigned to me between two dates.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The list of tasks spaces must be sorted by date.

> The Collaborator should be able to filter the results by the status of the task.

**From the client clarifications:**

> **Question:**
When a collaborator is registered, they are given an account with the registered email and a password? This allows them to log in and view their tasks later on. What should be the password for this collaborator's account?.

> **Answer:** Yes, it makes sense. About the password, not important in this stage of the project.

### 1.3. Acceptance Criteria

* **AC1:** The list of tasks must be sorted by date.
* **AC2:** The Collaborator should be able to filter the results by the status of the task.

### 1.4. Found out Dependencies

* None.

### 1.5 Input and Output Data

**Input Data:**

* Start date
* End date


**Output Data:**

* List of tasks assigned to the Collaborator between the specified dates, sorted by date, optionally filtered by task status.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us010-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* None