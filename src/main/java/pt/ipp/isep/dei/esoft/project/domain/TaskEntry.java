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
    private ArrayList<Vehicle> assignedVehicles = null;
    private ArrayList<Collaborator> assignedTeam = null;
    private CustomDate startDate = null, endDate = null;
    private CustomTime startTime = null, endTime = null;

    /**
     * Constructor for a new TaskEntry object. Any new TaskEntry object's state will be defined
     * as "PENDING" until any method that changes it is performed.
     * This method will throw an IllegalArgumentException if any field is null or blank, or if
     * the duration is below or equal to zero.
     * @param taskTitle The String representation of the new task's title.
     * @param taskDescription The String representation of the new task's description.
     * @param urgencyLevel The urgency level of this task, expressed with an urgencyLevel enumerator.
     * @param duration An int representing the duration of this task, in hours.
     * @param greenSpace The GreenSpace object associated with this task.
     */
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

    /**
     * Adds the necessary data to put this task in the Agenda. This method will change the
     * task's state to "PLANNED".
     * This method will throw an IllegalArgumentException if it receives any null or blank
     * values, or for any reason outlined in the CustomDate and CustomTime constructors.
     * @param date The String representation of this task's start date.
     * @param time The String representation of this task's start time.
     * @return This TaskEntry object, updated with the agenda data.
     */
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

    /**
     * Checks if this TaskEntry object is equal to another.
     * Two TaskEntries are considered equal if they have the same title and are on the same green space.
     * For information on how two green spaces being the same is verified, check the documentation of
     * GreenSpace.equals().
     * @param o The TaskEntry to compare against.
     * @return A boolean value representing if the tasks are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TaskEntry)) {
            return false;
        }
        TaskEntry taskEntry = (TaskEntry) o;
        return taskTitle.equalsIgnoreCase(taskEntry.getTaskTitle()) && greenSpace.equals(taskEntry.getGreenSpace());
    }

    /**
     * Returns the String representation of this task.
     * @return The String representation of this task.
     */
    @Override
    public String toString(){
        return taskTitle + " | " + taskDescription;
    }

    /**
     * Postpones this task to a new start date and time. Also changed the task's state to
     * "POSTPONED".
     * This method will throw an IllegalArgument exception if this task is currently not in
     * the agenda (more formally, if the task's state is "PENDING"), if it receives any null
     * or blank fields, if the new start date is after the current date, or for any
     * reason outlined in the CustomDate and CustomTime constructors.
     * @param date The String representation of the new start date.
     * @param time The String representation of the new start time.
     * @return An Optional object containing this task, with its values updated.
     */
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

    /**
     * Assigns a list of vehicles to this task, if the vehicle in question isn't already
     * assigned to the task.
     * This method will throw an IllegalArgumentException if it receives a null value, or
     * if the list of vehicles is empty.
     * @param vehicles The list of vehicles to assign to this task.
     * @return An Optional object containing a list of all vehicles actually assigned to the
     * task. If no vehicles were assigned, an empty Optional object.
     */
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

    /**
     * Checks if the vehicle in question
     * @param vehicle The vehicle to search for.
     * @return A boolean value representing if this task has this vehicle assigned to it.
     */
    public boolean hasVehicle(Vehicle vehicle) {
        if(vehicle == null){
            throw new IllegalArgumentException("Null fields not allowed");
        }
        return assignedVehicles.contains(vehicle);
    }

    /**
     * Assigns a team to this task, if the team's list of members is different from the
     * list of currently assigned collaborators to the task.
     * Should the team really be assigned to the task, each of the team's members will also
     * receive an e-mail (sent to the e-mail addressed registered on each team member's
     * Collaborator object) informing them of the assignment. In this proof-of-concept program,
     * this e-mail is nothing more than a text file created in an "emailSimulations" folder.
     * This method will throw an IllegalArgumentException if it receives any null fields, or
     * if the e-mail address from which to send the notification messages from isn't configured
     * in the config.properties file.
     * @param team The team to assign to this task.
     * @return An Optional object containing this task, if the team was actually assigned to it.
     * If the team ended up not being assigned, an empty Optional object instead.
     */
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

    /**
     * Gets this task's description.
     * @return The String representation of this task's description.
     */
    public String getTaskDescription() {return taskDescription;}

    /**
     * Gets this task's urgency level.
     * @return This task's urgency level, expressed with an urgencyLevel enumerator.
     */
    public urgencyLevel getUrgencyLevel() {return urgencyLevel;}

    /**
     * Gets this task's state.
     * @return This task's state, expressed with a State enumerator.
     */
    public State getState() {return state;}

    /**
     * Gets this task's duration in hours.
     * @return An int representing this task's duration in hours.
     */
    public int getDuration() {return duration;}

    /**
     * Gets this task's title.
     * @return A String representing this task's title.
     */
    public String getTaskTitle() {return taskTitle;}

    /**
     * Gets this task's assigned team.
     * @return The list of Collaborators that make up this task's team.
     */
    public ArrayList<Collaborator> getAssignedTeam() { return assignedTeam; }

    /**
     * Gets this task's assigned vehicles.
     * @return The list of Vehicles assigned to this task.
     */
    public ArrayList<Vehicle> getAssignedVehicles() { return assignedVehicles; }

    /**
     * Gets this task's start date.
     * @return The CustomDate object representing this task's start date.
     */
    public CustomDate getStartDate() {
        return startDate;
    }

    /**
     * Gets this task's start date in string form.
     * @return The String object representing this task's start date.
     */
    public String getStartDateString() {
        return startDate.toString();
    }

    /**
     * Gets this task's end date.
     * @return The CustomDate object representing this task's end date.
     */
    public CustomDate getEndDate() {
        return endDate;
    }

    /**
     * Gets this task's start time.
     * @return The CustomDate object representing this task's start time.
     */
    public CustomTime getStartTime() {
        return startTime;
    }

    /**
     * Gets this task's start time in a String format.
     * @return The String object representing this task's start time.
     */
    public String getStartTimeString() {
        return startTime.toString();
    }

    /**
     * Gets this task's end time.
     * @return The CustomDate object representing this task's end time.
     */
    public CustomTime getEndTime() {
        return endTime;
    }

    /**
     * Gets the String representation of this task's green space.
     * @return The String representation of this task's green space.
     */
    public String getGreenSpace() {
        return greenSpace.toString();
    }

    /**
     * Gets the GreenSpace object associated with this task.
     * @return The GreenSpace representation of this task's green space.
     */
    public GreenSpace getGreenSpaceObject() {
        return greenSpace;
    }

    /**
     * Checks if the list of collaborators assigned to this task is the same as another list.
     * This method will throw an IllegalArgumentException if the passed list is null or empty.
     * @param otherTeam The list of collaborators to compare against.
     * @return A boolean value representing if the list of collaborators currently assigned
     * to the task is the same as the list passed to this method.
     */
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

    /**
     * Cancels this task. All this does is change this task's state to "CANCELED".
     * @return An Optional object containing this task, with its updated state. If no
     * update was made, an empty Optional object instead.
     */
    public Optional<TaskEntry> cancelTask() {
        if (state == State.CANCELED) {
            return Optional.empty();
        } else {
            state = State.CANCELED;
            return Optional.of(this);
        }
    }

    /**
     * Marks this task as completed. All this does is change the task's state to "COMPLETED".
     * @return An Optional object containing this task, with its updated state. If no
     * update was made, an empty Optional object instead.
     */
    public Optional<TaskEntry> completeTask() {
        if (state == State.COMPLETED) {
            return Optional.empty();
        } else {
            state = State.COMPLETED;
            return Optional.of(this);
        }
    }


}
