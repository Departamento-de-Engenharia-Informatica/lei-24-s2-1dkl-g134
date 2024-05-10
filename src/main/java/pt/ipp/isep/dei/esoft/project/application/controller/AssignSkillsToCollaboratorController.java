package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.ArrayList;
import java.util.Optional;

public class AssignSkillsToCollaboratorController {
    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;

    public AssignSkillsToCollaboratorController(){
        getCollaboratorRepository();
        getSkillRepository();
    }

    private void getCollaboratorRepository(){
        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
    }
    private void getSkillRepository(){
        skillRepository = Repositories.getInstance().getSkillRepository();
    }

    public Optional<ArrayList<Collaborator>> getCollaboratorList(){
        return collaboratorRepository.getCollaboratorList();
    }

    public Optional<ArrayList<Skill>> getSkillList(){
        return skillRepository.getSkillList();
    }

    public Optional<ArrayList<Skill>> assignSkillsToCollaborator(ArrayList<Skill> skills, Collaborator collaborator){
        return collaboratorRepository.assignSkillsToCollaborator(collaborator, skills);
    }
}
