# US011 - Data Collect from the User Portal


## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I want to be able to collect data from the user portal
about the use of the park, so that I may understand the use of the park
by different age groups.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> To analyse the use of the park by age groups,
a three-question survey was inserted in the user portal:

| Question                                        | Answer type                                                                                           |
|-------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| Age range                                       | 1 - Child (up to 15 years old) <br/>2 - Adult (beetwen 16 and 65 years old) <br/>3 - Senior (over 65 years old) |
| Would you recommend the park to the others?     | Y/N                                                                                                   |
| How many times do you visit the park per month? | Numeric                                                                                               |

> The obtained responses are recorded in the ”Inquiry.csv” file.<br>
– Indicate the type of each of the three variables.<br>
– Indicate the proportion of users from each age group who would
recommend the park to others.<br>
– Create a boxplot for each age group, regarding the monthly frequency of use of the park, and draw the main conclusions obtained
from this type of graph.


**From the client clarifications:**

> **Question:** 
>
> **Answer:** 

> **Question:** 
>
> **Answer:** 

### 1.3. Acceptance Criteria

* **AC1:** Programming Language: Python;
* **AC2:** Development environment: Jupyter Notebook;
* **AC3:** Work delivery format: A single Jupyter Notebook file, which contains
  all the work carried out;
* **AC4:** Each US must be composed of: (1) introduction (succinct and objective); (2) code and results, and (3) analysis and interpretation of the
  results;
* **AC5:** Formulas must be written in LaTeX;
* **AC6:** At the end of the file, you must indicate the contribution (in %) of each
  member of the group to the development of the work (the sum of all
  percentages must be 100%).

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