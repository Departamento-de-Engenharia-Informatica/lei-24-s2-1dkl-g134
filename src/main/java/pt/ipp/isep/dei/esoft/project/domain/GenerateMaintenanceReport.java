package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.CheckupRepository;

import java.io.PrintStream;
import java.util.List;

public class GenerateMaintenanceReport {
    private CheckupRepository checkupRepository;

    public GenerateMaintenanceReport(CheckupRepository checkupRepository) {
        this.checkupRepository = checkupRepository;
    }

    public void generateReport() {
        List<CheckUp> checkUps = checkupRepository.getVehicleCheckUp();
        if (checkUps.isEmpty()) {
            System.out.println("No vehicles need maintenance at this time.");
            return;
        }
        System.out.println("Maintenance Report:");
        System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%n",
                "Plate", "Brand", "Model", "Curr.Kms", "Freq", "Next");
        for (CheckUp checkUp : checkUps) {
            PrintStream printf = System.out.printf("%-10s%-10s%-10s%-10d%-10d%-10d%n", checkUp.getVehicle().getPlate(),
                    checkUp.getVehicle().getBrand(),
                    checkUp.getVehicle().getModel(),
                    checkUp.getCurrentKM());
        }
    }
}
