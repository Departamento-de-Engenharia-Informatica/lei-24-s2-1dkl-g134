package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;

import java.util.ArrayList;
import java.util.Optional;

public class AssignTaskToAgendaController {
    private AgendaRepository agendaRepository;
    private ToDoListRepository toDoListRepository;

    /**
     * Constructor for a new AssignTaskToAgendaController object.
     * All this does is get the necessary repositories.
     */
    public AssignTaskToAgendaController(){
        this.agendaRepository = Repositories.getInstance().getAgendaRepository();
        this.toDoListRepository = Repositories.getInstance().getToDoListRepository();
    }

    /**
     * Adds a task to the agenda repository.
     * If the task is equal to any task currently in the agenda, no task is added.
     * Check the documentation of TaskEntry.equals() for the criteria of such.
     * @param taskEntryDTO The TaskEntryDTO object containing all necessary data.
     * @return An Optional object containing the added task. If none was added, an empty
     * Optional object instead.
     */
    public Optional<TaskEntry> assignTaskToAgenda(TaskEntryDTO taskEntryDTO){
        return agendaRepository.add(taskEntryDTO.attachedTaskEntry,taskEntryDTO.startDateStringForm,taskEntryDTO.startTimeStringForm);
    }

    /**
     * Gets the list of all tasks currently not part of the agenda.
     * More formally, this is the list of all tasks whose state is "PENDING".
     * @return An Optional object containing the list of all pending tasks (in a DTO format). If none are found,
     * an empty Optional object instead.
     */
    public Optional<ArrayList<TaskEntryDTO>> getPendingTasks(){
        return TaskEntryMapper.getMapper().objectListToDTOList(toDoListRepository.getPendingTasks());
    }
}
