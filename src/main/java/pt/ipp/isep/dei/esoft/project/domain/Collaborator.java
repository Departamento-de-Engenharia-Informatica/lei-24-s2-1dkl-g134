package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.session.UserSession;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class Collaborator implements Serializable {
    private String name;
    private CustomDate birthDate;
    private CustomDate admissionDate;
    private String address;
    private String phoneNumber;
    private String email;
    private String identificationDocumentType;
    private String taxpayerNumber;
    private String identificationNumber;
    private Job job;
    private ArrayList<Skill> collaboratorSkills;

    /**
     * Constructor for a new Collaborator object.
     * This method will throw an IllegalArgumentException if any field is blank, the birthdate
     * indicates that the new collaborator is a legal minor, the admission date indicates that the
     * new collaborator was admitted as a legal minor, the admission date is in the future, the
     * email has an invalid syntax, the phone number has an invalid syntax (not a 9-digit number,
     * unless it contains an international calling code), or for any reason outlined in the
     * CustomDate constructor.
     * @param name A String representing the collaborator's name
     * @param birthDate A String representing the collaborator's birthdate.
     * @param admissionDate A String representing the collaborator's admission date.
     * @param address A String representing the collaborator's address.
     * @param phoneNumber A String representing the collaborator's phone number.
     * @param email A String representing the collaborator's email.
     * @param identificationDocumentType A String representing the collaborator's ID document type.
     * @param identificationNumber A String representing the collaborator's ID number.
     * @param taxpayerNumber A String representing the collaborator's taxpayer number.
     * @param job A Job object representing the collaborator's job.
     */
    public Collaborator(String name, String birthDate,String admissionDate,String address,
                        String phoneNumber, String email, String identificationDocumentType,
                        String identificationNumber, String taxpayerNumber, Job job) {
        if(name == null || birthDate == null || admissionDate == null || address == null ||
        phoneNumber == null || email == null || identificationDocumentType == null ||
        identificationNumber == null || taxpayerNumber == null || job == null) {
            throw new IllegalArgumentException("No field can be null.");
        }
        if(name.isBlank() || birthDate.isBlank() || admissionDate.isBlank() || address.isBlank()
        || phoneNumber.isBlank() || email.isBlank() || identificationDocumentType.isBlank()
        || identificationNumber.isBlank() || taxpayerNumber.isBlank()) {
            throw new IllegalArgumentException("All fields must be filled in.");
        }
        name = name.trim();
        birthDate = birthDate.trim();
        admissionDate = admissionDate.trim();
        address = address.trim();
        phoneNumber = phoneNumber.trim();
        email = email.trim();
        identificationDocumentType = identificationDocumentType.trim();
        identificationNumber = identificationNumber.trim();
        taxpayerNumber = taxpayerNumber.trim();
        char[] nameCharacters = name.toCharArray();
        for(char c : nameCharacters){
            if(!Character.isLetter(c) && !Character.isWhitespace(c)){
                throw new IllegalArgumentException("Collaborator name can only contain letters and whitespaces.");
            }
        }
        this.name = name;
        this.birthDate = new CustomDate(birthDate);
        if(!this.birthDate.isLegalAge()){
            throw new IllegalArgumentException("Collaborator is a legal minor.");
        }
        this.admissionDate = new CustomDate(admissionDate);
        if(this.admissionDate.yearDifference(this.birthDate) < 18){
            throw new IllegalArgumentException("Collaborator cannot be admitted as a legal minor.");
        }
        if(this.admissionDate.isFuture()){
            throw new IllegalArgumentException("Admission date must not be in the future.");
        }
        this.address = address;
        this.phoneNumber = phoneNumber;
        char[] phoneNumberCharacters = phoneNumber.toCharArray();
        for(int i = 0; i < phoneNumberCharacters.length; i++){
            if(!Character.isDigit(phoneNumberCharacters[i])){
                if(phoneNumberCharacters[i] == '+' && i == 0 && phoneNumberCharacters.length > 10 && phoneNumberCharacters.length < 15){
                    continue;
                }
                throw new IllegalArgumentException("Phone number must only contain numerical digits (aside from a + as the first digit of an international calling code)");
            }
        }
        if(phoneNumberCharacters.length != 9 && phoneNumberCharacters[0] != '+'){
            throw new IllegalArgumentException("Phone numbers without an international calling code must be 9 digits long.");
        }
        this.email = email;
        if(!email.contains("@")){
            throw new IllegalArgumentException("Email address has an invalid syntax.");
        }
        this.identificationDocumentType = identificationDocumentType;
        this.identificationNumber = identificationNumber;
        this.taxpayerNumber = taxpayerNumber;
        this.job = job;
        collaboratorSkills = new ArrayList<>();
    }

    /**
     * Returns the ID number of this collaborator.
     * @return A String representing the ID number of this collaborator.
     */
    public String getIdentificationNumber() {return identificationNumber;}

    /**
     * Returns this collaborator's name.
     * @return A String representing this collaborator's name.
     */
    public String getName() {return name;}

    /**
     * Returns this collaborator's email.
     * @return A String representing this collaborator's email.
     */
    public String getEmail() {return email;}

    /**
     * Checks if this collaborator is the same as another.
     * Two collaborators are considered the same if their ID number is the same.
     * @param o The collaborator to compare against.
     * @return A boolean value representing if the collaborators are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Collaborator)) {
            return false;
        }
        Collaborator newCollaborator = (Collaborator) o;
        if(identificationNumber.compareTo(newCollaborator.getIdentificationNumber()) == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the String representation of this collaborator.
     * @return A String representing the collaborator's name and ID number respectively, separated by a '|' character.
     */
    @Override
    public String toString() {
        return name + " | " + identificationNumber;
    }

    /**
     * Assigns a number of skills to this collaborator.
     * This method receives a list of skills, but will only assign those that are not already present
     * on this collaborator's list of skills.
     * @param skillList The list of skills to assign to the collaborator.
     * @return The list of skills actually assigned to the collaborator.
     */
    public Optional<ArrayList<Skill>> assignSkills(ArrayList<Skill> skillList){
        ArrayList<Skill> assignedSkills = new ArrayList<Skill>();
        boolean anySkillAssigned = false;
        for(Skill skill : skillList){
            if(!hasSkill(skill)){
                collaboratorSkills.add(skill);
                assignedSkills.add(skill);
                anySkillAssigned = true;
            }
        }
        if(anySkillAssigned){
            return Optional.of(assignedSkills);
        }
        return Optional.empty();
    }

    /**
     * Checks if the collaborator has a certain skill.
     * @param skill The skill to search for.
     * @return A boolean value representing if this collaborator has this skill in question.
     */
    public boolean hasSkill(Skill skill) {return collaboratorSkills.contains(skill);}
}
