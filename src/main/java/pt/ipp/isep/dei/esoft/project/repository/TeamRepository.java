package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.Optional;

public class TeamRepository {
    private ArrayList<Team> teams;

    public Optional<Team> add(ArrayList<Collaborator> collaboratorList, ArrayList<Skill> skillList) {
        Team newTeam = new Team(collaboratorList, skillList);

        return Optional.of(newTeam);
    }
}
