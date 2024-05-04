package pt.ipp.isep.dei.esoft.project.repository;

// MaintenanceRepository.java
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepository {
    private List<Vehicle> vehicles;

    public MaintenanceRepository() {
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehiclesNeedingMaintenance() {
        return vehicles;
    }
}