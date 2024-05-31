package pt.ipp.isep.dei.esoft.project.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceMapper;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class GetGreenSpacesManagedByUserController {
    private GreenSpaceRepository greenSpaceRepository;

    /**
     * Constructor for a new GetGreenSpacesManagedByUserController object.
     * All this does is get the necessary repositories.
     */
    public GetGreenSpacesManagedByUserController() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    /**
     * Gets the list of all green spaces that are managed by the current logged-in user.
     * This method uses the name and email of the current logged-in user account as identifiers.
     * @return An Optional object containing the list of all green spaces managed by the
     * current logged-in user. If none are found, an empty Optional object instead.
     */
    public Optional<ArrayList<GreenSpaceDTO>> getGreenSpacesManagedByCurrentUser() {
        return GreenSpaceMapper.getMapper().objectListToDTOList(greenSpaceRepository.getGreenSpacesManagedByUser());
    }
}

