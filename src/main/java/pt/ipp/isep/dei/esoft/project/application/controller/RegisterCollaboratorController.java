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

    /**
     * Constructor for a new RegisterCollaboratorController object.
     * All this does is get the necessary repositories.
     */
    public RegisterCollaboratorController(){
        collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        jobRepository = Repositories.getInstance().getJobRepository();
    }

    /**
     * Registers a new collaborator.
     * If the collaborator is equal to any other collaborator in the repository, no collaborator is added.
     * Check the documentation of collaborator.equals() for the criteria of such.
     * @param name A String representing the collaborator's name
     * @param birthDate A String representing the collaborator's birthdate.
     * @param admissionDate A String representing the collaborator's admission date.
     * @param address A String representing the collaborator's address.
     * @param phoneNumber A String representing the collaborator's phone number.
     * @param email A String representing the collaborator's email.
     * @param identificationDocumentType A String representing the collaborator's ID document type.
     * @param identificationNumber A String representing the collaborator's ID number.
     * @param taxpayerNumber A String representing the collaborator's taxpayer number.
     * @param job A Job object representing the collaborator's job.
     * @return If a collaborator was added, the added collaborator. Otherwise, an empty Optional object.
     */
    public Optional<Collaborator> createCollaborator(String name, String birthDate,String admissionDate,String address,
                                                     String phoneNumber, String email, String identificationDocumentType,
                                                     String identificationNumber, String taxpayerNumber, Job job){
        return collaboratorRepository.add( name,  birthDate, admissionDate, address,
                 phoneNumber,  email,  identificationDocumentType,
                 identificationNumber, taxpayerNumber, job);

    }

    /**
     * Gets the full list of jobs.
     * @return The list of all jobs.
     */
    public Optional<ArrayList<Job>> getJobList(){
        return jobRepository.getJobList();
    }
}
