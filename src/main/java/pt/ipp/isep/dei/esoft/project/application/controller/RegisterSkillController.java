package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.Objects;

public class RegisterSkillController {

    private final SkillRepository skillRepository;


    public RegisterSkillController() {
        this.skillRepository = Repositories.getInstance().getSkillRepository();

    }

    public boolean registerSkill(String name) {
        return skillRepository.add(name);
    }

}