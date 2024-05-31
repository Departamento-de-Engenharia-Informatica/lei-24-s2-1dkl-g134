package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Vehicle;

import java.util.ArrayList;
import java.util.Optional;

public class VehicleMapper {

    /**
     * Converts the object associated with this mapper to its DTO.
     * @param vehicle The vehicle to convert.
     * @return A DTO representing the vehicle.
     */
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

    /**
     * Converts the DTO associated with this mapper to its object.
     * @param vehicleDTO The DTO to convert.
     * @return The object associated with this DTO.
     */
    public Vehicle DTOToObject(VehicleDTO vehicleDTO) {
        return vehicleDTO.attachedVehicle;
    }

    /**
     * Converts a list of objects associated with this mapper to a list of DTOs.
     * @param vehicles The list of vehicles to convert.
     * @return The list of DTOs representing the vehicles.
     */
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

    /**
     * Gets an instance of this Mapper.
     * @return An instance of VehicleMapper.
     */
    public static VehicleMapper getMapper(){
        return new VehicleMapper();
    }
}
