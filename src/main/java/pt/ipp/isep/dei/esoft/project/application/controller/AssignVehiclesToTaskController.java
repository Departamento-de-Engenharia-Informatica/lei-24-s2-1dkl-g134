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
    public Optional<ArrayList<VehicleDTO>> getVehicleList(){
        return VehicleMapper.getMapper().objectListToDTOList(vehicleRepository.getVehicleList());
    }


    public Optional<ArrayList<Vehicle>> assignVehiclesToTask(ArrayList<Vehicle> vehicles, TaskEntry taskEntry){
        return agendaRepository.assignVehiclesToTask(taskEntry, vehicles);
    }

    public boolean isVehicleAvailable(Vehicle vehicle, TaskEntry taskEntry){
        return agendaRepository.isVehicleAvailable(vehicle, taskEntry);
    }
}
