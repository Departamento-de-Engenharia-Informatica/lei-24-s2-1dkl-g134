
# US004 - Assign a skill to collaborator


## 1. Requirements Engineering

### 1.1. User Story Description

As a Human Resources Manager (HRM), I want to assign one or more skills to a collaborator.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each skill assignment includes selecting a collaborator from the existing list, specifying the skill(s) to assign, and recording the assignment in the system.

**From the client clarifications:**

> **Question:** Is there a minimum or maximum number of skills?
>
> **Answer:** No.

> **Question:** Is there a criteria collaborators must meet before a certain skill can be assigned to them?
>
> **Answer:** No.

### 1.3. Acceptance Criteria

* **AC1:** The collaborator's registered skill set is updated with the assigned skill(s).

### 1.4. Found out Dependencies

* There is a dependency on "US003 - Register a collaborator with a job" as there must be existing collaborators in the system for skill assignments to occur.
* There is a dependency on "US001 - Register Skills" as there must be existing skills in the system for a skill assignment to occur.

### 1.5 Input and Output Data

**Input Data:**

* Selected data:
    * Collaborator
    * Skill(s) to assign

**Output Data:**

* Success or failure message indicating the outcome of the assignment operation


### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us004-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* None