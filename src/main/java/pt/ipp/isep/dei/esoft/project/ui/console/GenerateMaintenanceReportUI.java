package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.domain.GenerateMaintenanceReport;

public class GenerateMaintenanceReportController {
    private GenerateMaintenanceReport reportGenerator;

    public GenerateMaintenanceReportController(GenerateMaintenanceReport reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    public void generateReport() {
        reportGenerator.generateReport();
    }
}
