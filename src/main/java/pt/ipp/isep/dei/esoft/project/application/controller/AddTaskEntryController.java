package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;

import java.util.ArrayList;
import java.util.Optional;

public class AddTaskEntryController {
    private ToDoListRepository ToDoListRepository;
    private GreenSpaceRepository greenSpaceRepository;


    public AddTaskEntryController(){
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        ToDoListRepository = Repositories.getInstance().getToDoListRepository();
    }
    public Optional<TaskEntry> addTaskEntry(String taskTitle, String taskDescription,urgencyLevel urgencyLevel, int duration, GreenSpace greenSpace){
        return ToDoListRepository.add(taskTitle,taskDescription,urgencyLevel,duration,greenSpace);

    }

    public Optional<ArrayList<GreenSpace>> getGreenSpacesManagedByCurrentUser() {
        return greenSpaceRepository.getGreenSpacesManagedByUser();
    }

}
