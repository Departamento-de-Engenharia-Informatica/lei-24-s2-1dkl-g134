package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

import java.util.ArrayList;

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

    public GreenSpaceDTO inputToDTO(String name, String address, int area, GreenSpaceType type) {
        GreenSpaceDTO greenSpaceDTO = new GreenSpaceDTO();
        greenSpaceDTO.name = name;
        greenSpaceDTO.address = address;
        greenSpaceDTO.area = area;
        greenSpaceDTO.type = type;
        return greenSpaceDTO;
    }

    public ArrayList<GreenSpaceDTO> objectListToDTOList(ArrayList<GreenSpace> greenSpaceList) {
        ArrayList<GreenSpaceDTO> greenSpaceDTOList = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList) {
            greenSpaceDTOList.add(objectToDTO(greenSpace));
        }
        return greenSpaceDTOList;
    }

    public ArrayList<GreenSpace> DTOListToObjectList(ArrayList<GreenSpaceDTO> greenSpaceDTOArrayList){
        ArrayList<GreenSpace> greenSpaces = new ArrayList<>();
        for(GreenSpaceDTO greenSpaceDTO : greenSpaceDTOArrayList){
            greenSpaces.add(DTOToObject(greenSpaceDTO));
        }
        return greenSpaces;
    }

    public static GreenSpaceMapper getMapper(){
        return new GreenSpaceMapper();
    }
}
