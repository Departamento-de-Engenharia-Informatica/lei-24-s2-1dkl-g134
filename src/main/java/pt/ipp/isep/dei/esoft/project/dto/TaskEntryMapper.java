package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;

import java.util.ArrayList;
import java.util.Optional;

public class TaskEntryMapper {

    /**
     * Converts the object associated with this mapper to its DTO.
     * @param taskEntry The task to convert.
     * @return A DTO representing the task.
     */
    public TaskEntryDTO objectToDTO(TaskEntry taskEntry) {
        TaskEntryDTO taskEntryDTO = new TaskEntryDTO();
        taskEntryDTO.attachedTaskEntry = taskEntry;
        taskEntryDTO.taskTitle = taskEntry.getTaskTitle();
        taskEntryDTO.taskDescription = taskEntry.getTaskDescription();
        taskEntryDTO.urgencyLevel = taskEntry.getUrgencyLevel();
        taskEntryDTO.state = taskEntry.getState();
        taskEntryDTO.startDate = taskEntry.getStartDate();
        taskEntryDTO.endDate = taskEntry.getEndDate();
        taskEntryDTO.startTime = taskEntry.getStartTime();
        taskEntryDTO.endTime = taskEntry.getEndTime();
        taskEntryDTO.duration = taskEntry.getDuration();
        taskEntryDTO.greenSpaceDomainReturn = taskEntry.getGreenSpace();
        taskEntryDTO.greenSpace = taskEntry.getGreenSpaceObject();
        taskEntryDTO.assignedTeam = taskEntry.getAssignedTeam();
        taskEntryDTO.assignedVehicles = taskEntry.getAssignedVehicles();
        return taskEntryDTO;
    }

    /**
     * Converts the DTO associated with this mapper to its object.
     * @param taskEntryDTO The DTO to convert.
     * @return The object associated with this DTO.
     */
    public TaskEntry DTOToObject(TaskEntryDTO taskEntryDTO) {
        return taskEntryDTO.attachedTaskEntry;
    }

    /**
     * Converts a list of objects associated with this mapper to a list of DTOs.
     * @param taskEntries The list of tasks to convert.
     * @return The list of DTOs representing the tasks.
     */
    public Optional<ArrayList<TaskEntryDTO>> objectListToDTOList(Optional<ArrayList<TaskEntry>> taskEntries) {
        if(taskEntries.isEmpty()) {
            return Optional.empty();
        }
        ArrayList<TaskEntryDTO> taskEntryDTOList = new ArrayList<>();
        for (TaskEntry taskEntry : taskEntries.get()) {
            taskEntryDTOList.add(objectToDTO(taskEntry));
        }
        return Optional.of(taskEntryDTOList);
    }

    /**
     * Gets an instance of this Mapper.
     * @return An instance of TaskEntryMapper.
     */
    public static TaskEntryMapper getMapper(){
        return new TaskEntryMapper();
    }
}
