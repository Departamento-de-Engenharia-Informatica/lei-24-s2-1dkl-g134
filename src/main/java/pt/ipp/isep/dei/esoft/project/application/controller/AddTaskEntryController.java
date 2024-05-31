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

    /**
     * Constructor for a new AddTaskEntryController object.
     * All this does is get the necessary repositories.
     */
    public AddTaskEntryController(){
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
        ToDoListRepository = Repositories.getInstance().getToDoListRepository();
    }

    /**
     * Adds a new task entry to the to-do list.
     * If the task entry is equal to any in the to-do list, no task entry is added.
     * Check the documentation of TaskEntry.equals() for the criteria of such.
     * @param taskEntryDTO The TaskEntryDTO that contains all the necessary information for the creation of the task.
     * @return An Optional object containing the added task, if any was added.
     * If not, an empty Optional object.
     */
    public Optional<TaskEntry> addTaskEntry(TaskEntryDTO taskEntryDTO){
        return ToDoListRepository.add(taskEntryDTO.taskTitle,taskEntryDTO.taskDescription,taskEntryDTO.urgencyLevel,taskEntryDTO.duration,taskEntryDTO.greenSpace);
    }

    /**
     * Gets the list of all green spaces managed by the currently logged-in user.
     * @return An Optional object containing the list of all GreenSpaceDTOs representing the
     * green spaces managed by the user. If none are found, an empty Optional object.
     */
    public Optional<ArrayList<GreenSpaceDTO>> getGreenSpacesManagedByCurrentUser() {
        return GreenSpaceMapper.getMapper().objectListToDTOList(greenSpaceRepository.getGreenSpacesManagedByUser());
    }

}
