# US008 - List Vehicles Needing Check-up

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID  | Question: Which class is responsible for...          | Answer                              | Justification (with patterns)                                                                                 |
|:----------------|:-----------------------------------------------------|:------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1  		      | 	... interacting with the actor?                     | GenerateMaintenanceReportUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			             | 	... coordinating the US?                            | GenerateMaintenanceReportController | Controller                                                                                                    |
|                 | ...knowing the vehicles needing maintenance?         | VehicleRepository                   | IE: Knows all vehicle data and has methods to retrieve vehicles needing maintenance.                          |
| 	               | 	...formatting the report data?                      | GenerateMaintenanceReportUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 	        Step 2 | 	...generating the list vehicles in need of checkup? | VehicleRepository                   | IE: Knows all vehicles and respective data.                                                                   |
|                 | 	... displaying the generated list                   | GenerateMaintenanceReportUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |

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