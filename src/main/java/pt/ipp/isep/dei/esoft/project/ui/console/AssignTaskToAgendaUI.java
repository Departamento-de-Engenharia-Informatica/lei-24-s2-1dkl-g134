package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignTaskToAgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AssignTaskToAgendaUI implements Runnable {
    private TaskEntry taskEntry;
    private String date, time;
    private AssignTaskToAgendaController assignTaskToAgendaController = new AssignTaskToAgendaController();

    /**
     * Runs this functionality.
     */
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

    /**
     * Requests all data and assigns it to its respective variables.
     */
    public void requestData(){
        taskEntry = requestTaskEntry();
        if(taskEntry == null){return;}
        date = requestDate();
        time = requestTime();
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    public boolean confirmData(){
        System.out.println("\n>>>>>>>>>> ASSIGNMENT INFORMATION <<<<<<<<<< \n");
        System.out.println("Task: "+ taskEntry.toString());
        System.out.println("Start Date: "+ date);
        System.out.println("Start Time: "+ time);
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submits the inputted data and provides the respective feedback.
     */
    public void submitData(){
        try{
            TaskEntryDTO taskEntryDTO = new TaskEntryDTO();
            taskEntryDTO.attachedTaskEntry = taskEntry;
            taskEntryDTO.startDateStringForm = date;
            taskEntryDTO.startTimeStringForm = time;
            Optional<TaskEntry> assignedTask = assignTaskToAgendaController.assignTaskToAgenda(taskEntryDTO);
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

    /**
     * Requests the task to assign to the agenda.
     * @return The selected task.
     */
    private TaskEntry requestTaskEntry(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<TaskEntryDTO>> taskEntries = assignTaskToAgendaController.getPendingTasks();
        if(taskEntries.isEmpty()){
            System.out.println("Error: No pending tasks for a green space managed by you. Task assignment to Agenda aborted.");
            return null;
        }
        System.out.println("Choose a task from the following list:\n");
        for(int i = 0; i < taskEntries.get().size(); i++){
            System.out.println((i+1) + "- "+taskEntries.get().get(i).attachedTaskEntry.toString());
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
        return taskEntries.get().get(option-1).attachedTaskEntry;
    }

    /**
     * Requests the task's start date.
     * @return The task's start date.
     */
    public String requestDate(){
        Scanner input = new Scanner(System.in);
        System.out.println("Start date (YYYY/MM/DD, with leading zeros): ");
        return input.nextLine();
    }

    /**
     * Requests the task's start time.
     * @return The task's start time.
     */
    public String requestTime(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Starting hour (00-23, with leading zeros): ");
                String hour = input.nextLine();
                int hourInt = Integer.parseInt(hour);
                return hour+":00";
            }catch(Exception e){
                System.out.println("Hour value must be a number.");
            }
        }


    }
}
