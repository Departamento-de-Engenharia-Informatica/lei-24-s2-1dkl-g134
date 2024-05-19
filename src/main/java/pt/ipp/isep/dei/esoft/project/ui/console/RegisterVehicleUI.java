package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.CustomDate;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class RegisterVehicleUI implements Runnable {
    private String brand;
    private String model;
    private int tare;
    private double grossWeight;
    private int currentKM;
    private String registerDate;
    private String acquisitionDate;
    private int checkUpFrequency;
    private String plateNumber;
    private String type;
    private RegisterVehicleController registerVehicleController = new RegisterVehicleController();

    /**
     * Run this functionality.
     */
    public void run(){
        System.out.println("\n >>>>>>>>>> REGISTER VEHICLE <<<<<<<<<< \n");

        while(true){
            requestData();
            if(!confirmData()){continue;}
            submitData();
            break;
        }
    }

    /**
     * Request all necessary data and save it to its respective variables.
     */
    private void requestData(){
        brand = requestBrand();
        model = requestModel();
        tare = requestTare();
        grossWeight = requestGrossWeight();
        currentKM = requestCurrentKM();
        registerDate = requestRegisterDate();
        acquisitionDate = requestAcquisitionDate();
        checkUpFrequency = requestCheckUpFrequency();
        plateNumber = requestPlateNumber();
        type = requestType();
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> VEHICLE INFORMATION <<<<<<<<<< \n");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Tare: " + tare);
        System.out.println("Gross Weight: "+ grossWeight);
        System.out.println("Current KM: " + currentKM);
        System.out.println("Register Date: " + registerDate);
        System.out.println("Acquisition Date: " + acquisitionDate);
        System.out.println("Check Up Frequency: " + checkUpFrequency);
        System.out.println("Plate: " + plateNumber);
        System.out.println("Type: " + type);
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submit the inputted data and provide the respective feedback.
     */
    private void submitData(){
        try{
            Optional<Vehicle> newVehicle = registerVehicleController.registerVehicle(brand,  model,  tare,  grossWeight,
                    currentKM,  registerDate,  acquisitionDate,
                    checkUpFrequency,  plateNumber,  type);
            if (newVehicle.isEmpty()){
                System.out.println("Failed to add new vehicle!");
            } else {
                System.out.println("Vehicle added successfully!");
            }
        }catch(Exception e){
            System.out.println("Failed to add new vehicle!");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Requests the vehicle's brand.
     * @return The vehicle's brand.
     */
    private String requestBrand(){
        Scanner input = new Scanner(System.in);
        System.out.println("Brand: ");
        return input.nextLine();
    }

    /**
     * Requests the vehicle's model.
     * @return The vehicle's model.
     */
    private String requestModel(){
        Scanner input = new Scanner(System.in);
        System.out.println("Model: ");
        return input.nextLine();
    }

    /**
     * Requests the vehicle's tare weight.
     * @return The vehicle's tare weight.
     */
    private int requestTare(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Tare (in kilograms): ");
                return Integer.parseInt(input.nextLine());
            }catch(Exception e){
                System.out.println("Tare value must be a number.");
            }
        }
    }

    /**
     * Requests the vehicle's gross weight.
     * @return The vehicle's gross weight.
     */
    private double requestGrossWeight(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Gross Weight (in kilograms): ");
                return Double.parseDouble(input.nextLine());
            }catch(Exception e){
                System.out.println("Gross Weight value must be a number.");
            }
        }
    }

    /**
     * Requests the vehicle's current km.
     * @return The vehicle's current km.
     */
    private int requestCurrentKM(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Current KM: ");
                return Integer.parseInt(input.nextLine());
            }catch(Exception e){
                System.out.println("Current KM value must be a number.");
            }
        }
    }

    /**
     * Requests the vehicle's registration date.
     * @return The vehicle's registration date.
     */
    private String requestRegisterDate(){
        Scanner input = new Scanner(System.in);
        System.out.print("Register Date (YYYY/MM/DD, with leading zeros): ");
        return input.nextLine();
        /*CustomDate date = Utils.readDateFromConsole("Register Date: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return date.toString();*/
    }

    /**
     * Requests the vehicle's acquisition date.
     * @return The vehicle's acquisition date.
     */
    private String requestAcquisitionDate(){
        Scanner input = new Scanner(System.in);
        System.out.print("Acquisition Date (YYYY/MM/DD, with leading zeros): ");
        return input.nextLine();
        /*CustomDate date = Utils.readDateFromConsole("Acquisition Date: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return date.toString();*/
    }

    /**
     * Requests the vehicle's checkup frequency.
     * @return The vehicle's checkup frequency.
     */
    private int requestCheckUpFrequency(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Checkup Frequency (in kilometers): ");
                return Integer.parseInt(input.nextLine());
            }catch(Exception e){
                System.out.println("Checkup Frequency value must be a number.");
            }
        }
    }

    /**
     * Requests the vehicle's plate number.
     * @return The vehicle's plate number.
     */
    private String requestPlateNumber(){
        Scanner input = new Scanner(System.in);
        System.out.println("Plate Number: ");
        return input.nextLine();
    }

    /**
     * Requests the vehicle's type.
     * @return The vehicle's type.
     */
    private String requestType(){
        Scanner input = new Scanner(System.in);
        System.out.println("Type: ");
        return input.nextLine();
    }

}
