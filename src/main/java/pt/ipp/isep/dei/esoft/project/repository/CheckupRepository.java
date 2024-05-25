package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.CustomDate;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckupRepository implements Serializable {
    private ArrayList<CheckUp> checkups;

    /**
     * Constructor for a new checkup repository.
     * All this does is initialize the list of checkups.
     */
    public CheckupRepository() {
        checkups = new ArrayList<>();
    }

    /**
     * Adds a new checkup to this repository.
     * If there is a checkup associated with the specified vehicle that either takes place on the
     * same date or after the date of this checkup or has a higher or equal km value than this
     * checkup, no checkup is added.
     * @param vehicle The Vehicle object to be associated with this checkup
     * @param currentKM The KMs of the vehicle as of the time of this checkup
     * @param date The date on which this checkup was performed.
     * @return The added checkup. If no checkup was added, an empty Optional object.
     */
    public Optional<CheckUp> add(Vehicle vehicle, int currentKM, String date){
        CheckUp newCheckUp = new CheckUp(vehicle, currentKM, date);
        /*if(checkups.contains(newCheckUp)){
            return Optional.empty();
        }*/
        Optional<CheckUp> latestCheckUpOfVehicle = getLatestCheckUpOfVehicle(vehicle);
        if(latestCheckUpOfVehicle.isPresent()){
            if(latestCheckUpOfVehicle.get().getDate().isAfterDate(newCheckUp.getDate()) || latestCheckUpOfVehicle.get().getCurrentKM() >= currentKM){
                return Optional.empty();
            }
        }
        if(vehicle.getCurrentKM() < currentKM){vehicle.setCurrentKM(currentKM);}
        checkups.add(newCheckUp);
        return Optional.of(newCheckUp);
    }

    /**
     * Gets all vehicles currently requiring checkup.
     * A vehicle requires a checkup if the difference between the vehicle's current kms and the
     * kms at the time of its latest checkup is greater than or otherwise is within a 5% margin of
     * the checkup frequency.
     * @return The list of all vehicles that currently require checkups.
     */
    public Optional<ArrayList<Vehicle>> getVehiclesRequiringCheckUp() {
        Optional<ArrayList<Vehicle>> vehicles = Repositories.getInstance().getVehicleRepository().getVehicleList();
        if(vehicles.isEmpty()){
            return Optional.empty();
        }
        ArrayList<Vehicle> vehiclesRequiringCheckUp = new ArrayList<>();
        for(Vehicle vehicle : vehicles.get()){
            Optional<CheckUp> vehicleLatestCheckup = getLatestCheckUpOfVehicle(vehicle);
            int vehicleCurrentKms = vehicle.getCurrentKM();
            int vehicleLatestCheckupKms = 0;
            if(vehicleLatestCheckup.isPresent()){
                vehicleLatestCheckupKms = vehicleLatestCheckup.get().getCurrentKM();
            }
            int distance = vehicleCurrentKms - vehicleLatestCheckupKms;
            if(distance > vehicle.getCheckUpFrequency() || (float)distance / (float)vehicle.getCheckUpFrequency() >= 0.95){
                vehiclesRequiringCheckUp.add(vehicle);
            }
        }
        if(vehiclesRequiringCheckUp.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(vehiclesRequiringCheckUp);
    }

    /**
     * Gets the latest checkup of a vehicle.
     * Checkups are examined by date.
     * @param vehicle The vehicle for which to search for the latest checkup.
     * @return The latest checkup of the vehicle. If no checkup was found, an empty Optional
     * object instead.
     */
    public Optional<CheckUp> getLatestCheckUpOfVehicle(Vehicle vehicle) {
        CheckUp latestCheckUp = new CheckUp(vehicle, 1, "0001/01/01");
        CustomDate latestDate = new CustomDate("0001/01/01");
        boolean anyCheckupFound = false;
        for(int i = 0; i < checkups.size(); i++){
            if(checkups.get(i).getVehicle().equals(vehicle) && checkups.get(i).getDate().isAfterDate(latestDate)){
                latestCheckUp = checkups.get(i);
                latestDate = new CustomDate(checkups.get(i).getDate().toString());
                anyCheckupFound = true;
            }
        }
        if(!anyCheckupFound){
            return Optional.empty();
        }
        return Optional.of(latestCheckUp);
    }
}
