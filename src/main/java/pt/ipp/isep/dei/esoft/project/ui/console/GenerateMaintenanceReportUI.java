package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateMaintenanceReportController;

import java.util.Scanner;

public class GenerateMaintenanceReportUI {
    private GenerateMaintenanceReportController controller;

    public GenerateMaintenanceReportUI(GenerateMaintenanceReportController controller) {
        this.controller = controller;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Generate Maintenance Report");
        System.out.println("1. Generate Report");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                controller.generateReport();
                break;
            case 0:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        displayMenu();
    }
}
