package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.Optional;

public class SkillRepository {

    private final ArrayList<Skill> skills;

    /**
     * Constructor for a new skill repository.
     * All this does is initialize the list of skills.
     */
    public SkillRepository() {
        this.skills = new ArrayList<>();
    }

    /**
     * Adds a new skill to this repository.
     * If the skill is equal to any other skill in the repository, no skill is added.
     * Check the documentation of Skill.equals() for the criteria of such.
     * @param name The name of the skill to be added.
     * @return If a skill was added, the added skill. Otherwise, an empty Optional object.
     */
    public Optional<Skill> add(String name) {
        Skill s = new Skill(name);
        if(skills.contains(s)){
            return Optional.empty();
        }
        skills.add(s);
        return Optional.of(s);
    }

    /**
     * Returns the list of all skills in the repository.
     * @return The list of all skills in the repository
     */
    public Optional<ArrayList<Skill>> getSkillList() {
        if(skills.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(skills);
    }
}