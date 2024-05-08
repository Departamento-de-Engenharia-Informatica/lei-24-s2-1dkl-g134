package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class Team {
    private ArrayList<Collaborator> teamMembers;
    private ArrayList<Skill> teamSkills;

    public Team(ArrayList<Collaborator> teamMembers, ArrayList<Skill> teamSkills) {
        if(!teamMembers.isEmpty() && !teamSkills.isEmpty()) {
            this.teamMembers = teamMembers;
            this.teamSkills = teamSkills;
        }else{
            throw new IllegalArgumentException("Team must contain at least one collaborator and at least one skill");
        }
    }

    public ArrayList<Collaborator> getTeamMembers() {return teamMembers;}

    @Override
    public boolean equals(Object o){
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
}
