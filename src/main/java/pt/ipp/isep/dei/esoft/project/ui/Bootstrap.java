package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addTaskCategories();
        addOrganization();
        addUsers();
        addWorkforce();
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addEmployee(new Employee("admin@this.app"));
        organization.addEmployee(new Employee("employee@this.app"));
        organizationRepository.add(organization);
    }

    private void addTaskCategories() {
        //TODO: add bootstrap Task Categories here

        //get task category repository
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Employee", "employee@this.app", "pwd",
                AuthenticationController.ROLE_EMPLOYEE);
    }

    private void addWorkforce(){
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        Skill skill1 = skillRepository.add("Tree Pruner").get();
        Skill skill2 = skillRepository.add("Light Vehicle Driver").get();
        Skill skill3 = skillRepository.add("Lifting").get();
        Skill skill4 = skillRepository.add("Gardening").get();
        Skill skill5 = skillRepository.add("First Aid").get();
        Skill skill6 = skillRepository.add("Leadership").get();
        Skill skill7 = skillRepository.add("Style").get();
        Skill skill8 = skillRepository.add("Communication").get();
        Job job = jobRepository.add("Member").get();
        Collaborator collaborator1 = collaboratorRepository.add("Roger", "2005/05/22", "2020/04/25", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job).get();
        Collaborator collaborator2 = collaboratorRepository.add("Emily", "2005/05/22", "2020/04/25", "Her house, duh",
        "960144802", "roger@is.real", "CC", "15017808", "256", job).get();
        Collaborator collaborator3 = collaboratorRepository.add("Exas", "2005/05/22", "2020/04/25", "Their house, duh",
        "960144802", "roger@is.real", "CC", "15017809", "256", job).get();
        Collaborator collaborator4 = collaboratorRepository.add("Stanley", "2005/05/22", "2020/04/25", "The bucket",
        "960144802", "roger@is.real", "CC", "427", "256", job).get();
        Collaborator collaborator5 = collaboratorRepository.add("Narrator", "2005/05/22", "2020/04/25", "Stanley",
        "960144802", "roger@is.real", "CC", "8888", "256", job).get();
        Collaborator collaborator6 = collaboratorRepository.add("Bucket", "2005/05/22", "2020/04/25", "The universe",
        "960144802", "roger@is.real", "CC", "3", "256", job).get();
        Collaborator collaborator7 = collaboratorRepository.add("Coleman", "2005/05/22", "2020/04/25", "The girls and the gays",
        "960144802", "roger@is.real", "CC", "69420", "256", job).get();
        Collaborator collaborator8 = collaboratorRepository.add("Daniel", "2005/05/22", "2020/04/25", "The Irish Casino",
        "960144802", "roger@is.real", "CC", "777", "256", job).get();
        ArrayList<Skill> collabSkills = new ArrayList<>();
        collabSkills.add(skill1);
        collabSkills.add(skill3);
        collaboratorRepository.assignSkillsToCollaborator(collaborator1, collabSkills);
        collabSkills.set(1, skill4);
        collaboratorRepository.assignSkillsToCollaborator(collaborator2, collabSkills);
        collabSkills.set(1, skill2);
        collaboratorRepository.assignSkillsToCollaborator(collaborator3, collabSkills);
        collabSkills.add(skill3);
        collabSkills.add(skill4);
        collabSkills.add(skill5);
        collabSkills.add(skill6);
        collabSkills.add(skill7);
        collabSkills.add(skill8);
        collaboratorRepository.assignSkillsToCollaborator(collaborator4, collabSkills);
        collabSkills.clear();
        collabSkills.add(skill6);
        collabSkills.add(skill7);
        collabSkills.add(skill8);
        collaboratorRepository.assignSkillsToCollaborator(collaborator5, collabSkills);
        collabSkills.set(0, skill3);
        collabSkills.set(2, skill4);
        collaboratorRepository.assignSkillsToCollaborator(collaborator6, collabSkills);
        collabSkills.set(2, skill2);
        collaboratorRepository.assignSkillsToCollaborator(collaborator7, collabSkills);
        collabSkills.clear();
        collabSkills.add(skill6);
        collabSkills.add(skill5);
        collaboratorRepository.assignSkillsToCollaborator(collaborator8, collabSkills);

        //ROGER - Lifting and pruning
        //EMILY - Gardening and pruning
        //EXAS - Driving and pruning
        //STANLEY - All Skills
        //NARRATOR - Leadership, Style, and Communication
        //BUCKET - Lifting, Gardening, and Style
        //COLEMAN - Lifting, Driving, and Style
        //DANIEL - First Aid, Leadership

        //If we were to form a team with 3 pruners, a lifter, a gardener, and a driver,
        //the combinations would be:
        //Roger - Emily - Exas ----------------- DONE
        //Roger - Emily - Stanley --------------- DONE
        //Roger - Stanley - Exas -------------- DONE
        //Stanley - Emily - Exas ------------------- DONE
        //Roger - Emily - Coleman - Exas ----------------- DONE
        //Stanley - Emily - Coleman - Exas ----------------- DONE
        //Roger - Stanley - Coleman - Exas ----------------- DONE
        //Roger - Emily - Stanley - Exas ------------------- DONE
        //Roger - Emily - Coleman - Stanley ---------------- DONE
        //Roger - Emily - Bucket - Exas --------------------- DONE
        //Stanley - Emily - Bucket - Exas ------------------- DONE
        //Roger - Stanley - Bucket - Exas --------------------- DONE
        //Roger - Emily - Bucket - Stanley -------------------- DONE
        //Roger - Emily - Coleman - Exas - Bucket -------------- DONE
        //Stanley - Emily - Coleman - Exas - Bucket -------------- DONE
        //Roger - Stanley - Coleman - Exas - Bucket ------------- DONE
        //Roger - Emily - Stanley - Exas - Bucket ----------- DONE
        //Roger - Emily - Coleman - Stanley - Bucket ------------ DONE
        //Roger - Emily - Coleman - Exas - Stanley ----------- DONE
    }
}