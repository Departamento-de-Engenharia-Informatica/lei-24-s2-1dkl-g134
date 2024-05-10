package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillsToCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class AssignSkillsToCollaboratorUI implements Runnable {
    private Collaborator collaborator = null;
    private ArrayList<Skill> selectedSkills = null;
    private AssignSkillsToCollaboratorController controller = new AssignSkillsToCollaboratorController();

    public void run(){
        System.out.println("\n >>>>>>>>>> ASSIGN SKILLS TO COLLABORATOR <<<<<<<<<< \n");

        requestData();
        if(collaborator == null || selectedSkills == null){ return; }
        submitData();
    }

    private void requestData(){
        collaborator = requestCollaborator();
        if(collaborator == null){ return; }
        selectedSkills = requestSkills();
    }

    private Collaborator requestCollaborator(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<Collaborator>> collaborators = controller.getCollaboratorList();
        if(collaborators.isEmpty()){
            System.out.println("Error: No collaborators. Skill assignment aborted.");
            return null;
        }
        System.out.println("Choose a collaborator from the following list (Name | ID number):\n");
        for(int i = 0; i < collaborators.get().size(); i++){
            System.out.println((i+1) + "- "+collaborators.get().get(i).getName() + " | "+collaborators.get().get(i).getIdentificationNumber());
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
                    System.out.println((i+1) + "- "+skills.get().get(i).getName()+" (Selected)");
                }else{
                    System.out.println((i+1) + "- "+skills.get().get(i).getName());
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

    private void submitData(){
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
    }
}
