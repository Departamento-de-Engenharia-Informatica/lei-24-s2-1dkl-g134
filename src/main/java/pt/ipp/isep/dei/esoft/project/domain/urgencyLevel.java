package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public enum urgencyLevel {
    HIGH,
    MEDIUM,
    LOW;

    public static ArrayList<urgencyLevel> getAllUrgencyLevels(){
        ArrayList<urgencyLevel> allUrgencyLevels = new ArrayList<>();
        allUrgencyLevels.add(urgencyLevel.HIGH);
        allUrgencyLevels.add(urgencyLevel.MEDIUM);
        allUrgencyLevels.add(urgencyLevel.LOW);
        return allUrgencyLevels;
    }

}
