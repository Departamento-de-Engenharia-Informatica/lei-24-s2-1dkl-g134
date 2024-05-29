package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignTeamToTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AssignTeamToTaskUI {
    private TaskEntry taskEntry = null;
    private Team selectedTeam= null;
    private AssignTeamToTaskController controller = new AssignTeamToTaskController();

    /**
     * Run this functionality.
     */
    public void run(){
        System.out.println("\n >>>>>>>>>> ASSIGN TEAM TO TASK <<<<<<<<<< \n");

        while(true){
            requestData();
            if(taskEntry == null || selectedTeam == null){ return; }
            if(!confirmData()){continue;}
            submitData();
            break;
        }
    }

    /**
     * Request all necessary data and save it to its respective variables.
     */
    private void requestData(){
        taskEntry = requestTaskEntry();
        if(taskEntry == null){ return; }
        selectedTeam = requestTeam();
    }

    /**
     * Requests the collaborator to assign skills to.
     * @return The collaborator to assign skills to.
     */
    private TaskEntry requestTaskEntry(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<TaskEntryDTO>> taskEntries = controller.getPlannedAndPostponedTasks();
        if(taskEntries.isEmpty()){
            System.out.println("Error: No tasks. Team assignment aborted.");
            return null;
        }
        System.out.println("Choose a task from the following list (Title | Description):\n");
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
     * Requests the list of skills to assign.
     * @return The list of skills to assign.
     */
    private Team requestTeam(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<TeamDTO>> teams = controller.getTeamList();
        if(teams.isEmpty()){
            System.out.println("Error: No teams. Team assignment aborted.");
            return null;
        }
        for(TeamDTO team : teams.get()){
            if(!controller.isTeamAvailable(team.attachedTeam, taskEntry)){
                teams.get().remove(teams.get().indexOf(team));
            }
        }
        System.out.println("Choose a team from the following list (Title | Description):\n");
        for(int i = 0; i < teams.get().size(); i++){
            System.out.println((i+1) + "-\n "+teams.get().get(i).attachedTeam.toString());
        }
        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a team.");
                option = Integer.parseInt(input.nextLine());
                if(option <= 0 || option > teams.get().size()){
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return teams.get().get(option-1).attachedTeam;
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> ASSIGNMENT INFORMATION <<<<<<<<<< \n");
        System.out.println("Assign the following team: ");
        System.out.println(selectedTeam.toString());
        System.out.println("\nTo the task "+taskEntry.toString());
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submit the inputted data and provide the respective feedback.
     */
    private void submitData(){
        try{
            Optional<TaskEntry> assignedTask = controller.assignTeamToTask(selectedTeam, taskEntry);
            if(assignedTask.isPresent()){
                System.out.println("Team assigned successfully!");
            }else{
                System.out.println("Failed to assign Team!");
            }
        }catch(Exception e){
            System.out.println("Failed to assign Team!");
            System.out.println(e.getMessage());
        }

    }
}
