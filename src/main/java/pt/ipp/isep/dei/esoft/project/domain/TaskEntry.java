package pt.ipp.isep.dei.esoft.project.domain;


public class TaskEntry {

    private String taskTitle;
    private String taskDescription;
    private urgencyLevel urgencyLevel;
    private State state;
    private int duration;

    public TaskEntry(String taskTitle, String taskDescription, urgencyLevel urgencyLevel, int duration, State state) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.urgencyLevel = urgencyLevel;
        this.duration = duration; //in minutes??
        this.state = state;
    }
@Override
public boolean equals(Object o) {

    if (!(o instanceof TaskEntry)) {
        return false;
    }
    TaskEntry taskEntry = (TaskEntry) o;
    return taskTitle.equalsIgnoreCase(taskEntry.getTaskTitle());
}


    public String getTaskDescription() {return taskDescription;}

    public urgencyLevel getUrgencyLevel() {return urgencyLevel;}

    public State getState() {return state;}

    public int getDuration() {return duration;}

    public String getTaskTitle() {return taskTitle;}
}
