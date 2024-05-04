package pt.ipp.isep.dei.esoft.project.domain;


import java.util.ArrayList;
import java.util.List;

public class GenerateMaintenanceReport {
    private List<CheckUp> checkUps;

    public GenerateMaintenanceReport() {
        this.checkUps = new ArrayList<>();
    }

    public void addCheckUp(CheckUp checkUp) {

    }

    public List<CheckUp> getCheckUps() {
        return checkUps;
    }

    public List<String[]> generateMaintenanceReport() {
        List<String[]> report = new ArrayList<>();
        for (CheckUp checkUp : checkUps) {
            String[] rowData = new String[7];
            rowData[0] = checkUp.getVehicle().getPlate();
            rowData[1] = checkUp.getVehicle().getBrand();
            rowData[2] = checkUp.getVehicle().getModel();
            rowData[3] = String.valueOf(checkUp.getCurrentKM());
            rowData[5] = String.valueOf(checkUp.getVehicle().getLastMaintenanceKM());
            rowData[6] = String.valueOf(checkUp.getVehicle().getNextMaintenanceKM());
            report.add(rowData);
        }
        return report;
    }

    public void generateReport() {
    }
}
