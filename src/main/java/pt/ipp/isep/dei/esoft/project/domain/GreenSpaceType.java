package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;

public enum GreenSpaceType implements Serializable {
    GARDEN,
    MEDIUM_SIZE,
    LARGE_SIZE;

    public static ArrayList<GreenSpaceType> getAllTypes(){
        ArrayList<GreenSpaceType> allTypes = new ArrayList<>();
        allTypes.add(GreenSpaceType.GARDEN);
        allTypes.add(GreenSpaceType.MEDIUM_SIZE);
        allTypes.add(GreenSpaceType.LARGE_SIZE);
        return allTypes;
    }
}
