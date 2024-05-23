package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CancelTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CancelTaskUI implements Runnable {
    private TaskEntry taskEntry = null;
    private CancelTaskController controller = new CancelTaskController();

    /**
     * Run this functionality.
     */
    public void run() {
        System.out.println("\n >>>>>>>>>> CANCEL TASK <<<<<<<<<< \n");

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
     * Request all necessary data and save it to its respective variables.
     */
    private void requestData() {
        taskEntry = requestTaskEntry();
    }

    /**
     * Requests the collaborator to assign skills to.
     *
     * @return The collaborator to assign skills to.
     */
    private TaskEntry requestTaskEntry() {
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<TaskEntry>> taskEntries = controller.getPlannedAndPostponedTasks();
        if (taskEntries.isEmpty()) {
            System.out.println("Error: No tasks. Task cancellation aborted.");
            return null;
        }
        System.out.println("Choose a task from the following list (Title | Description):\n");
        for (int i = 0; i < taskEntries.get().size(); i++) {
            System.out.println((i + 1) + "- " + taskEntries.get().get(i).toString());
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
        return taskEntries.get().get(option - 1);
    }

    /**
     * Confirm user inputs and selections.
     *
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData() {
        System.out.println("\n>>>>>>>>>> ASSIGNMENT INFORMATION <<<<<<<<<< \n");
        System.out.println("\nCancel the task " + taskEntry.toString());
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submit the inputted data and provide the respective feedback.
     */
    private void submitData() {
        try {
            Optional<TaskEntry> cancelledTask = controller.cancelTask(taskEntry);
            if (cancelledTask.isPresent()) {
                System.out.println("Task cancelled successfully:");
            } else {
                System.out.println("Failed to Cancel Task!");
            }
        } catch (Exception e) {
            System.out.println("Failed to Cancel Task!");
            System.out.println(e.getMessage());
        }

    }
}