package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

import java.util.ArrayList;
import java.util.Optional;

public class GreenSpaceMapper {
    public GreenSpaceDTO objectToDTO(GreenSpace greenSpace) {
        GreenSpaceDTO greenSpaceDTO = new GreenSpaceDTO();
        greenSpaceDTO.attachedGreenSpace = greenSpace;
        greenSpaceDTO.name = greenSpace.getName();
        greenSpaceDTO.address = greenSpace.getAddress();
        greenSpaceDTO.area = greenSpace.getArea();
        greenSpaceDTO.type = greenSpace.getType();
        return greenSpaceDTO;
    }

    public GreenSpace DTOToObject(GreenSpaceDTO greenSpaceDTO) {
        return greenSpaceDTO.attachedGreenSpace;
    }

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

    public static GreenSpaceMapper getMapper(){
        return new GreenSpaceMapper();
    }
}
