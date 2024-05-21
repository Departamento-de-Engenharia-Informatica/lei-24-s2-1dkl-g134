package pt.ipp.isep.dei.esoft.project.domain;


public class TaskEntry {

    private String taskTitle;
    private String taskDescription;
    private urgencyLevel urgencyLevel;
    private State state;
    private int duration;
    private CustomDate date;

    public TaskEntry(String taskTitle, String taskDescription, urgencyLevel urgencyLevel, int duration) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.urgencyLevel = urgencyLevel;
        this.duration = duration;
        this.state = State.PENDING;
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


    public String getTaskDescription() {return taskDescription;}

    public urgencyLevel getUrgencyLevel() {return urgencyLevel;}

    public State getState() {return state;}

    public int getDuration() {return duration;}

    public String getTaskTitle() {return taskTitle;}

}
