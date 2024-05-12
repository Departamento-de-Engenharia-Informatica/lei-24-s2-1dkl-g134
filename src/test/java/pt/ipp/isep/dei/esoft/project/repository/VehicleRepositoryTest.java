package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehicleRepositoryTest {

    @Test
    void ensureNewVehicleSuccessfullyAdded() {
        VehicleRepository VehicleRepository = new VehicleRepository();
        assertTrue(VehicleRepository.add("Ford", "T", 5000, 2500.0,750,
                "2010/04/25", "2009/04/25", 500, "H3LL0", "Car").isPresent());
    }

    @Test
    void ensureGetVehicleListReturnsEmptyWithNoVehicles() {
        VehicleRepository VehicleRepository = new VehicleRepository();
        assertTrue(VehicleRepository.getVehicleList().isEmpty());
    }

    @Test
    void ensureGetVehicleListReturnsTheCorrectList() {
        //Arrange
        VehicleRepository VehicleRepository = new VehicleRepository();
        Vehicle Vehicle = new Vehicle("Ford", "T", 5000, 2500.0,750,
                "2010/04/25", "2009/04/25", 500, "H3LL0", "Car");
        VehicleRepository.add("Ford", "T", 5000, 2500.0,750,
                "2010/04/25", "2009/04/25", 500, "H3LL0", "Car");
        int expectedSize = 1;

        //Act
        int size = VehicleRepository.getVehicleList().get().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(Vehicle, VehicleRepository.getVehicleList().get().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateVehicleFails() {
        //Arrange
        VehicleRepository VehicleRepository = new VehicleRepository();
        //Add the first task
        VehicleRepository.add("Ford", "T", 5000, 2500.0,750,
                "2010/04/25", "2009/04/25", 500, "H3LL0", "Car");

        //Act
        Optional<Vehicle> duplicateVehicle = VehicleRepository.add("Ford", "T", 5000, 2500.0,750,
                "2010/04/25", "2009/04/25", 500, "H3LL0", "Car");

        //Assert
        assertTrue(duplicateVehicle.isEmpty());
    }

    @Test
    void ensureAddingDifferentVehiclesWorks() {
        //Arrange
        VehicleRepository VehicleRepository = new VehicleRepository();
        //Add the first task
        VehicleRepository.add("Ford", "T", 5000, 2500.0,750,
                "2010/04/25", "2009/04/25", 500, "H3LL0", "Car");

        //Act
        Optional<Vehicle> result = VehicleRepository.add("Ford", "X", 5000, 2500.0,1000,
                "2010/04/25", "2009/04/25", 750, "G00DBY3", "Truck");

        //Assert
        assertTrue(result.isPresent());
    }
}