package pt.ipp.isep.dei.esoft.project.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class GetGreenSpacesManagedByUserController {
    private GreenSpaceRepository greenSpaceRepository;

    public GetGreenSpacesManagedByUserController() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }

    public Optional<ArrayList<GreenSpace>> getGreenSpacesManagedByCurrentUser() {
        return greenSpaceRepository.getGreenSpacesManagedByUser();
    }


}

