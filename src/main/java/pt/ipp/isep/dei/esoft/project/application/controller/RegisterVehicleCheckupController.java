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

    public RegisterVehicleCheckupController(){
        getCheckupRepository();
        getVehicleRepository();
    }
    private void getCheckupRepository(){
        Repositories repositories = Repositories.getInstance();
        checkupRepository = repositories.getCheckupRepository();
    }
    private void getVehicleRepository(){
        Repositories repositories = Repositories.getInstance();
        vehicleRepository = repositories.getVehicleRepository();
    }
    public Optional<CheckUp> registerCheckup(Vehicle vehicle, int currentKM, String date) {
        return checkupRepository.add(vehicle, currentKM, date);
    }
    public Optional<ArrayList<Vehicle>> getVehicleList(){
        return vehicleRepository.getVehicleList();
    }
}
