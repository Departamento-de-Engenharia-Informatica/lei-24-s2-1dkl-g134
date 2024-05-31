package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoListRepository implements Serializable {
    private List<TaskEntry> toDoList;

    /**
     * Constructor for a new To-Do List Repository.
     * All this does is initialize the list of tasks that make up the to-do list.
     */
    public ToDoListRepository() {
        this.toDoList = new ArrayList<>();
    }

    /**
     * Adds a new task to this repository.
     * If the task is equal to any other task in this repository, no task is added.
     * Check the documentation of TaskEntry.equals() for the criteria of such.
     * @param taskTitle A String representing the task's title.
     * @param taskDescription A String representing the tasks's description.
     * @param urgencyLevel The urgency level of this task, represented by an urgencyLevel enumerator.
     * @param duration An int representing the task's duration in hours.
     * @param greenSpace The Green Space associated with this task, in a GreenSpace object.
     * @return An Optional object containing the added task. If none was added, an empty
     * Optional object instead.
     */
    public Optional<TaskEntry> add(String taskTitle, String taskDescription, urgencyLevel urgencyLevel, int duration, GreenSpace greenSpace) {
        TaskEntry taskEntry =new TaskEntry(taskTitle,taskDescription,urgencyLevel,duration,greenSpace);

        if(toDoList.contains(taskEntry)){
            return Optional.empty();
        }

        toDoList.add(taskEntry);
        return Optional.of(taskEntry);
    }

    /**
     * Gets the list of all tasks currently not part of the agenda assigned to a green space
     * managed by the current logged-in user
     * More formally, this is the list of all tasks fitting the criteria above whose state
     * is "PENDING".
     * @return An Optional object containing the list of all pending tasks managed by the user.
     * If none are found, an empty Optional object instead.
     */
    public Optional<ArrayList<TaskEntry>> getPendingTasks(){
        ArrayList<TaskEntry> pendingTasks = new ArrayList<>();
        for(TaskEntry taskEntry : toDoList){
            if(taskEntry.getState() == State.PENDING && taskEntry.getGreenSpaceObject().isCreatedBy(ApplicationSession.getInstance().getCurrentSession().getUserName(), ApplicationSession.getInstance().getCurrentSession().getUserEmail())){
                pendingTasks.add(taskEntry);
            }
        }
        if (pendingTasks.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(pendingTasks);
    }
}
