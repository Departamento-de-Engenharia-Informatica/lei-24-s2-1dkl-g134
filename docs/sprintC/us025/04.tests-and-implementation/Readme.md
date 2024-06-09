# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to cancel a null Task. 

	@Test
    void testCancelTaskFailsWithNullTask(){
        AgendaRepository agendaRepository = new AgendaRepository();
        assertThrows(IllegalArgumentException.class,
                () -> agendaRepository.cancelTask(null));
    }


## 5. Construction (Implementation)

### Class AgendaRepository 

```java
public Optional<TaskEntry> cancelTask(TaskEntry taskEntry) {
    if(taskEntry == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    return agenda.get(agenda.indexOf(taskEntry)).cancelTask();
}
```

### Class TaskEntry

```java
public Optional<TaskEntry> cancelTask() {
    if (state == State.CANCELED) {
        return Optional.empty();
    } else {
        state = State.CANCELED;
        return Optional.of(this);
    }
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a