package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PostponeTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class PostponeTaskUI implements Runnable{
    private TaskEntry taskEntry;
    private String date;
    private PostponeTaskController controller = new PostponeTaskController();


    public void run() {
        System.out.println("\n >>>>>>>>>> POSTPONE TASK <<<<<<<<<< \n");

        while(true){
            requestData();
            if (taskEntry==null){
                return;
            }
            if(!confirmData()){continue;}
            submitData();
            break;
        }
    }
    private void requestData() {
        taskEntry= requestTaskEntry();
        if (taskEntry==null){
            return;
        }
        date = requestDate();
    }

    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> TASK ENTRY INFORMATION <<<<<<<<<< \n");
        System.out.println("New date: " + date);
        System.out.println("Postponed task: " + taskEntry.toString());
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }
    private void submitData() {
        Optional<TaskEntry> taskEntries = controller.postponeTask(this.taskEntry,date);
        if (taskEntries.isPresent()) {
            System.out.println("\nSuccessfully postponed!");
        } else {
            System.out.println("\nFailed to be postponed");
        }
    }

    private String requestDate(){
        Scanner input = new Scanner(System.in);
        System.out.print("New date (YYYY/MM/DD, with leading zeros): ");
        return input.nextLine();
        /*CustomDate date = Utils.readDateFromConsole("Current Date: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return date.toString();*/
    }

    private TaskEntry requestTaskEntry(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<TaskEntry>> taskEntries = controller.getPlannedAndPostponedTask();
        if(taskEntries.isEmpty()){
            System.out.println("Error: No available tasks. Task postponing aborted.");
            return null;
        }
        System.out.println("Choose a task from the following list (Title | Description):\n");
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
}
