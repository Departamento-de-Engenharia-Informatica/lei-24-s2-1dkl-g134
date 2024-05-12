package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    void ensureJobIsCreatedSuccessfully() {
        Job Job = new Job("Job");
    }

    @Test
    void ensureJobNameIsNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Job(null));
    }

    @Test
    void testEqualsSameObject() {
        Job job = new Job("Job");

        assertEquals(job, job);
    }

    @Test
    void testEqualsDifferentClass() {
        Job job = new Job("Job");

        assertNotEquals(job, new Object());
    }

    @Test
    void testEqualsNull() {
        Job job = new Job("Job");

        assertNotEquals(job, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Job job = new Job("Job");
        Job job1 = new Job("JobA");

        assertNotEquals(job, job1);
    }

    @Test
    void testEqualsSameObjectSameDescription() {
        Job job = new Job("Job");
        Job job1 = new Job("Job");

        assertEquals(job, job1);
    }

    @Test
    void testHashCodeSameObject() {
        Job job = new Job("Job");

        assertEquals(job.hashCode(), job.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Job job = new Job("Job");
        Job job1 = new Job("JobA");

        assertNotEquals(job.hashCode(), job1.hashCode());
    }

    @Test
    void testGetName(){
        Job job = new Job("job");

        assertEquals("job", job.getName());
    }
}