# US028 - Consult Tasks Assigned to Collaborator

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...        | Answer                          | Justification (with patterns)                                                                                 |
|:---------------|:---------------------------------------------------|:--------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                   | ListTasksBetweenDatesUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			            | 	... coordinating the US?                          | ListTasksBetweenDatesController | Controller                                                                                                    |
| Step 2  		     | ...knowing the task entry list?                    | AgendaRepository                | IE: Knows all task entries' data and has methods to retrieve tasks that can be postponed.                     |                                           |
| 		             | 	...sorting the task entry list?                   | AgendaRepository            | IE: Sorts all task entries                                                                                    |
| Step 3 	    	  | 	... showing list of user's tasks?                 | ListTasksBetweenDatesUI            | IE: Is responsible for interacting with the actor.                                                            |
| 		             | 	...requesting confirmation?                       | ListTasksBetweenDatesUI            | IE: Is responsible for user interactions.                                                                     |
| 	              | ...displaying the form for the user to input data? | ListTasksBetweenDatesUI            | IE:Is responsible for interacting with the actor.                                                             |
|                | 	... showing tasks assign to current user?         | AgendaRepository            |IE: Is responsible for interacting with the actor                                                                   |
| Step 4  		     | 	... informing operation success?                  | ListTasksBetweenDatesUI            | IE: is responsible for user interactions.                                                                     |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* GenerateMaintenanceReportUI
* GenerateMaintenanceReportController
* VehicleRepository
* RepositorySingleton

Other software classes (i.e. Pure Fabrication) identified:

* None

## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us008-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us008-class-diagram.svg)