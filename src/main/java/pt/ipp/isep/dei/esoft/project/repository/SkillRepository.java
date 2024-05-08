package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.Optional;

public class SkillRepository {

    private final ArrayList<Skill> skills;

    public SkillRepository() {
        this.skills = new ArrayList<>();
    }

    public Optional<Skill> add(String name) {
        Skill s = new Skill(name);
        if(skills.contains(s)){
            return Optional.empty();
        }
        skills.add(s);
        return Optional.of(s);
    }

    public Optional<ArrayList<Skill>> getSkillList() {
        if(skills.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(skills);
    }
}