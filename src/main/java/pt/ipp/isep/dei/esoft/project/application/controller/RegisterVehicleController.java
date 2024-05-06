package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.Optional;

public class RegisterVehicleController {
    private VehicleRepository vehicleRepository;

    public RegisterVehicleController(){
        getVehicleRepository();
    }
    private void getVehicleRepository(){
        Repositories repositories = Repositories.getInstance();
        vehicleRepository = repositories.getVehicleRepository();
    }
    public Optional<Vehicle> registerVehicle(String brand, String model, int tare, double grossWeight,
                                             int currentKM, String registerDate, String acquisitionDate,
                                             int checkUpFrequency, String plateNumber, String type) {
        return vehicleRepository.add( brand,  model,  tare,  grossWeight,
                                      currentKM,  registerDate,  acquisitionDate,
                                     checkUpFrequency,  plateNumber,  type);

    }



}
