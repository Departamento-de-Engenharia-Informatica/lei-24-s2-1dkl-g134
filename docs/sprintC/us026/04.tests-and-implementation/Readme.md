# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to verify the availability of a null vehicle. 

	@Test
    void ensureIsVehicleAvailableFailsWithNullFields(){
        AgendaRepository agendaRepository = new AgendaRepository();
        assertThrows(IllegalArgumentException.class, () -> {
            agendaRepository.isVehicleAvailable(null, null);
        });
    }


## 5. Construction (Implementation)

### Class TaskEntry 

```java
public Optional<ArrayList<Vehicle>> assignVehicles(ArrayList<Vehicle> vehicles){
    if(vehicles == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    if(vehicles.isEmpty()){
        throw new IllegalArgumentException("List of vehicles to assign must contain one or more vehicles.");
    }
    ArrayList<Vehicle> vehiclesToAssign = new ArrayList<Vehicle>();
    boolean anyVehicleAssigned = false;
    for(Vehicle vehicle : vehicles){
        if(!hasVehicle(vehicle)){
            assignedVehicles.add(vehicle);
            vehiclesToAssign.add(vehicle);
            anyVehicleAssigned = true;
        }
    }
    if(anyVehicleAssigned){
        return Optional.of(vehiclesToAssign);
    }
    return Optional.empty();
}

public boolean hasVehicle(Vehicle vehicle) {
    if(vehicle == null){
        throw new IllegalArgumentException("Null fields not allowed");
    }
    return assignedVehicles.contains(vehicle);
}
```

### Class AgendaRepository

```java
public Optional<ArrayList<Vehicle>> assignVehiclesToTask(TaskEntry taskEntry, ArrayList<Vehicle> vehicles) {
    if(taskEntry == null || vehicles == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    return agenda.get(agenda.indexOf(taskEntry)).assignVehicles(vehicles);
}

public boolean isVehicleAvailable(Vehicle vehicle, TaskEntry taskEntry) {
    if(taskEntry == null || vehicle == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    for(TaskEntry taskToCompare : agenda){
        if(taskToCompare.equals(taskEntry) || taskToCompare.getState() == State.CANCELED || taskToCompare.getState() == State.COMPLETED){
            continue;
        }
        if(taskToCompare.hasVehicle(vehicle)){
            if((taskToCompare.getStartDate().isAfterDate(taskEntry.getEndDate()) && !taskToCompare.getStartDate().equals(taskEntry.getEndDate()) || !taskToCompare.getEndDate().isAfterDate(taskEntry.getStartDate()))){
                continue;
            }
            if(taskEntry.getStartDate().equals(taskToCompare.getEndDate())){
                if(taskEntry.getStartTime().getHour() > taskToCompare.getEndTime().getHour()){
                    continue;
                }
            }
            if(taskEntry.getEndDate().equals(taskToCompare.getStartDate())){
                if(taskEntry.getEndTime().getHour() < taskToCompare.getStartTime().getHour()){
                    continue;
                }
            }
            return false;
        }
    }
    return true;
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a