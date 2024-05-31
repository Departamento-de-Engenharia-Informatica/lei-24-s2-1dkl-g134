package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.Optional;

public class TeamMapper {

    /**
     * Converts the object associated with this mapper to its DTO.
     * @param team The team to convert.
     * @return A DTO representing the team.
     */
    public TeamDTO objectToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.attachedTeam = team;
        teamDTO.teamMembers = new ArrayList<>();
        teamDTO.teamMembers.addAll(team.getTeamMembers());
        return teamDTO;
    }

    /**
     * Converts the DTO associated with this mapper to its object.
     * @param teamDTO The DTO to convert.
     * @return The object associated with this DTO.
     */
    public Team DTOToObject(TeamDTO teamDTO) {
        return teamDTO.attachedTeam;
    }

    /**
     * Converts a list of objects associated with this mapper to a list of DTOs.
     * @param teams The list of teams to convert.
     * @return The list of DTOs representing the green spaces.
     */
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

    /**
     * Gets an instance of this Mapper.
     * @return An instance of TeamMapper.
     */
    public static TeamMapper getMapper(){
        return new TeamMapper();
    }
}
