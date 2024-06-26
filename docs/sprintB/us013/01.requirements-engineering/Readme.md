# US13 - Route Planning and Pipeline Deployment Algorithm


## 1. Requirements Engineering

### 1.1. User Story Description

As a Green Space Manager,  I want to apply an algorithm that returns the routes to be opened and pipes needed to be laid with a minimum accumulated cost, ensuring that all points are adequately supplied.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The use of computer systems and namely powerful algorithms can save materials like pipes, reduce the time required for the planning and installation, and allow to create more efficient irrigation systems. This becomes even more relevant when considering the installation of irrigation systems in parks that are already in operation, because periods of construction prevent their normal operation.

**From the client clarifications:**

> Questions: None

### 1.3. Acceptance Criteria

* **AC1:** All implemented procedures must only use primitive operations, and not existing functions in JAVA libraries.
* **AC2:** The map presented must have a minimum associated cost to its pipes

### 1.4. Found out Dependencies

* There is a dependency on US12 - "Import Irrigation System Planning File" because all possible pipe routes are needed in order to execute the algorithm.

### 1.5 Input and Output Data

**Input Data:**

* None

**Output Data:**

* Routes to be opened and pipes needed to be laid with a minimum accumulated cost, ensuring that all points are adequately supplied.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram](svg/us13-system-sequence-diagram-main-solution.svg)

### 1.7 Other Relevant Remarks

* None