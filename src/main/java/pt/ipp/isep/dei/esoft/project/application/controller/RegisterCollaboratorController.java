package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Optional;

public class RegisterCollaboratorController {
    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;

    public RegisterCollaboratorController(){
        getCollaboratorRepository();
        getJobRepository();
    }

    private void getCollaboratorRepository(){
        Repositories repositories = Repositories.getInstance();
        collaboratorRepository = repositories.getCollaboratorRepository();
    }
    private void getJobRepository(){
        Repositories repositories = Repositories.getInstance();
        jobRepository = repositories.getJobRepository();
    }

    public Optional<Collaborator> createCollaborator(String name, String birthDate,String admissionDate,String address,
                                                     String phoneNumber, String email, String identificationDocumentType,
                                                     String identificationNumber, String taxpayerNumber, Job job){
        return collaboratorRepository.add( name,  birthDate, admissionDate, address,
                 phoneNumber,  email,  identificationDocumentType,
                 identificationNumber, taxpayerNumber, job);

    }
    public Optional<ArrayList<Job>> getJobList(){
        return jobRepository.getJobList();
    }
}
