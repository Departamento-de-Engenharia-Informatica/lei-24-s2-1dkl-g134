package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.CustomDate;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class RegisterCollaboratorUI implements Runnable {
    private String name;
    private String birthDate;
    private String admissionDate;
    private String address;
    private String phoneNumber;
    private String email;
    private String identificationDocumentType;
    private String identificationNumber;
    private String taxpayerNumber;
    private Job job = null;
    private RegisterCollaboratorController registerCollaboratorController = new RegisterCollaboratorController();

    /**
     * Run this functionality.
     */
    public void run(){
        System.out.println("\n >>>>>>>>>> REGISTER COLLABORATOR <<<<<<<<<< \n");

        while(true){
            requestData();
            if(job == null){ return; }
            if(!confirmData()){continue;}
            submitData();
            break;
        }

    }

    /**
     * Request all necessary data and save it to its respective variables.
     */
    private void requestData(){
        name = requestName();
        birthDate = requestBirthDate();
        admissionDate = requestAdmissionDate();
        address = requestAddress();
        phoneNumber = requestPhoneNumber();
        email  = requestEmail();
        identificationDocumentType = requestIdentificationDocumentType();
        identificationNumber = requestIdentificationNumber();
        taxpayerNumber = requestTaxpayerNumber();
        job = requestJob();
    }

    /**
     * Confirm user inputs and selections.
     * @return A boolean value describing if the user confirms their selection.
     */
    private boolean confirmData(){
        System.out.println("\n>>>>>>>>>> COLLABORATOR INFORMATION <<<<<<<<<< \n");
        System.out.println("Name: "+ name);
        System.out.println("Birth Date: "+ birthDate);
        System.out.println("Admission Date: "+ admissionDate);
        System.out.println("Address: "+ address);
        System.out.println("Phone Number: "+ phoneNumber);
        System.out.println("Email: "+ email);
        System.out.println("Identification Document Type: "+ identificationDocumentType);
        System.out.println("Identification Number: "+ identificationNumber);
        System.out.println("Taxpayer Number: "+ taxpayerNumber);
        System.out.println("Job: "+ job.toString());
        return Utils.confirm("Do you wish to proceed? (s or n)");
    }

    /**
     * Submit the inputted data and provide the respective feedback.
     */
    private void submitData(){
        try{
            Optional<Collaborator> newCollaborator = registerCollaboratorController.createCollaborator( name,  birthDate, admissionDate, address,
                    phoneNumber,  email,  identificationDocumentType,
                    identificationNumber, taxpayerNumber,  job);
            if(newCollaborator.isEmpty()){
                System.out.println("Failed to add new collaborator!");
            }else{
                System.out.println("Collaborator added successfully!");
            }
        }catch(Exception e){
            System.out.println("Failed to add new collaborator!");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Requests the collaborator's name.
     * @return The collaborator's name.
     */
    private String requestName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Name: ");
        return input.nextLine();
    }

    /**
     * Requests the collaborator's birthdate.
     * @return The collaborator's birthdate.
     */
    private String requestBirthDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Birth Date: ");
        return input.nextLine();
        /*CustomDate date = Utils.readDateFromConsole("Birth Date: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return date.toString();*/
    }

    /**
     * Requests the collaborator's admission date.
     * @return The collaborator's admission date.
     */
    private String requestAdmissionDate() {
        Scanner input = new Scanner(System.in);
        System.out.print("Birth Date: ");
        return input.nextLine();
        /*CustomDate date = Utils.readDateFromConsole("Admission Date: ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return date.toString();*/
    }

    /**
     * Requests the collaborator's address.
     * @return The collaborator's address.
     */
    private String requestAddress() {
        Scanner input = new Scanner(System.in);
        System.out.print("Address: ");
        return input.nextLine();
    }

    /**
     * Requests the collaborator's phone number.
     * @return The collaborator's phone number.
     */
    private String requestPhoneNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Phone Number: ");
        return input.nextLine();
    }

    /**
     * Requests the collaborator's email.
     * @return The collaborator's email.
     */
    private String requestEmail() {
        Scanner input = new Scanner(System.in);
        System.out.print("Email: ");
        return input.nextLine();
    }

    /**
     * Requests the collaborator's ID document type.
     * @return The collaborator's ID document type.
     */
    private String requestIdentificationDocumentType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Identification Document Type: ");
        return input.nextLine();
    }

    /**
     * Requests the collaborator's ID number.
     * @return The collaborator's ID number.
     */
    private String requestIdentificationNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Identification number: ");
        return input.nextLine();
    }

    /**
     * Requests the collaborator's taxpayer number.
     * @return The collaborator's taxpayer number.
     */
    private String requestTaxpayerNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Taxpayer number: ");
        return input.nextLine();
    }

    /**
     * Requests the collaborator's job.
     * @return The collaborator's job.
     */
    private Job requestJob(){
        Scanner input = new Scanner(System.in);
        Optional<ArrayList<Job>> jobs = registerCollaboratorController.getJobList();
        if(jobs.isEmpty()){
            System.out.println("Error: No jobs. Collaborator registration aborted.");
            return null;
        }
        System.out.println("Choose a job from the following list:\n");
        for(int i = 0; i < jobs.get().size(); i++){
            System.out.println((i+1) + "- "+jobs.get().get(i).toString());
        }
        int option = 0;
        while(true){
            try{
                System.out.println("Choose a number corresponding to a job.");
                option = Integer.parseInt(input.nextLine());
                if(option <= 0 || option > jobs.get().size()){
                    System.out.println("Error: Invalid option.");
                    continue;
                }
                break;
            }catch(Exception e){
                System.out.println("Error: Selected option must be a number.");
            }
        }
        return jobs.get().get(option-1);
    }
}
