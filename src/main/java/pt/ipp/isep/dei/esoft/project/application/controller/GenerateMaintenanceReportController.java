package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GenerateMaintenanceReport;
import pt.ipp.isep.dei.esoft.project.ui.console.GenerateMaintenanceReportUI;

public class GenerateMaintenanceReportController {
    private GenerateMaintenanceReport reportGenerator;
    private GenerateMaintenanceReportUI reportUI;

    public GenerateMaintenanceReportController(GenerateMaintenanceReport reportGenerator, GenerateMaintenanceReportUI reportUI) {
        this.reportGenerator = reportGenerator;
        this.reportUI = reportUI;
    }

    public void generateReport() {
        reportGenerator.generateReport();
    }

    public void run() {
        reportUI.displayMenu();
    }
}
