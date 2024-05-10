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

    public void run() {
        System.out.println("\n >>>>>>>>>> GENERATE TEAM PROPOSALS <<<<<<<<<< \n");

        requestData();

        finalTeamMembers = generateProposals();
        if(finalTeamMembers.isEmpty()){
            return;
        }
        submitData();
    }

    public void requestData(){
        requiredSkills = requestSkills();
        if(requiredSkills.isEmpty()){
            System.out.println("Error: No skills selected. Aborting team generation.");
            return;
        }
        minTeamSize = requestMinTeamSize();

        maxTeamSize = requestMaxTeamSize();
    }

    private int requestMinTeamSize(){
        Scanner input = new Scanner(System.in);
        int minTeamSizeAccordingToRequiredSkills = minSizeOfRequiredSkills();
        while(true){
            try{
                System.out.println("Minimum team size: ");
                int minTeamSize = Integer.parseInt(input.nextLine());
                if(minTeamSize < minTeamSizeAccordingToRequiredSkills){
                    System.out.println("Minimum team size impossible according to the list of required skills.");
                    continue;
                }
                return minTeamSize;
            }catch(Exception e){
                System.out.println("Minimum team size value must be a number.");
            }
        }
    }

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
                System.out.println((i+1) + "- "+skills.get().get(i).getName());
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

    public Optional<ArrayList<Collaborator>> generateProposals(){
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

    public void submitData(){
        Optional<Team> newTeam = controller.registerTeam(finalTeamMembers.get(), requiredSkills);
        if(newTeam.isEmpty()){
            System.out.println("Failed to register new team!");
        }else{
            System.out.println("Team registered successfully!");
        }
    }
}
