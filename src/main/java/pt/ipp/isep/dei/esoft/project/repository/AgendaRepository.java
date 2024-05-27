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

    public Optional<TaskEntry> add(TaskEntry task, String date, String time) {
        if (agenda.contains(task)) {
            return Optional.empty();
        }

        task.addAgendaData(date, time);

        agenda.add(task);
        return Optional.of(task);
    }

    public Optional<TaskEntry> postponeTask(TaskEntry taskEntry, String date, String time) {
        if(taskEntry == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        return taskEntry.postponeTask(date, time);
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
        if(taskEntry == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        return agenda.get(agenda.indexOf(taskEntry)).assignVehicles(vehicles);
    }

    public Optional<TaskEntry> assignTeamToTask(TaskEntry taskEntry, Team team) throws IOException {
        if(taskEntry == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        return agenda.get(agenda.indexOf(taskEntry)).assignTeam(team);
    }

    public Optional<TaskEntry> cancelTask(TaskEntry taskEntry) {
        if(taskEntry == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        return agenda.get(agenda.indexOf(taskEntry)).cancelTask();
    }

    public Optional<ArrayList<TaskEntry>> getCurrentUserTasksBetweenTwoDates(String firstDate, String secondDate) throws InvalidRoleException, CollaboratorNotFoundException {
        if(firstDate == null || secondDate == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
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
                if (taskEntry.getStartDate().isAfterDate(start) && !taskEntry.getStartDate().isAfterDate(end)) {
                    foundTasks.add(taskEntry);
                }
            }
        }
        if (foundTasks.isEmpty()) {
            return Optional.empty();
        } else return Optional.of(foundTasks);
    }

    public boolean isTeamAvailable(Team team, TaskEntry taskEntry) {
        if(taskEntry == null || team == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        //HOW THIS WORKS:
        //Check each planned/postponed task, check if the team is in there.
        //Compare dates: First we check if the task we're on starts strictly after the received
        //task ends, or ends strictly before the received task starts. If either one of these
        //is true, the task we're on is skipped.
        //The only times this check does not skip where the team won't be available is when
        //the start of one is equal to the end of another, and the times are compatible.
        //We check each one of those and skip if the times are compatible.
        //If no skips are performed, we know the tasks conflict, so we return false.
        //If we successfully skip to the end of the array, no tasks conflict, so we return true.
        for(TaskEntry taskToCompare : agenda){
            if(taskToCompare.equals(taskEntry) || taskToCompare.getState() == State.CANCELED || taskToCompare.getState() == State.COMPLETED){
                continue;
            }
            if(taskToCompare.isSameTeam(team.getTeamMembers())){
                if((taskToCompare.getStartDate().isAfterDate(taskEntry.getEndDate()) && !taskToCompare.getStartDate().equals(taskEntry.getEndDate()) || !taskToCompare.getEndDate().isAfterDate(taskEntry.getStartDate()))){
                    continue;
                }
                if(taskEntry.getStartDate().equals(taskToCompare.getEndDate())){
                    if(taskEntry.getStartTime().getHour() > taskToCompare.getEndTime().getHour()){
                        continue;
                    }
                }
                if(taskEntry.getEndDate().equals(taskToCompare.getStartDate())){
                    if(taskEntry.getEndTime().getHour() < taskToCompare.getStartTime().getHour()){
                        continue;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean isVehicleAvailable(Vehicle vehicle, TaskEntry taskEntry) {
        if(taskEntry == null || vehicle == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        //HOW THIS WORKS:
        //Check each planned/postponed task, check if the vehicle is in there.
        //Compare dates: First we check if the task we're on starts strictly after the received
        //task ends, or ends strictly before the received task starts. If either one of these
        //is true, the task we're on is skipped.
        //The only times this check does not skip where the team won't be available is when
        //the start of one is equal to the end of another, and the times are compatible.
        //We check each one of those and skip if the times are compatible.
        //If no skips are performed, we know the tasks conflict, so we return false.
        //If we successfully skip to the end of the array, no tasks conflict, so we return true.
        for(TaskEntry taskToCompare : agenda){
            if(taskToCompare.equals(taskEntry) || taskToCompare.getState() == State.CANCELED || taskToCompare.getState() == State.COMPLETED){
                continue;
            }
            if(taskToCompare.hasVehicle(vehicle)){
                if((taskToCompare.getStartDate().isAfterDate(taskEntry.getEndDate()) && !taskToCompare.getStartDate().equals(taskEntry.getEndDate()) || !taskToCompare.getEndDate().isAfterDate(taskEntry.getStartDate()))){
                    continue;
                }
                if(taskEntry.getStartDate().equals(taskToCompare.getEndDate())){
                    if(taskEntry.getStartTime().getHour() > taskToCompare.getEndTime().getHour()){
                        continue;
                    }
                }
                if(taskEntry.getEndDate().equals(taskToCompare.getStartDate())){
                    if(taskEntry.getEndTime().getHour() < taskToCompare.getStartTime().getHour()){
                        continue;
                    }
                }
                return false;
            }
        }
        return true;
    }
}
