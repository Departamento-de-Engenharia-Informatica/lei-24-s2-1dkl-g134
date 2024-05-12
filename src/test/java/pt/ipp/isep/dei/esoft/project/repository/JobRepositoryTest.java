package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JobRepositoryTest {

    @Test
    void ensureNewJobSuccessfullyAdded() {
        JobRepository JobRepository = new JobRepository();
        String JobDescription = "Job";
        assertTrue(JobRepository.add(JobDescription).isPresent());
    }

    @Test
    void ensureGetJobListReturnsEmptyWithNoJobs() {
        JobRepository JobRepository = new JobRepository();
        assertTrue(JobRepository.getJobList().isEmpty());
    }

    @Test
    void ensureGetJobListReturnsTheCorrectList() {
        //Arrange
        JobRepository JobRepository = new JobRepository();
        Job Job = new Job("yes");
        JobRepository.add("yes");
        int expectedSize = 1;

        //Act
        int size = JobRepository.getJobList().get().size();

        //Assert
        assertEquals(expectedSize, size);
        assertEquals(Job, JobRepository.getJobList().get().get(size - 1));
    }

    @Test
    void ensureAddingDuplicateJobFails() {
        //Arrange
        JobRepository JobRepository = new JobRepository();
        //Add the first task
        JobRepository.add("Job");

        //Act
        Optional<Job> duplicateJob = JobRepository.add("Job");

        //Assert
        assertTrue(duplicateJob.isEmpty());
    }

    @Test
    void ensureAddingDifferentJobsWorks() {
        //Arrange
        JobRepository JobRepository = new JobRepository();
        //Add the first task
        JobRepository.add("yes");

        //Act
        Optional<Job> result = JobRepository.add("no");

        //Assert
        assertTrue(result.isPresent());
    }
}