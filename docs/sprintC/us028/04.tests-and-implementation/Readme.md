# US006 - Create a Task 

## 4. Tests 

This US requires Session data that cannot be accessed in unit tests. So, this US has no unit tests.

## 5. Construction (Implementation)

### Class CollaboratorRepository 

```java
public Optional<Collaborator> getCurrentUserCollaborator() throws InvalidRoleException{
    if(!ApplicationSession.getInstance().getCurrentSession().isLoggedInWithRole(AuthenticationController.ROLE_COLLAB)){
        throw new InvalidRoleException("Attempting to search for current collaborator with non-collaborator account");
    }
    String useremail = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    for(Collaborator collaborator : collaborators){
        if(useremail.equals(collaborator.getEmail())){
            return Optional.of(collaborator);
        }
    }
    return Optional.empty();
}
```

### Class AgendaRepository

```java
public Optional<ArrayList<TaskEntry>> getCurrentUserTasksBetweenTwoDates(String firstDate, String secondDate) throws InvalidRoleException, CollaboratorNotFoundException {
    if(firstDate == null || secondDate == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    CustomDate start = new CustomDate(firstDate);
    CustomDate end = new CustomDate(secondDate);
    ArrayList<TaskEntry> foundTasks = new ArrayList<>();
    if (start.isAfterDate(end)) {
        throw new IllegalArgumentException("First date must be before the second date.");
    }
    Optional<Collaborator> currentCollaborator = Repositories.getInstance().getCollaboratorRepository().getCurrentUserCollaborator();
    if (currentCollaborator.isEmpty()) {
        throw new CollaboratorNotFoundException("No collaborator corresponding to your email and name was found in the system.");
    }
    for (TaskEntry taskEntry : agenda) {
        if(taskEntry.getAssignedTeam() == null){
            continue;
        }
        if (taskEntry.getAssignedTeam().contains(currentCollaborator.get())) {
            if (taskEntry.getStartDate().isAfterDate(start) && !taskEntry.getStartDate().isAfterDate(end)) {
                foundTasks.add(taskEntry);
            }
        }
    }
    if (foundTasks.isEmpty()) {
        return Optional.empty();
    } else return Optional.of(foundTasks);
}
```


## 6. Integration and Demo 

* A new option on the Employee menu options was added.

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a