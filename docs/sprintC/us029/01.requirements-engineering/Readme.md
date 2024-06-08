# US029 - Record Completion of a Task


## 1. Requirements Engineering

### 1.1. User Story Description

As a Collaborator, I want to record the completion of a task

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The To-Do List comprises all pending tasks for all parks. The entries in
this list describe the required task, the degree of urgency (High, Medium,
and Low), and the approximate expected duration. The Agenda is made
up of entries that relate to a task (which was previously in the To-Do List),
the team that will carry out the task, the vehicles/equipment assigned to
the task, expected duration, and the status (Planned, Postponed, Canceled,
Done).




**From the client clarifications:**

> **Question:** The collaborator can see what type of entrys? Like what status can he filter ? Can he see canceled Entry's?
> 
> **Answer:** The ones assigned to him. He can filter by the different values the status of the status, like planned, executed, canceled ...

> **Question:** When a collaborator records a task, it should be asked for any observations regarding the completed task?
>
> **Answer:** Maybe if optional, not mandatory.

> **Question:** Can an employee record more than one completed task at a time?
>
> **Answer:** It's a matter of UX/UI, each dev team can decide about it.



### 1.3. Acceptance Criteria

* None.

### 1.4. Found out Dependencies

* There are dependencies for this US found in:
   * US020 as there must be at least one green space registered to associate it to a Task;
   * US021 as there must be at least one entry in the To-Do List to successfully add a new entry in the Agenda;
   * US022 as there must be at least one entry in the Agenda;
   * US023 as there must be at least one Team assigned to an entry in the Agenda

### 1.5 Input and Output Data

**Input Data:**

* Selected data:
   * An Agenda Entry

**Output Data:**

* (In)Sucess of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us010-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* None