package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.urgencyLevel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToDoListRepositoryTest {

    @Test
    void ensureNewToDoListSuccessfullyAdded() {
        ToDoListRepository ToDoListRepository = new ToDoListRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        assertTrue(ToDoListRepository.add("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace).isPresent());
    }

    @Test
    void ensureGetPendingTasksReturnsEmptyWithNoToDoLists() {
        ToDoListRepository ToDoListRepository = new ToDoListRepository();
        assertTrue(ToDoListRepository.getPendingTasks().isEmpty());
    }

    @Test
    void ensureGetPendingTasksReturnsTheCorrectList() {
        //Arrange
        ToDoListRepository ToDoListRepository = new ToDoListRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        TaskEntry taskEntry = new TaskEntry("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        TaskEntry otherTaskEntry = new TaskEntry("Yes", "No", urgencyLevel.LOW, 25, greenSpace);
        ToDoListRepository.add("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);
        ToDoListRepository.add("Yes", "No", urgencyLevel.LOW, 25, greenSpace);
        int expectedSize = 2;

        //Act
        int size = ToDoListRepository.getPendingTasks().get().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(taskEntry, ToDoListRepository.getPendingTasks().get().get(size - 2));
        assertEquals(otherTaskEntry, ToDoListRepository.getPendingTasks().get().get(size - 1));

        AgendaRepository agendaRepository = new AgendaRepository();
        agendaRepository.add(ToDoListRepository.getPendingTasks().get().get(size-1), "2024/05/27", "16:00");

        size = ToDoListRepository.getPendingTasks().get().size();

        assertEquals(1, size);
        assertEquals(taskEntry, ToDoListRepository.getPendingTasks().get().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateTaskFails() {
        //Arrange

        ToDoListRepository ToDoListRepository = new ToDoListRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        //Add the first task
        ToDoListRepository.add("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        //Act
        Optional<TaskEntry> duplicateToDoList = ToDoListRepository.add("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        //Assert
        assertTrue(duplicateToDoList.isEmpty());
    }

    @Test
    void ensureAddingDifferentTasksWorks() {
        //Arrange
        ToDoListRepository ToDoListRepository = new ToDoListRepository();
        GreenSpace greenSpace = new GreenSpace("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);
        //Add the first task
        ToDoListRepository.add("Test", "Test", urgencyLevel.MEDIUM, 50, greenSpace);

        //Act
        Optional<TaskEntry> result = ToDoListRepository.add("Yes", "No", urgencyLevel.LOW, 25, greenSpace);

        //Assert
        assertTrue(result.isPresent());
    }
}