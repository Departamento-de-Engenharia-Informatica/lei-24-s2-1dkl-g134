# US023 - Assign a Team to an Entry

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...         | Answer                         | Justification (with patterns)                                                                                                                                               |
|:---------------|:----------------------------------------------------|:-------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                     | AssignTeamToTaskUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                                               |
|                | ... coordinating the US?                            | AssignTeamToTaskController | Controller: coordinates the interactions related to assigning a skill to a collaborator in the user interface (UI) and executes the logic needed to process these requests. |
| Step 2         | ... displaying the form for the user to imput data? | AssignTeamToTaskUI         | IE: Is responsible for interacting with the actor.                                                                                                                          |
| Step 3         | ... knowing the Tasks?                              | AgendaRepository               | IE: owns all its tasks.                                                                                                                                                     |
|                | ... saving selected task?                           | AssignTeamToTaskUI         | IE: AssignTeamToTask manages the selected task data.                                                                                                                        |
|                | ... knowing the teams?                              | TeamRepository              | IE: owns team list.                                                                                                                                                         |
|                | ... saving selected team?                           | AssignTeamToTaskUI         | IE: AssignTeamToTask manages the selected data.                                                                                                                             |
| Step 4  		     | 	...displaying all the data?                        | AssignTeamToTaskUI         | IE: Keeps all information before submission.                                                                                                                                |
| 		             | 	...requesting confirmation?                        | AssignTeamToTaskUI         | IE: Is responsible for user interactions.                                                                                                                                   |
| 	Step 5 		  	  | ...saving all data?                                 | TaskEntry                      | IE: Has its own data.                                                                                                                                                       |
| 			  	         | 	... validating all data (local validation)?        | TaskEntry                      | IE: owns all its data.                                                                                                                                                      |
| 			  	         | 	... validating all data (global validation)?       | AgendaRepository               | IE: knows all tasks.                                                                                                                                                        |
| Step 6  		     | 	... informing operation success?                   | AssignVehiclesToTaskUI         | IE: is responsible for user interactions.                                                                                                                                   |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* TeamRepository
* AgendaRepository

Other software classes (i.e. Pure Fabrication) identified:

* AssignTeamToTaskUI
* AssignTeamToTaskController

## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us023-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us023-class-diagram.svg)