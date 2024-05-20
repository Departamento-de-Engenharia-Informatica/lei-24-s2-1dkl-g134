package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoListRepository {
    private List<TaskEntry> toDoList;


    public ToDoListRepository() {
        this.toDoList = new ArrayList<>();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ToDoListRepository that = (ToDoListRepository) o;
        return toDoList.equals(that.toDoList);
    }

    public Optional<TaskEntry> add(String taskTitle, String taskDescription, urgencyLevel urgencyLevel, State state, int duration) {
        TaskEntry taskEntry =new TaskEntry(taskTitle,taskDescription,urgencyLevel,duration,state);

        if(toDoList.contains(taskEntry)){
            return Optional.empty();
        }

        toDoList.add(taskEntry);
        return Optional.of(taskEntry);
    }
}
