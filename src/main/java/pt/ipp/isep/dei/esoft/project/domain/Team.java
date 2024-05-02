package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;

public class Team {
    private ArrayList<Collaborator> teamMembers;
    private ArrayList<Skill> teamSkills;

    public Team(ArrayList<Collaborator> teamMembers, ArrayList<Skill> teamSkills) {
        if(!teamMembers.isEmpty() && !teamSkills.isEmpty()) {
            this.teamMembers = teamMembers;
            this.teamSkills = teamSkills;
        }
    }

    public ArrayList<Collaborator> getTeamMembers() {return teamMembers;}

    @Override
    public boolean equals(Object o){
        Team newTeam = (Team) o;
        boolean discrepancyFound = false;
        ArrayList<Collaborator> newTeamMembers = newTeam.getTeamMembers();
        if(newTeamMembers.size() == teamMembers.size()){
            for(Collaborator member : teamMembers){
                if(!newTeamMembers.contains(member)){
                    discrepancyFound = true;
                }
            }
        }
        return false;
    }
}
