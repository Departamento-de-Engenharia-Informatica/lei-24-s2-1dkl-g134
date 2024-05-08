package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.CheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class GenerateMaintenanceReportController {
    private CheckupRepository checkupRepository;

    public GenerateMaintenanceReportController() {
        getCheckupRepository();
    }

    private void getCheckupRepository() {
        checkupRepository = Repositories.getInstance().getCheckupRepository();
    }

    public Optional<ArrayList<Vehicle>> getVehiclesRequiringCheckup() {
        return checkupRepository.getVehiclesRequiringCheckUp();
    }

    public Optional<CheckUp> getLatestCheckUpOfVehicle(Vehicle vehicle){
        return checkupRepository.getLatestCheckUpOfVehicle(vehicle);
    }
}
