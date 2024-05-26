package pt.ipp.isep.dei.esoft.project.domain;


import javafx.application.Application;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class TaskEntry implements Serializable {

    private String taskTitle;
    private String taskDescription;
    private urgencyLevel urgencyLevel;
    private State state;
    private int duration;
    private GreenSpace greenSpace;
    private ArrayList<Vehicle> assignedVehicles;
    private ArrayList<Collaborator> assignedTeam;
    private CustomDate startDate, endDate;
    private CustomTime startTime, endTime;

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

    public TaskEntry addAgendaData(String date, String time){
        if(date == null || time == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        date = date.trim();
        if(date.isBlank() || time.isBlank()){
            throw new IllegalArgumentException("Blank fields not allowed.");
        }
        this.state = State.PLANNED;
        this.startDate = new CustomDate(date);
        this.startTime = new CustomTime(time);
        this.endTime = startTime.adjust(duration);
        this.endDate = startDate.adjust(duration / Bootstrap.dailyWorkHours);
        return this;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof TaskEntry)) {
            return false;
        }
        TaskEntry taskEntry = (TaskEntry) o;
        return taskTitle.equalsIgnoreCase(taskEntry.getTaskTitle());
    }

    @Override
    public String toString(){
        return taskTitle + " | " + taskDescription;
    }

    public Optional<TaskEntry> postponeTask(String date, String time){
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

    public Optional<ArrayList<Vehicle>> assignVehicles(ArrayList<Vehicle> vehicles){
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
        return assignedVehicles.contains(vehicle);
    }

    public Optional<TaskEntry> assignTeam(Team team) throws IOException {
        if(team == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        if(ApplicationSession.getInstance().getProperties().getProperty("Email.Service") == null){
            throw new IllegalArgumentException("Email service not configured.");
        }
        ArrayList<Collaborator> teamMembers = team.getTeamMembers();
        boolean discrepancyFound = false;
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
            emailCreator.append("Subject: Assingment to task '"+taskTitle+"'\n");
            emailCreator.append("Message:\n\n");
            emailCreator.append("Dear collaborator,\nAs part of the team comprised of the members:\n");
            emailCreator.append(team.toString());
            emailCreator.append("You and your teammates have been assigned to the task '"+taskTitle+"'.\n");
            emailCreator.append("Task description: "+taskDescription+"\n");
            emailCreator.append("This task will take place in in the following green space: "+greenSpace.toString()+"\n");
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
    public String getTaskDescription() {return taskDescription;}

    public urgencyLevel getUrgencyLevel() {return urgencyLevel;}

    public State getState() {return state;}

    public int getDuration() {return duration;}

    public String getTaskTitle() {return taskTitle;}

    public ArrayList<Collaborator> getAssignedTeam() { return assignedTeam; }

    public ArrayList<Vehicle> getAssignedVehicles() { return assignedVehicles; }

    public CustomDate getStartDate() {
        return startDate;
    }

    public CustomDate getEndDate() {
        return endDate;
    }

    public CustomTime getStartTime() {
        return startTime;
    }

    public CustomTime getEndTime() {
        return endTime;
    }

    public String getGreenSpace() {
        return greenSpace.toString();
    }

    public boolean isSameTeam(ArrayList<Collaborator> otherTeam){
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

    public Optional<TaskEntry> cancelTask() {
        if (state == State.CANCELED) {
            return Optional.empty();
        } else {
            state = State.CANCELED;
            return Optional.of(this);
        }
    }


}
