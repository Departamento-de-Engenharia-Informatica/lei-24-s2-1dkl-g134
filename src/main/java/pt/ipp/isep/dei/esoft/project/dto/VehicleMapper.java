package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Optional;

public class VehicleMapper {
    public VehicleDTO objectToDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.attachedVehicle = vehicle;
        vehicleDTO.brand = vehicle.getBrand();
        vehicleDTO.model = vehicle.getModel();
        vehicleDTO.currentKM = vehicle.getCurrentKM();
        vehicleDTO.checkUpFrequency = vehicle.getCheckUpFrequency();
        vehicleDTO.plateNumber = vehicle.getPlateNumber();
        return vehicleDTO;
    }

    public Vehicle DTOToObject(VehicleDTO vehicleDTO) {
        return vehicleDTO.attachedVehicle;
    }

    public Optional<ArrayList<VehicleDTO>> objectListToDTOList(Optional<ArrayList<Vehicle>> vehicles) {
        if(vehicles.isEmpty()) {
            return Optional.empty();
        }
        ArrayList<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for (Vehicle vehicle : vehicles.get()) {
            vehicleDTOList.add(objectToDTO(vehicle));
        }
        return Optional.of(vehicleDTOList);
    }

    public static VehicleMapper getMapper(){
        return new VehicleMapper();
    }
}
