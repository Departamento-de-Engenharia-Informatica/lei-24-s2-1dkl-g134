package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;

public enum urgencyLevel implements Serializable {
    HIGH,
    MEDIUM,
    LOW;

    /**
     * Gets a list of every existing level of urgency.
     * @return An ArrayList of urgencyLevel enumerators, each representing a possible value
     * for urgency levels.
     */
    public static ArrayList<urgencyLevel> getAllUrgencyLevels(){
        ArrayList<urgencyLevel> allUrgencyLevels = new ArrayList<>();
        allUrgencyLevels.add(urgencyLevel.HIGH);
        allUrgencyLevels.add(urgencyLevel.MEDIUM);
        allUrgencyLevels.add(urgencyLevel.LOW);
        return allUrgencyLevels;
    }

}
