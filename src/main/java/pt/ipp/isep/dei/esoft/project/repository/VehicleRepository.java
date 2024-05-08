package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Optional;

public class VehicleRepository {
    private ArrayList<Vehicle> vehicles;

    public VehicleRepository() {
        this.vehicles = new ArrayList<>();
    }

    public Optional<ArrayList<Vehicle>> getVehicleList() {
        if(vehicles.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(vehicles);
    }

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


