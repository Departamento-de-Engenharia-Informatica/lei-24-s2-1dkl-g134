package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateMaintenanceReportController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class GenerateMaintenanceReportUI implements Runnable {
    private GenerateMaintenanceReportController controller = new GenerateMaintenanceReportController();

    /**
     * Run this functionality.
     */
    public void run(){
        System.out.println("\n >>>>>>>>>> LIST OF VEHICLES REQUIRING CHECKUP <<<<<<<<<< \n");

        Optional<ArrayList<Vehicle>> vehiclesRequiringCheckup = controller.getVehiclesRequiringCheckup();
        if(vehiclesRequiringCheckup.isEmpty()){
            System.out.println("No vehicles requiring checkup found");
            return;
        }
        System.out.println("PLATE NUMBER | BRAND | MODEL | CURRENT KM | CHECKUP FREQ | LAST CHECKUP KM | NEXT CHECKUP KM");
        for(Vehicle vehicle : vehiclesRequiringCheckup.get()){
            int lastCheckupKm = 0;
            if(controller.getLatestCheckUpOfVehicle(vehicle).isPresent()){
                lastCheckupKm = controller.getLatestCheckUpOfVehicle(vehicle).get().getCurrentKM();
            }
            System.out.println(vehicle.getPlateNumber() + " | " + vehicle.getBrand() + " | " + vehicle.getModel() + " | "
            + vehicle.getCurrentKM() + " | " + vehicle.getCheckUpFrequency() + " | " + lastCheckupKm
            + " | " + (lastCheckupKm + vehicle.getCheckUpFrequency()));
        }
        Scanner in = new Scanner(System.in);
        System.out.println("\nPress ENTER to continue.");
        in.nextLine();
    }
}
