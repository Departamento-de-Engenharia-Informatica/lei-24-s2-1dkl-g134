# US026 - Assign Vehicles to an Entry

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...         | Answer                         | Justification (with patterns)                                                                                                                                               |
|:---------------|:----------------------------------------------------|:-------------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1         | ... interacting with the actor?                     | AssignVehiclesToTaskUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                                               |
|                | ... coordinating the US?                            | AssignVehiclesToTaskController | Controller: coordinates the interactions related to assigning a skill to a collaborator in the user interface (UI) and executes the logic needed to process these requests. |
| Step 2         | ... displaying the form for the user to imput data? | AssignVehiclesToTaskUI         | IE: Is responsible for interacting with the actor.                                                                                                                          |
| Step 3         | ... knowing the Tasks?                              | AgendaRepository               | IE: owns all its tasks.                                                                                                                                                     |
|                | ... saving selected task?                           | AssignVehiclesToTaskUI         | IE: AssignVehiclesToTask manages the selected task data.                                                                                                                    |
|                | ... knowing the vehicle/s?                          | VehicleRepository              | IE: owns vehicles list.                                                                                                                                                     |
|                | ... saving selected vehicle/s?                      | AssignVehiclesToTaskUI         | IE: AssignVehiclesToTask manages the selected Skill data.                                                                                                                   |
| Step 4  		     | 	...displaying all the data?                        | AssignVehiclesToTaskUI         | IE: Keeps all information before submission.                                                                                                                                |
| 		             | 	...requesting confirmation?                        | AssignVehiclesToTaskUI         | IE: Is responsible for user interactions.                                                                                                                                   |
| 	Step 5 		  	  | ...saving all data?                                 | TaskEntry                      | IE: Has its own data.                                                                                                                                                       |
| 			  	         | 	... validating all data (local validation)?        | TaskEntry                      | IE: owns all its data.                                                                                                                                                      |
| 			  	         | 	... validating all data (global validation)?       | AgendaRepository               | IE: knows all tasks.                                                                                                                                                        |
| Step 6  		     | 	... informing operation success?                   | AssignVehiclesToTaskUI         | IE: is responsible for user interactions.                                                                                                                                   |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:


* AgendaRepository
* VehicleRepository
* TaskEntry

Other software classes (i.e. Pure Fabrication) identified:

* AssignVehiclesToTaskUI
* AssignVehiclesToTaskController
## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us026-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us026-class-diagram.svg)