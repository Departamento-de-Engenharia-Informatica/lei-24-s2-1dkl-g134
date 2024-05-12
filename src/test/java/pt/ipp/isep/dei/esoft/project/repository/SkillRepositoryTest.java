package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SkillRepositoryTest {

    @Test
    void ensureNewSkillSuccessfullyAdded() {
        SkillRepository SkillRepository = new SkillRepository();
        String SkillDescription = "skill";
        assertTrue(SkillRepository.add(SkillDescription).isPresent());
    }

    @Test
    void ensureGetSkillListReturnsEmptyWithNoSkills() {
        SkillRepository SkillRepository = new SkillRepository();
        assertTrue(SkillRepository.getSkillList().isEmpty());
    }

    @Test
    void ensureGetSkillListReturnsTheCorrectList() {
        //Arrange
        SkillRepository SkillRepository = new SkillRepository();
        Skill skill = new Skill("yes");
        SkillRepository.add("yes");
        int expectedSize = 1;

        //Act
        int size = SkillRepository.getSkillList().get().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(skill, SkillRepository.getSkillList().get().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateSkillFails() {
        //Arrange
        SkillRepository SkillRepository = new SkillRepository();
        //Add the first task
        SkillRepository.add("skill");

        //Act
        Optional<Skill> duplicateSkill = SkillRepository.add("skill");

        //Assert
        assertTrue(duplicateSkill.isEmpty());
    }

    @Test
    void ensureAddingDifferentSkillsWorks() {
        //Arrange
        SkillRepository SkillRepository = new SkillRepository();
        //Add the first task
        SkillRepository.add("yes");

        //Act
        Optional<Skill> result = SkillRepository.add("no");

        //Assert
        assertTrue(result.isPresent());
    }
}