package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @Test
    void ensureSkillIsCreatedSuccessfully() {
        Skill skill = new Skill("skill");
    }

    @Test
    void ensureSkillNameIsNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Skill(null));
    }

    @Test
    void testEqualsSameObject() {
        Skill skill = new Skill("skill");

        assertEquals(skill, skill);
    }

    @Test
    void testEqualsDifferentClass() {
        Skill skill = new Skill("skill");

        assertNotEquals(skill, new Object());
    }

    @Test
    void testEqualsNull() {
        Skill skill = new Skill("skill");

        assertNotEquals(skill, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Skill skill = new Skill("skill");
        Skill skill1 = new Skill("skillA");

        assertNotEquals(skill, skill1);
    }

    @Test
    void testEqualsSameObjectSameDescription() {
        Skill skill = new Skill("skill");
        Skill skill1 = new Skill("skill");

        assertEquals(skill, skill1);
    }

    @Test
    void testHashCodeSameObject() {
        Skill skill = new Skill("skill");

        assertEquals(skill.hashCode(), skill.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Skill skill = new Skill("skill");
        Skill skill1 = new Skill("skillA");

        assertNotEquals(skill.hashCode(), skill1.hashCode());
    }

    @Test
    void testGetName(){
        Skill skill = new Skill("skill");

        assertEquals("skill", skill.getName());
    }
}