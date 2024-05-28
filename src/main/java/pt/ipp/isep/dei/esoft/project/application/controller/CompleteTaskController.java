package pt.ipp.isep.dei.esoft.project.application.controller;

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

    public Optional<TaskEntry> getCompletedTask(TaskEntry taskEntry){
        return agendaRepository.getCompletedTask(taskEntry);
    }
    public Optional<ArrayList<TaskEntry>> getPlannedAndPostponedTasks() {
        return agendaRepository.getPlannedAndPostponedTasks();
    }
}
