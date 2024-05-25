package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoListRepository implements Serializable {
    private List<TaskEntry> toDoList;


    public ToDoListRepository() {
        this.toDoList = new ArrayList<>();
    }

    public Optional<TaskEntry> add(String taskTitle, String taskDescription, urgencyLevel urgencyLevel, int duration, GreenSpace greenSpace) {
        TaskEntry taskEntry =new TaskEntry(taskTitle,taskDescription,urgencyLevel,duration,greenSpace);

        if(toDoList.contains(taskEntry)){
            return Optional.empty();
        }

        toDoList.add(taskEntry);
        return Optional.of(taskEntry);
    }
    public Optional<ArrayList<TaskEntry>> getPendingTasks(){
        ArrayList<TaskEntry> pendingTasks = new ArrayList<>();
        for(TaskEntry taskEntry : toDoList){
            if(taskEntry.getState() == State.PENDING){
                pendingTasks.add(taskEntry);
            }
        }
        if (pendingTasks.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(pendingTasks);
    }
}
