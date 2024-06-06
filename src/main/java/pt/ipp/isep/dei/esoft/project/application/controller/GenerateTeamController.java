package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Optional;

public class GenerateTeamController {
    private CollaboratorRepository collaboratorRepository;
    private TeamRepository teamRepository;
    private SkillRepository skillRepository;

    /**
     * Constructor for a new GenerateTeamController object.
     * All this does is get the necessary repositories.
     */
    public GenerateTeamController(){
        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        teamRepository = Repositories.getInstance().getTeamRepository();
        skillRepository = Repositories.getInstance().getSkillRepository();
    }

    /**
     * Gets the full list of skills.
     * @return The list of all skills.
     */
    public Optional<ArrayList<Skill>> getSkillList(){
        return skillRepository.getSkillList();
    }

    /**
     * Checks if there are any registered collaborators.
     * @return A boolean value indicating if any collaborators exist.
     */
    public boolean doCollaboratorsExist(){
        return collaboratorRepository.getCollaboratorList().isPresent();
    }

    /**
     * Generates a team proposal. If no proposal is found, returns an empty Optional object.
     * Refer to comments in this method's (and methods called by it) source code for further
     * information.
     * @param minTeamSize The team's minimum size.
     * @param maxTeamSize The team's maximum size.
     * @param requiredSkills The required skills for that team's composition.
     * @return A list of collaborators forming a valid team proposal, if any could be found.
     * If not, an empty Optional object.
     */
    public Optional<ArrayList<Collaborator>> generateTeamProposal(int minTeamSize, int maxTeamSize, ArrayList<Skill> requiredSkills){
        return collaboratorRepository.generateTeamProposal(minTeamSize, maxTeamSize, requiredSkills);
    }

    /**
     * Blacklists a team proposal.
     * This prevents that team from being suggested again until the lists are reset.
     * @param teamProposal The list of collaborators that form the team to be blacklisted.
     */
    public void blacklistTeamProposal(ArrayList<Collaborator> teamProposal){
        collaboratorRepository.blacklistTeamProposal(teamProposal);
    }

    /**
     * Registers a new team.
     * If the team is equal to any team on this repository, no team is added.
     * Check the documentation of Team.equals() for the criteria of such.
     * @param teamMembers The list of Collaborators that form this team.
     * @param teamSkills The list of skills that define this team's composition.
     * @return If it was added, the added team. Otherwise, an empty Optional object.
     */
    public Optional<Team> registerTeam(ArrayList<Collaborator> teamMembers, ArrayList<Skill> teamSkills){
        return teamRepository.add(teamMembers, teamSkills);
    }

    /**
     * Resets the temporary lists in the Collaborator Repository.
     */
    public void resetLists(){
        collaboratorRepository.resetLists();
    }
}
