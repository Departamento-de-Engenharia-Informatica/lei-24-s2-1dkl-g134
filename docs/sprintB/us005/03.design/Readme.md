# US005 - Automatic Team Generation 

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...                | Answer                 | Justification (with patterns)                                                                                                                            |
|:---------------|:-----------------------------------------------------------|:-----------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1  		     | 	... interacting with the actor?                           | GenarateTeamUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                            |
| 			  		        | 	... coordinating the US?                                  | GenarateTeamController | Controller: coordinates the interactions related to genarate a team  in the user interface (UI) and executes the logic needed to process these requests. |
| Step 2  		     | 	... displaying the form for the user to imput data?						 | GenarateTeamUI         | IE: Is responsible for interacting with the actor.                                                                                                       |
| Step 3         | ... knowing the team?                                      | TeamRepository         | IE: owns all the teams.                                                                                                                                  |
|                | ... saving selected team?                                  | GenarateTeamUI         | IE: manages the selected team data.                                                                                                                      |
|                | ... knowing the collaborator?                              | CollaboratorRepository | IE: owns all its collaborators.                                                                                                                          |
|                | ... saving selected collaborator?                          | GenarateTeamUI         | IE: manages the selected collaborator data.                                                                                                              |
| Step 4  		     | 	...displaying all the data?                               | GenarateTeamUI         | IE: Keeps all information before submission.                                                                                                             |
| 		             | 	...requesting confirmation?                               | GenarateTeamUI         | IE: Is responsible for user interactions.                                                                                                                |
| 	Step 5 		  	  | ...saving all data?                                        | Team                   | IE: Has its own data.                                                                                                                                    |
| 			  	         | 	... validating all data (local validation)?               | Team                   | IE: owns all its data.                                                                                                                                   |
| 			  	         | 	... validating all data (global validation)?              | TeamRepository         | IE: knows all check-ups.                                                                                                                                 |
| 	Step 6		  	   | 	... displaying team proposal?                             | GenarateTeamUI         | IE: manages the selected team                                                                                                                  |
| 	Step 7		  	   | 	... confirming team creation?                             | TeamRepository         | IE: manages the selected team                                                                                                                  |
| Step 8  		     | 	... creating a team ?                                     | GenarateTeamUI           | IE: is responsible for user interactions.                                                                                                                | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Organization
* Task

Other software classes (i.e. Pure Fabrication) identified: 

* CreateTaskUI  
* CreateTaskController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us005-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us005-class-diagram.svg)