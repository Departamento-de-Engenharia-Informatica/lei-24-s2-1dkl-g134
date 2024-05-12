package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.CheckupRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.Optional;

public class RegisterVehicleCheckupController {
    private CheckupRepository checkupRepository;
    private VehicleRepository vehicleRepository;

    /**
     * Constructor for a new RegisterVehicleCheckupController object.
     * All this does is get the necessary repositories.
     */
    public RegisterVehicleCheckupController(){
        checkupRepository = Repositories.getInstance().getCheckupRepository();
        vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    /**
     * Registers a new checkup.
     * If there is a checkup associated with the specified vehicle that either takes place on the
     * same date or after the date of this checkup or has a higher or equal km value than this
     * checkup, no checkup is added.
     * @param vehicle The Vehicle object to be associated with this checkup
     * @param currentKM The KMs of the vehicle as of the time of this checkup
     * @param date The date on which this checkup was performed.
     * @return The added checkup. If no checkup was added, an empty Optional object.
     */
    public Optional<CheckUp> registerCheckup(Vehicle vehicle, int currentKM, String date) {
        return checkupRepository.add(vehicle, currentKM, date);
    }

    /**
     * Gets the full list of vehicles.
     * @return The list of all vehicles.
     */
    public Optional<ArrayList<Vehicle>> getVehicleList(){
        return vehicleRepository.getVehicleList();
    }
}
