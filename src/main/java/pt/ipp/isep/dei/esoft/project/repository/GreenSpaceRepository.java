package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
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
    public Optional<ArrayList<GreenSpace>> getGreenSpacesManagedByUser(){
        ArrayList<GreenSpace> greenSpacesManagedByUser = new ArrayList<>();
         for (GreenSpace greenSpace : greenSpaces){
             if (greenSpace.getCreator().equals(ApplicationSession.getInstance().getCurrentSession())){
                 greenSpacesManagedByUser.add(greenSpace);
             }
         }
         if(greenSpacesManagedByUser.isEmpty()){
             return Optional.empty();
         }
         return Optional.of(greenSpacesManagedByUser);
    }
}

