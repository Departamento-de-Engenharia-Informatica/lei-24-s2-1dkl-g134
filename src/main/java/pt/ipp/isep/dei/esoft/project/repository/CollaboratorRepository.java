package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Optional;

public class CollaboratorRepository {
    private ArrayList<Collaborator> collaborators;
    private ArrayList<ArrayList<Collaborator>> blacklistedTeamProposals;
    private ArrayList<Collaborator> collaboratorBlacklist;
    private ArrayList<Collaborator> collaboratorWhitelist;

    public CollaboratorRepository() {
        this.collaborators = new ArrayList<>();
        this.blacklistedTeamProposals = new ArrayList<>();
        this.collaboratorBlacklist = new ArrayList<>();
        this.collaboratorWhitelist = new ArrayList<>();
    }

    public Optional<Collaborator> add(String name, String birthDate,String admissionDate,String address,
                                      String phoneNumber, String email, String identificationDocumentType,
                                      String identificationNumber, String taxpayerNumber, Job job){
        Collaborator pessoa = new Collaborator(name, birthDate, admissionDate, address,
                phoneNumber, email, identificationDocumentType,
                identificationNumber, taxpayerNumber, job);
        if(!collaborators.contains(pessoa)){
            collaborators.add(pessoa);
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
        if(collaboratorWhitelist.isEmpty() && collaboratorBlacklist.isEmpty()){
            ArrayList<Skill> requiredSkillsNoDuplicates = new ArrayList<>();
            for(Skill skill : requiredSkills){
                if(!requiredSkillsNoDuplicates.contains(skill)){
                    requiredSkillsNoDuplicates.add(skill);
                }
            }
            collaboratorBlacklist = Repositories.getInstance().getTeamRepository().getAllCollaboratorsInTeams();
            collaboratorWhitelist = new ArrayList<>();
            for(Skill skill : requiredSkillsNoDuplicates){
                Optional<ArrayList<Collaborator>> collaboratorsWithSkill = getAllCollaboratorsWithSkill(skill);
                if(collaboratorsWithSkill.isEmpty()){ return Optional.empty(); }
                for(Collaborator collaborator : collaboratorsWithSkill.get()){
                    if(!collaboratorWhitelist.contains(collaborator) && !collaboratorBlacklist.contains(collaborator)){
                        collaboratorWhitelist.add(collaborator);
                    }
                }
            }
            if(collaboratorWhitelist.isEmpty()){ return Optional.empty(); }
        }
        return generationAlgorithm(minTeamSize, maxTeamSize, requiredSkills);
    }

    private Optional<ArrayList<Collaborator>> generationAlgorithm(int minTeamSize, int maxTeamSize, ArrayList<Skill> requiredSkills){
        ArrayList<TeamMember> teamProposal = new ArrayList<>();
        ArrayList<Skill> requiredSkillsCopy = new ArrayList<>();
        requiredSkillsCopy.addAll(requiredSkills);
        boolean impossible = false;
        while(true){

            //HOW THIS WORKS, IN A NUTSHELL

            //The idea is to essentially use 1 for loop for the whole cycle.

            //We iterate through the whitelist, and if a collaborator is fit for adding, we add that
            //collaborator and register every skill that that collaborator contributions to the team,
            //removing every skill provided by the collaborator from the list of required skills.

            //After each addition, we check if the resulting team is fit for a proposal. If it is,
            //we break out of the for, check once again, and break out of this while.
            //If the team is NOT fit for a proposal, we check if there is a combination of contributions
            //by the last element of the list that we haven't attempted yet. If there is, we attempt that
            //combination, adjust the requirements list to the new contributions, and continue iterating.
            //If there isn't, we remove that collaborator, reinstate the contributions the collaborator
            //was currently making to the list of requirements, blacklist it, and continue iterating from
            //there.

            //The for cycle explicitly goes up to 1 iteration above the size of the whitelist. This
            //iteration is special: If we have exhausted the whitelist, we will verify the makeup
            //of the current team. If it's fit for proposal, we break out. If it isn't, we first check
            //if there is a combination of contributions that collaborator can make that we haven't
            //attempted. If there is one, we switch to that combination, adjust the requirements list
            //to the new contributions, reset the for cycle's index to directly in front of that
            //collaborator, and continue iterating from there. If there *isn't*, we simply remove that
            //collaborator, reinstate the contributions to the requirements the element was currently
            //making to the requirements, blacklist the proposal, reset the for cycle's index to directly
            //in front of that collaborator, and continue iterating from there.

            //This is very analogous to N-queens with extra steps, however, unlike N-queens, the order
            //of the collaborators within the list is irrelevant for this case, which is why this system
            //of backtracking and index-resetting presumably works. There's no reason for us to, upon
            //removing an element from the team, check every whitelist member that came before that
            //element, because every previous collaborator had already been included or excluded based
            //on the team makeup *up to that point*.

            //Team generation is considered impossible once there are no combinations left that fulfill
            //the minimum team size established by the user.

            //CRITERIA FOR A TEAM BEING FIT FOR PROPOSAL:
            //1- The list of required skills is exhausted.
            //2- The team's size is between the minimum and maximum set by the user.

            //CRITERIA FOR A TEAM BEING A DEAD END:
            //If the list of required skills is exhausted, then
            //1- Either the team is blacklisted...
            //2- ... OR the team's size is below the minimum set by the user.
            //3- AND, in addition to either one of the previous ones, the latest collaborator is only
            //contributing **ONE** skill to the team.
            //IF the list of required skills is NOT exhausted, then
            //1- The team's size is equal to the maximum set by the user.

            for(int i = 0; i <= collaboratorWhitelist.size(); i++){
                if(i == collaboratorWhitelist.size()){
                    if(requiredSkillsCopy.isEmpty() && teamProposal.size() >= minTeamSize && teamProposal.size() <= maxTeamSize){
                        break;
                    }else{
                        i = collaboratorWhitelist.indexOf(teamProposal.get(teamProposal.size()-1).getCollaborator());
                        if(teamProposal.get(teamProposal.size()-1).attemptNextCombination()) {
                            requiredSkillsCopy.addAll(teamProposal.get(teamProposal.size() - 1).getSkillsRemoved());
                            ArrayList<Skill> currentMemberContributions = teamProposal.get(teamProposal.size() - 1).getCurrentlyUsedSkills();
                            for(Skill skill : currentMemberContributions){
                                requiredSkillsCopy.remove(skill);
                            }
                        }else{
                            ArrayList<Collaborator> teamProposalCopy = new ArrayList<>();
                            for(TeamMember teamMember : teamProposal){
                                teamProposalCopy.add(teamMember.getCollaborator());
                            }
                            blacklistedTeamProposals.add(teamProposalCopy);
                            requiredSkillsCopy.addAll(teamProposal.get(teamProposal.size()-1).getCurrentlyUsedSkills());
                            teamProposal.remove(teamProposal.size()-1);
                            if(collaboratorWhitelist.size() - i < minTeamSize && teamProposal.isEmpty()){
                                impossible = true;
                                break;
                            }
                        }
                        continue;
                    }
                }
                ArrayList<Collaborator> teamMembers = new ArrayList<>();
                for(TeamMember teamMember : teamProposal){
                    teamMembers.add(teamMember.getCollaborator());
                }
                if(teamProposal.size() < maxTeamSize && !teamMembers.contains(collaboratorWhitelist.get(i))){
                    ArrayList<Skill> removedSkills = new ArrayList<>();
                    for(int j = 0; j < requiredSkillsCopy.size(); j++){
                        if(collaboratorWhitelist.get(i).hasSkill(requiredSkillsCopy.get(j)) && !removedSkills.contains(requiredSkillsCopy.get(j))){
                            removedSkills.add(requiredSkillsCopy.get(j));
                            requiredSkillsCopy.remove(requiredSkillsCopy.get(j));
                            j--;
                        }
                    }
                    if(!removedSkills.isEmpty()){
                        teamProposal.add(new TeamMember(collaboratorWhitelist.get(i), removedSkills));
                        teamMembers.add(collaboratorWhitelist.get(i));
                    }
                }
                boolean isBlacklisted = isProposalBlacklisted(teamMembers);
                if(!isBlacklisted && requiredSkillsCopy.isEmpty() && teamProposal.size() >= minTeamSize){
                    break;
                }
                if(!requiredSkillsCopy.isEmpty() && teamProposal.size() < maxTeamSize){
                    continue;
                }
                if(teamProposal.get(teamProposal.size()-1).attemptNextCombination()) {
                    requiredSkillsCopy.addAll(teamProposal.get(teamProposal.size() - 1).getSkillsRemoved());
                    ArrayList<Skill> currentMemberContributions = teamProposal.get(teamProposal.size() - 1).getCurrentlyUsedSkills();
                    for(Skill skill : currentMemberContributions){
                        requiredSkillsCopy.remove(skill);
                    }
                }else{
                    if(!isBlacklisted){
                        ArrayList<Collaborator> teamProposalCopy = new ArrayList<>();
                        for(TeamMember teamMember : teamProposal){
                            teamProposalCopy.add(teamMember.getCollaborator());
                        }
                        blacklistedTeamProposals.add(teamProposalCopy);
                    }
                    requiredSkillsCopy.addAll(teamProposal.get(teamProposal.size()-1).getCurrentlyUsedSkills());
                    teamProposal.remove(teamProposal.size()-1);
                }
            }
            ArrayList<Collaborator> teamMembers = new ArrayList<>();
            for(TeamMember teamMember : teamProposal){
                teamMembers.add(teamMember.getCollaborator());
            }
            if((teamProposal.size() >= minTeamSize && teamProposal.size() <= maxTeamSize && !isProposalBlacklisted(teamMembers) && requiredSkillsCopy.isEmpty()) || impossible){
                break;
            }
        }
        if(impossible){
            return Optional.empty();
        }
        ArrayList<Collaborator> teamMembers = new ArrayList<>();
        for(TeamMember teamMember : teamProposal){
            teamMembers.add(teamMember.getCollaborator());
        }
        return Optional.of(teamMembers);
    }

    private boolean isProposalBlacklisted(ArrayList<Collaborator> proposal){
        for(ArrayList<Collaborator> blacklistMember : blacklistedTeamProposals){
            boolean discrepancyFound = false;
            if(proposal.size() != blacklistMember.size()){
                discrepancyFound = true;
            }else{
                for(Collaborator collaborator : blacklistMember){
                    if(!proposal.contains(collaborator)){
                        discrepancyFound = true;
                        break;
                    }
                }
            }
            if(!discrepancyFound){return true;}
        }
        return false;
    }

    private Optional<ArrayList<Collaborator>> getAllCollaboratorsWithSkill(Skill skill){
        ArrayList<Collaborator> collaboratorsWithSkill = new ArrayList<>();
        for(Collaborator collaborator : collaborators){
            if(collaborator.hasSkill(skill)){
                collaboratorsWithSkill.add(collaborator);
            }
        }
        if(collaboratorsWithSkill.isEmpty()){ return Optional.empty(); }
        else { return Optional.of(collaboratorsWithSkill); }
    }

    public void blacklistTeamProposal(ArrayList<Collaborator> blacklistedTeam){
        blacklistedTeamProposals.add(blacklistedTeam);
    }

    public void resetLists(){
        blacklistedTeamProposals = new ArrayList<>();
        collaboratorWhitelist = new ArrayList<>();
        collaboratorBlacklist = new ArrayList<>();
    }
}
