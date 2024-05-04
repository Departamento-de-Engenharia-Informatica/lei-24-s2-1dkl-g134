package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

public class CheckupRepository {
    private final List<CheckUp> checkups;

    public CheckupRepository() {
        checkups = new ArrayList<>();
    }
        public List<CheckUp> getVehicleCheckUp(List<Vehicle> vehicleList) {

        }

    public List<CheckUp> getVehicleCheckUp() {
    }
}
