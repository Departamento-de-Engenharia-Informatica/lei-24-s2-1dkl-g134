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

    /**
     * Constructor for a new AssignSkillsToCollaboratorController object.
     * All this does is get the necessary repositories.
     */
    public AssignSkillsToCollaboratorController(){
        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        skillRepository = Repositories.getInstance().getSkillRepository();
    }

    /**
     * Gets the full list of collaborators.
     * @return The list of all collaborators.
     */
    public Optional<ArrayList<Collaborator>> getCollaboratorList(){
        return collaboratorRepository.getCollaboratorList();
    }

    /**
     * Gets the full list of skills.
     * @return The list of all skills.
     */
    public Optional<ArrayList<Skill>> getSkillList(){
        return skillRepository.getSkillList();
    }

    /**
     * Assigns a number of skills to a collaborator.
     * This method receives a list of skills, but will only assign those that are not already present
     * on the collaborator's list of skills.
     * @param  collaborator The collaborator to add the skills to.
     * @param skills The list of skills to assign to the collaborator.
     * @return The list of skills actually assigned to the collaborator.
     */
    public Optional<ArrayList<Skill>> assignSkillsToCollaborator(ArrayList<Skill> skills, Collaborator collaborator){
        return collaboratorRepository.assignSkillsToCollaborator(collaborator, skills);
    }
}
