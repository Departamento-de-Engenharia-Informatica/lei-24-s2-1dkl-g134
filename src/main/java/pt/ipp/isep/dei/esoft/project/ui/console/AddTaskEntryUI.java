package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddTaskEntryController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AddTaskEntryUI implements Runnable {

    private final AddTaskEntryController controller = new AddTaskEntryController();
    private String taskTitle;
    private String taskDescription;
    private urgencyLevel urgencyLevel;
    private int duration;
    private GreenSpace greenSpace;

    /**
     * Gets the controller for this functionality.
     * @return This functionality's controller.
     */
    public AddTaskEntryController getController() {return controller;}

    /**
     * Runs this functionality.
     */
    public void run() {
        System.out.println("\n >>>>>>>>>> ADD TASK ENTRY <<<<<<<<<< \n");

        while(true){
            requestData();
            if(greenSpace == null) {
                return;
            }
            if(!confirmData()){continue;}
            submitData();
            break;
        }
    }

    /**
     * Requests all data and assigns it to its respective variables.
     */
    private void requestData() {

        greenSpace = requestGreenSpaceManagedByUser();
        if(greenSpace == null) {
            return;
        }

        taskTitle = requestTaskTitle();

        taskDescription = requestTaskDescription();

        urgencyLevel = requestUrgencyLevel();

        duration = requestTaskDuration();

    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> TASK ENTRY INFORMATION <<<<<<<<<< \n");
        System.out.println("Title: " + taskTitle);
        System.out.println("Description: " + taskDescription);
        System.out.println("Green Space: " + greenSpace.toString());
        System.out.println("Level of Urgency: " + urgencyLevel);
        System.out.println("Duration: "+ duration+" hours");
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submits the inputted data and provides the respective feedback.
     */
    private void submitData() {
        try{
            TaskEntryDTO taskEntryDTO = new TaskEntryDTO();
            taskEntryDTO.taskTitle = taskTitle;
            taskEntryDTO.taskDescription = taskDescription;
            taskEntryDTO.urgencyLevel = urgencyLevel;
            taskEntryDTO.duration = duration;
            taskEntryDTO.greenSpace = greenSpace;
            Optional<TaskEntry> taskEntry = getController().addTaskEntry(taskEntryDTO);
            if (taskEntry.isPresent()) {
                System.out.println("\nTask Entry successfully created!");
            } else {
                System.out.println("\nTask Entry not created!");
            }
        }catch(Exception e){
            System.out.println("Failed to create the task!");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Requests the task's title.
     * @return The task's title.
     */
    private String requestTaskTitle() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Title: ");
        return input.nextLine();
    }

    /**
     * Requests the task's description.
     * @return The task's description.
     */
    private String requestTaskDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Description: ");
        return input.nextLine();
    }

    /**
     * Requests the task's urgency level.
     * @return The task's urgency level.
     */
    private urgencyLevel requestUrgencyLevel() {
        Scanner input = new Scanner(System.in);
        ArrayList<urgencyLevel> allUrgencyLevels = pt.ipp.isep.dei.esoft.project.domain.urgencyLevel.getAllUrgencyLevels();
        System.out.println("Choose a urgency level from the following list of their respective titles:\n");
        for(int i = 0; i < allUrgencyLevels.size(); i++){
            System.out.println((i+1) + "- "+allUrgencyLevels.get(i));
        }

        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a urgency level.");
                option = Integer.parseInt(input.nextLine());
                if(option <= 0 || option > allUrgencyLevels.size()){
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return allUrgencyLevels.get(option-1);
    }

    /**
     * Requests the task's duration.
     * @return The task's duration.
     */
    private int requestTaskDuration() {
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Task Duration (in hours): ");
                return Integer.parseInt(input.nextLine());
            }catch(Exception e){
                System.out.println("Task Duration value must be a number.");
            }
        }
    }

    /**
     * Requests the task's green space, from a list of green spaces managed by the current
     * logged-in user.
     * @return The task's green space.
     */
    private GreenSpace requestGreenSpaceManagedByUser() {
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<GreenSpaceDTO>> greenSpacesManagedByUser = controller.getGreenSpacesManagedByCurrentUser();
        if(greenSpacesManagedByUser.isEmpty()){
            System.out.println("No green spaces managed by you were found. Task creation aborted.");
            return null;
        }
        System.out.println("Choose a green space from the following list of their respective titles:\n");
        for(int i = 0; i < greenSpacesManagedByUser.get().size(); i++){
            System.out.println((i+1) + "- "+greenSpacesManagedByUser.get().get(i).attachedGreenSpace.toString());
        }
        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a green space.");
                option = Integer.parseInt(input.nextLine());
                if(option <= 0 || option > greenSpacesManagedByUser.get().size()){
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return greenSpacesManagedByUser.get().get(option-1).attachedGreenSpace;
    }

}
