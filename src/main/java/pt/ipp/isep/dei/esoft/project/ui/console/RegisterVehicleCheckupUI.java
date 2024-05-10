package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class RegisterVehicleCheckupUI implements Runnable {
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

    private void requestData(){
        vehicle = requestVehicle();
        if(vehicle == null){ return; }
        currentKM = requestCurrentKM();
        date = requestDate();
    }

    private Vehicle requestVehicle(){
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

    private String requestDate(){
        Date date = Utils.readDateFromConsole("Current Date: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(date);
    }

    private void submitData(){
        Optional<CheckUp> newCheckUp = registerVehicleCheckupController.registerCheckup(vehicle, currentKM, date);
        if (newCheckUp.isEmpty()){
            System.out.println("Failed to add new check up!");
        } else {
            System.out.println("Check up added successfully!");
        }
    }
}
