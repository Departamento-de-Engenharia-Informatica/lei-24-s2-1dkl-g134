package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GreenSpaceRepositoryTest {

    @Test
    void ensureNewGreenSpaceSuccessfullyAdded() {
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
        assertTrue(greenSpaceRepository.add("Hello", "Somewhere", 50, GreenSpaceType.GARDEN).isPresent());
    }

    @Test
    void ensureAddingDuplicateGreenSpaceFails() {
        //Arrange
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
        greenSpaceRepository.add("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        //Act
        Optional<GreenSpace> duplicateGreenSpace = greenSpaceRepository.add("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        //Assert
        assertTrue(duplicateGreenSpace.isEmpty());
    }

    @Test
    void ensureAddingDifferentGreenSpacesWorks() {
        //Arrange
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
        greenSpaceRepository.add("Hello", "Somewhere", 50, GreenSpaceType.GARDEN);

        //Act
        Optional<GreenSpace> result = greenSpaceRepository.add("Bye", "Nowhere", 50, GreenSpaceType.GARDEN);

        //Assert
        assertTrue(result.isPresent());
    }
}