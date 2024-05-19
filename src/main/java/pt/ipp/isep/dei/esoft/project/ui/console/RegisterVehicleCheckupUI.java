package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.CustomDate;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class RegisterVehicleCheckupUI implements Runnable {
    private Vehicle vehicle;
    private int currentKM;
    private String date;
    private RegisterVehicleCheckupController registerVehicleCheckupController = new RegisterVehicleCheckupController();

    /**
     * Run this functionality.
     */
    public void run(){
        System.out.println("\n >>>>>>>>>> REGISTER VEHICLE CHECKUP <<<<<<<<<< \n");
        while(true){
            requestData();
            if(vehicle == null){ return; }
            if(!confirmData()){continue;}
            submitData();
            break;
        }
    }

    /**
     * Request all necessary data and save it to its respective variables.
     */
    private void requestData(){
        vehicle = requestVehicle();
        if(vehicle == null){ return; }
        currentKM = requestCurrentKM();
        date = requestDate();
    }

    /**
     * Requests the checkup's vehicle.
     * @return The checkup's vehicle.
     */
    private Vehicle requestVehicle(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<Vehicle>> vehicles = registerVehicleCheckupController.getVehicleList();
        if(vehicles.isEmpty()){
            System.out.println("Error: No vehicles found. Checkup registration aborted.");
            return null;
        }
        System.out.println("Choose a vehicle from the following list of their respective plate numbers:\n");
        for(int i = 0; i < vehicles.get().size(); i++){
            System.out.println((i+1) + "- "+vehicles.get().get(i).toString());
        }

        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a vehicle.");
                option = Integer.parseInt(input.nextLine());
                if(option <= 0 || option > vehicles.get().size()){
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return vehicles.get().get(option-1);
    }

    /**
     * Requests the checkup's current km.
     * @return The checkup's current km.
     */
    private int requestCurrentKM(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Current KM (at checkup): ");
                return Integer.parseInt(input.nextLine());
            }catch(Exception e){
                System.out.println("Current KM value must be a number.");
            }
        }
    }

    /**
     * Requests the checkup's date.
     * @return The checkup's date.
     */
    private String requestDate(){
        Scanner input = new Scanner(System.in);
        System.out.print("Checkup Date (YYYY/MM/DD, with leading zeros): ");
        return input.nextLine();
        /*CustomDate date = Utils.readDateFromConsole("Current Date: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return date.toString();*/
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> CHECKUP INFORMATION <<<<<<<<<< \n");
        System.out.println("Vehicle: " + vehicle.toString());
        System.out.println("Current KM: " + currentKM);
        System.out.println("Date: " + date);
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submit the inputted data and provide the respective feedback.
     */
    private void submitData(){
        try{
            Optional<CheckUp> newCheckUp = registerVehicleCheckupController.registerCheckup(vehicle, currentKM, date);
            if (newCheckUp.isEmpty()){
                System.out.println("Failed to add new check up! Are you sure the checkup date or kms are correct?");
            } else {
                System.out.println("Check up added successfully!");
            }
        }catch(Exception e){
            System.out.println("Failed to add new check up!");
            System.out.println(e.getMessage());
        }
    }
}
