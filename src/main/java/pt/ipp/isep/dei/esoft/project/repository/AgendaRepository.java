package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
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

    /**
     * Constructor for a new Agenda Repository.
     * All this does is initialize the list of tasks that make up the agenda.
     */
    public AgendaRepository() {
        this.agenda = new ArrayList<>();
    }

    /**
     * Adds a task to the agenda repository.
     * If the task is equal to any task currently in the agenda, no task is added.
     * Check the documentation of TaskEntry.equals() for the criteria of such.
     * @param task The TaskEntry object to be added to the agenda.
     * @param date A String representation of the task's start date.
     * @param time A String representation of the task's start time.
     * @return An Optional object containing the added task. If none was added, an empty
     * Optional object instead.
     */
    public Optional<TaskEntry> add(TaskEntry task, String date, String time) {
        if (agenda.contains(task)) {
            return Optional.empty();
        }

        task.addAgendaData(date, time);

        agenda.add(task);
        return Optional.of(task);
    }

    /**
     * Postpones this task to a new start date and time.
     * This method will throw an IllegalArgument exception if this it receives any null
     * fields, if any vehicle or team assigned to this task would not be available
     * to perform this task were it postponed to the specified date and time, or for any
     * reason outlined in the TaskEntry.postponeTask() method.
     * @param taskEntry The task to be postponed, as a TaskEntry object.
     * @param date The String representation of the new start date.
     * @param time The String representation of the new start time.
     * @return An Optional object containing this task, with its values updated.
     */
    public Optional<TaskEntry> postponeTask(TaskEntry taskEntry, String date, String time) {
        if(taskEntry == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        TaskEntry testTaskEntry = new TaskEntry(taskEntry.getTaskTitle(), taskEntry.getTaskDescription(), taskEntry.getUrgencyLevel(), taskEntry.getDuration(), taskEntry.getGreenSpaceObject());
        testTaskEntry.addAgendaData(date, time);
        if(taskEntry.getAssignedVehicles() != null){
            if(!taskEntry.getAssignedVehicles().isEmpty()){
                for(Vehicle vehicle : taskEntry.getAssignedVehicles()){
                    if(!isVehicleAvailable(vehicle, testTaskEntry)){
                        throw new IllegalArgumentException("One or more vehicles assigned to this task would not be available if it was postponed to this date and time!");
                    }
                }
            }
        }
        if(taskEntry.getAssignedTeam() != null){
            if(!taskEntry.getAssignedTeam().isEmpty()){
                if(!isTeamAvailable(taskEntry.getAssignedTeam(), testTaskEntry)){
                    throw new IllegalArgumentException("The team assigned to this task would not be available if it was postponed to this date and time!");
                }
            }
        }
        return taskEntry.postponeTask(date, time);
    }

    /**
     * Gets the list of all tasks currently in a "PLANNED" or "POSTPONED" state whose
     * assigned green space is managed by the current logged-in user.
     * @return An Optional object containing the list of all tasks in a "PLANNED" or "POSTPONED"
     * state managed by the user. If none is found, an empty Optional object instead.
     */
    public Optional<ArrayList<TaskEntry>> getPlannedAndPostponedTasks() {
        ArrayList<TaskEntry> currentTasks = new ArrayList<>();
        for (TaskEntry taskEntry : agenda) {
            if ((taskEntry.getState() == State.PLANNED || taskEntry.getState() == State.POSTPONED) && taskEntry.getGreenSpaceObject().isCreatedBy(ApplicationSession.getInstance().getCurrentSession().getUserEmail())) {
                currentTasks.add(taskEntry);
            }
        }
        if (currentTasks.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(currentTasks);
    }

    /**
     * Gets the list of all tasks currently in a "PLANNED" or "POSTPONED" state to which the
     * currently logged-in Collaborator is assigned.
     * This method throws an InvalidRoleException for any reason outlined in the
     * CollaboratorRepository.getCurrentUserCollaborator() method, and throws a
     * CollaboratorNotFound exception if no collaborator associated with the current logged-in
     * user account is found.
     * @return An Optional object containing the list of tasks to which the current logged-in
     * Collaborator is assigned. If no task is found, an empty Optional object instead.
     */
    public Optional<ArrayList<TaskEntry>> getPlannedAndPostponedTasksBelongingToCurrentUser() throws InvalidRoleException, CollaboratorNotFoundException {
        ArrayList<TaskEntry> collaboratorTasks = new ArrayList<>();
        if(agenda.isEmpty()){
            return Optional.empty();
        }
        Optional<Collaborator> currentCollaborator = Repositories.getInstance().getCollaboratorRepository().getCurrentUserCollaborator();
        if (currentCollaborator.isEmpty()) {
            throw new CollaboratorNotFoundException("No collaborator corresponding to your email and name was found in the system.");
        }
        for(TaskEntry taskEntry : agenda){
            if(taskEntry.getAssignedTeam() == null){
                continue;
            }
            if(taskEntry.getAssignedTeam().contains(currentCollaborator.get()) && (taskEntry.getState() == State.PLANNED || taskEntry.getState() == State.POSTPONED)){
                collaboratorTasks.add(taskEntry);
            }
        }
        if(collaboratorTasks.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(collaboratorTasks);
    }

    /**
     * Assigns a list of vehicles to a task.
     * This method throws an IllegalArgumentException if it receives any null fields.
     * Outside of that, all this does is call the TaskEntry.assignVehicles() method: Check
     * the documentation for that method.
     * @param taskEntry The task to assign vehicles to.
     * @param vehicles The list of vehicles to assign to the task.
     * @return An Optional object containing the list of all vehicles actually assigned to
     * the task. If none was assigned, an empty Optional object instead.
     */
    public Optional<ArrayList<Vehicle>> assignVehiclesToTask(TaskEntry taskEntry, ArrayList<Vehicle> vehicles) {
        if(taskEntry == null || vehicles == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        return agenda.get(agenda.indexOf(taskEntry)).assignVehicles(vehicles);
    }

    /**
     * Assigns a team to a task.
     * This method throws an IllegalArgumentException if it receives any null fields.
     * Outside of that, all this does is call the TaskEntry.assignTeam() method: Check
     * the documentation for that method.
     * @param taskEntry The task to assign a team to.
     * @param team The team to assign to the task.
     * @return An Optional object containing the task with its updated values.
     * If nothing was updated, an empty Optional object instead.
     */
    public Optional<TaskEntry> assignTeamToTask(TaskEntry taskEntry, Team team) throws IOException {
        if(taskEntry == null || team == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        return agenda.get(agenda.indexOf(taskEntry)).assignTeam(team);
    }

    /**
     * Cancels a task.
     * This method throws an IllegalArgumentException if it receives any null fields.
     * Outside of that, all this does is call the TaskEntry.cancelTask() method: Check
     * the documentation for that method.
     * @param taskEntry The task to cancel.
     * @return An Optional object containing the task with its updated values.
     * If nothing was updated, an empty Optional object instead.
     */
    public Optional<TaskEntry> cancelTask(TaskEntry taskEntry) {
        if(taskEntry == null){
            throw new IllegalArgumentException("Null fields not allowed.");
        }
        return agenda.get(agenda.indexOf(taskEntry)).cancelTask();
    }

    /**
     * Gets the list of all tasks to which the current logged-in user is assigned to that start
     * between two specific dates.
     * This method throws an InvalidRoleException for any reason outlined in the
     * CollaboratorRepository.getCurrentUserCollaborator() method, and throws a
     * CollaboratorNotFound exception if no collaborator associated with the current logged-in
     * user account is found. It also throws an IllegalArgumentException if it receives any null
     * fields or if the second date is before the first date, or for any reason outlined in the
     * CustomDate constructor.
     * @param firstDate The former of the two dates to limit the search by.
     * @param secondDate The latter of the two dates to limit the search by.
     * @return An Optional object containing the list of all tasks that fit the criteria of this method.
     * If none is found, an empty Optional object instead.
     */
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
            if(taskEntry.getAssignedTeam() == null){
                continue;
            }
            if (taskEntry.getAssignedTeam().contains(currentCollaborator.get())) {
                if (taskEntry.getStartDate().isAfterDate(start) && !taskEntry.getStartDate().isAfterDate(end)) {
                    foundTasks.add(taskEntry);
                }
            }
        }
        if (foundTasks.isEmpty()) {
            return Optional.empty();
        } else return Optional.of(foundTasks);
    }

    /**
     * Marks a task as complete.
     * This method throws an IllegalArgumentException if it receives any null fields.
     * Outside of that, all this does is call the TaskEntry.completeTask() method: Check
     * the documentation for that method.
     * @param taskEntry The task to complete.
     * @return An Optional object containing the task with its updated values.
     * If nothing was updated, an empty Optional object instead.
     */
    public Optional<TaskEntry> completeTask(TaskEntry taskEntry){
        if(taskEntry == null){
            throw new IllegalArgumentException("Null fields not allowed");
        }
        return agenda.get(agenda.indexOf(taskEntry)).completeTask();
    }

    /**
     * Checks if the specified list of collaborators is available to perform the specified task.
     * The criteria for this relies on whether the specified collaborators are assigned to any task
     * whose schedule overlaps even by just an hour with the specified task's schedule.
     * This method throws an IllegalArgumentException if it receives any null fields.
     * @param team The list of collaborators to check for availability.
     * @param taskEntry The task to check for availability.
     * @return A boolean value representing if the specified collaborators are available to perform the
     * specified task.
     */
    public boolean isTeamAvailable(ArrayList<Collaborator> team, TaskEntry taskEntry) {
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
            if(taskToCompare.isSameTeam(team)){
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

    /**
     * Checks if the specified vehicle is available to perform the specified task.
     * The criteria for this relies on whether the specified vehicle is assigned to any task
     * whose schedule overlaps even by just an hour with the specified task's schedule.
     * This method throws an IllegalArgumentException if it receives any null fields.
     * @param vehicle The vehicle to check for availability.
     * @param taskEntry The task to check for availability.
     * @return A boolean value representing if the specified vehicle is available to perform the
     * specified task.
     */
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
