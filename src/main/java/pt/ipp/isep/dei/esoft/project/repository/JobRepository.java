package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class JobRepository implements Serializable {
    private final ArrayList<Job> job;

    /**
     * Constructor for a new job repository.
     * All this does is initialize the list of jobs.
     */
    public JobRepository() {
        this.job = new ArrayList<>();
    }

    /**
     * Adds a new job to this repository.
     * If the job is equal to any other job in the repository, no job is added.
     * Check the documentation of Job.equals() for the criteria of such.
     * @param name The name of the job to be added.
     * @return If a job was added, the added job. Otherwise, an empty Optional object.
     */
    public Optional<Job> add(String name) {
        Job s = new Job(name);
        if(job.contains(s)){
            return Optional.empty();
        }
        job.add(s);
        return Optional.of(s);
    }

    /**
     * Returns the list of all jobs in the repository.
     * @return The list of all jobs in the repository
     */
    public Optional<ArrayList<Job>> getJobList(){
        if(job.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(job);
    }
}
