package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Comparator;

public class TeamMember {
    private Collaborator collaborator;
    private ArrayList<Skill> contributions;
    private ArrayList<ArrayList<Skill>> contributionCombinations;

    public TeamMember(Collaborator collaborator, ArrayList<Skill> contributions) {
        this.collaborator = collaborator;
        contributionCombinations = calculateContributionCombinations(contributions);
        contributionCombinations.sort(Comparator.comparing(ArrayList::size, Comparator.reverseOrder()));
        this.contributions = contributionCombinations.get(0);
    }

    public boolean attemptNextCombination(){
        if(contributionCombinations.indexOf(contributions)+1 >= contributionCombinations.size()){
            return false;
        }
        contributions = contributionCombinations.get(contributionCombinations.indexOf(contributions)+1);
        return true;
    }

    public ArrayList<Skill> getSkillsRemoved(){
        return contributionCombinations.get(contributionCombinations.indexOf(contributions)-1);
    }

    public ArrayList<Skill> getCurrentlyUsedSkills(){
        return contributions;
    }

    private ArrayList<ArrayList<Skill>> calculateContributionCombinations(ArrayList<Skill> contributions) {
        ArrayList<Skill> combination = new ArrayList<>();
        ArrayList<ArrayList<Skill>> combinations = new ArrayList<>();

        int index = 0;
        recursiveCalculation(contributions, combinations, combination, index);

        for(ArrayList<Skill> skills : combinations){
            if(skills.isEmpty()){
                combinations.remove(combinations.indexOf(skills));
                break;
            }
        }

        return combinations;
    }

    public static void recursiveCalculation(ArrayList<Skill> contributions, ArrayList<ArrayList<Skill>> combinations, ArrayList<Skill> combination, int index) {
        combinations.add(new ArrayList<>(combination));
        for (int i = index; i < contributions.size(); i++) {
            combination.add(contributions.get(i));
            recursiveCalculation(contributions, combinations, combination, i + 1);
            combination.remove(combination.size() - 1);
        }
    }

    public Collaborator getCollaborator() {return collaborator;}
}
