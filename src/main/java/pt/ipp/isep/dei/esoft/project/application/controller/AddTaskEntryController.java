package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;

import java.util.Optional;

public class AddTaskEntryController {
    private ToDoListRepository ToDoListRepository;


    public AddTaskEntryController(){
        ToDoListRepository = Repositories.getInstance().getToDoListRepository();
    }
    public Optional<TaskEntry> addTaskEntry(String taskTitle, String taskDescription,urgencyLevel urgencyLevel, int duration){
        return ToDoListRepository.add(taskTitle,taskDescription,urgencyLevel,duration);

    }
}
