# US008 - List Vehicles Needing Check-up 


## 1. Requirements Engineering

### 1.1. User Story Description

As a Fleet Manager, I want to list the vehicles needing the check-up.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> Vehicles are needed to carry out the tasks assigned to the teams as well as to transport machines and equipment. This type of vehicle can be only for passengers or mixed, light or heavy, open box or closed vans or trucks.

> As for machines, MS has tractors, backhoe loaders and rotating machines, lawn-mowers, among others. The equipment can be greatly diverse, such as sprayers, lifting platforms, chainsaws, brush cutters, blowers, ladders, cisterns and the various elements that can be attached to tractors, such as disc harrows, weeders, aerators and scarifiers

**From the client clarifications:**

> **Question:** What information must be presented about each vehicle in the list?
>
> **Answer:** Plate number, brand, model, current km, checkup frequency, and kms at last checkup.

> **Question:** What is the criteria for a vehicle to be on the list?
>
> **Answer:** The difference between its current kms and its kms at last checkup must have either exceeded the checkup frequency or have a difference with it lower than 5%.

### 1.3. Acceptance Criteria

* **AC1:** All vehicles fitting the criteria for requiring check-up are listed.

### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register Vehicle" as there must be at least one vehicle registered to check if any vehicles require a check-up.

### 1.5 Input and Output Data

**Input Data:**

* None

**Output Data:**

* List of vehicles requiring check-up
* Plate number, brand, model, current km, checkup frequency, km at last checkup of every vehicle in the list
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us008-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* None