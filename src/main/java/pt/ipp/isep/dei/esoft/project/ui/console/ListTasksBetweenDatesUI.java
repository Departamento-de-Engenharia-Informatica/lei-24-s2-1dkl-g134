package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListTasksBetweenDatesController;
import pt.ipp.isep.dei.esoft.project.customexceptions.InvalidRoleException;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;

import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
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
    private ArrayList<State> stateFilters = new ArrayList<>();
    private ArrayList<State> stateList = new ArrayList<>();

    /**
     * Runs this functionality.
     */
    public void run() {
        System.out.println("\n >>>>>>>>>>  LIST TASKS BETWEEN DATES <<<<<<<<<< \n");

        stateList.add(State.PLANNED);
        stateList.add(State.POSTPONED);
        stateList.add(State.COMPLETED);
        stateList.add(State.CANCELED);

        while (true) {
            requestData();
            if (!confirmData()) {
                continue;
            }
            break;
        }
        try {
            Optional<ArrayList<TaskEntryDTO>> taskList = controller.getCurrentUserTasksBetweenTwoDates(firstDate, secondDate);
            if (taskList.isEmpty()) {
                System.out.println("No tasks found for you between these dates.");
                return;
            }

            taskList = Optional.of(sortTasks(taskList.get()));

            int option = -1;
            while(option != 0){
                System.out.println("Tasks found between " + firstDate + " and " + secondDate + "\n");
                System.out.println("TITLE | DESCRIPTION | URGENCY | STATE | GREEN SPACE | DURATION | START DATE");
                for (TaskEntryDTO taskEntry : taskList.get()) {
                    if(stateFilters.isEmpty() || stateFilters.contains(taskEntry.state)){
                        System.out.println(taskEntry.taskTitle+" | "+ taskEntry.taskDescription+" | "+taskEntry.urgencyLevel+ " | "+taskEntry.state+ " | "+taskEntry.greenSpace.toString()+" | "+taskEntry.duration+" | "+taskEntry.startDate.toString());
                    }
                }
                Scanner in = new Scanner(System.in);
                System.out.print("\nFiltering results by state(s): ");
                if(stateFilters.isEmpty()){
                    System.out.println("None.");
                }else{
                    for(State state : stateFilters){
                        System.out.print(state + " | ");
                    }
                    System.out.println();
                }
                System.out.println("Insert a number between 1 and 4 to add/remove a state filter (Planned, Postponed, Completed, and Canceled, respectively).");
                System.out.println("\nInsert 0 to return to the main menu.");
                while(true){
                    try{
                        option = Integer.parseInt(in.nextLine());
                        if(option < 0 || option > 4){
                            System.out.println("Error: The inputted number must be between 0 and 4.");
                            continue;
                        }else if(option != 0){
                            if(stateFilters.contains(stateList.get(option-1))){
                                stateFilters.remove(stateFilters.indexOf(stateList.get(option-1)));
                            }else{
                                stateFilters.add(stateList.get(option-1));
                            }
                        }
                        break;
                    }catch(Exception e){
                        System.out.println("Error: Invalid input.");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Failed to get task list!");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Requests all data and assigns it to its respective variables.
     */
    private void requestData() {
        Scanner input = new Scanner(System.in);
        System.out.print("First Date (YYYY/MM/DD, with leading zeros): ");
        firstDate = input.nextLine();
        System.out.println("Second Date (YYYY/MM/DD, with leading zeros):");
        secondDate = input.nextLine();
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData() {
        System.out.println("\n >>>>>>>>>>  LIST INFORMATION <<<<<<<<<< \n");
        System.out.println("First Date: " + firstDate);
        System.out.println("Second Date: " + secondDate);
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Sorts a list of task by their dates.
     * @param taskEntries The list of tasks to sort.
     * @return The sorted list of tasks.
     */
    private ArrayList<TaskEntryDTO> sortTasks(ArrayList<TaskEntryDTO> taskEntries){
        for(int i = 0; i < taskEntries.size(); i++){
            for(int j = 1; j < taskEntries.size()-i; j++){
                if(taskEntries.get(j-1).startDate.isAfterDate(taskEntries.get(j).startDate)){
                    TaskEntryDTO temp = taskEntries.get(j-1);
                    taskEntries.set(j-1, taskEntries.get(j));
                    taskEntries.set(j, temp);
                }
            }
        }
        return taskEntries;
    }
}