package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;

import java.util.ArrayList;
import java.util.Optional;

public class TaskEntryMapper {
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
        taskEntryDTO.assignedTeam = taskEntry.getAssignedTeam();
        taskEntryDTO.assignedVehicles = taskEntry.getAssignedVehicles();
        return taskEntryDTO;
    }

    public TaskEntry DTOToObject(TaskEntryDTO taskEntryDTO) {
        return taskEntryDTO.attachedTaskEntry;
    }

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

    public static TaskEntryMapper getMapper(){
        return new TaskEntryMapper();
    }
}
