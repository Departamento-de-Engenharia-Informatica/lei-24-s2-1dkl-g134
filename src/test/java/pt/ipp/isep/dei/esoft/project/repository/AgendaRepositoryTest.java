package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaRepositoryTest {

    /* ------------------- WARNING -------------------------
    Some of these unit tests have hardcoded values that may or may not be permitted
    according to user settings in the bootstrap for the sake of simplicity.
    To ensure the correct functioning of all these unit tests, please set the workHoursStart
    variable in Bootstrap to 9 and the workHoursEnd variable to 17.
     */

    @Test
    void ensureTaskAddedSuccessfully(){
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        agendaRepository.add(taskEntry, "2005/01/20", "11:00");
    }

    @Test
    void ensureDuplicatesNotAllowed(){
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        agendaRepository.add(taskEntry, "2005/01/20", "11:00");

        assertTrue(agendaRepository.add(taskEntry, "2005/01/20", "11:00").isEmpty());
    }

    @Test
    void testPostponeTask(){
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskPending = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry = agendaRepository.add(taskPending, "2005/01/20", "11:00").get();

        agendaRepository.postponeTask(taskEntry, "2005/01/25", "16:00");

        CustomDate newStartDate = new CustomDate("2005/01/25");
        CustomTime newStartTime = new CustomTime("16:00");
        CustomDate newEndDate = newStartDate.adjust(taskEntry.getDuration()/ Bootstrap.dailyWorkHours);
        CustomTime newEndTime = newStartTime.adjust(taskEntry.getDuration());

        assertEquals(newStartDate, taskEntry.getStartDate());
        assertEquals(newStartTime, taskEntry.getStartTime());
        assertEquals(newEndDate, taskEntry.getEndDate());
        assertEquals(newEndTime, taskEntry.getEndTime());
        assertEquals(State.POSTPONED, taskEntry.getState());
    }

    @Test
    void ensurePostponeTaskFailsForPastDate(){
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskPending = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry = agendaRepository.add(taskPending, "2005/01/20", "11:00").get();

        assertThrows(IllegalArgumentException.class,
                () -> agendaRepository.postponeTask(taskEntry, "2005/01/10", "16:00"));
    }

    @Test
    void ensurePostponeTaskFailsForNullFields(){
        AgendaRepository agendaRepository = new AgendaRepository();

        assertThrows(IllegalArgumentException.class,
                () -> agendaRepository.postponeTask(null, null, null));
    }

    @Test
    void testAssignVehicles(){
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskPending = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry = agendaRepository.add(taskPending, "2005/01/20", "11:00").get();

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        assertThrows(IllegalArgumentException.class,
                () -> agendaRepository.assignVehiclesToTask(taskEntry, vehicles));
        assertThrows(IllegalArgumentException.class,
                () -> agendaRepository.assignVehiclesToTask(null, null));

        Vehicle vehicle1 = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "H3LL0", "Car");
        Vehicle vehicle2 = new Vehicle("Ford", "X", 5000, 2500.0,1000,
                "2010/04/25", "2009/04/25", 750, "G00DBY3", "Truck");
        Vehicle vehicle3 = new Vehicle("The USSR", "Stalin", 5000, 2500.0,1000,
                "2010/04/25", "2009/04/25", 1010, "CCCP", "Tank");

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        int expectedSize = 2;

        ArrayList<Vehicle> receivedVehicles = agendaRepository.assignVehiclesToTask(taskEntry, vehicles).get();

        assertEquals(expectedSize, receivedVehicles.size());
        assertEquals(vehicle1, receivedVehicles.get(0));
        assertEquals(vehicle2, receivedVehicles.get(1));

        vehicles.add(vehicle3);
        expectedSize = 1;

        receivedVehicles = agendaRepository.assignVehiclesToTask(taskEntry, vehicles).get();

        assertEquals(expectedSize, receivedVehicles.size());
        assertEquals(vehicle3, receivedVehicles.get(0));
    }

    @Test
    void testAssignTeam() throws IOException {
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskPending = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry = agendaRepository.add(taskPending, "2005/01/20", "11:00").get();

        Job job = new Job("Member");
        Collaborator collaborator1 = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator2 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        Skill skill = new Skill("Skill");
        ArrayList<Collaborator> collaborators = new ArrayList<>();
        ArrayList<Skill> skills = new ArrayList<>();
        collaborators.add(collaborator1);
        skills.add(skill);
        Team team = new Team(collaborators, skills);

        assertThrows(IllegalArgumentException.class,
                () -> agendaRepository.assignTeamToTask(null, null));

        agendaRepository.assignTeamToTask(taskEntry, team);

        int expectedSize = 1;
        ArrayList<Collaborator> assignedTeam = taskEntry.getAssignedTeam();
        assertEquals(expectedSize, assignedTeam.size());
        assertEquals(collaborator1, assignedTeam.get(0));

        collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);
        Team newTeam = new Team(collaborators, skills);

        agendaRepository.assignTeamToTask(taskEntry, newTeam);

        expectedSize = 2;
        assignedTeam = taskEntry.getAssignedTeam();
        assertEquals(expectedSize, assignedTeam.size());
        assertEquals(collaborator1, assignedTeam.get(0));
        assertEquals(collaborator2, assignedTeam.get(1));

        collaborators = new ArrayList<>();
        collaborators.add(collaborator2);
        collaborators.add(collaborator1);
        Team newerTeam = new Team(collaborators, skills);

        assertTrue(agendaRepository.assignTeamToTask(taskEntry, newerTeam).isEmpty());
    }

    @Test
    void testCancelTaskFailsWithNullTask(){
        AgendaRepository agendaRepository = new AgendaRepository();
        assertThrows(IllegalArgumentException.class,
                () -> agendaRepository.cancelTask(null));
    }

    @Test
    void testCompleteTaskFailsWithNullTask(){
        AgendaRepository agendaRepository = new AgendaRepository();
        assertThrows(IllegalArgumentException.class,
                () -> agendaRepository.completeTask(null));
    }

    @Test
    void testAllStateChanges(){
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskPending = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        assertEquals(State.PENDING, taskPending.getState());
        TaskEntry taskEntry = agendaRepository.add(taskPending, "2005/01/20", "11:00").get();

        assertEquals(State.PLANNED, taskEntry.getState());

        agendaRepository.postponeTask(taskEntry, "2005/01/25", "16:00");

        assertEquals(State.POSTPONED, taskEntry.getState());

        agendaRepository.cancelTask(taskEntry);

        assertEquals(State.CANCELED, taskEntry.getState());

        agendaRepository.completeTask(taskEntry);

        assertEquals(State.COMPLETED, taskEntry.getState());
    }

    @Test
    void testIsVehicleAvailable(){
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskPending1 = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry1 = agendaRepository.add(taskPending1, "2005/01/20", "11:00").get();
        TaskEntry taskPending2 = new TaskEntry("No", "No", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry2 = agendaRepository.add(taskPending2, "2004/12/10", "11:00").get();

        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "H3LL0", "Car");
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);
        agendaRepository.assignVehiclesToTask(taskEntry1, vehicles);

        assertTrue(agendaRepository.isVehicleAvailable(vehicle, taskEntry2));

        TaskEntry taskPending3 = new TaskEntry("Yes", "Yes", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry3 = agendaRepository.add(taskPending3, "2005/01/19", "11:00").get();

        assertFalse(agendaRepository.isVehicleAvailable(vehicle, taskEntry3));

        TaskEntry taskPending4 = new TaskEntry("Maybe", "Maybe", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry4 = agendaRepository.add(taskPending4, "2005/01/21", "11:00").get();

        assertFalse(agendaRepository.isVehicleAvailable(vehicle, taskEntry4));

        TaskEntry taskPending5 = new TaskEntry("Perhaps", "Perhaps", urgencyLevel.MEDIUM, 3, greenSpace);
        TaskEntry taskEntry5 = agendaRepository.add(taskPending5, "2006/01/21", "11:00").get();

        assertTrue(agendaRepository.isVehicleAvailable(vehicle, taskEntry5));

        agendaRepository.assignVehiclesToTask(taskEntry5, vehicles);

        TaskEntry taskPending6 = new TaskEntry("Stop it", "Just stop", urgencyLevel.MEDIUM, 1, greenSpace);
        TaskEntry taskEntry6 = agendaRepository.add(taskPending6, "2006/01/21", "09:00").get();

        assertTrue(agendaRepository.isVehicleAvailable(vehicle, taskEntry6));

        TaskEntry taskPending7 = new TaskEntry("I hate", "Unit tests", urgencyLevel.MEDIUM, 1, greenSpace);
        TaskEntry taskEntry7 = agendaRepository.add(taskPending7, "2006/01/21", "15:00").get();

        assertTrue(agendaRepository.isVehicleAvailable(vehicle, taskEntry7));

        TaskEntry taskPending8 = new TaskEntry("I want them", "to die", urgencyLevel.MEDIUM, 1, greenSpace);
        TaskEntry taskEntry8 = agendaRepository.add(taskPending8, "2006/01/21", "12:00").get();

        assertFalse(agendaRepository.isVehicleAvailable(vehicle, taskEntry8));

        TaskEntry taskPending9 = new TaskEntry("Why did I make", "that field unique", urgencyLevel.MEDIUM, 3, greenSpace);
        TaskEntry taskEntry9 = agendaRepository.add(taskPending9, "2006/01/21", "11:00").get();

        assertFalse(agendaRepository.isVehicleAvailable(vehicle, taskEntry9));

        TaskEntry taskPending10 = new TaskEntry("The grand", "finale", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry10 = agendaRepository.add(taskPending10, "2005/01/20", "11:00").get();

        assertFalse(agendaRepository.isVehicleAvailable(vehicle, taskEntry10));
    }

    @Test
    void ensureIsVehicleAvailableFailsWithNullFields(){
        AgendaRepository agendaRepository = new AgendaRepository();
        assertThrows(IllegalArgumentException.class, () -> {
            agendaRepository.isVehicleAvailable(null, null);
        });
    }

    @Test
    void testIsTeamAvailable() throws IOException {
        AgendaRepository agendaRepository = new AgendaRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskPending1 = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry1 = agendaRepository.add(taskPending1, "2005/01/20", "11:00").get();
        TaskEntry taskPending2 = new TaskEntry("No", "No", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry2 = agendaRepository.add(taskPending2, "2004/12/10", "11:00").get();

        Job job = new Job("Member");
        Collaborator collaborator1 = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator2 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        Skill skill = new Skill("Skill");
        ArrayList<Collaborator> collaborators = new ArrayList<>();
        ArrayList<Skill> skills = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);
        skills.add(skill);
        Team team = new Team(collaborators, skills);
        agendaRepository.assignTeamToTask(taskEntry1, team);

        assertTrue(agendaRepository.isTeamAvailable(team, taskEntry2));

        TaskEntry taskPending3 = new TaskEntry("Yes", "Yes", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry3 = agendaRepository.add(taskPending3, "2005/01/19", "11:00").get();

        assertFalse(agendaRepository.isTeamAvailable(team, taskEntry3));

        TaskEntry taskPending4 = new TaskEntry("Maybe", "Maybe", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry4 = agendaRepository.add(taskPending4, "2005/01/21", "11:00").get();

        assertFalse(agendaRepository.isTeamAvailable(team, taskEntry4));

        TaskEntry taskPending5 = new TaskEntry("Perhaps", "Perhaps", urgencyLevel.MEDIUM, 3, greenSpace);
        TaskEntry taskEntry5 = agendaRepository.add(taskPending5, "2006/01/21", "11:00").get();

        assertTrue(agendaRepository.isTeamAvailable(team, taskEntry5));

        agendaRepository.assignTeamToTask(taskEntry5, team);

        TaskEntry taskPending6 = new TaskEntry("Stop it", "Just stop", urgencyLevel.MEDIUM, 1, greenSpace);
        TaskEntry taskEntry6 = agendaRepository.add(taskPending6, "2006/01/21", "09:00").get();

        assertTrue(agendaRepository.isTeamAvailable(team, taskEntry6));

        TaskEntry taskPending7 = new TaskEntry("I hate", "Unit tests", urgencyLevel.MEDIUM, 1, greenSpace);
        TaskEntry taskEntry7 = agendaRepository.add(taskPending7, "2006/01/21", "15:00").get();

        assertTrue(agendaRepository.isTeamAvailable(team, taskEntry7));

        TaskEntry taskPending8 = new TaskEntry("I want them", "to die", urgencyLevel.MEDIUM, 1, greenSpace);
        TaskEntry taskEntry8 = agendaRepository.add(taskPending8, "2006/01/21", "12:00").get();

        assertFalse(agendaRepository.isTeamAvailable(team, taskEntry8));

        TaskEntry taskPending9 = new TaskEntry("Why did I make", "that field unique", urgencyLevel.MEDIUM, 3, greenSpace);
        TaskEntry taskEntry9 = agendaRepository.add(taskPending9, "2006/01/21", "11:00").get();

        assertFalse(agendaRepository.isTeamAvailable(team, taskEntry9));

        TaskEntry taskPending10 = new TaskEntry("The grand", "finale", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry10 = agendaRepository.add(taskPending10, "2005/01/20", "11:00").get();

        assertFalse(agendaRepository.isTeamAvailable(team, taskEntry10));
    }

    @Test
    void ensureIsTeamAvailableFailsWithNullFields(){
        AgendaRepository agendaRepository = new AgendaRepository();
        assertThrows(IllegalArgumentException.class, () -> {
            agendaRepository.isTeamAvailable(null, null);
        });
    }
}
