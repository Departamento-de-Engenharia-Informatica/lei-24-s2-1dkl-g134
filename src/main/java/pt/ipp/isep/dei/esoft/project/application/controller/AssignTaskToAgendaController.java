package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;

import java.util.ArrayList;
import java.util.Optional;

public class AssignTaskToAgendaController {
    private AgendaRepository agendaRepository;
    private ToDoListRepository toDoListRepository;

    public AssignTaskToAgendaController(){
        this.agendaRepository = Repositories.getInstance().getAgendaRepository();
        this.toDoListRepository = Repositories.getInstance().getToDoListRepository();
    }
    public Optional<TaskEntry> assignTaskToAgenda(TaskEntry task, State state, String date){
        return agendaRepository.add(task,state,date);
    }
    public Optional<ArrayList<TaskEntry>> getPendingTasks(){
        return toDoListRepository.getPendingTasks();
    }
}
