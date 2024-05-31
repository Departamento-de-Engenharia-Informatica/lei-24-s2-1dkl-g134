package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.customexceptions.CollaboratorNotFoundException;
import pt.ipp.isep.dei.esoft.project.customexceptions.InvalidRoleException;
import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class ListTasksBetweenDatesController {
    private final AgendaRepository agendaRepository;

    /**
     * Constructor for a new ListTasksBetweenDatesController object.
     * All this does is get the necessary repositories.
     */
    public ListTasksBetweenDatesController() {
        this.agendaRepository = Repositories.getInstance().getAgendaRepository();
    }

    /**
     * Gets the list of all tasks to which the current logged-in user is assigned to that start
     * between two specific dates.
     * @param firstDate The former of the two dates to limit the search by.
     * @param secondDate The latter of the two dates to limit the search by.
     * @return An Optional object containing the list of all tasks that fit the criteria of this method.
     * If none is found, an empty Optional object instead.
     */
    public Optional<ArrayList<TaskEntryDTO>> getCurrentUserTasksBetweenTwoDates(String firstDate, String secondDate) throws InvalidRoleException, CollaboratorNotFoundException {
        return TaskEntryMapper.getMapper().objectListToDTOList(agendaRepository.getCurrentUserTasksBetweenTwoDates(firstDate, secondDate));
    }
}

