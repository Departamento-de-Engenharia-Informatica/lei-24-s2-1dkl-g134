package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.AddTaskEntryController;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
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
        Optional<ArrayList<TaskEntry>> taskEntries = controller.getPlannedAndPostponedTask();
        
    }
}
