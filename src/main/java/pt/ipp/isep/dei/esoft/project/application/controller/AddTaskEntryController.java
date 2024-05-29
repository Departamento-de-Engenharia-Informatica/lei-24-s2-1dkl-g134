package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
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
    public Optional<TaskEntry> addTaskEntry(TaskEntryDTO taskEntryDTO){
        return ToDoListRepository.add(taskEntryDTO.taskTitle,taskEntryDTO.taskDescription,taskEntryDTO.urgencyLevel,taskEntryDTO.duration,taskEntryDTO.greenSpace);
    }

    public Optional<ArrayList<GreenSpaceDTO>> getGreenSpacesManagedByCurrentUser() {
        return GreenSpaceMapper.getMapper().objectListToDTOList(greenSpaceRepository.getGreenSpacesManagedByUser());
    }

}
