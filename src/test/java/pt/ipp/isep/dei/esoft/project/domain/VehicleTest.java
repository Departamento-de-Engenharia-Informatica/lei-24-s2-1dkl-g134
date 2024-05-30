package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void ensureVehicleIsCreatedSuccessfully() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
        "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");
    }

    @Test
    void ensureVehicleParamsAreNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vehicle(null, null, 5000, 2500.0,3000,
                null, null, 500, null, null));
    }

    @Test
    void ensureVehicleParamsAboveZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vehicle("Ford", "T", -500, 0,-10,
                        "2010/04/25", "2009/04/25", -20, "45-50-LL", "Car"));
    }

    @Test
    void ensureVehiclePlateNumberFollowsFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vehicle("Ford", "T", 500, 20,10,
                        "2010/04/25", "2009/04/25", 20, "H3LLO", "Car"));
    }

    @Test
    void ensureVehicleAcquiredBeforeRegistered() {
        assertThrows(IllegalArgumentException.class,
                () -> new Vehicle("Ford", "T", 5000, 2500.0,3000,
                        "2010/04/25", "2015/04/25", 500, "45-50-DL", "Car"));
    }

    @Test
    void testEqualsSameObject() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertEquals(vehicle, vehicle);
    }

    @Test
    void testEqualsDifferentClass() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertNotEquals(vehicle, new Object());
    }

    @Test
    void testEqualsNull() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertNotEquals(vehicle, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");
        Vehicle vehicle1 = new Vehicle("The US", "F1", 5000, 2500.0,2000,
                "2010/04/25", "2009/04/25", 5000, "60-40-XL", "Jet");

        assertNotEquals(vehicle, vehicle1);
    }

    @Test
    void testEqualsSameObjectSamePlateNumber() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");
        Vehicle vehicle1 = new Vehicle("The US", "F1", 5000, 2500.0,2000,
                "2010/04/25", "2009/04/25", 5000, "45-50-DL", "Jet");

        assertEquals(vehicle, vehicle1);
    }

    @Test
    void testHashCodeSameObject() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertEquals(vehicle.hashCode(), vehicle.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");
        Vehicle vehicle1 = new Vehicle("The US", "F1", 5000, 2500.0,2000,
                "2010/04/25", "2009/04/25", 5000, "60-40-XL", "Jet");

        assertNotEquals(vehicle.hashCode(), vehicle1.hashCode());
    }

    @Test
    void testGetBrand(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertEquals("Ford", vehicle.getBrand());
    }

    @Test
    void testToString(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertEquals("45-50-DL", vehicle.toString());
    }

    @Test
    void testGetModel(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertEquals("T", vehicle.getModel());
    }

    @Test
    void testGetPlateNumber(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertEquals("45-50-DL", vehicle.getPlateNumber());
    }

    @Test
    void testGetCheckupFrequency(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertEquals(500, vehicle.getCheckUpFrequency());
    }

    @Test
    void testGetCurrentKms(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        assertEquals(3000, vehicle.getCurrentKM());
    }

    @Test
    void testSetCurrentKms(){
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");

        vehicle.setCurrentKM(5000);

        assertEquals(5000, vehicle.getCurrentKM());
    }


}