package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.ArrayList;
import java.util.Optional;

public class RegisterGreenSpaceController {
    private GreenSpaceRepository greenSpaceRepository;


    public RegisterGreenSpaceController(){
        greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }



    public Optional<GreenSpace> registerGreenSpace(GreenSpaceDTO greenSpaceDTO){
        return greenSpaceRepository.add(greenSpaceDTO.name, greenSpaceDTO.address, greenSpaceDTO.area, greenSpaceDTO.type);
    }
}
