# Supplementary Specification (FURPS+)

## Functionality

* The application must contain a login and authentication function, restricting access to its various functions to users with the adequate permissions.
* The application must contain a general navigation dashboard through which the user must have easy access to all of the application's features.
* The application must also provide the user with a general information dashboard displaying various pieces of relevant information about the green space to the user.
* The GSM must have full, unrestricted access to all dashboards and functionalities across the entire application.

## Usability

* The user interface must be as legible and understandable as possible.
* The application's different functionalities must be clearly and cleanly separated between dashboards, accessed through a global navigation dashboard.
* Each dashboard must unmistakably identify its corresponding function.
* Different dashboards should not overlap, as to reduce cluttering and keep the interface as simple and readable as possible.
* There is no particular aesthetic or design to follow.
* All input errors such as invalid inputs must be outlined to the user in a human-readable manner and without any technical jargon.
* Short, legible, understandable help on the usage of functionalities must be presented on-screen as that functionality is carried out.

## Reliability

* Fatal errors are unacceptable, and the application must never crash.
* All internal, non-I/O errors must be handled without compromising the application's availability.
* I/O errors such as invalid inputs must be prevented through input validation.

## Performance

* The application must be available for public use at all times, with as little downtime as possible.
* Response times on all tasks must be kept as low as possible.
* The usage of resources such as memory and CPU is limited to the hosting hardware's capacity, but should also be kept as low as possible.

## Supportability

* The application should be easily installable on almost any personal computer, requiring little to no technical know-how.
* The application must be compatible with as many desktop computer systems as possible, though no such compatibility is required for mobile systems.

## +

### Design Constraints

* All documentation will be developed in the English language, using Markdown formatting.
* All graphs and images will be developed and modelled in UML, and will be generated in SVG format.
* Development will utilize the IntelliJ IDEA editor.
* Development of the application's graphical interface will be developed in JavaFX 11.
* Development progress and version control will be handled using Bitbucket.
* Development will utilize the PlantUML plugin for IntelliJ IDEA as the preferred UML editor.
* The preferred Markdown editor and reader will be the integrated Markdown interpreter in IntelliJ IDEA.
* All documentation shall follow the standards established in the UC ESOFT.

### Implementation Constraints

* Business rule validation must be respected when recording and updating data.
* All of the code and implementation will be developed in the Java programming language.
* All methods must have associated unit tests, with the exception of I/O operations.
* All unit tests must be implemented using the JUnit 5 framework.
* Data persistence shall be ensured using object serialization rather than a database.
* The implementation will follow all the standards and good practices suggested in the UC PPROG: Javadoc, CamelCase, etc.

### Interface Constraints

* There is no external software the system must communicate with.

### Physical Constraints

* The system must be somewhat portable and capable of functioning in a relatively low-end host.