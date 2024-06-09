# US006 - Create a Task 

## 4. Tests 

This US requires Session data that is not possible to create in Unit Tests. As such, it has no associated unit tests.

## 5. Construction (Implementation)

### Class GreenSpace 

```java
public boolean isCreatedBy(String email) {
    if(email.equalsIgnoreCase(creatorEmail)){
        return true;
    }
    return false;
}
```

### Class GreenSpaceRepository

```java
public Optional<ArrayList<GreenSpace>> getGreenSpacesManagedByUser(){
    ArrayList<GreenSpace> greenSpacesManagedByUser = new ArrayList<>();
    for (GreenSpace greenSpace : greenSpaces){
        if(greenSpace.isCreatedBy(ApplicationSession.getInstance().getCurrentSession().getUserEmail())){
            greenSpacesManagedByUser.add(greenSpace);
        }
    }
    if(greenSpacesManagedByUser.isEmpty()){
        return Optional.empty();
    }
    return Optional.of(greenSpacesManagedByUser);
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a