package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListTasksBetweenDatesController;
import pt.ipp.isep.dei.esoft.project.customexceptions.InvalidRoleException;
import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;

import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ListTasksBetweenDatesUI implements Runnable {
    private ListTasksBetweenDatesController controller = new ListTasksBetweenDatesController();
    private String firstDate, secondDate;

    public void run() {
        System.out.println("\n >>>>>>>>>>  LIST TASKS BETWEEN DATES <<<<<<<<<< \n");

        while (true) {
            requestData();
            if (!confirmData()) {
                continue;
            }
            break;
        }
        try {
            Optional<ArrayList<TaskEntry>> taskList = controller.getCurrentUserTasksBetweenTwoDates(firstDate, secondDate);
            if (taskList.isEmpty()) {
                System.out.println("No tasks found for you between these dates.");
                return;
            }
            System.out.println("Tasks found between " + firstDate + " and " + secondDate + "\n");
            System.out.println("TITLE | DESCRIPTION | URGENCY | STATE | GREEN SPACE | DURATION | DATE");
            for (TaskEntry taskEntry : taskList.get()) {
                System.out.println(taskEntry.getTaskTitle()+" | "+ taskEntry.getTaskDescription()+" | "+taskEntry.getUrgencyLevel()+ " | "+taskEntry.getState()+ " | "+taskEntry.getGreenSpace()+" | "+taskEntry.getDuration()+" | "+taskEntry.getDate());
            }
            Scanner in = new Scanner(System.in);
            System.out.println("\nPress ENTER to continue.");
            in.nextLine();
        } catch (Exception e) {
            System.out.println("Failed to get task list!");
            System.out.println(e.getMessage());
        }
    }

    private void requestData() {
        Scanner input = new Scanner(System.in);
        System.out.print("First Date (YYYY/MM/DD, with leading zeros): ");
        firstDate = input.nextLine();
        Scanner input2 = new Scanner(System.in);
        System.out.println("Second Date (YYYY/MM/DD, with leading zeros):");
        secondDate = input.nextLine();
    }

    private boolean confirmData() {
        System.out.println("\n >>>>>>>>>>  LIST INFORMATION <<<<<<<<<< \n");
        System.out.println("First Date: " + firstDate);
        System.out.println("Second Date: " + secondDate);
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

}