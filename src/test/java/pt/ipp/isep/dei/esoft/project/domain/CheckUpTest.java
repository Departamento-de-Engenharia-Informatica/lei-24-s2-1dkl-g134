package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckUpTest {

    @Test
    void ensureCheckUpIsCreatedSuccessfully() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
        "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");
    }

    @Test
    void ensureCheckUpDateAndVehicleIsNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new CheckUp(null, 1000, null));
    }

    @Test
    void ensureCheckUpDateNotFuture() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        assertThrows(IllegalArgumentException.class,
                () -> new CheckUp(vehicle, 1000, "9999/12/31"));
    }

    @Test
    void ensureCheckUpKmsPositive() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        assertThrows(IllegalArgumentException.class,
                () -> new CheckUp(vehicle, -40, "2010/12/31"));
    }

    @Test
    void testEqualsSameObject() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");

        assertEquals(checkUp, checkUp);
    }

    @Test
    void testEqualsDifferentClass() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");

        assertNotEquals(checkUp, new Object());
    }

    @Test
    void testEqualsNull() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");

        assertNotEquals(checkUp, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
        "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");
        Vehicle vehicle1 = new Vehicle("Ford", "X", 5000, 2500.0,1000,
        "2010/04/25", "2009/04/25", 750, "46-51-DL", "Truck");
        CheckUp checkUp1 = new CheckUp(vehicle1, 1020, "2020/05/25");

        assertNotEquals(checkUp, checkUp1);
    }

    @Test
    void testEqualsSameObjectSameVehicleAndDate() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");
        CheckUp checkUp1 = new CheckUp(vehicle, 1020, "2020/05/24");

        assertEquals(checkUp, checkUp1);
    }

    @Test
    void testHashCodeSameObject() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");

        assertEquals(checkUp.hashCode(), checkUp.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");
        Vehicle vehicle1 = new Vehicle("Ford", "X", 5000, 2500.0,1000,
                "2010/04/25", "2009/04/25", 750, "46-51-DL", "Truck");
        CheckUp checkUp1 = new CheckUp(vehicle1, 1020, "2020/05/25");

        assertNotEquals(checkUp.hashCode(), checkUp1.hashCode());
    }

    @Test
    void testGetVehicle(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");

        assertEquals(vehicle, checkUp.getVehicle());
    }

    @Test
    void testGetCurrentKms(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");

        assertEquals(1000, checkUp.getCurrentKM());
    }

    @Test
    void testGetDate(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        CheckUp checkUp = new CheckUp(vehicle, 1000, "2020/05/24");
        CustomDate date = new CustomDate("2020/05/24");

        assertEquals(date, checkUp.getDate());
    }
}