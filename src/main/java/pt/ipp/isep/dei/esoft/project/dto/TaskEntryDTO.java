package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;

public class TaskEntryDTO {
    public String taskTitle;
    public String taskDescription;
    public pt.ipp.isep.dei.esoft.project.domain.urgencyLevel urgencyLevel;
    public State state;
    public int duration;
    public GreenSpace greenSpace;
    public String greenSpaceDomainReturn;
    public ArrayList<Vehicle> assignedVehicles = null;
    public ArrayList<Collaborator> assignedTeam = null;
    public Team team = null;
    public CustomDate startDate = null, endDate = null;
    public CustomTime startTime = null, endTime = null;
    public String startDateStringForm = null, startTimeStringForm = null;
    public TaskEntry attachedTaskEntry = null;
}
