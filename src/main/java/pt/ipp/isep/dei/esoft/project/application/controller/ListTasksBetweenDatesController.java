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

    public ListTasksBetweenDatesController() {
        this.agendaRepository = Repositories.getInstance().getAgendaRepository();
    }

    public Optional<ArrayList<TaskEntryDTO>> getCurrentUserTasksBetweenTwoDates(String firstDate, String secondDate) throws InvalidRoleException, CollaboratorNotFoundException {
        return TaskEntryMapper.getMapper().objectListToDTOList(agendaRepository.getCurrentUserTasksBetweenTwoDates(firstDate, secondDate));
    }
}

