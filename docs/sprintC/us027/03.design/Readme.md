# US024 - Cancel Entry

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...          | Answer               | Justification (with patterns)                                                                                 |
|:---------------|:-----------------------------------------------------|:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1         | 	... interacting with the actor?                     | CancelTaskUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 	              | 	... coordinating the US?                            | CancelTaskController | Controller                                                                                                    |
| Step 2         | ...knowing the task entry list?                      | AgendaRepository     | IE: Knows all task entries' data and has methods to retrieve tasks that can be postponed.                     |
| 	              | ... displaying the form for the user to input data?  | CancelTaskUI         | IE: Is responsible for interacting with the actor.                                                            | 
| Step 3 	       | 	...temporarily saving the inputted data?            | CancelTaskUI         | IE: Is responsible for saving the imputed data temporarily.                                                   |
| Step 4         | 	...requesting confirmation?                         | CancelTaskUI         | IE: Is responsible for user interactions.                                                                     |
| Step 5		  	    | ...saving all data?                                  | TaskEntry            | IE: Has its own data.                                                                                         |
| 			  	         | 	... validating all data (local validation)?         | TaskEntry            | IE: owns all its data.                                                                                        |
| 			  	         | 	... validating all data (global validation)?        | AgendaRepository     | IE: knows all tasks entries.                                                                                  |
| Step 6  		     | 	... informing operation success?                    | CancelTaskUI         | IE: is responsible for user interactions.                                                                     |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:


* AgendaRepository
* TaskEntry

Other software classes (i.e. Pure Fabrication) identified:

* CancelTaskUI
* CancelTaskController
## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us025-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us025-class-diagram.svg)