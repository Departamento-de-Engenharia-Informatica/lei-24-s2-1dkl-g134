package pt.ipp.isep.dei.esoft.project.ui.console;

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
        Optional<ArrayList<TaskEntry>> taskEntries = null;
        try{
            taskEntries = completeTaskController.getPlannedAndPostponedTasksBelongingToCurrentUser();
        }catch(Exception e){
            System.out.println("Error encountered. Task completion aborted.");
            System.out.println(e.getMessage());
        }
        if (taskEntries.isEmpty()) {
            System.out.println("Error: No tasks. Task completion aborted.");
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

    private boolean confirmData() {
        System.out.println("\n>>>>>>>>>> ASSIGNMENT INFORMATION <<<<<<<<<< \n");
        System.out.println("\nComplete the task " + taskEntry.toString());
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

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
