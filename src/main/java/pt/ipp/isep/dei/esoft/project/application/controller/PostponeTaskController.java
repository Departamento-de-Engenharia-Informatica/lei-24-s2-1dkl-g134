package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.urgencyLevel;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class PostponeTaskController {

    private AgendaRepository agendaRepository;

    /**
     * Constructor for a new PostponeTaskController object.
     * All this does is get the necessary repositories.
     */
    public PostponeTaskController() { agendaRepository = Repositories.getInstance().getAgendaRepository(); }

    /**
     * Gets the list of all tasks currently in a "PLANNED" or "POSTPONED" state.
     * @return An Optional object containing the list of all tasks in a "PLANNED" or "POSTPONED"
     * state. If none is found, an empty Optional object instead.
     */
    public Optional<ArrayList<TaskEntryDTO>> getPlannedAndPostponedTask(){
        return TaskEntryMapper.getMapper().objectListToDTOList(agendaRepository.getPlannedAndPostponedTasks());
    }

    /**
     * Postpones this task to a new start date and time.
     * The vehicles and collaborators assigned to this task must be available to perform it
     * were it to be postponed to this new start date and time, and the date must occur after
     * the current start date of the task.
     * @param taskEntry The task to be postponed, as a TaskEntry object.
     * @param date The String representation of the new start date.
     * @param time The String representation of the new start time.
     * @return An Optional object containing this task, with its values updated.
     */
    public Optional<TaskEntry> postponeTask(TaskEntry taskEntry, String date, String time){
        return agendaRepository.postponeTask(taskEntry,date,time);
    }
}
