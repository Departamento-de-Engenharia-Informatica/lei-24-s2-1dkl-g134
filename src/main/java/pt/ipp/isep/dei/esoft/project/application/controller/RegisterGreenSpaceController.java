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

    /**
     * Constructor for a new RegisterGreenSpaceController object.
     * All this does is get the necessary repositories.
     */
    public RegisterGreenSpaceController(){
        greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    /**
     * Registers a new green space.
     * If the green space is equal to any green space currently in the system, no green space is added.
     * Check the documentation of GreenSpace.equals() for the criteria of such.
     * @param greenSpaceDTO A DTO containing all the necessary data for a new green space.
     * @return An Optional object containing the added green space. If none was added,
     * an empty Optional object.
     */
    public Optional<GreenSpace> registerGreenSpace(GreenSpaceDTO greenSpaceDTO){
        return greenSpaceRepository.add(greenSpaceDTO.name, greenSpaceDTO.address, greenSpaceDTO.area, greenSpaceDTO.type);
    }
}
