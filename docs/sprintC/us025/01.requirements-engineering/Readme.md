# US025 - Cancel Entry


## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Space Manager (GSM), I want to cancel an entry in the agenda.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> When a GSM cancels an entry in the agenda, the entry should not be deleted but rather change its state to "CANCELED.

**From the client clarifications:**

> **Question:** When the GSM wants to cancel a task, this task can only be canceled if its status is PLANNED or POSTPONED, correct?.

> **Answer:**No, just planned because if there is a Postponed entry then there is also an Planned Entry with the new date.

### 1.3. Acceptance Criteria

* **AC1:** A canceled task should not be deleted but rather change its state to "CANCELED."

### 1.4. Found out Dependencies

* None.

### 1.5 Input and Output Data

**Input Data:**

* Task ID

**Output Data:**

Confirmed task cancelled.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us025-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* None