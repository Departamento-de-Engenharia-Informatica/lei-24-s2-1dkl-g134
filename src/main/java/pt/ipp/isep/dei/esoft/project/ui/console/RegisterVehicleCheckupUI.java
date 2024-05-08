package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class RegisterVehicleCheckupUI {
    private Vehicle vehicle;
    private int currentKM;
    private String date;
    private RegisterVehicleCheckupController registerVehicleCheckupController = new RegisterVehicleCheckupController();

    public void run(){
        System.out.println("\n >>>>>>>>>> REGISTER VEHICLE CHECKUP <<<<<<<<<< \n");

        requestData();
        if(vehicle == null){ return; }
        submitData();
    }

    public void requestData(){
        vehicle = requestVehicle();
        if(vehicle == null){ return; }
        currentKM = requestCurrentKM();
        date = requestDate();
    }

    public Vehicle requestVehicle(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<Vehicle>> vehicles = registerVehicleCheckupController.getVehicleList();
        if(vehicles.isEmpty()){
            System.out.println("Error: No vehicles found. Checkup registration aborted.");
            return null;
        }
        System.out.println("Choose a vehicle from the following list of their respective plate numbers:\n");
        for(int i = 0; i < vehicles.get().size(); i++){
            System.out.println((i+1) + "- "+vehicles.get().get(i).getPlateNumber());
        }

        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a vehicle.");
                option = input.nextInt();
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

    public int requestCurrentKM(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Current KM (at checkup): ");
                return input.nextInt();
            }catch(Exception e){
                System.out.println("Current KM value must be a number.");
            }
        }
    }

    public String requestDate(){
        Scanner input = new Scanner(System.in);
        System.out.println("Current Date: ");
        return input.nextLine();
    }

    public void submitData(){
        Optional<CheckUp> newCheckUp = registerVehicleCheckupController.registerCheckup(vehicle, currentKM, date);
        if (newCheckUp.isEmpty()){
            System.out.println("Failed to add new check up!");
        } else {
            System.out.println("Check up added successfully!");
        }
    }
}
