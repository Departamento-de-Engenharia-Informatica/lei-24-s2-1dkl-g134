package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Optional;

public class RegisterSkillUI implements Runnable {

    private final RegisterSkillController ctrl = new RegisterSkillController();
    private String name;

    public void run() {
        System.out.println("\n >>>>>>>>>> REGISTER SKILL <<<<<<<<<< \n");

        name = Utils.readLineFromConsole("Insert the skill name:");

        validate();

    }

    private void validate() {
        System.out.println(">>>>>>>>>> SKILL INFORMATION <<<<<<<<<< \n");

        System.out.printf("Skill name: %s\n", name);

        System.out.println();

        boolean input = Utils.confirm("Do you wish to proceed? (s or n):\n");

        if (input) {
            Optional<Skill> skill = ctrl.registerSkill(name);
            if (skill.isPresent()){
                System.out.println("\nSkill registration successful.");

            } else {
                System.out.println("\nError: Invalid skill. Skill registration aborted.");

            }
        } else {
            System.out.println("Cancelling registration\n");
            run();
        }
    }
}