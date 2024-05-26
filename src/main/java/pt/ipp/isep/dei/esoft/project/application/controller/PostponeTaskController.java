package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.urgencyLevel;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class PostponeTaskController {

    private AgendaRepository agendaRepository;

    public PostponeTaskController() { agendaRepository = Repositories.getInstance().getAgendaRepository(); }
    public Optional<ArrayList<TaskEntry>> getPlannedAndPostponedTask(){
                return agendaRepository.getPlannedAndPostponedTasks();
    }
   public Optional<TaskEntry> postponeTask(TaskEntry taskEntry, String date, String time){
        return agendaRepository.postponeTask(taskEntry,date,time);
   }
}
