package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesTest {

    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }

    @Test
    void testGetOrganizationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getOrganizationRepository());
    }

    @Test
    void testGetTaskCategoryRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getTaskCategoryRepository());
    }

    @Test
    void testGetCheckupRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getCheckupRepository());
    }

    @Test
    void testGetCollaboratorRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getCollaboratorRepository());
    }

    @Test
    void testGetJobRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getJobRepository());
    }

    @Test
    void testGetSkillRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getSkillRepository());
    }

    @Test
    void testGetTeamRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getTeamRepository());
    }

    @Test
    void testGetVehicleRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getVehicleRepository());
    }
}