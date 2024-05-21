package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.urgencyLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendaRepository {
    private List<TaskEntry> agenda;


    public AgendaRepository() {
        this.agenda = new ArrayList<>();
    }

    public Optional<TaskEntry> add(TaskEntry task, State state, String date) {
        if(agenda.contains(task)){
            return Optional.empty();
        }

        task.addAgendaData(state,date);

        agenda.add(task);
        return Optional.of(task);
    }
    public Optional<TaskEntry> postponeTask(TaskEntry taskEntry, String date){
        return taskEntry.postponeTask(date);
    }
    public Optional<ArrayList<TaskEntry>> getPlannedAndPostponedTasks(){
        ArrayList<TaskEntry> currentTasks = new ArrayList<>();
        for(TaskEntry taskEntry : agenda){
            if(taskEntry.getState() == State.PLANNED || taskEntry.getState() == State.POSTPONED){
                currentTasks.add(taskEntry);
            }
        }
        if (currentTasks.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(currentTasks);
    }
}
