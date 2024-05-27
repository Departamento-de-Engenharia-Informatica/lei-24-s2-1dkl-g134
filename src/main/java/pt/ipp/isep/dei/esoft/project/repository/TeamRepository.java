package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class TeamRepository implements Serializable {
    private ArrayList<Team> teams;

    /**
     * Constructor for a team repository.
     * All this does is initialize the list of teams.
     */
    public TeamRepository() {
        this.teams = new ArrayList<>();
    }

    /**
     * Adds a team to this repository
     * If the team is equal to any team on this repository, no team is added.
     * Check the documentation of Team.equals() for the criteria of such.
     * @param collaboratorList The list of Collaborators that form this team.
     * @param skillList The list of skills that define this team's composition.
     * @return If it was added, the added team. Otherwise, an empty Optional object.
     */
    public Optional<Team> add(ArrayList<Collaborator> collaboratorList, ArrayList<Skill> skillList) {
        Team newTeam = new Team(collaboratorList, skillList);
        Repositories.getInstance().getCollaboratorRepository().resetLists();
        if(teams.contains(newTeam)) {
            return Optional.empty();
        }
        teams.add(newTeam);
        return Optional.of(newTeam);
    }

    /**
     * Gets the full list of all collaborators in any team registered on this repository.
     * @return The list of all collaborators in any team.
     */
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

    /**
     * Gets the full list of all teams.
     * @return A list of every team in the system. If no teams are found, an empty Optional object.
     */
    public Optional<ArrayList<Team>> getTeamList(){
        if(teams.isEmpty()){
             return Optional.empty();
        }
        return Optional.of(teams);
    }
}
