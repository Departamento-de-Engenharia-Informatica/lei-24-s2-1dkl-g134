package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.CustomDate;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckupRepositoryTest {

    @Test
    void ensureNewCheckupSuccessfullyAdded() {
        CheckupRepository CheckupRepository = new CheckupRepository();
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,750,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        assertTrue(CheckupRepository.add(vehicle, 1000, "2020/04/25").isPresent());
    }

    @Test
    void testGetVehiclesRequiringCheckup() {
        // -------------------------- TO THE TEACHER EXAMINING THESE TESTS ----------------------
        //We are fully aware that congregating tests into individual functions like this defeats
        //the point of unit tests. However, we found no other solution to get around one particular
        //problem: These tests are all done on functions that depend on the repositories created in
        //the Repositories class, and they require sometimes conflicting conditions within those
        //repositories. We found that splitting these tests into different unit tests would cause
        //errors due to there being no way to clear a repository's data, but if we congregate all
        //of them into 1 test, there are no errors, for some reason. So we elected to do this,
        //despite the fact that it should not be required.
        assertTrue(ensureGetVehiclesRequiringCheckupReturnsEmptyWithNoVehicles());
        assertTrue(ensureGetVehiclesRequiringCheckupReturnsEmptyWithNoVehiclesRequiringCheckup());
        assertTrue(ensureGetVehiclesRequiringCheckupReturnsVehiclesRequiringCheckup());
    }

    boolean ensureGetVehiclesRequiringCheckupReturnsEmptyWithNoVehicles() {
        CheckupRepository CheckupRepository = new CheckupRepository();
        return CheckupRepository.getVehiclesRequiringCheckUp().isEmpty();
    }

    boolean ensureGetVehiclesRequiringCheckupReturnsEmptyWithNoVehiclesRequiringCheckup() {
        CheckupRepository CheckupRepository = new CheckupRepository();
        VehicleRepository VehicleRepository = Repositories.getInstance().getVehicleRepository();
        VehicleRepository.add("The US", "F1", 5000, 2500.0,2000,
                "2010/04/25", "2009/04/25", 5000, "45-50-LL", "Jet");
        Vehicle vehicle = VehicleRepository.add("The USSR", "Stalin", 5000, 2500.0,1000,
                "2010/04/25", "2009/04/25", 1010, "46-51-DL", "Tank").get();
        CheckupRepository.add(vehicle, 1500, "2020/03/15");
        return CheckupRepository.getVehiclesRequiringCheckUp().isEmpty();
    }

    boolean ensureGetVehiclesRequiringCheckupReturnsVehiclesRequiringCheckup(){
        CheckupRepository CheckupRepository = new CheckupRepository();
        VehicleRepository VehicleRepository = Repositories.getInstance().getVehicleRepository();
        Vehicle vehicleOne = VehicleRepository.add("Ford", "T", 5000, 2500.0,495,
        "2010/04/25", "2009/04/25", 500, "48-55-AL", "Car").get();
        Vehicle vehicleTwo = VehicleRepository.add("Ford", "X", 5000, 2500.0,1000,
        "2010/04/25", "2009/04/25", 750, "42-49-ZL", "Truck").get();
        Vehicle vehicleThree = VehicleRepository.add("Ford", "Y", 5000, 2500.0,600,
        "2010/04/25", "2009/04/25", 500, "47-52-XL", "Lorry").get();
        CheckupRepository.add(vehicleThree, 50, "2020/03/15");
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicleOne);
        vehicles.add(vehicleTwo);
        vehicles.add(vehicleThree);
        Optional<ArrayList<Vehicle>> vehiclesRequiringCheckup = CheckupRepository.getVehiclesRequiringCheckUp();
        if(vehiclesRequiringCheckup.isEmpty()){
            return false;
        }
        for(Vehicle vehicle : vehicles){
            if(!vehiclesRequiringCheckup.get().contains(vehicle)){
                return false;
            }
        }
        return true;
    }

    @Test
    void ensureGetLatestCheckupOfVehicleReturnsEmptyWithNoCheckups(){
        CheckupRepository CheckupRepository = new CheckupRepository();
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,750,
        "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        assertTrue(CheckupRepository.getLatestCheckUpOfVehicle(vehicle).isEmpty());
    }

    @Test
    void ensureGetLatestCheckupOfVehicleReturnsCorrectCheckup() {
        //Arrange
        CheckupRepository CheckupRepository = new CheckupRepository();
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,750,
        "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        //Add the first task
        Optional<CheckUp> olderCheckUp = CheckupRepository.add(vehicle, 900, "2020/03/25");
        Optional<CheckUp> oldCheckUp = CheckupRepository.add(vehicle, 1000, "2021/03/22");
        Optional<CheckUp> checkUp = CheckupRepository.add(vehicle, 2000, "2022/04/25");

        //Assert
        assertTrue(checkUp.get().equals(CheckupRepository.getLatestCheckUpOfVehicle(vehicle).get()));
    }

    @Test
    void ensureAddingInvalidCheckupFails() {
        //Arrange
        CheckupRepository CheckupRepository = new CheckupRepository();
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,750,
        "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        //Add the first task
        CheckupRepository.add(vehicle, 1000, "2020/04/25");

        //Act
        Optional<CheckUp> duplicateCheckup = CheckupRepository.add(vehicle, 2000, "2020/04/25");
        assertTrue(duplicateCheckup.isEmpty());
        duplicateCheckup = CheckupRepository.add(vehicle, 2000, "2020/03/25");
        assertTrue(duplicateCheckup.isEmpty());
        duplicateCheckup = CheckupRepository.add(vehicle, 1000, "2020/05/25");
        assertTrue(duplicateCheckup.isEmpty());
        duplicateCheckup = CheckupRepository.add(vehicle, 500, "2020/05/25");
        assertTrue(duplicateCheckup.isEmpty());
    }

    @Test
    void ensureAddingValidCheckupWorks() {
        //Arrange
        CheckupRepository CheckupRepository = new CheckupRepository();
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,750,
        "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        //Add the first task
        CheckupRepository.add(vehicle, 1000, "2020/04/25");

        //Act
        Optional<CheckUp> result = CheckupRepository.add(vehicle, 2000, "2020/05/25");

        //Assert
        assertTrue(result.isPresent());
    }
}