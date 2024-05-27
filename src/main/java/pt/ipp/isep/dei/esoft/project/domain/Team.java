package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    private ArrayList<Collaborator> teamMembers;
    private ArrayList<Skill> teamSkills;

    /**
     * Constructor for a new Team object.
     * This method will throw an IllegalArgumentException if either the list of members or skills of
     * this team are empty.
     * @param teamMembers The list of Collaborators that form this team.
     * @param teamSkills The list of skills that define this team's composition.
     */
    public Team(ArrayList<Collaborator> teamMembers, ArrayList<Skill> teamSkills) {
        if(teamMembers == null || teamSkills == null){
            throw new IllegalArgumentException("Null parameters are not allowed.");
        }
        if(!teamMembers.isEmpty() && !teamSkills.isEmpty()) {
            this.teamMembers = teamMembers;
            this.teamSkills = teamSkills;
        }else{
            throw new IllegalArgumentException("Team must contain at least one collaborator and at least one skill");
        }
    }

    /**
     * Gets all collaborators that are part of this team.
     * @return The list of collaborators that form this team.
     */
    public ArrayList<Collaborator> getTeamMembers() {return teamMembers;}

    /**
     * Checks if this team is equal to another.
     * Two teams are considered equal if they contain the exact same collaborators
     * @param o The Team object to compare against.
     * @return A boolean value indicating if these teams are equal or not.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Team)) {
            return false;
        }
        Team newTeam = (Team) o;
        ArrayList<Collaborator> newTeamMembers = newTeam.getTeamMembers();
        if(newTeamMembers.size() == teamMembers.size()){
            for(Collaborator member : teamMembers){
                if(!newTeamMembers.contains(member)){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns the String representation of this Team
     * @return A String representing each of the team's collaborators.
     */
    @Override
    public String toString(){
        String representation = "";
        for(Collaborator collaborator : teamMembers){
            representation += collaborator.toString() + "\n";
        }
        return representation;
    }
}
