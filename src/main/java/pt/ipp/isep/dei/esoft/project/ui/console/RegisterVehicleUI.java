package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterVehicleController;
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

    public void run(){
        System.out.println("\n >>>>>>>>>> REGISTER VEHICLE <<<<<<<<<< \n");

        requestData();

        submitData();
    }
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
    private void submitData(){
        Optional<Vehicle> newVehicle = registerVehicleController.registerVehicle(brand,  model,  tare,  grossWeight,
                currentKM,  registerDate,  acquisitionDate,
                checkUpFrequency,  plateNumber,  type);
        if (newVehicle.isEmpty()){
            System.out.println("Failed to add new vehicle!");
        } else {
            System.out.println("Vehicle added successfully!");
        }
    }
    private String requestBrand(){
        Scanner input = new Scanner(System.in);
        System.out.println("Brand: ");
        return input.nextLine();
    }
    private String requestModel(){
        Scanner input = new Scanner(System.in);
        System.out.println("Model: ");
        return input.nextLine();
    }
    private int requestTare(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Tare: ");
                return Integer.parseInt(input.nextLine());
            }catch(Exception e){
                System.out.println("Tare value must be a number.");
            }
        }
    }
    private double requestGrossWeight(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Gross Weight: ");
                return Double.parseDouble(input.nextLine());
            }catch(Exception e){
                System.out.println("Gross Weight value must be a number.");
            }
        }
    }
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
    private String requestRegisterDate(){
        Date date = Utils.readDateFromConsole("Register Date: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(date);
    }
    private String requestAcquisitionDate(){
        Date date = Utils.readDateFromConsole("Acquisition Date: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(date);
    }
    private int requestCheckUpFrequency(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Checkup Frequency: ");
                return Integer.parseInt(input.nextLine());
            }catch(Exception e){
                System.out.println("Checkup Frequency value must be a number.");
            }
        }
    }
    private String requestPlateNumber(){
        Scanner input = new Scanner(System.in);
        System.out.println("Plate Number: ");
        return input.nextLine();
    }
    private String requestType(){
        Scanner input = new Scanner(System.in);
        System.out.println("Type: ");
        return input.nextLine();
    }

}
