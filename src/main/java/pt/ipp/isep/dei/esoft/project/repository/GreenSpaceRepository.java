package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceMapper;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class GreenSpaceRepository implements Serializable {
    private ArrayList<GreenSpace> greenSpaces;

    /**
     * Constructor for a new Green Space Repository.
     * All this does is initialize the list of green spaces.
     */
    public GreenSpaceRepository(){this.greenSpaces=new ArrayList<>();}

    /**
     * Adds a new green space to this repository.
     * If the green space is equal to any green space currently in the repository, no green space is added.
     * Check the documentation of GreenSpace.equals() for the criteria of such.
     * @param name A String representing this green space's name.
     * @param address A String representing this green space's address.
     * @param area An int representing this green space's area in hectares.
     * @param type This green space's type, expressed with a GreenSpaceType enumerator.
     * @return An Optional object containing the added green space. If none was added,
     * an empty Optional object.
     */
    public Optional<GreenSpace> add(String name, String address, int area, GreenSpaceType type){

        GreenSpace greenSpace=new GreenSpace(name, address, area, type);

        if(greenSpaces.contains(greenSpace)){
            return Optional.empty();
        }

        greenSpaces.add(greenSpace);
        return Optional.of(greenSpace);
    }

    /**
     * Gets the list of all green spaces that are managed by the current logged-in user.
     * This method uses the name and email of the current logged-in user account as identifiers.
     * @return An Optional object containing the list of all green spaces managed by the
     * current logged-in user. If none are found, an empty Optional object instead.
     */
    public Optional<ArrayList<GreenSpace>> getGreenSpacesManagedByUser(){
        ArrayList<GreenSpace> greenSpacesManagedByUser = new ArrayList<>();
         for (GreenSpace greenSpace : greenSpaces){
             if(greenSpace.isCreatedBy(ApplicationSession.getInstance().getCurrentSession().getUserName(), ApplicationSession.getInstance().getCurrentSession().getUserEmail())){
                 greenSpacesManagedByUser.add(greenSpace);
             }
         }
         if(greenSpacesManagedByUser.isEmpty()){
             return Optional.empty();
         }
         return Optional.of(greenSpacesManagedByUser);
    }
}

