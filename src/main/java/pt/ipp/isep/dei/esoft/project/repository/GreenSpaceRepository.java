package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;


import java.util.ArrayList;
import java.util.Optional;

public class GreenSpaceRepository {
    private ArrayList<GreenSpace> GreenSpaces;
    public GreenSpaceRepository(){
        this.GreenSpaces=new ArrayList<>();}
public Optional<ArrayList<GreenSpace>> add(String name, String household, int area, String type){
        GreenSpace greenSpace=new GreenSpace(name,household,area,type);

    return Optional.empty();
}
    }

