# US014 - Observe execution time of the US13 algorithm.
 


## 1. Requirements Engineering

### 1.1. User Story Description

As a QAM, I want to run tests for inputs of variable size, to  observe the asymptotic behavior of the execution time of the US13  algorithm.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The graphic referring to the asymptotic behavior of the execution running time tests should be presented in a time unit  that allows to distinguish the running times of all tested examples.

 

**From the client clarifications:**

> **Question:** 
>
> **Answer:** 

> **Question:** 
>
> **Answer:** 

### 1.3. Acceptance Criteria

* **AC1:**  : The tested algorithm must not result in an infinite loop.
* **AC2:**  : The graphic referring to the asymptotic behavior of the execution running time tests should be presented in a time unit that allows to distinguish the running times of all tested examples.

### 1.4. Found out Dependencies

* There is a dependency on "US013 - Route plannig and pipeline deplotment algorithm " as there must be amjm correctly developed algorithm to be tested 

### 1.5 Input and Output Data

**Input Data:**

* None

**Output Data:**

* Information about the execution time of the US13 algorithm.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us014-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* None