package pt.ipp.isep.dei.esoft.project.domain;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class Collaborator {
    private String name;
    private String birthDate;
    private String admissionDate;
    private String address;
    private String phoneNumber;
    private String email;
    private String identificationDocumentType;

    private String identificationNumber;
    private Job job;
    private ArrayList<Skill> collaboratorSkills;

    public Collaborator(String name, String birthDate,String admissionDate,String address,
                        String phoneNumber, String email, String identificationDocumentType,
                        String identificationNumber, Job job) {
        // NEEDS LOCAL VALIDATION
        this.name = name;
        this.birthDate = birthDate;
        this.admissionDate = admissionDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.identificationDocumentType = identificationDocumentType;
        this.identificationNumber = identificationNumber;
        this.job = job;
        collaboratorSkills = new ArrayList<>();
    }

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
