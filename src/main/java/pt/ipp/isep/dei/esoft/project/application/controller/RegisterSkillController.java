package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Objects;
import java.util.Optional;

public class RegisterSkillController {

    private final SkillRepository skillRepository;

    /**
     * Constructor for a new RegisterSkillController object.
     * All this does is get the necessary repositories.
     */
    public RegisterSkillController() {
        this.skillRepository = Repositories.getInstance().getSkillRepository();

    }

    /**
     * Registers a new skill
     * If the skill is equal to any other skill in the repository, no skill is added.
     * Check the documentation of Skill.equals() for the criteria of such.
     * @param name The name of the skill to be added.
     * @return If a skill was added, the added skill. Otherwise, an empty Optional object.
     */
    public Optional<Skill> registerSkill(String name) {
        return skillRepository.add(name);
    }

}