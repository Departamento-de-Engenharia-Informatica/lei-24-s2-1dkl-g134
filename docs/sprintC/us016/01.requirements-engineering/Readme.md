# US016 - Apply Polynomial Regression to US014 Data


## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Space Manager, I want to know which piece(s) of equipment is/are used in each day.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> Consider that the park has the following equipment: walking paths, children’s playground, picnic area, and exercise machines (gymnastics equipment). At the park exit there is an electronic device with a list of all the equipment, in which the user(s) must indicate the equipment they used that day.

> In the file ”EquipmentUsed.csv” the choices of 1000 users are recorded. Make a pie chart representing, in percentage, the use of each piece of equipment.

**From the client clarifications:**

> **Question:** None.
>
> **Answer:** None.

### 1.3. Acceptance Criteria

* **AC1:** The pie chart is successfully created, accurately represents the registered data, and is presented to the user.

### 1.4. Found out Dependencies

* None.

### 1.5 Input and Output Data

**Input Data:**

* None

**Output Data:**

* Pie chart representing, in percentage, the data available in the EquipmentUsed.csv file.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us010-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* None