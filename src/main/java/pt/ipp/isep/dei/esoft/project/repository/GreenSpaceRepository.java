package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;


import java.util.ArrayList;
import java.util.Optional;

public class GreenSpaceRepository {
    private ArrayList<GreenSpace> greenSpaces;

    public GreenSpaceRepository(){this.greenSpaces=new ArrayList<>();}

    public Optional<GreenSpace> add(String name, String address, int area, GreenSpaceType type){

        GreenSpace greenSpace=new GreenSpace(name,address,area,type);

        if(greenSpaces.contains(greenSpace)){
            return Optional.empty();
        }

        greenSpaces.add(greenSpace);
        return Optional.of(greenSpace);
    }

}

