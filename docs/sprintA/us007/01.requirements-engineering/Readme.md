# US006 - Register a Vehicle 


## 1. Requirements Engineering

### 1.1. User Story Description

As an FM, I wish to register a vehicle including Brand, Model, Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Check-up Frequency (in Kms).

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> Vehicles are needed to carry out the tasks assigned to the teams as well as to transport machines and equipment. This type of vehicle can be only for passengers or mixed, light or heavy, open box or closed vans or trucks.

> As for machines, MS has tractors, backhoe loaders and rotating machines, lawn-mowers, among others. The equipment can be greatly diverse, such as sprayers, lifting platforms, chainsaws, brush cutters, blowers, ladders, cisterns and the various elements that can be attached to tractors, such as disc harrows, weeders, aerators and scarifiers

**From the client clarifications:**

> **Question:** Should the application identify a registered vehicle by a serial number or another attribute?
>
> **Answer:** By Plate ID.

### 1.3. Acceptance Criteria

* **AC1:** The vehicle is successfully recorded and saved in the system.

### 1.4. Found out Dependencies

* None

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * Brand
    * Model 
    * Tare
    * Gross Weight
    * Current Km
    * Register Date
    * Acquisition Date
    * Maintenance/Check-up Frequency
    * Plate Number
	
* Selected or typed data:
    * Type

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us007-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* It's unclear whether or not the Type is a form of selected or typed data. This is because the vehicle's type may be restricted to a select few categories as the specification documentation implies, but more clarification is needed.