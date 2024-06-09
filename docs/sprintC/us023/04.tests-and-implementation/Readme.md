# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to check the availability of a null team. 

	@Test
    void ensureIsTeamAvailableFailsWithNullFields(){
        AgendaRepository agendaRepository = new AgendaRepository();
        assertThrows(IllegalArgumentException.class, () -> {
            agendaRepository.isTeamAvailable(null, null);
        });
    }
	

## 5. Construction (Implementation)

### Class TaskEntry 

```java
public Optional<TaskEntry> assignTeam(Team team) throws IOException {
    if(team == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    if(ApplicationSession.getInstance().getProperties().getProperty("Email.Service") == null){
        throw new IllegalArgumentException("Email service not configured.");
    }
    ArrayList<Collaborator> teamMembers = team.getTeamMembers();
    boolean discrepancyFound = false;
    if(assignedTeam != null){
        if(assignedTeam.size() != teamMembers.size()){
            discrepancyFound = true;
        }else{
            for(Collaborator member : teamMembers){
                if(!assignedTeam.contains(member)){
                    discrepancyFound = true;
                    break;
                }
            }
        }
        if(!discrepancyFound){
            return Optional.empty();
        }
    }

    assignedTeam = new ArrayList<>();
    assignedTeam.addAll(teamMembers);

    File simulationDirectory = new File("emailSimulations");
    if(!simulationDirectory.exists() || !simulationDirectory.isDirectory()){
        simulationDirectory.mkdir();
    }
    for(Collaborator collaborator : teamMembers){
        File email = new File(simulationDirectory+"\\email-"+ collaborator.getName()+".txt");
        if(email.isFile()){
            email.delete();
        }
        FileWriter emailCreator = new FileWriter(email.getPath());
        emailCreator.append("From: "+ ApplicationSession.getInstance().getProperties().getProperty("Email.Service")+"\n");
        emailCreator.append("To: " + collaborator.getName() + "\n");
        emailCreator.append("Address: " + collaborator.getEmail() + "\n");
        emailCreator.append("Subject: Assignment to task '"+taskTitle+"'\n");
        emailCreator.append("Message:\n\n");
        emailCreator.append("Dear collaborator,\nAs part of the team comprised of the members:\n");
        emailCreator.append(team.toString());
        emailCreator.append("You and your teammates have been assigned to the task '"+taskTitle+"'.\n");
        emailCreator.append("Task description: "+taskDescription+"\n");
        emailCreator.append("This task will take place in the following green space: "+greenSpace.toString()+"\n");
        emailCreator.append("The address of this green space is: "+greenSpace.getAddress()+"\n");
        emailCreator.append("This task is scheduled to start on the following date and time: "+ startDate.toString()+" "+startTime.toString()+"\n");
        emailCreator.append("And is scheduled to end on the following date and time: "+ endDate.toString()+" "+endTime.toString()+"\n");
        emailCreator.append("This task is expected to have a duration of "+duration+" hours.\n");
        emailCreator.append("The urgency level of this task is: "+urgencyLevel+"\n");
        emailCreator.append("\nThank you, and good work!");
        emailCreator.close();
    }

    return Optional.of(this);
}
```

### Class AgendaRepository

```java
public Optional<TaskEntry> assignTeamToTask(TaskEntry taskEntry, Team team) throws IOException {
    if(taskEntry == null || team == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    return agenda.get(agenda.indexOf(taskEntry)).assignTeam(team);
}

public boolean isTeamAvailable(ArrayList<Collaborator> team, TaskEntry taskEntry) {
    if(taskEntry == null || team == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    for(TaskEntry taskToCompare : agenda){
        if(taskToCompare.equals(taskEntry) || taskToCompare.getState() == State.CANCELED || taskToCompare.getState() == State.COMPLETED){
            continue;
        }
        if(taskToCompare.isSameTeam(team)){
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

public boolean isSameTeam(ArrayList<Collaborator> otherTeam){
    if(otherTeam == null){
        throw new IllegalArgumentException("Null fields not allowed.");
    }
    if(otherTeam.isEmpty()){
        throw new IllegalArgumentException("Other team is empty.");
    }
    if(assignedTeam == null){
        return false;
    }
    if(assignedTeam.size() != otherTeam.size()){
        return false;
    }
    for(Collaborator collaborator : otherTeam){
        if(!assignedTeam.contains(collaborator)){
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