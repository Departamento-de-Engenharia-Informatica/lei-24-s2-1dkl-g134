package pt.ipp.isep.dei.esoft.project.domain;

public class TaskEntry {

    public String greenSpace;
    public String taskDescription;
    public String urgency;
    public int duration;

    public TaskEntry(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {return taskDescription;}

    public String getUrgency() {return urgency;}

    public int getDuration() {return duration;}

    public String getGreenSpace() {return greenSpace;}
}
