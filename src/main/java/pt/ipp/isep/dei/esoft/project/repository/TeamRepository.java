package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.Optional;

public class TeamRepository {
    private ArrayList<Team> teams;

    public TeamRepository() {
        this.teams = new ArrayList<>();
    }

    public Optional<Team> add(ArrayList<Collaborator> collaboratorList, ArrayList<Skill> skillList) {
        Team newTeam = new Team(collaboratorList, skillList);
        Repositories.getInstance().getCollaboratorRepository().resetLists();
        if(teams.contains(newTeam)) {
            return Optional.empty();
        }
        teams.add(newTeam);
        return Optional.of(newTeam);
    }

    public ArrayList<Collaborator> getAllCollaboratorsInTeams(){
        ArrayList<Collaborator> collaboratorsInTeams = new ArrayList<>();
        for(Team team : teams) {
            for(Collaborator collaborator : team.getTeamMembers()){
                if(!collaboratorsInTeams.contains(collaborator)) {
                    collaboratorsInTeams.add(collaborator);
                }
            }
        }
        return collaboratorsInTeams;
    }
}
