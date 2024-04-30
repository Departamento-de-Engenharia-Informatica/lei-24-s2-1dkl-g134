# US004 - Assign a skill to collaborator

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID      | Question: Which class is responsible for...         | Answer                   | Justification (with patterns)                                                                                                                                               |
|:--------------------|:----------------------------------------------------|:-------------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1              | ... interacting with the actor?                     | AssignSkillUI            | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                                                               |
|                     | ... coordinating the US?                            | AssignSkillController    | Controller: coordinates the interactions related to assigning a skill to a collaborator in the user interface (UI) and executes the logic needed to process these requests. |
| Step 2              | ... displaying the form for the user to imput data? | AssignSkillUI            | IE: Is responsible for interacting with the actor.                                                                                                                          |
| Step 3              | ... knowing the collaborator?                       | CollaboratorsRepository  | IE: owns all its collaborators.                                                                                                                                             |
|                     | ... saving selected collaborator?                   | AssignSkillUI            | IE: AssignSkill manages the selected collaborator data.                                                                                                                     |
|                     | ... knowing the skill/s?                            | SkillRepository          | IE: owns skills list.                                                                                                                                                       |
|                     | ... saving selected skill/s?                        | AssignSkillUI            | IE: AssignSkill manages the selected Skill data.                                                                                                                            |
| Step 4  		          | 	...displaying all the data?                        | AssignSkillUI            | IE: Keeps all information before submission.                                                                                                                                |
| 		                  | 	...requesting confirmation?                        | AssignSkillUI            | IE: Is responsible for user interactions.                                                                                                                                   |
| 	Step 5 		  	       | ...saving all data?                                 | Collaborator             | IE: Has its own data.                                                                                                                                                       |
| 			  	              | 	... validating all data (local validation)?        | Collaborator             | IE: owns all its data.                                                                                                                                                      |
| 			  	              | 	... validating all data (global validation)?       | CollaboratorsRepository  | IE: knows all check-ups.                                                                                                                                                    |
| Step 6  		          | 	... informing operation success?                   | AssignSkillUI | IE: is responsible for user interactions.                                                                                                                                   |
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* CollaboratorRepository
* Collaborator

Other software classes (i.e. Pure Fabrication) identified:

* AssignSkillUI
* AssignSkillController


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us004-sequence-diagram-full.svg)


## 3.3. Class Diagram (CD)

![Class Diagram](svg/us004-class-diagram.svg)