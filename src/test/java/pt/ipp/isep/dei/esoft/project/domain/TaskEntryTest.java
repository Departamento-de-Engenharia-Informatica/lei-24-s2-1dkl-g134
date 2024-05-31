package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskEntryTest {
    @Test
    void ensureTaskEntrySuccessfulyCreated(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
    }

    @Test
    void ensureNoNullFieldsAllowed(){
        assertThrows(IllegalArgumentException.class,
        () -> new TaskEntry(null, null, null, 50, null));
    }

    @Test
    void ensureDurationGreaterThanZero(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        assertThrows(IllegalArgumentException.class,
                () -> new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 0, greenSpace));
    }

    @Test
    void ensureAgendaDataSuccessfullyAdded(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");
    }

    @Test
    void ensureAgendaNoNullFieldsAllowed(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.addAgendaData(null, null));
    }

    @Test
    void testEqualsSameObject() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertEquals(taskEntry, taskEntry);
    }

    @Test
    void testEqualsDifferentClass() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertNotEquals(taskEntry, new Object());
    }

    @Test
    void testEqualsNull() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertNotEquals(taskEntry, null);
    }

    @Test
    void testEqualsDifferentObject() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry1 = new TaskEntry("No", "Yes", urgencyLevel.LOW, 55, greenSpace);

        assertNotEquals(taskEntry, taskEntry1);
    }

    @Test
    void testEqualsSameTitle() {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry taskEntry1 = new TaskEntry("Test", "Yes", urgencyLevel.LOW, 55, greenSpace);

        assertEquals(taskEntry, taskEntry1);
    }

    @Test
    void testToString(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertEquals("Test | Test", taskEntry.toString());
    }



    @Test
    void testGetTaskTitle(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertEquals("Test", taskEntry.getTaskTitle());
    }

    @Test
    void testGetTaskDescription(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertEquals("Test", taskEntry.getTaskDescription());
    }

    @Test
    void testGetUrgencyLevel(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertEquals(urgencyLevel.MEDIUM, taskEntry.getUrgencyLevel());
    }

    @Test
    void testGetGreenSpace(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertEquals(greenSpace.toString(), taskEntry.getGreenSpace());
    }

    @Test
    void testGetDuration(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertEquals(50, taskEntry.getDuration());
    }

    @Test
    void testGetDatesAndTimes(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        CustomDate startDate = new CustomDate("2005/01/20");
        CustomTime startTime = new CustomTime("11:00");
        CustomDate endDate = startDate.adjust(taskEntry.getDuration()/ Bootstrap.dailyWorkHours);
        CustomTime endTime = startTime.adjust(taskEntry.getDuration());

        assertEquals(startDate, taskEntry.getStartDate());
        assertEquals(startTime, taskEntry.getStartTime());
        assertEquals(endDate, taskEntry.getEndDate());
        assertEquals(endTime, taskEntry.getEndTime());
    }

    @Test
    void testPostponeTask(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        taskEntry.postponeTask("2005/01/25", "16:00");

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
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        assertThrows(IllegalArgumentException.class,
        () -> taskEntry.postponeTask("2005/01/10", "16:00"));
    }

    @Test
    void ensurePostponeTaskFailsForNullFields(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.postponeTask(null, null));
    }

    @Test
    void ensurePostponeTaskFailsForPendingTasks(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.postponeTask("2005/01/20", "16:00"));
    }

    @Test
    void testAssignVehicles(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.assignVehicles(vehicles));
        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.assignVehicles(null));

        Vehicle vehicle1 = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-DL", "Car");
        Vehicle vehicle2 = new Vehicle("Ford", "X", 5000, 2500.0,1000,
                "2010/04/25", "2009/04/25", 750, "44-51-LL", "Truck");
        Vehicle vehicle3 = new Vehicle("The USSR", "Stalin", 5000, 2500.0,1000,
                "2010/04/25", "2009/04/25", 1010, "40-55-SS", "Tank");

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        int expectedSize = 2;

        ArrayList<Vehicle> receivedVehicles = taskEntry.assignVehicles(vehicles).get();

        assertEquals(expectedSize, receivedVehicles.size());
        assertEquals(vehicle1, receivedVehicles.get(0));
        assertEquals(vehicle2, receivedVehicles.get(1));

        vehicles.add(vehicle3);
        expectedSize = 1;

        receivedVehicles = taskEntry.assignVehicles(vehicles).get();

        assertEquals(expectedSize, receivedVehicles.size());
        assertEquals(vehicle3, receivedVehicles.get(0));
    }

    @Test
    void testHasVehicle(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.hasVehicle(null));

        Vehicle vehicle1 = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        Vehicle vehicle2 = new Vehicle("Ford", "X", 5000, 2500.0,1000,
                "2010/04/25", "2009/04/25", 750, "46-51-DL", "Truck");

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        taskEntry.assignVehicles(vehicles);

        assertTrue(taskEntry.hasVehicle(vehicle1));
        assertTrue(taskEntry.hasVehicle(vehicle2));
    }

    @Test
    void testGetAssignedVehicles(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        assertTrue(taskEntry.getAssignedVehicles().isEmpty());

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.assignVehicles(vehicles));

        Vehicle vehicle1 = new Vehicle("Ford", "T", 5000, 2500.0,3000,
                "2010/04/25", "2009/04/25", 500, "45-50-LL", "Car");
        Vehicle vehicle2 = new Vehicle("Ford", "X", 5000, 2500.0,1000,
                "2010/04/25", "2009/04/25", 750, "46-51-DL", "Truck");

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        taskEntry.assignVehicles(vehicles);
        int expectedSize = 2;
        ArrayList<Vehicle> assignedVehicles = taskEntry.getAssignedVehicles();

        assertEquals(expectedSize, assignedVehicles.size());
        assertEquals(vehicle1, assignedVehicles.get(0));
        assertEquals(vehicle2, assignedVehicles.get(1));
    }

    @Test
    void testAssignTeamAndGetAssignedTeam() throws IOException {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        assertNull(taskEntry.getAssignedTeam());

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
                () -> taskEntry.assignTeam(null));

        taskEntry.assignTeam(team);

        int expectedSize = 1;
        ArrayList<Collaborator> assignedTeam = taskEntry.getAssignedTeam();
        assertEquals(expectedSize, assignedTeam.size());
        assertEquals(collaborator1, assignedTeam.get(0));

        collaborators = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);
        Team newTeam = new Team(collaborators, skills);

        taskEntry.assignTeam(newTeam);

        expectedSize = 2;
        assignedTeam = taskEntry.getAssignedTeam();
        assertEquals(expectedSize, assignedTeam.size());
        assertEquals(collaborator1, assignedTeam.get(0));
        assertEquals(collaborator2, assignedTeam.get(1));

        collaborators = new ArrayList<>();
        collaborators.add(collaborator2);
        collaborators.add(collaborator1);
        Team newerTeam = new Team(collaborators, skills);

        assertTrue(taskEntry.assignTeam(newerTeam).isEmpty());
    }

    @Test
    void testIsSameTeam() throws IOException {
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        taskEntry.addAgendaData("2005/01/20", "11:00");

        assertNull(taskEntry.getAssignedTeam());
        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.isSameTeam(null));

        Job job = new Job("Member");
        Collaborator collaborator1 = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Collaborator collaborator2 = new Collaborator("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job);
        Skill skill = new Skill("Skill");
        ArrayList<Collaborator> collaborators = new ArrayList<>();
        ArrayList<Collaborator> finalCollaborators = collaborators;
        assertThrows(IllegalArgumentException.class,
                () -> taskEntry.isSameTeam(finalCollaborators));
        ArrayList<Skill> skills = new ArrayList<>();
        collaborators.add(collaborator1);
        collaborators.add(collaborator2);
        skills.add(skill);
        Team team = new Team(collaborators, skills);

        assertFalse(taskEntry.isSameTeam(team.getTeamMembers()));

        taskEntry.assignTeam(team);

        assertTrue(taskEntry.isSameTeam(collaborators));
        collaborators = new ArrayList<>();
        collaborators.add(collaborator2);
        assertFalse(taskEntry.isSameTeam(collaborators));
    }

    @Test
    void testAllStateChanges(){
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        assertEquals(State.PENDING, taskEntry.getState());

        taskEntry.addAgendaData("2005/01/20", "11:00");

        assertEquals(State.PLANNED, taskEntry.getState());

        taskEntry.postponeTask("2005/01/25", "16:00");

        assertEquals(State.POSTPONED, taskEntry.getState());

        taskEntry.cancelTask();

        assertEquals(State.CANCELED, taskEntry.getState());

        taskEntry.completeTask();

        assertEquals(State.COMPLETED, taskEntry.getState());
    }
}
