package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

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
    public Optional<ArrayList<Vehicle>> assignVehiclesToTask(TaskEntry taskEntry, ArrayList<Vehicle> vehicles) {
        return agenda.get(agenda.indexOf(taskEntry)).assignVehicles(vehicles);
    }

    public Optional<TaskEntry> cancelTask(TaskEntry taskEntry) {
        return agenda.get(agenda.indexOf(taskEntry)).cancelTask();
    }
}
