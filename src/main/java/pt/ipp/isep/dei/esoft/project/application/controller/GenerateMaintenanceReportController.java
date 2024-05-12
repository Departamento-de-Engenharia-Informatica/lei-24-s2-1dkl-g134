package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.CheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class GenerateMaintenanceReportController {
    private CheckupRepository checkupRepository;

    /**
     * Constructor for a new GenerateMaintenanceReportController object.
     * All this does is get the necessary repositories.
     */
    public GenerateMaintenanceReportController() {
        checkupRepository = Repositories.getInstance().getCheckupRepository();
    }

    /**
     * Gets all vehicles currently requiring checkup.
     * A vehicle requires a checkup if the difference between the vehicle's current kms and the
     * kms at the time of its latest checkup is greater than or otherwise is within a 5% margin of
     * the checkup frequency.
     * @return The list of all vehicles that currently require checkups.
     */
    public Optional<ArrayList<Vehicle>> getVehiclesRequiringCheckup() {
        return checkupRepository.getVehiclesRequiringCheckUp();
    }

    /**
     * Gets the latest checkup of a vehicle.
     * Checkups are examined by date.
     * @param vehicle The vehicle for which to search for the latest checkup.
     * @return The latest checkup of the vehicle. If no checkup was found, an empty Optional
     * object instead.
     */
    public Optional<CheckUp> getLatestCheckUpOfVehicle(Vehicle vehicle){
        return checkupRepository.getLatestCheckUpOfVehicle(vehicle);
    }
}
