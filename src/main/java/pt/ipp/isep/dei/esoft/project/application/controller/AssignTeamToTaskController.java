package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryMapper;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class AssignTeamToTaskController {
    private AgendaRepository agendaRepository;
    private TeamRepository teamRepository;

    /**
     * Constructor for a new AssignVehiclesToTaskController object.
     * All this does is get the necessary repositories.
     */
    public AssignTeamToTaskController(){
        agendaRepository = Repositories.getInstance().getAgendaRepository();
        teamRepository = Repositories.getInstance().getTeamRepository();
    }

    /**
     * Gets the full list of collaborators.
     * @return The list of all collaborators.
     */
    public Optional<ArrayList<TaskEntryDTO>> getPlannedAndPostponedTasks(){
        return TaskEntryMapper.getMapper().objectListToDTOList(agendaRepository.getPlannedAndPostponedTasks());
    }

    /**
     * Gets the full list of skills.
     * @return The list of all skills.
     */
    public Optional<ArrayList<TeamDTO>> getTeamList(){
        return TeamMapper.getMapper().objectListToDTOList(teamRepository.getTeamList());
    }

    public Optional<TaskEntry> assignTeamToTask(Team team, TaskEntry taskEntry) throws IOException {
        return agendaRepository.assignTeamToTask(taskEntry, team);
    }

    public boolean isTeamAvailable(Team team, TaskEntry taskEntry){
        return agendaRepository.isTeamAvailable(team.getTeamMembers(), taskEntry);
    }
}
