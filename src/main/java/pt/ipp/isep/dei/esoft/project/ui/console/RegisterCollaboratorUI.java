package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class RegisterCollaboratorUI {
    private String name;
    private String birthDate;
    private String admissionDate;
    private String address;
    private String phoneNumber;
    private String email;
    private String identificationDocumentType;
    private String identificationNumber;
    private Job job;
    private RegisterCollaboratorController registerCollaboratorController = new RegisterCollaboratorController();

    public void run(){
        System.out.println("\n >>>>>>>>>> REGISTER COLLABORATOR <<<<<<<<<< \n");

    }
    private void requestData(){
        name = requestName();
        birthDate = requestBirthDate();
        admissionDate = requestAdmissionDate();
        address = requestAddress();
        phoneNumber = requestPhoneNumber();
        email  = requestEmail();
        identificationDocumentType = requestIdentificationDocumentType();
        identificationNumber = requestIdentificationNumber();
        job = requestJob();
    }
    private void submitData(){
        Optional<Collaborator> newCollaborator = registerCollaboratorController.createCollaborator( name,  birthDate, admissionDate, address,
                phoneNumber,  email,  identificationDocumentType,
                identificationNumber,  job);
        if(newCollaborator.isEmpty()){
            System.out.println("Failed to add new collaborator!");
        }else{
            System.out.println("Collaborator added successfully!");
        }
    }

    private String requestName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        return input.nextLine();
    }
    private String requestBirthDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Birth Date: ");
        return input.nextLine();
    }

    private String requestAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Admission date: ");
        return input.nextLine();
    }
    private String requestAddress() {
        Scanner input = new Scanner(System.in);
        System.out.print("Address: ");
        return input.nextLine();
    }
    private String requestPhoneNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Phone Number: ");
        return input.nextLine();
    }
    private String requestEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("Email: ");
        return input.nextLine();
    }
    private String requestIdentificationDocumentType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Identification Document Type: ");
        return input.nextLine();
    }
    private String requestIdentificationNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Identification number: ");
        return input.nextLine();
    }

    private Job requestJob(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<Job>> jobs = registerCollaboratorController.getJobList();
        if(jobs.isEmpty()){
            System.out.println("Error: No jobs.");
            return null;
        }
        System.out.println("Choose a job from the following list:\n");
        for(int i = 0; i < jobs.get().size(); i++){
            System.out.println((i+1) + "- "+jobs.get().get(i).getName());
        }
        System.out.println("Choose a number corresponding to a job.");
        int option = 0;
        while(true){
            option = input.nextInt();
            if(option <= 0 || option > jobs.get().size()){
                System.out.println("Error: Invalid option.");
                continue;
            }
            break;
        }
        return jobs.get().get(option-1);
    }
}
