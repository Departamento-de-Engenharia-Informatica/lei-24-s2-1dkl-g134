# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to postpone a task to an earlier date. 

	@Test
    void ensurePostponeTaskFailsForPastDate(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        assertThrows(IllegalArgumentException.class,
        () -> taskEntry.postponeTask("2005/01/10", "16:00"));
    }
	

**Test 2:** Check that it is not possible to postpone a task with null fields. 

	@Test
    void ensurePostponeTaskFailsForNullFields(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.postponeTask(null, null));
    }

**Test 3:** Check that it is not possible to postpone a pending task.

	@Test
    void ensurePostponeTaskFailsForPendingTasks(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.postponeTask("2005/01/20", "16:00"));
    }

_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### Class TaskEntry 

```java
public Optional<TaskEntry> postponeTask(String date, String time){
    if(this.state == State.PENDING){
        throw new IllegalArgumentException("Cannot postpone task not in agenda.");
    }
    if(date == null || time == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    date = date.trim();
    time = time.trim();
    if(date.isBlank() || time.isBlank()){
        throw new IllegalArgumentException("Blank fields not allowed.");
    }
    CustomDate newDate = new CustomDate(date);
    CustomTime newTime = new CustomTime(time);
    if(newDate.isAfterDate(this.startDate)){
        this.startDate=newDate;
        this.startTime=newTime;
        this.endTime = startTime.adjust(duration);
        this.endDate = startDate.adjust(duration / Bootstrap.dailyWorkHours);
        this.state=State.POSTPONED;
    }else{
        throw new IllegalArgumentException("Postponed date can't be before current date");
    }
    return Optional.of(this);
}
```

### Class AgendaRepository

```java
public Optional<TaskEntry> postponeTask(TaskEntry taskEntry, String date, String time) {
    if(taskEntry == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    TaskEntry testTaskEntry = new TaskEntry(taskEntry.getTaskTitle(), taskEntry.getTaskDescription(), taskEntry.getUrgencyLevel(), taskEntry.getDuration(), taskEntry.getGreenSpaceObject());
    testTaskEntry.addAgendaData(date, time);
    if(taskEntry.getAssignedVehicles() != null){
        if(!taskEntry.getAssignedVehicles().isEmpty()){
            for(Vehicle vehicle : taskEntry.getAssignedVehicles()){
                if(!isVehicleAvailable(vehicle, testTaskEntry)){
                    throw new IllegalArgumentException("One or more vehicles assigned to this task would not be available if it was postponed to this date and time!");
                }
            }
        }
    }
    if(taskEntry.getAssignedTeam() != null){
        if(!taskEntry.getAssignedTeam().isEmpty()){
            if(!isTeamAvailable(taskEntry.getAssignedTeam(), testTaskEntry)){
                throw new IllegalArgumentException("The team assigned to this task would not be available if it was postponed to this date and time!");
            }
        }
    }
    return taskEntry.postponeTask(date, time);
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a