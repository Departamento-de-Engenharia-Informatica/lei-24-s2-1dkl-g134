package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;

public class TeamDTO {
    public ArrayList<Collaborator> teamMembers;
    public Team attachedTeam;

    @Override
    public boolean equals(Object o) {
        return attachedTeam.equals(o);
    }
}
