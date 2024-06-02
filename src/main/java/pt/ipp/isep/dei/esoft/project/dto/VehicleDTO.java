package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.CustomDate;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

public class VehicleDTO {
    public String brand;
    public String model;
    public int tare;
    public int currentKM;
    public int checkUpFrequency;
    public String plateNumber;
    public String type;
    public Vehicle attachedVehicle;

    @Override
    public boolean equals(Object o) {
        return attachedVehicle.equals(((VehicleDTO)o).attachedVehicle);
    }
}
