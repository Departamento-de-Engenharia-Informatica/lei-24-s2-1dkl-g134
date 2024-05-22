package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AddTaskEntryController;
import pt.ipp.isep.dei.esoft.project.domain.*;
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

    public AddTaskEntryController getController() {return controller;}

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


    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> TASK ENTRY INFORMATION <<<<<<<<<< \n");
        System.out.println("Title: " + taskTitle);
        System.out.println("Description: " + taskDescription);
        System.out.println("Level of Urgency: " + urgencyLevel);
        System.out.println("Duration: "+ duration);
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }
    private void submitData() {
        Optional<TaskEntry> taskEntry = getController().addTaskEntry(taskTitle, taskDescription, urgencyLevel, duration);

        if (taskEntry.isPresent()) {
            System.out.println("\nTask Entry successfully created!");
        } else {
            System.out.println("\nTask Entry not created!");
        }
    }


    private String requestTaskTitle() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Title: ");
        return input.nextLine();
    }

    private String requestTaskDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Description: ");
        return input.nextLine();
    }

    private urgencyLevel requestUrgencyLevel() {
        Scanner input = new Scanner(System.in);
        ArrayList<urgencyLevel> allUrgencyLevels = urgencyLevel.getAllUrgencyLevels();
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

    private int requestTaskDuration() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Duration (in days): ");
        return input.nextInt();
    }

    private GreenSpace requestGreenSpaceManagedByUser() {
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<GreenSpace>> greenSpacesManagedByUser = controller.getGreenSpacesManagedByCurrentUser();
        if(greenSpacesManagedByUser.isEmpty()){
            System.out.println("No green spaces managed by you were found. Task creation aborted..");
            return null;
        }
        System.out.println("Choose a green space from the following list of their respective titles:\n");
        for(int i = 0; i < greenSpacesManagedByUser.get().size(); i++){
            System.out.println((i+1) + "- "+greenSpacesManagedByUser.get().get(i));
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
        return greenSpacesManagedByUser.get().get(option-1);
    }

}
