package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
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
    public Optional<ArrayList<TaskEntry>> getPlannedAndPostponedTasks(){
        return agendaRepository.getPlannedAndPostponedTasks();
    }

    /**
     * Gets the full list of skills.
     * @return The list of all skills.
     */
    public Optional<ArrayList<Team>> getTeamList(){
        return teamRepository.getTeamList();
    }


    public Optional<TaskEntry> assignTeamToTask(Team team, TaskEntry taskEntry) throws IOException {
        return agendaRepository.assignTeamToTask(taskEntry, team);
    }
}
