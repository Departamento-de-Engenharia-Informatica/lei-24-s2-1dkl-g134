package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Optional;

public class Bootstrap implements Runnable {

    public static int workHoursStart = 9;
    public static int workHoursEnd = 17;
    public static int dailyWorkHours = workHoursEnd - workHoursStart;

    //Add some task categories to the repository as bootstrap
    public void run() {
        addTaskCategories();
        addOrganization();
        addUsers();
        addWorkforce();
        addFleet();
    }

    private void addOrganization() {
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addEmployee(new Employee("admin@this.app"));
        organization.addEmployee(new Employee("hrm@this.app"));
        organization.addEmployee(new Employee("vfm@this.app"));
        organization.addEmployee(new Employee("collab1@this.app"));
        organization.addEmployee(new Employee("collab2@this.app"));
        organization.addEmployee(new Employee("collab3@this.app"));
        organization.addEmployee(new Employee("gsm1@this.app"));
        organization.addEmployee(new Employee("gsm2@this.app"));
        organization.addEmployee(new Employee("employee@this.app"));
        organizationRepository.add(organization);
    }

    private void addTaskCategories() {

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

    public void addUsers() {
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLAB, AuthenticationController.ROLE_COLLAB);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_EMPLOYEE,
                AuthenticationController.ROLE_EMPLOYEE);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "hrm",
                AuthenticationController.ROLE_HRM);

        authenticationRepository.addUserWithRole("VFM", "vfm@this.app", "vfm",
                AuthenticationController.ROLE_VFM);

        authenticationRepository.addUserWithRole("Collab1", "collab1@this.app", "collab1",
                AuthenticationController.ROLE_COLLAB);

        authenticationRepository.addUserWithRole("Collab2", "collab2@this.app", "collab2",
                AuthenticationController.ROLE_COLLAB);

        authenticationRepository.addUserWithRole("Collab3", "collab3@this.app", "collab3",
                AuthenticationController.ROLE_COLLAB);

        authenticationRepository.addUserWithRole("GSM1", "gsm1@this.app", "gsm1",
                AuthenticationController.ROLE_GSM);

        authenticationRepository.addUserWithRole("GSM2", "gsm2@this.app", "gsm2",
                AuthenticationController.ROLE_GSM);

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
        Collaborator collaborator1 = collaboratorRepository.add("Roger", "2005/05/22", "2023/05/22", "His house, duh",
        "960144802", "roger@is.real", "CC", "15017807", "256", job).get();
        Collaborator collaborator2 = collaboratorRepository.add("Emily", "2005/05/22", "2023/05/22", "Her house, duh",
        "960144802", "emily@is.real", "CC", "15017808", "256", job).get();
        Collaborator collaborator3 = collaboratorRepository.add("Exas", "2005/05/22", "2023/05/22", "Their house, duh",
        "960144802", "exas@is.real", "CC", "15017809", "256", job).get();
        Collaborator collaborator4 = collaboratorRepository.add("Stanley", "2005/05/22", "2023/05/22", "The bucket",
        "960144802", "the@stanley.parable", "CC", "427", "256", job).get();
        Collaborator collaborator5 = collaboratorRepository.add("Narrator", "2005/05/22", "2023/05/22", "Stanley",
        "960144802", "will@never.end", "CC", "8888", "256", job).get();
        Collaborator collaborator6 = collaboratorRepository.add("Bucket", "2005/05/22", "2023/05/22", "The universe",
        "960144802", "the@reassurance.bucket", "CC", "3", "256", job).get();
        Collaborator collaborator7 = collaboratorRepository.add("Coleman", "2005/05/22", "2023/05/22", "The girls and the gays",
        "960144802", "coleslaw@poeslaw.coleman", "CC", "1337", "256", job).get();
        Collaborator collaborator8 = collaboratorRepository.add("Daniel", "2005/05/22", "2023/05/22", "The Irish Casino",
        "960144802", "rumble@tumble.games", "CC", "777", "256", job).get();
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

    private void addFleet(){
        VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
        CheckupRepository checkupRepository = Repositories.getInstance().getCheckupRepository();
        Vehicle vehicle = vehicleRepository.add("Ford", "T", 5000, 2500.0,3000,
        "2010/04/25", "2009/04/25", 500, "H3LL0", "Car").get();
        vehicleRepository.add("Ford", "X", 5000, 2500.0,1000,
        "2010/04/25", "2009/04/25", 750, "G00DBY3", "Truck");
        vehicleRepository.add("The USSR", "Stalin", 5000, 2500.0,1000,
        "2010/04/25", "2009/04/25", 1010, "CCCP", "Tank");
        vehicleRepository.add("The US", "F1", 5000, 2500.0,2000,
        "2010/04/25", "2009/04/25", 5000, "0407", "Jet");
    }
}