package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Optional;

public class RegisterJobUI implements Runnable {

    private final RegisterJobController ctrl = new RegisterJobController();
    private String name;

    public void run() {
        System.out.println("\n >>>>>>>>>> REGISTER JOB <<<<<<<<<< \n");

        name = Utils.readLineFromConsole("Insert the job name:");

        validate();

    }

    private void validate() {
        System.out.println(">>>>>>>>>> JOB NAME <<<<<<<<<< \n");

        System.out.printf("Job name: %s\n", name);

        System.out.println();

        boolean input = Utils.confirm("Do you wish to proceed? (s or n):\n");

        if (input) {
            Optional<Job> job = ctrl.registerJob(name);
            if (job.isPresent()){
                System.out.println("\nJob registration successful.");

            } else {
                System.out.println("\nError: Invalid Job. Job registration aborted.");
            }
        } else {
            System.out.println("Cancelling registration\n");
            run();
        }
    }
}