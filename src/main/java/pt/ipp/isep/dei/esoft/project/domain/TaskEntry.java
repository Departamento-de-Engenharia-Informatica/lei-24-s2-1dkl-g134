package pt.ipp.isep.dei.esoft.project.domain;

public class TaskEntry {

    private String taskDescription;
    private String urgency;
    private int duration;

    public TaskEntry(String taskDescription, String urgency, int duration) {
        this.taskDescription = taskDescription;
        this.urgency = urgency; //high, medium, low
        this.duration = duration; //in minutes??
    }
@Override
public boolean equals(Object o) {
    if (this == o) {
        return true;
    }
    if (!(o instanceof TaskEntry)) {
        return false;
    }
    TaskEntry taskEntry = (TaskEntry) o;
    return taskDescription.equals(taskEntry.taskDescription) && urgency.equals(taskEntry.urgency) &&
            duration == taskEntry.duration;
}


    public String getTaskDescription() {return taskDescription;}

    public String getUrgency() {return urgency;}

    public int getDuration() {return duration;}

}
