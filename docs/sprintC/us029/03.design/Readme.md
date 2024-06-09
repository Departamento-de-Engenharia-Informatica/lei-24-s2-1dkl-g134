# US029 - Record Completion of a Task

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...   | Answer                 | Justification (with patterns)                                                                                 |
|:---------------|:----------------------------------------------|:-----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1         | 	... interacting with the actor?              | CompleteTaskUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 	              | 	... coordinating the US?                     | CompleteTaskController | Controller                                                                                                    |
| Step 2         | ...knowing the task entry list?               | AgendaRepository       | IE: Knows all task entries' data and has methods to retrieve tasks that can be postponed.                     |
| 	              | ... Showing list of user's tasks?             | CompleteTaskUI         | IE: Is responsible for interacting with the actor.                                                            | 
| Step 3 	       | 	...temporarily saving the inputted data?     | CompleteTaskUI         | IE: Is responsible for saving the imputed data temporarily.                                                   |
| Step 4         | 	...requesting confirmation?                  | CompleteTaskUI         | IE: Is responsible for user interactions.                                                                     |
| Step 5		  	    | ...saving all data?                           | TaskEntry              | IE: Has its own data.                                                                                         |
| 			  	         | 	... validating all data (local validation)?  | TaskEntry              | IE: owns all its data.                                                                                        |
| 			  	         | 	... validating all data (global validation)? | AgendaRepository       | IE: knows all tasks entries.                                                                                  |
| Step 6  		     | 	... informing operation success?             | CompleteTaskUI         | IE: is responsible for user interactions.                                                                     |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:


* AgendaRepository
* TaskEntry

Other software classes (i.e. Pure Fabrication) identified:

* CompleteTaskUI
* CompleteTaskController
## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us029-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us029-class-diagram.svg)