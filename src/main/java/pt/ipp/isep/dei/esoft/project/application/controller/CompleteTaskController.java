package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.customexceptions.CollaboratorNotFoundException;
import pt.ipp.isep.dei.esoft.project.customexceptions.InvalidRoleException;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class CompleteTaskController {
    private AgendaRepository agendaRepository;

    public CompleteTaskController(){
        agendaRepository = Repositories.getInstance().getAgendaRepository();
    }

    public Optional<TaskEntry> completeTask(TaskEntry taskEntry){
        return agendaRepository.completeTask(taskEntry);
    }
    public Optional<ArrayList<TaskEntry>> getPlannedAndPostponedTasksBelongingToCurrentUser() throws CollaboratorNotFoundException, InvalidRoleException {
        return agendaRepository.getPlannedAndPostponedTasksBelongingToCurrentUser();
    }
}
