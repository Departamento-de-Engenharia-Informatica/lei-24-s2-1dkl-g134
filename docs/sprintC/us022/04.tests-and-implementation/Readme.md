# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that duplicate entries are not allowed. 

	@Test
    void ensureDuplicatesNotAllowed(){
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        agendaRepository.add(taskEntry, "2005/01/20", "11:00");

        assertTrue(agendaRepository.add(taskEntry, "2005/01/20", "11:00").isEmpty());
    }
	

**Test 2:** Check that it is not possible to add null agenda data. 

	@Test
    void ensureAgendaNoNullFieldsAllowed(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.addAgendaData(null, null));
    }


## 5. Construction (Implementation)

### Class TaskEntry 

```java
public TaskEntry addAgendaData(String date, String time){
    if(date == null || time == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    date = date.trim();
    if(date.isBlank() || time.isBlank()){
        throw new IllegalArgumentException("Blank fields not allowed.");
    }
    this.startDate = new CustomDate(date);
    this.startTime = new CustomTime(time);
    this.endTime = startTime.adjust(duration);
    this.endDate = startDate.adjust(duration / Bootstrap.dailyWorkHours);
    this.state = State.PLANNED;
    return this;
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