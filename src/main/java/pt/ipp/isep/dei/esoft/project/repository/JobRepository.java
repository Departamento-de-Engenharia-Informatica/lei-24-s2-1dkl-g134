package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.Optional;

public class JobRepository {
    private final ArrayList<Job> job;

    public JobRepository() {
        this.job = new ArrayList<>();
    }

    public Optional<Job> add(String name) {
        Job s = new Job(name);
        if(job.contains(s)){
            return Optional.empty();
        }
        job.add(s);
        return Optional.of(s);
    }

    public Optional<ArrayList<Job>> getJobList(){
        if(job.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(job);
    }
}
