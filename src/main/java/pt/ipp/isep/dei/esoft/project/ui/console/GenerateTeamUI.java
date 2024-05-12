package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class GenerateTeamUI implements Runnable {
    private ArrayList<Skill> requiredSkills;
    private Optional<ArrayList<Collaborator>> finalTeamMembers;
    private int minTeamSize, maxTeamSize;
    private GenerateTeamController controller = new GenerateTeamController();

    /**
     * Run this functionality.
     */
    public void run() {
        System.out.println("\n >>>>>>>>>> GENERATE TEAM PROPOSALS <<<<<<<<<< \n");

        if(!controller.doCollaboratorsExist()){
            System.out.println("Error: No collaborators. Aborting team generation.");
            return;
        }

        while(true){
            requestData();

            finalTeamMembers = generateProposals();
            if(finalTeamMembers.isEmpty()){
                return;
            }
            if(!confirmData()){continue;}
            submitData();
            return;
        }
    }

    /**
     * Request all necessary data and save it to its respective variables.
     */
    public void requestData(){
        requiredSkills = requestSkills();
        if(requiredSkills.isEmpty()){
            System.out.println("Error: No skills selected. Aborting team generation.");
            return;
        }
        minTeamSize = requestMinTeamSize();

        maxTeamSize = requestMaxTeamSize();
    }

    /**
     * Requests the team's minimum size.
     * @return The team's minimum size.
     */
    private int requestMinTeamSize(){
        Scanner input = new Scanner(System.in);
        int minTeamSizeAccordingToRequiredSkills = minSizeOfRequiredSkills();
        while(true){
            try{
                System.out.println("Minimum team size: ");
                int minTeamSize = Integer.parseInt(input.nextLine());
                if(minTeamSize < minTeamSizeAccordingToRequiredSkills || minTeamSize > requiredSkills.size()){
                    System.out.println("Minimum team size impossible according to the list of required skills.");
                    continue;
                }
                return minTeamSize;
            }catch(Exception e){
                System.out.println("Minimum team size value must be a number.");
            }
        }
    }

    /**
     * Calculates the minimum size of the team according to the list of required skills.
     * @return The team's minimum possible size.
     */
    private int minSizeOfRequiredSkills(){
        ArrayList<Skill> requiredSkillsNoDuplicates = new ArrayList<>();
        ArrayList<Integer> skillRepresentation = new ArrayList<>();
        for(Skill skill : requiredSkills){
            if(requiredSkillsNoDuplicates.contains(skill)){
                skillRepresentation.set(requiredSkillsNoDuplicates.indexOf(skill), skillRepresentation.get(requiredSkillsNoDuplicates.indexOf(skill)) + 1);
            }else{
                requiredSkillsNoDuplicates.add(skill);
                skillRepresentation.add(1);
            }
        }
        Integer maxRepresentation = -1;
        for(Integer representation : skillRepresentation){
            if(representation > maxRepresentation){
                maxRepresentation = representation;
            }
        }
        return maxRepresentation;
    }

    /**
     * Requests the team's maximum size.
     * @return The team's maximum size.
     */
    private int requestMaxTeamSize(){
        Scanner input = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Maximum team size: ");
                int maxTeamSize = Integer.parseInt(input.nextLine());
                if(maxTeamSize < minTeamSize){
                    System.out.println("Maximum team size must be above or equal to the minimum team size.");
                    continue;
                }
                return maxTeamSize;
            }catch(Exception e){
                System.out.println("Maximum team size value must be a number.");
            }
        }
    }

    /**
     * Requests the team's required skills.
     * @return The team's required skills.
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
            System.out.println("Choose skills from the following list (duplicate selections are allowed):\n");
            for(int i = 0; i < skills.get().size(); i++){
                System.out.println((i+1) + "- "+skills.get().get(i).toString());
            }
            int option = 0;
            System.out.println("Current skill selection:");
            if(userSkillSelection.isEmpty()){
                System.out.println("None.");
            }else{
                for(Skill skill : userSkillSelection){
                    System.out.print(skill.getName() + " ");
                }
                System.out.println();
            }
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
            userSkillSelection.add(skills.get().get(option-1));
        }
    }

    /**
     * Generate team proposals, present them to the user, and request acceptance.
     * Should a proposal be accepted, returns that team proposal in the form of a list of collaborators.
     * Should it be rejected, generate another team proposal, and repeat the process.
     * @return The accepted team proposal in the form of a list of collaborators. If no proposal was
     * accepted, an empty Optional object instead.
     */
    private Optional<ArrayList<Collaborator>> generateProposals(){
        System.out.println("\n\n");
        while(true){
            Optional<ArrayList<Collaborator>> teamProposal = controller.generateTeamProposal(minTeamSize, maxTeamSize, requiredSkills);
            if(teamProposal.isEmpty()){
                System.out.println("No possible team could be found. Aborting team generation.");
                return Optional.empty();
            }
            System.out.println("The following team combination was found:\n");
            for(Collaborator collaborator : teamProposal.get()){
                System.out.println(collaborator.getName() + " | " + collaborator.getIdentificationNumber());
            }
            boolean accepted = Utils.confirm("Is the team proposal above acceptable? (s or n)");
            if(accepted){
                return teamProposal;
            }else{
                System.out.println("Attempting to find another combination...");
                controller.blacklistTeamProposal(teamProposal.get());
            }
        }
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> TEAM INFORMATION <<<<<<<<<< \n");

        System.out.println("Skillset:");
        for(Skill skill : requiredSkills){
            System.out.println(skill.toString());
        }
        System.out.println("\nMembers:");
        for(Collaborator collaborator : finalTeamMembers.get()){
            System.out.println(collaborator.toString());
        }
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submit the inputted data and provide the respective feedback.
     */
    private void submitData(){
        try{
            Optional<Team> newTeam = controller.registerTeam(finalTeamMembers.get(), requiredSkills);
            if(newTeam.isEmpty()){
                System.out.println("Failed to register new team!");
            }else{
                System.out.println("Team registered successfully!");
            }
        }catch(Exception e){
            System.out.println("Failed to register new team!");
            System.out.println(e.getMessage());
        }

    }
}
