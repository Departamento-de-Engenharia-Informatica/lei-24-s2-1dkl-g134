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
     * Constructor for a new AssignTeamToTaskController object.
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
     * Gets the full list of all teams.
     * @return The list of all teams.
     */
    public Optional<ArrayList<TeamDTO>> getTeamList(){
        return TeamMapper.getMapper().objectListToDTOList(teamRepository.getTeamList());
    }

    /**
     * Assigns a team to a task.
     * The team will only be assigned if its list of collaborators is different from the one
     * already assigned to the collaborator.
     * @param taskEntry The task to assign a team to.
     * @param team The team to assign to the task.
     * @return An Optional object containing the task with its updated values.
     * If nothing was updated, an empty Optional object instead.
     */
    public Optional<TaskEntry> assignTeamToTask(Team team, TaskEntry taskEntry) throws IOException {
        return agendaRepository.assignTeamToTask(taskEntry, team);
    }

    /**
     * Checks if the specified team is available to perform the specified task.
     * The criteria for this relies on whether the team is assigned to any task
     * whose schedule overlaps even by just an hour with the specified task's schedule.
     * @param team The team to check for availability.
     * @param taskEntry The task to check for availability.
     * @return A boolean value representing if the specified team is available to perform the
     * specified task.
     */
    public boolean isTeamAvailable(Team team, TaskEntry taskEntry){
        return agendaRepository.isTeamAvailable(team.getTeamMembers(), taskEntry);
    }
}
