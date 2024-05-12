package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Optional;

public class VehicleRepository {
    private ArrayList<Vehicle> vehicles;

    /**
     * Constructor for a vehicle repository.
     * All this does is initialize the list of vehicles.
     */
    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    /**
     * Returns the full list of vehicles registered in this repository.
     * @return A list of vehicles present in this repository.
     */
    public Optional<ArrayList<Vehicle>> getVehicleList() {
        if(vehicles.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(vehicles);
    }

    /**
     * Adds a vehicle to this repository.
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
    public Optional<Vehicle> add(String brand, String model, int tare, double grossWeight,
                                 int currentKM, String registerDate, String acquisitionDate,
                                 int checkUpFrequency, String plateNumber, String type) {
        Vehicle v = new Vehicle(brand, model, tare, grossWeight,currentKM,registerDate,acquisitionDate,checkUpFrequency,plateNumber,type);
        if(vehicles.contains(v)){
            return Optional.empty();
        }
        vehicles.add(v);
        return Optional.of(v);
    }
}


