package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Optional;

public class RegisterVehicleController {
    private VehicleRepository vehicleRepository;

    /**
     * Constructor for a new RegisterVehicleController object.
     * All this does is get the necessary repositories.
     */
    public RegisterVehicleController(){
        vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    /**
     * Register a new vehicle.
     * If this vehicle is equal to any other in the repository, the vehicle is not added.
     * Check the documentation of Vehicle.equals() for the criteria of such.
     * @param brand A String representing this vehicle's brand.
     * @param model A String representing this vehicle's model.
     * @param tare An int representing this vehicle's tare weight.
     * @param grossWeight A double representing this vehicle's gross weight.
     * @param currentKM An int representing this vehicle's current kms.
     * @param registerDate A String representing this vehicle's registration date.
     * @param acquisitionDate A String representing this vehicle's acquisition date.
     * @param checkUpFrequency An int representing this vehicle's check up frequency.
     * @param plateNumber A String representing this vehicle's plate number.
     * @param type A String representing this vehicle's type.
     * @return If it was added, the added vehicle. Otherwise, an empty Optional object.
     */
    public Optional<Vehicle> registerVehicle(String brand, String model, int tare, double grossWeight,
                                             int currentKM, String registerDate, String acquisitionDate,
                                             int checkUpFrequency, String plateNumber, String type) {
        return vehicleRepository.add( brand,  model,  tare,  grossWeight,
                                      currentKM,  registerDate,  acquisitionDate,
                                     checkUpFrequency,  plateNumber,  type);

    }
}
