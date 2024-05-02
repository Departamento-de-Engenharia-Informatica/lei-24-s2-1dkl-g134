package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Optional;

public class Collaborator {

    private String identificationNumber;
    private ArrayList<Skill> collaboratorSkills;

    public String getIdentificationNumber() {return identificationNumber;}

    @Override
    public boolean equals(Object o) {
        Collaborator newCollaborator = (Collaborator) o;
        if(identificationNumber.compareTo(newCollaborator.getIdentificationNumber()) == 0) {
            return true;
        }
        return false;
    }

    public Optional<ArrayList<Skill>> assignSkills(ArrayList<Skill> skillList){
        ArrayList<Skill> assignedSkills = new ArrayList<Skill>();
        boolean anySkillAssigned = false;
        for(Skill skill : skillList){
            if(!hasSkill(skill)){
                skillList.add(skill);
                assignedSkills.add(skill);
                anySkillAssigned = true;
            }
        }
        if(anySkillAssigned){
            return Optional.of(assignedSkills);
        }
        return Optional.empty();
    }

    public boolean hasSkill(Skill skill) {return collaboratorSkills.contains(skill);}
}
