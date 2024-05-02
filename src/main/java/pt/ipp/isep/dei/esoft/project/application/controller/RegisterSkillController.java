package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Objects;
import java.util.Optional;

public class RegisterSkillController {

    private final SkillRepository skillRepository;


    public RegisterSkillController() {
        this.skillRepository = Repositories.getInstance().getSkillRepository();

    }

    public Optional<Skill> registerSkill(String name) {
        return skillRepository.add(name);
    }

}