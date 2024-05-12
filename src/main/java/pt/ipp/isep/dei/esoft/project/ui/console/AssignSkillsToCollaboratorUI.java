package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillsToCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AssignSkillsToCollaboratorUI implements Runnable {
    private Collaborator collaborator = null;
    private ArrayList<Skill> selectedSkills = null;
    private AssignSkillsToCollaboratorController controller = new AssignSkillsToCollaboratorController();

    /**
     * Run this functionality.
     */
    public void run(){
        System.out.println("\n >>>>>>>>>> ASSIGN SKILLS TO COLLABORATOR <<<<<<<<<< \n");

        while(true){
            requestData();
            if(collaborator == null || selectedSkills == null){ return; }
            if(!confirmData()){continue;}
            submitData();
            break;
        }
    }

    /**
     * Request all necessary data and save it to its respective variables.
     */
    private void requestData(){
        collaborator = requestCollaborator();
        if(collaborator == null){ return; }
        selectedSkills = requestSkills();
    }

    /**
     * Requests the collaborator to assign skills to.
     * @return The collaborator to assign skills to.
     */
    private Collaborator requestCollaborator(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<Collaborator>> collaborators = controller.getCollaboratorList();
        if(collaborators.isEmpty()){
            System.out.println("Error: No collaborators. Skill assignment aborted.");
            return null;
        }
        System.out.println("Choose a collaborator from the following list (Name | ID number):\n");
        for(int i = 0; i < collaborators.get().size(); i++){
            System.out.println((i+1) + "- "+collaborators.get().get(i).toString());
        }
        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a collaborator.");
                option = Integer.parseInt(input.nextLine());
                if(option <= 0 || option > collaborators.get().size()){
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return collaborators.get().get(option-1);
    }

    /**
     * Requests the list of skills to assign.
     * @return The list of skills to assign.
     */
    private ArrayList<Skill> requestSkills(){
        ArrayList<Skill> userSkillSelection = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<Skill>> skills = controller.getSkillList();
        if(skills.isEmpty()){
            System.out.println("Error: No skills. Skill assignment aborted.");
            return null;
        }
        while(true){
            System.out.println("Choose skills from the following list (to remove a selected skill, choose it again):\n");
            for(int i = 0; i < skills.get().size(); i++){
                if(userSkillSelection.contains(skills.get().get(i))){
                    System.out.println((i+1) + "- "+skills.get().get(i).toString()+" (Selected)");
                }else{
                    System.out.println((i+1) + "- "+skills.get().get(i).toString());
                }
            }
            int option = 0;
            while(true){
                try{
                    System.out.println("Choose a number corresponding to a skill.");
                    System.out.println("When selection is done, choose the number 0.");
                    option = Integer.parseInt(input.nextLine());
                    if(option < 0 || option > skills.get().size()){
                        System.out.println("Error: Invalid option.");
                        continue;
                    }
                    break;
                }catch(Exception e){
                    System.out.println("Error: Selected option must be a number.");
                }
            }
            if(option == 0){
                return userSkillSelection;
            }
            if(userSkillSelection.contains(skills.get().get(option-1))){
                userSkillSelection.remove(skills.get().get(option-1));
            }else{
                userSkillSelection.add(skills.get().get(option-1));
            }
        }
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> ASSIGNMENT INFORMATION <<<<<<<<<< \n");
        System.out.println("Assign the following skills: ");
        for(Skill skill : selectedSkills){
            System.out.println(skill.toString());
        }
        System.out.println("\nTo the collaborator "+collaborator.toString());
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submit the inputted data and provide the respective feedback.
     */
    private void submitData(){
        try{
            Optional<ArrayList<Skill>> assignedSkills = controller.assignSkillsToCollaborator(selectedSkills, collaborator);
            if(assignedSkills.isPresent()){
                System.out.println("The following skills were assigned successfully:");
                for(Skill skill : assignedSkills.get()){
                    System.out.println(skill.getName());
                }
                System.out.println("If any selected skill is missing from this list, it was either invalid or already present in the collaborator.");
            }else{
                System.out.println("Failed to assign skills successfully! Are you sure the selected collaborator doesn't already have these skills?");
            }
        }catch(Exception e){
            System.out.println("Failed to assign skills successfully!");
            System.out.println(e.getMessage());
        }

    }
}
