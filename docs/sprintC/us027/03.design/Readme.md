# US027 - List Managed Green Spaces

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID | Question: Which class is responsible for...          | Answer               | Justification (with patterns)                                                                                 |
|:---------------|:-----------------------------------------------------|:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1         | 	... interacting with the actor?                     | GetGreenSpacesManagedByUserUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 	   Step 2     | 	... coordinating the US?                            | GreenSpaceController | Controller                                                                                                    |
| Step 3         | ...retrieving managed green spaces?                  | GreenSpaceRepository     | IE: Knows all managed green spaces' data and has methods to retrieve them.                     |
| 	     Step 4   | ...providing access to configuration file?           | GreenSpaceController         | IE: Is responsible for coordinating the retrieval and usage of the configuration file.                                                            | 
| Step 5	        | 	...sorting green spaces by size?            | GreenSpaceController         | IE: Is responsible for coordinating the sorting algorithm based on configuration.                                                   |
| Step 6         | 	...displaying the sorted list to the user?                         | GetGreenSpacesManagedByUserUI         | IE: Is responsible for interacting with the actor to display the sorted list.                                                                     |
| Step 7	  	     | ...retrieving and using multiple sorting algorithms?      | GreenSpaceController            | IE: Manages the retrieval and usage of multiple sorting algorithms.                                                                                         |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:


* GreenSpaceRepository
* GreenSpaceController
* GreenSpaceUI
* GreenSpace


Other software classes (i.e. Pure Fabrication) identified:

* GetGreenSpacesManagedByUserUI
## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us027-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us027-class-diagram.svg)