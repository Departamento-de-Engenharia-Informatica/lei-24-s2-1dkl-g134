package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CompleteTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CompleteTaskUI implements Runnable {
    private TaskEntry taskEntry = null;
    private CompleteTaskController completeTaskController = new CompleteTaskController();

    /**
     * Runs this functionality.
     */
    public void run() {
        System.out.println("\n >>>>>>>>>> COMPLETE TASK <<<<<<<<<< \n");

        while (true) {
            requestData();
            if (taskEntry == null) {
                return;
            }
            if (!confirmData()) {
                continue;
            }
            submitData();
            break;
        }
    }

    /**
     * Requests all data and assigns it to its respective variables.
     */
    private void requestData() {
        taskEntry = requestTaskEntry();
    }

    /**
     * Requests the task to complete.
     * @return The selected task.
     */
    private TaskEntry requestTaskEntry() {
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<TaskEntryDTO>> taskEntries = null;
        try{
            taskEntries = completeTaskController.getPlannedAndPostponedTasksBelongingToCurrentUser();
        }catch(Exception e){
            System.out.println("Error on getting your assigned tasks. Task completion aborted.");
            System.out.println(e.getMessage());
        }
        if (taskEntries.isEmpty()) {
            System.out.println("Error: No tasks. Task completion aborted.");
            return null;
        }
        System.out.println("Choose a task from the following list (Title | Description):\n");
        for (int i = 0; i < taskEntries.get().size(); i++) {
            System.out.println((i + 1) + "- " + taskEntries.get().get(i).attachedTaskEntry.toString());
        }
        int option = 0;
        while (true) {
            try {
                System.out.println("Choose a number corresponding to a task.");
                option = Integer.parseInt(input.nextLine());
                if (option <= 0 || option > taskEntries.get().size()) {
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return taskEntries.get().get(option - 1).attachedTaskEntry;
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData() {
        System.out.println("\n>>>>>>>>>> ASSIGNMENT INFORMATION <<<<<<<<<< \n");
        System.out.println("\nComplete the task " + taskEntry.toString());
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submits the inputted data and provides the respective feedback.
     */
    private void submitData() {
        try {
            Optional<TaskEntry> completedTask = completeTaskController.completeTask(taskEntry);
            if (completedTask.isPresent()) {
                System.out.println("Task completed successfully:");
            } else {
                System.out.println("Failed to complete Task!");
            }
        } catch (Exception e) {
            System.out.println("Failed to complete Task!");
            System.out.println(e.getMessage());
        }

    }
}
