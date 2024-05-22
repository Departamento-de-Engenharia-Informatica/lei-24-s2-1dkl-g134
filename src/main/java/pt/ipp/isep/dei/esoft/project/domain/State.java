package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public enum State{
    PENDING,
    PLANNED,
    POSTPONED,
    COMPLETED,
    CANCELED;

    public static ArrayList<State> getAllStates(){
        ArrayList<State> allStates = new ArrayList<>();
        allStates.add(State.PLANNED);
        allStates.add(State.POSTPONED);
        allStates.add(State.COMPLETED);
        allStates.add(State.CANCELED);
        return allStates;
    }
}
