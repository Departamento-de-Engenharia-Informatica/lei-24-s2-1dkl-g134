package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryMapper;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Optional;

public class AssignVehiclesToTaskController {
    private AgendaRepository agendaRepository;
    private VehicleRepository vehicleRepository;

    /**
     * Constructor for a new AssignVehiclesToTaskController object.
     * All this does is get the necessary repositories.
     */
    public AssignVehiclesToTaskController(){
        agendaRepository = Repositories.getInstance().getAgendaRepository();
        vehicleRepository = Repositories.getInstance().getVehicleRepository();
    }

    /**
     * Gets the list of all tasks currently in a "PLANNED" or "POSTPONED" state.
     * @return An Optional object containing the list of all tasks (in a DTO format) in a "PLANNED" or "POSTPONED"
     * state. If none is found, an empty Optional object instead.
     */
    public Optional<ArrayList<TaskEntryDTO>> getPlannedAndPostponedTasks(){
        return TaskEntryMapper.getMapper().objectListToDTOList(agendaRepository.getPlannedAndPostponedTasks());
    }

    /**
     * Gets the full list of all vehicles.
     * @return The list of all vehicles (in a DTO format).
     */
    public Optional<ArrayList<VehicleDTO>> getVehicleList(){
        return VehicleMapper.getMapper().objectListToDTOList(vehicleRepository.getVehicleList());
    }

    /**
     * Assigns a list of vehicles to a task.
     * This method will only assign, from the list of vehicles, the vehicles not already assigned
     * to the task.
     * @param taskEntry The task to assign vehicles to.
     * @param vehicles The list of vehicles to assign to the task.
     * @return An Optional object containing the list of all vehicles actually assigned to
     * the task. If none was assigned, an empty Optional object instead.
     */
    public Optional<ArrayList<Vehicle>> assignVehiclesToTask(ArrayList<Vehicle> vehicles, TaskEntry taskEntry){
        return agendaRepository.assignVehiclesToTask(taskEntry, vehicles);
    }

    /**
     * Checks if the specified vehicle is available to perform the specified task.
     * The criteria for this relies on whether the specified vehicle is assigned to any task
     * whose schedule overlaps even by just an hour with the specified task's schedule.
     * @param vehicle The vehicle to check for availability.
     * @param taskEntry The task to check for availability.
     * @return A boolean value representing if the specified vehicle is available to perform the
     * specified task.
     */
    public boolean isVehicleAvailable(Vehicle vehicle, TaskEntry taskEntry){
        return agendaRepository.isVehicleAvailable(vehicle, taskEntry);
    }
}
