package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.Optional;

public class CollaboratorRepository {
    private ArrayList<Collaborator> collaborators;
    private ArrayList<ArrayList<Collaborator>> blacklistedTeamProposals;

    public Optional<ArrayList<Skill>> assignSkillsToCollaborator(Collaborator collaborator, ArrayList<Skill> skills) {
        return collaborators.get(collaborators.indexOf(collaborator)).assignSkills(skills);
    }

    public Optional<ArrayList<Collaborator>> getCollaboratorList() {
        if(collaborators.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(collaborators);
    }

    public Optional<ArrayList<Collaborator>> generateTeamProposal(int minTeamSize, int maxTeamSize, ArrayList<Skill> requiredSkills){
        ArrayList<Collaborator> teamProposal = new ArrayList<>();
        boolean impossible = false;
        while(true){

            break;
        }
        if(impossible){
            return Optional.empty();
        }
        return Optional.of(teamProposal);
    }

    public void blacklistTeamProposal(ArrayList<Collaborator> blacklistedTeam){
        blacklistedTeamProposals.add(blacklistedTeam);
    }
}
