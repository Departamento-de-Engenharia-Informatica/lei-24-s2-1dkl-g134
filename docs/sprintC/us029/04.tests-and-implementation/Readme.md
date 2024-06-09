# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to complete a null task. 

	@Test
    void testCompleteTaskFailsWithNullTask(){
        AgendaRepository agendaRepository = new AgendaRepository();
        assertThrows(IllegalArgumentException.class,
                () -> agendaRepository.completeTask(null));
    }

## 5. Construction (Implementation)

### Class TaskEntry 

```java
public Optional<TaskEntry> completeTask() {
    if (state == State.COMPLETED) {
        return Optional.empty();
    } else {
        state = State.COMPLETED;
        return Optional.of(this);
    }
}
```

### Class AgendaRepository

```java
public Optional<TaskEntry> completeTask(TaskEntry taskEntry){
    if(taskEntry == null){
        throw new IllegalArgumentException("Null fields not allowed");
    }
    return agenda.get(agenda.indexOf(taskEntry)).completeTask();
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a