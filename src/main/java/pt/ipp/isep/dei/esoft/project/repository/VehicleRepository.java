package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private final ArrayList<Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    public Vehicle add(String brand, String model, int tare, double grossWeight,
                       int currentKM, String registerDate, String acquisitionDate,
                       int checkUpFrequency, String plateNumber, String type) {
        Vehicle newVehicle = new Vehicle(brand, model, tare, grossWeight, currentKM,
                registerDate, acquisitionDate, checkUpFrequency,
                plateNumber, type);
        vehicles.contains(newVehicle);
        if (!exists(newVehicle)) {
            vehicles.add(newVehicle);
            return newVehicle;
        }
        return null;
    }

    public boolean exists(Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equals(vehicle.getPlateNumber())) {
                return true;
            }
        }
        return false;
    }


}

