package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehiclesToTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AssignVehiclesToTaskUI implements Runnable {
    private TaskEntry taskEntry = null;
    private ArrayList<Vehicle> selectedVehicles = null;
    private AssignVehiclesToTaskController controller = new AssignVehiclesToTaskController();

    /**
     * Run this functionality.
     */
    public void run(){
        System.out.println("\n >>>>>>>>>> ASSIGN VEHICLES TO TASK <<<<<<<<<< \n");

        while(true){
            requestData();
            if(taskEntry == null || selectedVehicles == null){ return; }
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
        selectedVehicles = requestVehicles();
    }

    /**
     * Requests the task to assign vehicles to.
     * @return The selected task.
     */
    private TaskEntry requestTaskEntry(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<TaskEntryDTO>> taskEntries = controller.getPlannedAndPostponedTasks();
        if(taskEntries.isEmpty()){
            System.out.println("Error: No tasks in the agenda for a green space managed by you. Vehicle assignment aborted.");
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
     * Requests the list of vehicles to assign.
     * @return The list of vehicles to assign.
     */
    private ArrayList<Vehicle> requestVehicles(){
        ArrayList<Vehicle> userVehicleSelection = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<VehicleDTO>> vehicles = controller.getVehicleList();
        if(vehicles.isEmpty()){
            System.out.println("Error: No Vehicles. Vehicle assignment aborted.");
            return null;
        }
        for(VehicleDTO vehicle : vehicles.get()){
            if(!controller.isVehicleAvailable(vehicle.attachedVehicle, taskEntry)){
                vehicles.get().remove(vehicles.get().indexOf(vehicle));
            }
        }
        while(true){
            System.out.println("Choose vehicles from the following list (to remove a selected vehicle, choose it again):\n");
            for(int i = 0; i < vehicles.get().size(); i++){
                if(userVehicleSelection.contains(vehicles.get().get(i).attachedVehicle)){
                    System.out.println((i+1) + "- "+vehicles.get().get(i).attachedVehicle.toString()+" (Selected)");
                }else{
                    System.out.println((i+1) + "- "+vehicles.get().get(i).attachedVehicle.toString());
                }
            }
            int option = 0;
            while(true){
                try{
                    System.out.println("Choose a number corresponding to a vehicle.");
                    System.out.println("When selection is done, choose the number 0.");
                    option = Integer.parseInt(input.nextLine());
                    if(option < 0 || option > vehicles.get().size()){
                        System.out.println("Error: Invalid option.");
                        continue;
                    }
                    break;
                }catch(Exception e){
                    System.out.println("Error: Selected option must be a number.");
                }
            }
            if(option == 0){
                return userVehicleSelection;
            }
            if(userVehicleSelection.contains(vehicles.get().get(option-1).attachedVehicle)){
                userVehicleSelection.remove(vehicles.get().get(option-1).attachedVehicle);
            }else{
                userVehicleSelection.add(vehicles.get().get(option-1).attachedVehicle);
            }
        }
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> ASSIGNMENT INFORMATION <<<<<<<<<< \n");
        System.out.println("Assign the following vehicles: ");
        for(Vehicle vehicle : selectedVehicles){
            System.out.println(vehicle.toString());
        }
        System.out.println("\nTo the task "+taskEntry.toString());
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submit the inputted data and provide the respective feedback.
     */
    private void submitData(){
        try{
            Optional<ArrayList<Vehicle>> assignedVehicles = controller.assignVehiclesToTask(selectedVehicles, taskEntry);
            if(assignedVehicles.isPresent()){
                System.out.println("The following vehicles were assigned successfully:");
                for(Vehicle vehicle : assignedVehicles.get()){
                    System.out.println(vehicle.toString());
                }
                System.out.println("If any selected vehicle is missing from this list, it was either invalid or already present in the task.");
            }else{
                System.out.println("Failed to assign vehicles successfully! Are you sure the selected task doesn't already have these vehicles?");
            }
        }catch(Exception e){
            System.out.println("Failed to assign vehicles successfully!");
            System.out.println(e.getMessage());
        }

    }
}
