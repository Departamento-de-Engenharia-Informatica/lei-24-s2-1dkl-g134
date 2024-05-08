package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckupRepository {
    private final ArrayList<CheckUp> checkups;

    public CheckupRepository() {
        checkups = new ArrayList<>();
    }

    public Optional<CheckUp> add(Vehicle vehicle, int currentKM, String date){
        CheckUp newCheckUp = new CheckUp(vehicle, currentKM, date);
        if(checkups.contains(newCheckUp)){
            return Optional.empty();
        }
        checkups.add(newCheckUp);
        return Optional.of(newCheckUp);
    }

    public Optional<ArrayList<Vehicle>> getVehiclesRequiringCheckUp() {
        Optional<ArrayList<Vehicle>> vehicles = Repositories.getInstance().getVehicleRepository().getVehicleList();
        if(vehicles.isEmpty()){
            return Optional.empty();
        }
        ArrayList<Vehicle> vehiclesRequiringCheckUp = new ArrayList<>();
        for(Vehicle vehicle : vehicles.get()){
            Optional<CheckUp> vehicleLatestCheckup = getLatestCheckUpOfVehicle(vehicle);
            if(vehicleLatestCheckup.isEmpty()){
                continue;
            }
            int distance = vehicleLatestCheckup.get().getVehicle().getCurrentKM() - vehicleLatestCheckup.get().getCurrentKM();
            if(distance > vehicleLatestCheckup.get().getVehicle().getCheckUpFrequency() || (float)distance / (float)vehicleLatestCheckup.get().getVehicle().getCheckUpFrequency() >= 0.05){
                vehiclesRequiringCheckUp.add(vehicle);
            }
        }
        if(vehiclesRequiringCheckUp.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(vehiclesRequiringCheckUp);
    }

    public Optional<CheckUp> getLatestCheckUpOfVehicle(Vehicle vehicle) {
        CheckUp latestCheckUp = new CheckUp(vehicle, 1, "0000/00/00");
        int latestYear = 0, latestMonth = 0, latestDay = 0;
        for(CheckUp checkup : checkups){
            if(!checkup.getVehicle().equals(vehicle)){
                continue;
            }
            String[] dateComponents = checkup.getDate().split("/");
            int year = Integer.parseInt(dateComponents[0]);
            int month = Integer.parseInt(dateComponents[1]);
            int day = Integer.parseInt(dateComponents[2]);
            if(year >= latestYear && month >= latestMonth && day >= latestDay){
                latestYear = year;
                latestMonth = month;
                latestDay = day;
                latestCheckUp = checkup;
            }
        }
        if(latestYear == 0 || latestMonth == 0 || latestDay == 0){
            return Optional.empty();
        }
        return Optional.of(latestCheckUp);
    }
}
