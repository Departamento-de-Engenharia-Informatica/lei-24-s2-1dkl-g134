# US027 - List Green Spaces Managed by GSM


## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Space Manager (GSM), I need to list all green spaces managed by me

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>The list of green spaces must be sorted by size in descending order (area in hectares should be used).
>The sorting algorithm to be used by the application must be defined through a configuration file. At least two sorting algorithms should be available.

**From the client clarifications:**

> **Question:**Dear client, which info about Green Spaces do you want the GSM see when listing? only the name ?

> **Answer:**Each de team can decide about the aspects related to UX/UI.

### 1.3. Acceptance Criteria

* **AC1:** The list of green spaces must be sorted by size in descending order using the specified sorting algorithm from the configuration file.

### 1.4. Found out Dependencies

* None.

### 1.5 Input and Output Data

**Input Data:**

* Sorting algorithm specified in the configuration file

**Output Data:**

* List of green spaces managed by the GSM, sorted by size in descending order.
* 
### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us027-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* None