# US002 - Register a new job

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...                | Answer                 | Justification (with patterns)                                                                                                                              |
|:---------------|:-----------------------------------------------------------|:-----------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                            | RegisterJobUI          | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                              |
|                | ... coordinating the US?                                   | RegisterJobController  | Controller: Coordinates interactions related to registering new work in the user interface (UI) and executes the logic necessary to process these requests |
| Step 2         | ... knowing the user using the system?                     | UserSession            | IE: cf. A&A component documentation.                                                                                                                       |
| Step 3         | ... saving the new job?                                    | RegisterJobUI          | IE: RegisterJob manages the new job data.                                                                                                                  |
| Step 4         | ... getting the job?                                       | JobRepository          | IE: owns job list.                                                                                                                                         |                       |                                                                                                                                                                             |                                                                                                   |
| Step 5         | ... validating all data (local validation)?                | Job                    | IE: owns its data.                                                                                                                                         |
|                | ... validating all data (global validation)?               | Job                    | IE: knows all its organizations.                                                                                                                           |
| Step 6         | ...  saving the new job assigned to an organization?       | Organization           | IE: owns all its organizations.                                                                                                                            |
| Step 7         | ... informing operation success?                           | RegisterJobUI          | IE: is responsible for user interactions.                                                                                                                  |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* OrganizationRepository 
* Job
Other software classes (i.e. Pure Fabrication) identified:

* RegisterJobUI
* RegisterJobController

## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us002-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us002-class-diagram.svg)