package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Comparator;

public class TeamMember {
    private Collaborator collaborator;
    private ArrayList<Skill> contributions;
    private ArrayList<ArrayList<Skill>> contributionCombinations;

    /**
     * Constructor for a new TeamMember object.
     * @param collaborator The collaborator that is this member.
     * @param contributions The list of skills composing that member's potential contributions to the team.
     */
    public TeamMember(Collaborator collaborator, ArrayList<Skill> contributions) {
        this.collaborator = collaborator;
        contributionCombinations = calculateContributionCombinations(contributions);
        contributionCombinations.sort(Comparator.comparing(ArrayList::size, Comparator.reverseOrder()));
        this.contributions = contributionCombinations.get(0);
    }

    /**
     * Checks if there is a combination of contributions that have not been attempted yet for this
     * team member. If there is, automatically changes this member's contributions to the new
     * combination.
     * @return A boolean value indicating if there is an unattempted combination of contributions for
     * this team member.
     */
    public boolean attemptNextCombination(){
        if(contributionCombinations.indexOf(contributions)+1 >= contributionCombinations.size()){
            return false;
        }
        contributions = contributionCombinations.get(contributionCombinations.indexOf(contributions)+1);
        return true;
    }

    /**
     * Gets the list of skills representing the previous attempted combination of contributions by
     * this team member, unless this is the first attempted combination, in which case it returns
     * an empty list.
     * @return The list of skills of the previous contributions this team member was making.
     * If this is the first contribution the collaborator makes, returns an empty list.
     */
    public ArrayList<Skill> getSkillsRemoved(){
        if(contributionCombinations.indexOf(contributions) == 0){
            return new ArrayList<>();
        }
        return contributionCombinations.get(contributionCombinations.indexOf(contributions)-1);
    }

    /**
     * Gets the list of skills representing the current contributions this team member is making.
     * @return The list of skills representing the current contributions this team member is making.
     */
    public ArrayList<Skill> getCurrentlyUsedSkills(){
        return contributions;
    }

    /**
     * Calculates all possible combinations of contributions that this team member could make, given
     * a base of each skill they can possibly contribute towards.
     * More formally, it calculates all possible subsets of the set of this team member's contributions,
     * excluding the empty set.
     * @param contributions The list of all skills this collaborator can possibly contribute towards.
     * @return The list of all possible combinations of contributions this collaborator can make.
     */
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

    /**
     * Recursively calculates all the subsets of the set of all contributions this team member can make.
     * This method essentially works by iterating through the set of all skills this collaborator can
     * contribute towards, and recursing based on the possibility of including or excluding that skill
     * in the combination of contributions the collaborator makes.
     * By the time this recursion ends, the combinations parameter will include every possible subset of
     * the contributions list, including the empty and full sets.
     * @param contributions The set of all contributions this collaborator can make.
     * @param combinations The set of all subsets of the previous set.
     * @param combination A set of contributions this collaborator can make.
     * @param index The index of the contributions list to iterate from on this recursion.
     */
    private static void recursiveCalculation(ArrayList<Skill> contributions, ArrayList<ArrayList<Skill>> combinations, ArrayList<Skill> combination, int index) {
        combinations.add(new ArrayList<>(combination));
        for (int i = index; i < contributions.size(); i++) {
            combination.add(contributions.get(i));
            recursiveCalculation(contributions, combinations, combination, i + 1);
            combination.remove(combination.size() - 1);
        }
    }

    /**
     * Returns the Collaborator object associated with this team member.
     * @return The Collaborator object representing this team member.
     */
    public Collaborator getCollaborator() {return collaborator;}
}
