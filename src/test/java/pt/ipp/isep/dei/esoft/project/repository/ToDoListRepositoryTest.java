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

    //The getPendingTasks() method is not being tested as it requires session information
    //that would not be available for unit tests.

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