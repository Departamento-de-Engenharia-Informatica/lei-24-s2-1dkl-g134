package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CancelTaskController;
import pt.ipp.isep.dei.esoft.project.application.controller.CompleteTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CompleteTaskUI {
    private TaskEntry taskEntry = null;
    private CompleteTaskController completeTaskController = new CompleteTaskController();

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
    private void requestData() {
        taskEntry = requestTaskEntry();
    }

    private TaskEntry requestTaskEntry() {
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<TaskEntry>> taskEntries = completeTaskController.getPlannedAndPostponedTasks();
        if (taskEntries.isEmpty()) {
            System.out.println("Error: No tasks. Task completetion aborted.");
            return null;
        }
        
    }






    private boolean confirmData() {
        System.out.println("\n>>>>>>>>>> ASSIGNMENT INFORMATION <<<<<<<<<< \n");
        System.out.println("\nComplete the task " + taskEntry.toString());
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }



    private void submitData() {
        try {
            Optional<TaskEntry> completedTask = completeTaskController.getCompletedTask(taskEntry);
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
