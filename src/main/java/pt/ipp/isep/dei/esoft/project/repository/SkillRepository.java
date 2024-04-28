package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillRepository {

    private final List<Skill> skills;

    public SkillRepository() {
        this.skills = new ArrayList<>();
    }

    public boolean add( String name) {
        if(skills.isEmpty()){
            skills.add(new Skill(name)); // if its the first entry in the list
            return true;
        }

        if (!exists(name)) {
            skills.add(new Skill(name));
            return true;
        }
        return false;
    }


    public boolean exists(String name) {
        for (Skill skill : skills) {
            if (skill.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}