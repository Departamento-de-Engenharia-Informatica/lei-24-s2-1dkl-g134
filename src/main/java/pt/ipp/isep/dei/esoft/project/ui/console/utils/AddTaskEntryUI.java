package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AddTaskEntryController;
import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.urgencyLevel;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AddTaskEntryUI implements Runnable {

    private final AddTaskEntryController controller;
    private String taskTitle;
    private String taskDescription;
    private urgencyLevel urgencyLevel;
    private State state;
    private int duration;

    public AddTaskEntryUI() {controller = new AddTaskEntryController();}

    public AddTaskEntryController getController() {return controller;}

    public void run() {
        System.out.println("\n >>>>>>>>>> ADD TASK ENTRY <<<<<<<<<< \n");

        while(true){
            requestData();
            if(!confirmData()){continue;}
            submitData();
            break;
        }
    }

    private void requestData() {

        taskTitle = requestTaskTitle();

        taskDescription = requestTaskDescription();

        urgencyLevel = requestUrgencyLevel();

        state = requestTaskState();

        duration = requestTaskDuration();
    }


    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> TASK ENTRY INFORMATION <<<<<<<<<< \n");
        System.out.println("Title: " + taskTitle);
        System.out.println("Description: " + taskDescription);
        System.out.println("Level of Urgency: " + urgencyLevel);
        System.out.println("State: "+ state);
        System.out.println("Duration: "+ duration);
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }
    private void submitData() {
        Optional<Task> TaskEntry = getController().addTaskEntry(taskTitle, taskDescription, urgencyLevel, state, duration);

        if (TaskEntry.isPresent()) {
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

    private State requestTaskState() {
        Scanner input = new Scanner(System.in);
        ArrayList<State> allStates = State.getAllStates();
        System.out.println("Choose a state from the following list of their respective titles:\n");
        for(int i = 0; i < allStates.size(); i++){
            System.out.println((i+1) + "- "+allStates.get(i));
        }

        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a state.");
                option = Integer.parseInt(input.nextLine());
                if(option <= 0 || option > allStates.size()){
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return allStates.get(option-1);
    }
    private int requestTaskDuration() {
        Scanner input = new Scanner(System.in);
        System.out.print("Task Duration: ");
        return input.nextInt();
    }

}
