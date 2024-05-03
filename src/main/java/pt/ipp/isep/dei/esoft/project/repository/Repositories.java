package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final JobRepository jobRepository;

    private final SkillRepository skillRepository;


    private Repositories() {
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        skillRepository = new SkillRepository();
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public SkillRepository getSkillRepository() {return skillRepository; }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }
    public CollaboratorRepository getCollaboratorRepository(){ return  collaboratorRepository;}
    public JobRepository getJobRepository(){ return jobRepository;}
}