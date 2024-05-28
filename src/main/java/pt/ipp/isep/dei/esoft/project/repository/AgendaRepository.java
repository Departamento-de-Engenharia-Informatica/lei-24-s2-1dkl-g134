package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.customexceptions.CollaboratorNotFoundException;
import pt.ipp.isep.dei.esoft.project.customexceptions.InvalidRoleException;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendaRepository implements Serializable {
    private List<TaskEntry> agenda;


    public AgendaRepository() {
        this.agenda = new ArrayList<>();
    }

    public Optional<TaskEntry> add(TaskEntry task, State state, String date) {
        if (agenda.contains(task)) {
            return Optional.empty();
        }

        task.addAgendaData(state, date);

        agenda.add(task);
        return Optional.of(task);
    }

    public Optional<TaskEntry> postponeTask(TaskEntry taskEntry, String date) {
        return taskEntry.postponeTask(date);
    }

    public Optional<ArrayList<TaskEntry>> getPlannedAndPostponedTasks() {
        ArrayList<TaskEntry> currentTasks = new ArrayList<>();
        for (TaskEntry taskEntry : agenda) {
            if (taskEntry.getState() == State.PLANNED || taskEntry.getState() == State.POSTPONED) {
                currentTasks.add(taskEntry);
            }
        }
        if (currentTasks.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(currentTasks);
    }

    public Optional<ArrayList<Vehicle>> assignVehiclesToTask(TaskEntry taskEntry, ArrayList<Vehicle> vehicles) {
        return agenda.get(agenda.indexOf(taskEntry)).assignVehicles(vehicles);
    }

    public Optional<TaskEntry> assignTeamToTask(TaskEntry taskEntry, Team team) throws IOException {
        return agenda.get(agenda.indexOf(taskEntry)).assignTeam(team);
    }

    public Optional<TaskEntry> cancelTask(TaskEntry taskEntry) {
        return agenda.get(agenda.indexOf(taskEntry)).cancelTask();
    }

    public Optional<ArrayList<TaskEntry>> getCurrentUserTasksBetweenTwoDates(String firstDate, String secondDate) throws InvalidRoleException, CollaboratorNotFoundException {
        CustomDate start = new CustomDate(firstDate);
        CustomDate end = new CustomDate(secondDate);
        ArrayList<TaskEntry> foundTasks = new ArrayList<>();
        if (start.isAfterDate(end)) {
            throw new IllegalArgumentException("First date must be before the second date.");
        }
        Optional<Collaborator> currentCollaborator = Repositories.getInstance().getCollaboratorRepository().getCurrentUserCollaborator();
        if (currentCollaborator.isEmpty()) {
            throw new CollaboratorNotFoundException("No collaborator corresponding to your email and name was found in the system.");
        }
        for (TaskEntry taskEntry : agenda) {
            if (taskEntry.getAssignedTeam().contains(currentCollaborator)) {
                if (taskEntry.getDate().isAfterDate(start) && !taskEntry.getDate().isAfterDate(end)) {
                    foundTasks.add(taskEntry);
                }
            }
        }
        if (foundTasks.isEmpty()) {
            return Optional.empty();
        } else return Optional.of(foundTasks);
    }
    public Optional<TaskEntry> getCompletedTask(TaskEntry taskEntry){
        return agenda.get(agenda.indexOf(taskEntry)).completeTask();

    }
}
