package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.ArrayList;
import java.util.Optional;

public class RegisterGreenSpaceController {
    private GreenSpaceRepository GreenSpaceRepository;


    public RegisterGreenSpaceController(){
        GreenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }



    public Optional<GreenSpace> registerGreenSpace(String name, String address, int area, GreenSpaceType type){
        return GreenSpaceRepository.add( name,address,area , type);

    }
}
