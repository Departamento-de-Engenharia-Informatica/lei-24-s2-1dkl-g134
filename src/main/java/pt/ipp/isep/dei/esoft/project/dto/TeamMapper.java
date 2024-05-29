package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.Optional;

public class TeamMapper {
    public TeamDTO objectToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.attachedTeam = team;
        teamDTO.teamMembers = new ArrayList<>();
        teamDTO.teamMembers.addAll(team.getTeamMembers());
        return teamDTO;
    }

    public Team DTOToObject(TeamDTO teamDTO) {
        return teamDTO.attachedTeam;
    }

    public Optional<ArrayList<TeamDTO>> objectListToDTOList(Optional<ArrayList<Team>> teams) {
        if(teams.isEmpty()) {
            return Optional.empty();
        }
        ArrayList<TeamDTO> teamDTOList = new ArrayList<>();
        for (Team team : teams.get()) {
            teamDTOList.add(objectToDTO(team));
        }
        return Optional.of(teamDTOList);
    }

    public static TeamMapper getMapper(){
        return new TeamMapper();
    }
}
