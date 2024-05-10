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

    public GenerateTeamController(){
        getCollaboratorRepository();
        getTeamRepository();
        getSkillRepository();
    }

    private void getCollaboratorRepository(){
        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    }
    private void getTeamRepository(){
        teamRepository = Repositories.getInstance().getTeamRepository();
    }
    private void getSkillRepository(){
        skillRepository = Repositories.getInstance().getSkillRepository();
    }

    public Optional<ArrayList<Skill>> getSkillList(){
        return skillRepository.getSkillList();
    }

    public Optional<ArrayList<Collaborator>> generateTeamProposal(int minTeamSize, int maxTeamSize, ArrayList<Skill> requiredSkills){
        return collaboratorRepository.generateTeamProposal(minTeamSize, maxTeamSize, requiredSkills);
    }

    public void blacklistTeamProposal(ArrayList<Collaborator> teamProposal){
        collaboratorRepository.blacklistTeamProposal(teamProposal);
    }

    public Optional<Team> registerTeam(ArrayList<Collaborator> teamMembers, ArrayList<Skill> teamSkills){
        return teamRepository.add(teamMembers, teamSkills);
    }
}
