# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the TaskEntry with null values. 

	@Test
    void ensureNoNullFieldsAllowed(){
        assertThrows(IllegalArgumentException.class,
        () -> new TaskEntry(null, null, null, 50, null));
    }
	

**Test 2:** Check that it is not possible to create an instance of the Task class with a non-positive duration. 

	@Test
    void ensureDurationGreaterThanZero(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        assertThrows(IllegalArgumentException.class,
                () -> new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 0, greenSpace));
    }

**Test 3:** Check that it is not possible to create duplicate tasks.

	@Test
    void ensureDuplicatesNotAllowed(){
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        agendaRepository.add(taskEntry, "2005/01/20", "11:00");

        assertTrue(agendaRepository.add(taskEntry, "2005/01/20", "11:00").isEmpty());
    }

## 5. Construction (Implementation)

### Class TaskEntry 

```java
public TaskEntry(String taskTitle, String taskDescription, urgencyLevel urgencyLevel, int duration, GreenSpace greenSpace) {
    if(taskTitle == null || taskDescription == null || urgencyLevel == null || greenSpace == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    taskTitle = taskTitle.trim();
    taskDescription = taskDescription.trim();
    if(taskTitle.isBlank() || taskDescription.isBlank()){
        throw new IllegalArgumentException("Blank fields not allowed.");
    }
    if(duration <= 0){
        throw new IllegalArgumentException("Duration must be a number greater than 0.");
    }
    this.taskTitle = taskTitle;
    this.taskDescription = taskDescription;
    this.urgencyLevel = urgencyLevel;
    this.duration = duration;
    this.state = State.PENDING;
    this.greenSpace = greenSpace;
    assignedVehicles = new ArrayList<>();
    assignedTeam = null;
}

@Override
public boolean equals(Object o) {
    if (!(o instanceof TaskEntry)) {
        return false;
    }
    TaskEntry taskEntry = (TaskEntry) o;
    return taskTitle.equalsIgnoreCase(taskEntry.getTaskTitle()) && greenSpace.equals(taskEntry.getGreenSpaceObject());
}
```

### Class AgendaRepository

```java
public Optional<TaskEntry> add(TaskEntry task, String date, String time) {
    if (agenda.contains(task)) {
        return Optional.empty();
    }

    task.addAgendaData(date, time);

    agenda.add(task);
    return Optional.of(task);
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a