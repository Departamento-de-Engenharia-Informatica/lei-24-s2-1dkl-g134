package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignTaskToAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AssignTaskToAgendaUI implements Runnable {
    private TaskEntry taskEntry;
    private State state;
    private String date;
    private AssignTaskToAgendaController assignTaskToAgendaController = new AssignTaskToAgendaController();

    public void run(){
        System.out.println("\n >>>>>>>>>> ASSIGN TASK TO AGENDA <<<<<<<<<< \n");

        while (true){
            requestData();
            if(taskEntry == null){return;}
            if(!confirmData()){continue;}
            submitData();
            break;
        }
    }
    public void requestData(){
        taskEntry = requestTaskEntry();
        if(taskEntry == null){return;}
        state = requestTaskState();
        date = requestDate();
    }
    public boolean confirmData(){
        System.out.println("\n>>>>>>>>>> ASSIGNMENT INFORMATION <<<<<<<<<< \n");
        System.out.println("Task: "+ taskEntry.toString());
        System.out.println("State: "+ state);
        System.out.println("Date: "+ date);
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }
    public void submitData(){
        try{
            Optional<TaskEntry> assignedTask = assignTaskToAgendaController.assignTaskToAgenda(taskEntry,state,date);
            if(assignedTask.isEmpty()){
                System.out.println("Failed to assign task to Agenda!");
            }else{
                System.out.println("Task assigned successfully!");
            }
        }catch(Exception e){
            System.out.println("Failed to assign task to Agenda!");
            System.out.println(e.getMessage());
        }
    }

    private TaskEntry requestTaskEntry(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<TaskEntry>> taskEntries = assignTaskToAgendaController.getPendingTasks();
        if(taskEntries.isEmpty()){
            System.out.println("Error: No pending tasks entries. Task assignment to Agenda aborted.");
            return null;
        }
        System.out.println("Choose a task from the following list:\n");
        for(int i = 0; i < taskEntries.get().size(); i++){
            System.out.println((i+1) + "- "+taskEntries.get().get(i).toString());
        }
        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a task.");
                option = Integer.parseInt(input.nextLine());
                if(option <= 0 || option > taskEntries.get().size()){
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return taskEntries.get().get(option-1);
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

    public String requestDate(){
        Scanner input = new Scanner(System.in);
        System.out.println("Date (YYYY/MM/DD, with leading zeros): ");
        return input.nextLine();
    }
}
