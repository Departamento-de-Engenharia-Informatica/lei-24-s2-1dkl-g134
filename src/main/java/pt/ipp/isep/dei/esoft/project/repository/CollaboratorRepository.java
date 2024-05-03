package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.Optional;

public class CollaboratorRepository {
    private ArrayList<Collaborator> collaborators;
    private ArrayList<ArrayList<Collaborator>> blacklistedTeamProposals;

    public Optional<Collaborator> add(String name, String birthDate,String admissionDate,String address,
                                      String phoneNumber, String email, String identificationDocumentType,
                                      String identificationNumber, Job job){
        Collaborator pessoa = new Collaborator(name, birthDate, admissionDate, address,
                phoneNumber, email, identificationDocumentType,
                identificationNumber, job);
        if(!collaborators.contains(pessoa)){
            return Optional.of(pessoa);
        }
        return Optional.empty();
    }

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
