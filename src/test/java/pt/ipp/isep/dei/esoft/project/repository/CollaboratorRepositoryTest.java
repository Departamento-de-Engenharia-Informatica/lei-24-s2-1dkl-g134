package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollaboratorRepositoryTest {

    /*
    ----------------------------------- NOTE -----------------------------------------
    The getCurrentUserCollaborator() method is not being tested as it requires session information
    that would be unavailable in a unit test format.
     */

    @Test
    void ensureNewCollaboratorSuccessfullyAdded() {
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Job job = JobRepository.add("Member").get();
        assertTrue(CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job).isPresent());
    }

    @Test
    void ensureGetCollaboratorListReturnsEmptyWithNoCollaborators() {
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        assertTrue(CollaboratorRepository.getCollaboratorList().isEmpty());
    }

    @Test
    void ensureGetCollaboratorListReturnsTheCorrectList() {
        //Arrange
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator1 = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job).get();
        int expectedSize = 1;

        //Act
        int size = CollaboratorRepository.getCollaboratorList().get().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(collaborator1, CollaboratorRepository.getCollaboratorList().get().get(size - 1));
    }

    /*@Test
    void ensureGetLatestCollaboratorOfVehicleReturnsEmptyWithNoCollaborators(){
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        Vehicle vehicle = new Vehicle("Ford", "T", 5000, 2500.0,750,
        "2010/04/25", "2009/04/25", 500, "H3LL0", "Car");
        assertTrue(CollaboratorRepository.getLatestCollaboratorOfVehicle(vehicle).isEmpty());
    }*/

    @Test
    void ensureAssignSkillsToCollaboratorSuccessful() {
        //Arrange
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job).get();

        SkillRepository SkillRepository = new SkillRepository();
        Skill skill1 = SkillRepository.add("Tree Pruner").get();
        Skill skill2 = SkillRepository.add("Light Vehicle Driver").get();
        Skill skill3 = SkillRepository.add("Lifting").get();
        Skill skill4 = SkillRepository.add("Gardening").get();

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);
        skills.add(skill4);

        //Assert
        assertEquals(skills, CollaboratorRepository.assignSkillsToCollaborator(collaborator, skills).get());
    }

    @Test
    void ensureAssignSkillsToCollaboratorReturnsOnlyAssignedSkills() {
        //Arrange
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job).get();

        SkillRepository SkillRepository = new SkillRepository();
        Skill skill1 = SkillRepository.add("Tree Pruner").get();
        Skill skill2 = SkillRepository.add("Light Vehicle Driver").get();
        Skill skill3 = SkillRepository.add("Lifting").get();
        Skill skill4 = SkillRepository.add("Gardening").get();

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        ArrayList<Skill> skills2 = new ArrayList<>();
        skills2.add(skill1);
        skills2.add(skill2);
        skills2.add(skill3);
        skills2.add(skill4);
        ArrayList<Skill> skills3 = new ArrayList<>();
        skills3.add(skill3);
        skills3.add(skill4);

        CollaboratorRepository.assignSkillsToCollaborator(collaborator, skills);

        //Assert
        assertEquals(skills3, CollaboratorRepository.assignSkillsToCollaborator(collaborator, skills2).get());
    }

    @Test
    void ensureAssignSkillsToCollaboratorReturnsEmptyWhenNoSkillsAssigned(){
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job).get();

        SkillRepository SkillRepository = new SkillRepository();
        Skill skill1 = SkillRepository.add("Tree Pruner").get();
        Skill skill2 = SkillRepository.add("Light Vehicle Driver").get();

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        ArrayList<Skill> skills2 = new ArrayList<>();
        skills2.add(skill1);
        skills2.add(skill2);

        CollaboratorRepository.assignSkillsToCollaborator(collaborator, skills);

        //Assert
        assertTrue(CollaboratorRepository.assignSkillsToCollaborator(collaborator, skills2).isEmpty());
    }

    @Test
    void ensureAddingInvalidCollaboratorFails() {
        //Arrange
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Job job = JobRepository.add("Member").get();
        CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job);

        //Act
        assertTrue(CollaboratorRepository.add("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
        "960144802", "emily@is.real", "CC", "15017807", "256", job).isEmpty());
    }

    @Test
    void ensureAddingValidCollaboratorWorks() {
        //Arrange
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Job job = JobRepository.add("Member").get();
        CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job);

        //Act
        Optional<Collaborator> result = CollaboratorRepository.add("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
        "960144802", "emily@is.real", "CC", "15017808", "256", job);

        //Assert
        assertTrue(result.isPresent());
    }

    @Test
    void ensureGenerateTeamEmptyWhenImpossible(){
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator1 = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job).get();
        Collaborator collaborator2 = CollaboratorRepository.add("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
        "960144802", "emily@is.real", "CC", "15017808", "256", job).get();
        Collaborator collaborator3 = CollaboratorRepository.add("Exas", "2005/05/22", "2023/05/22", "Their house, duh",
        "960144802", "exas@is.real", "CC", "15017809", "256", job).get();

        SkillRepository SkillRepository = new SkillRepository();
        Skill skill1 = SkillRepository.add("Tree Pruner").get();
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);

        CollaboratorRepository.assignSkillsToCollaborator(collaborator1, skills);
        CollaboratorRepository.assignSkillsToCollaborator(collaborator2, skills);
        CollaboratorRepository.assignSkillsToCollaborator(collaborator3, skills);

        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        teamSkills.add(skill1);
        teamSkills.add(skill1);
        teamSkills.add(skill1);

        assertTrue(CollaboratorRepository.generateTeamProposal(3, 4, teamSkills).isEmpty());
    }

    @Test
    void ensureGenerateTeamPresentWhenPossible(){
        CollaboratorRepository CollaboratorRepository = new CollaboratorRepository();
        JobRepository JobRepository = new JobRepository();
        Job job = JobRepository.add("Member").get();
        Collaborator collaborator1 = CollaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job).get();
        Collaborator collaborator2 = CollaboratorRepository.add("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
                "960144802", "emily@is.real", "CC", "15017808", "256", job).get();
        Collaborator collaborator3 = CollaboratorRepository.add("Exas", "2005/05/22", "2023/05/22", "Their house, duh",
                "960144802", "exas@is.real", "CC", "15017809", "256", job).get();

        SkillRepository SkillRepository = new SkillRepository();
        Skill skill1 = SkillRepository.add("Tree Pruner").get();
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);

        CollaboratorRepository.assignSkillsToCollaborator(collaborator1, skills);
        CollaboratorRepository.assignSkillsToCollaborator(collaborator2, skills);
        CollaboratorRepository.assignSkillsToCollaborator(collaborator3, skills);

        ArrayList<Skill> teamSkills = new ArrayList<>();
        teamSkills.add(skill1);
        teamSkills.add(skill1);
        teamSkills.add(skill1);

        assertTrue(CollaboratorRepository.generateTeamProposal(3, 4, teamSkills).isPresent());
    }
}