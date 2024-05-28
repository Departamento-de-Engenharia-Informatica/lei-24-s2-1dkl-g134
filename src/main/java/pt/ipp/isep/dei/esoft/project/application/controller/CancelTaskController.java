package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class CancelTaskController {
    private AgendaRepository agendaRepository;

    public CancelTaskController() {
        this.agendaRepository = Repositories.getInstance().getAgendaRepository();
    }

    public Optional<TaskEntry> cancelTask(TaskEntry taskEntry) {
        return agendaRepository.cancelTask(taskEntry);
}

    public Optional<ArrayList<TaskEntry>> getPlannedAndPostponedTasks(){return agendaRepository.getPlannedAndPostponedTasks();}

    }