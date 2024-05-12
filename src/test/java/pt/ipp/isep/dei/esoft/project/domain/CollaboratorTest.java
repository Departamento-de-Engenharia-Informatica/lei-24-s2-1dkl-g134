package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    @Test
    void ensureCollaboratorIsCreatedSuccessfully() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job);
    }

    @Test
    void ensureCollaboratorParamsAreNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator(null, null, null, null,
                null, null, null, null, null, null));
    }

    @Test
    void ensureCollaboratorIsNotMinor() {
        Job job = new Job("Member");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("Roger", "2020/05/22", "2023/05/22", "His house, duh",
                        "960144802", "roger@is.real", "CC", "15017807", "256", job));
    }

    @Test
    void ensureCollaboratorNotAdmittedAsMinor() {
        Job job = new Job("Member");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("Roger", "2005/05/22", "2015/05/22", "His house, duh",
                        "960144802", "roger@is.real", "CC", "15017807", "256", job));
    }

    @Test
    void ensureCollaboratorNotAdmittedInTheFuture() {
        Job job = new Job("Member");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("Roger", "2005/05/22", "9999/05/22", "His house, duh",
                        "960144802", "roger@is.real", "CC", "15017807", "256", job));
    }

    @Test
    void ensureCollaboratorPhoneNumberOnlyContainsDigits() {
        Job job = new Job("Member");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                        "960HELLO2", "roger@is.real", "CC", "15017807", "256", job));
    }

    @Test
    void ensureCollaboratorPhoneNumberAcceptsCallingCode() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "+351960144802", "roger@is.real", "CC", "15017807", "256", job);
    }

    @Test
    void ensureCollaboratorPhoneNumberHasValidLengthWithoutCallingCode() {
        Job job = new Job("Member");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                        "96099999992", "roger@is.real", "CC", "15017807", "256", job));
    }

    @Test
    void ensureCollaboratorPhoneNumberHasValidLengthWithCallingCode() {
        Job job = new Job("Member");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                        "+33351960144802", "roger@is.real", "CC", "15017807", "256", job));
    }

    @Test
    void ensureCollaboratorEmailValid() {
        Job job = new Job("Member");
        assertThrows(IllegalArgumentException.class,
                () -> new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                        "960144802", "roger.is.real", "CC", "15017807", "256", job));
    }

    @Test
    void testEqualsSameObject() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job);

        assertEquals(collaborator, collaborator);
    }

    @Test
    void testEqualsDifferentClass() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);

        assertNotEquals(collaborator, new Object());
    }

    @Test
    void testEqualsNull() {
        Job job = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job);

        assertNotEquals(collaborator, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Job job1 = new Job("Member");
        Job job2 = new Job("MemberA");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job1);
        Collaborator collaborator1 = new Collaborator("Emily", "2004/05/22", "2022/05/22", "Her house, duh",
        "960145802", "emily@is.real", "BI", "15017808", "256", job2);

        assertNotEquals(collaborator, collaborator1);
    }

    @Test
    void testEqualsSameObjectSameIDNumber() {
        Job job1 = new Job("Member");
        Job job2 = new Job("MemberA");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job1);
        Collaborator collaborator1 = new Collaborator("Emily", "2004/05/22", "2022/05/22", "Her house, duh",
                "960145802", "emily@is.real", "BI", "15017807", "256", job2);

        assertEquals(collaborator, collaborator1);
    }

    @Test
    void testHashCodeSameObject() {
        Job job1 = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job1);

        assertEquals(collaborator.hashCode(), collaborator.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Job job1 = new Job("Member");
        Job job2 = new Job("MemberA");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job1);
        Collaborator collaborator1 = new Collaborator("Emily", "2004/05/22", "2022/05/22", "Her house, duh",
                "960145802", "emily@is.real", "BI", "15017807", "256", job2);

        assertNotEquals(collaborator.hashCode(), collaborator1.hashCode());
    }

    @Test
    void testGetName(){
        Job job1 = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job1);

        assertEquals("Roger", collaborator.getName());
    }

    @Test
    void testToString(){
        Job job1 = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job1);

        assertEquals("Roger | 15017807", collaborator.toString());
    }

    @Test
    void testGetIDNumber(){
        Job job1 = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job1);

        assertEquals("15017807", collaborator.getIdentificationNumber());
    }

    @Test
    void ensureAssignSkillsToCollaboratorSuccessful() {
        Job job1 = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job1);

        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Light Vehicle Driver");
        Skill skill3 = new Skill("Lifting");
        Skill skill4 = new Skill("Gardening");

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        skills.add(skill3);
        skills.add(skill4);

        assertEquals(skills, collaborator.assignSkills(skills).get());
    }

    @Test
    void ensureAssignSkillsToCollaboratorReturnsOnlyAssignedSkills() {
        Job job1 = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job1);

        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Light Vehicle Driver");
        Skill skill3 = new Skill("Lifting");
        Skill skill4 = new Skill("Gardening");

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

        collaborator.assignSkills(skills);

        assertEquals(skills3, collaborator.assignSkills(skills2).get());
    }

    @Test
    void ensureAssignSkillsToCollaboratorReturnsEmptyWhenNoSkillsAssigned(){
        Job job1 = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job1);

        Skill skill1 = new Skill("Tree Pruner");
        Skill skill2 = new Skill("Light Vehicle Driver");

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);
        skills.add(skill2);
        ArrayList<Skill> skills2 = new ArrayList<>();
        skills2.add(skill1);
        skills2.add(skill2);

        collaborator.assignSkills(skills);

        assertTrue(collaborator.assignSkills(skills2).isEmpty());
    }

    @Test
    void ensureHasSkillWorksWithSkill(){
        Job job1 = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job1);

        Skill skill1 = new Skill("Tree Pruner");

        ArrayList<Skill> skills = new ArrayList<>();
        skills.add(skill1);

        collaborator.assignSkills(skills);

        assertTrue(collaborator.hasSkill(skill1));
    }

    @Test
    void ensureHasSkillFailsWithoutSkill(){
        Job job1 = new Job("Member");
        Collaborator collaborator = new Collaborator("Roger", "2005/05/22", "2023/05/22", "His house, duh",
                "960144802", "roger@is.real", "CC", "15017807", "256", job1);

        Skill skill1 = new Skill("Tree Pruner");

        assertFalse(collaborator.hasSkill(skill1));
    }
}