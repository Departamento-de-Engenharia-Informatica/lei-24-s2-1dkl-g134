package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.customexceptions.CollaboratorNotFoundException;
import pt.ipp.isep.dei.esoft.project.customexceptions.InvalidRoleException;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class CompleteTaskController {
    private AgendaRepository agendaRepository;

    /**
     * Constructor for a new CompleteTaskController object.
     * All this does is get the necessary repositories.
     */
    public CompleteTaskController(){
        agendaRepository = Repositories.getInstance().getAgendaRepository();
    }

    /**
     * Marks a task as completed.
     * A task is only marked as completed if it isn't already complete.
     * @param taskEntry The task to complete.
     * @return An Optional object containing the task with its updated values.
     * If nothing was updated, an empty Optional object instead.
     */
    public Optional<TaskEntry> completeTask(TaskEntry taskEntry){
        return agendaRepository.completeTask(taskEntry);
    }

    /**
     * Gets the list of all tasks currently in a "PLANNED" or "POSTPONED" state.
     * @return An Optional object containing the list of all tasks in a "PLANNED" or "POSTPONED"
     * state. If none is found, an empty Optional object instead.
     */
    public Optional<ArrayList<TaskEntryDTO>> getPlannedAndPostponedTasksBelongingToCurrentUser() throws CollaboratorNotFoundException, InvalidRoleException {
        return TaskEntryMapper.getMapper().objectListToDTOList(agendaRepository.getPlannedAndPostponedTasksBelongingToCurrentUser());
    }
}
