package pt.ipp.isep.dei.esoft.project.domain;


import java.util.ArrayList;
import java.util.Optional;

public class TaskEntry {

    private String taskTitle;
    private String taskDescription;
    private urgencyLevel urgencyLevel;
    private State state;
    private int duration;
    private GreenSpace greenSpace;
    private ArrayList<Vehicle> assignedVehicles;
    private CustomDate date;

    public TaskEntry(String taskTitle, String taskDescription, urgencyLevel urgencyLevel, int duration, GreenSpace greenSpace) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.urgencyLevel = urgencyLevel;
        this.duration = duration;
        this.state = State.PENDING;
        this.greenSpace = greenSpace;
        assignedVehicles = new ArrayList<>();

    }

    public TaskEntry addAgendaData(State state, String date){
        this.state = state;
        this.date = new CustomDate(date);
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

    public Optional<TaskEntry> postponeTask(String date){
        CustomDate newDate = new CustomDate(date);
        if(newDate.isAfterDate(this.date)){
            this.date=newDate;
            this.state=State.POSTPONED;
        }else{
            throw new IllegalArgumentException("Postponed date can't be before current date");
        }
        return Optional.of(this);
    }

    public Optional<ArrayList<Vehicle>> assignVehicles(ArrayList<Vehicle> vehicles){
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
    public boolean hasVehicle(Vehicle vehicle) {return assignedVehicles.contains(vehicle);}
    public String getTaskDescription() {return taskDescription;}

    public urgencyLevel getUrgencyLevel() {return urgencyLevel;}

    public State getState() {return state;}

    public int getDuration() {return duration;}

    public String getTaskTitle() {return taskTitle;}



}
