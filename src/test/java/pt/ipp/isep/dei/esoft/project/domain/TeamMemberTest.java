package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamMemberTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Lifting");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        TeamMember teamMember = new TeamMember(collaborator, skills);
    }

    @Test
    void testHashCodeSameObject() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Lifting");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        TeamMember teamMember = new TeamMember(collaborator, skills);

        assertEquals(teamMember.hashCode(), teamMember.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Lifting");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        TeamMember teamMember = new TeamMember(collaborator, skills);

        Collaborator collaborator1 = new Collaborator("Emily", "2004/05/22", "2022/05/22", "Her house, duh",
                "960145802", "emily@is.real", "BI", "15017808", "256", job);
        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(skill1);

        TeamMember teamMember1 = new TeamMember(collaborator1, skills1);

        assertNotEquals(teamMember.hashCode(), teamMember1.hashCode());
    }

    @Test
    void testGetCollaborator(){
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Lifting");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        TeamMember teamMember = new TeamMember(collaborator, skills);

        assertEquals(collaborator, teamMember.getCollaborator());
    }

    @Test
    void testAttemptNextCombinationWorksWithOtherCombination(){
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Lifting");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        TeamMember teamMember = new TeamMember(collaborator, skills);

        assertTrue(teamMember.attemptNextCombination());
    }

    @Test
    void testAttemptNextCombinationFailsWithNoOtherCombination(){
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Skill skill1 = new Skill("Tree Pruner");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);

        TeamMember teamMember = new TeamMember(collaborator, skills);

        assertFalse(teamMember.attemptNextCombination());
    }

    @Test
    void testGetSkillsRemovedWorksWithPreviousCombination(){
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Lifting");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        TeamMember teamMember = new TeamMember(collaborator, skills);

        teamMember.attemptNextCombination();

        assertEquals(skills, teamMember.getSkillsRemoved());
    }

    @Test
    void testGetSkillsRemovedFailsWithNoPreviousCombination(){
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Lifting");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        TeamMember teamMember = new TeamMember(collaborator, skills);

        assertTrue(teamMember.getSkillsRemoved().isEmpty());
    }

    @Test
    void testGetCurrentlyUsedSkills(){
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);
        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Lifting");
        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);

        TeamMember teamMember = new TeamMember(collaborator, skills);

        assertEquals(skills, teamMember.getCurrentlyUsedSkills());
    }
}