package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;


import java.util.Optional;

public class RegisterJobController {

    private final JobRepository jobRepository;


    public RegisterJobController() {
        this.jobRepository = Repositories.getInstance().getJobRepository();

    }

    public Optional<Job> registerSkill(String name) {
        return jobRepository.add(name);
    }

    public Optional<Job> registerJob(String name) {
    return Optional.empty();
    }
}
