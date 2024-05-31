package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class CancelTaskController {
    private AgendaRepository agendaRepository;

    /**
     * Constructor for a new CancelTaskController object.
     * All this does is get the necessary repositories.
     */
    public CancelTaskController() {
        this.agendaRepository = Repositories.getInstance().getAgendaRepository();
    }

    /**
     * Cancels a task.
     * The task will only be marked as cancelled if it isn't already cancelled.
     * @param taskEntry The task to cancel.
     * @return An Optional object containing the task with its updated values.
     * If nothing was updated, an empty Optional object instead.
     */
    public Optional<TaskEntry> cancelTask(TaskEntry taskEntry) {
        return agendaRepository.cancelTask(taskEntry);
}

    /**
     * Gets the list of all tasks currently in a "PLANNED" or "POSTPONED" state.
     * @return An Optional object containing the list of all tasks in a "PLANNED" or "POSTPONED"
     * state. If none is found, an empty Optional object instead.
     */
    public Optional<ArrayList<TaskEntryDTO>> getPlannedAndPostponedTasks(){return TaskEntryMapper.getMapper().objectListToDTOList(agendaRepository.getPlannedAndPostponedTasks());}

    }