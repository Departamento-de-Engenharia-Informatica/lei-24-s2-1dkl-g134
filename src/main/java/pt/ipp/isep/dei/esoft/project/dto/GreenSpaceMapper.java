package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

import java.util.ArrayList;
import java.util.Optional;

public class GreenSpaceMapper {

    /**
     * Converts the object associated with this mapper to its DTO.
     * @param greenSpace The Green Space to convert.
     * @return A DTO representing the green space.
     */
    public GreenSpaceDTO objectToDTO(GreenSpace greenSpace) {
        GreenSpaceDTO greenSpaceDTO = new GreenSpaceDTO();
        greenSpaceDTO.attachedGreenSpace = greenSpace;
        greenSpaceDTO.name = greenSpace.getName();
        greenSpaceDTO.address = greenSpace.getAddress();
        greenSpaceDTO.area = greenSpace.getArea();
        greenSpaceDTO.type = greenSpace.getType();
        return greenSpaceDTO;
    }

    /**
     * Converts the DTO associated with this mapper to its object.
     * @param greenSpaceDTO The DTO to convert.
     * @return The object associated with this DTO.
     */
    public GreenSpace DTOToObject(GreenSpaceDTO greenSpaceDTO) {
        return greenSpaceDTO.attachedGreenSpace;
    }

    /**
     * Converts a list of objects associated with this mapper to a list of DTOs.
     * @param greenSpaceList The list of green spaces to convert.
     * @return The list of DTOs representing the green spaces.
     */
    public Optional<ArrayList<GreenSpaceDTO>> objectListToDTOList(Optional<ArrayList<GreenSpace>> greenSpaceList) {
        if(greenSpaceList.isEmpty()) {
            return Optional.empty();
        }
        ArrayList<GreenSpaceDTO> greenSpaceDTOList = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList.get()) {
            greenSpaceDTOList.add(objectToDTO(greenSpace));
        }
        return Optional.of(greenSpaceDTOList);
    }

    /**
     * Gets an instance of this Mapper.
     * @return An instance of GreenSpaceMappr.
     */
    public static GreenSpaceMapper getMapper(){
        return new GreenSpaceMapper();
    }
}
