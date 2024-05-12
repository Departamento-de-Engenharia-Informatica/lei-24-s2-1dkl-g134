package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;


import java.util.Optional;

public class RegisterJobController {

    private final JobRepository jobRepository;

    /**
     * Constructor for a new RegisterJobController object.
     * All this does is get the necessary repositories.
     */
    public RegisterJobController() {
        this.jobRepository = Repositories.getInstance().getJobRepository();

    }

    /**
     * Registers a new job.
     * If the job is equal to any other job in the repository, no job is added.
     * Check the documentation of Job.equals() for the criteria of such.
     * @param name The name of the job to be added.
     * @return If a job was added, the added job. Otherwise, an empty Optional object.
     */
    public Optional<Job> registerJob(String name) {
    return jobRepository.add(name);
    }
}
