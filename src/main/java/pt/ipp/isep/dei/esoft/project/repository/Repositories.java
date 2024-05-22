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
    private final VehicleRepository vehicleRepository;
    private final CheckupRepository checkupRepository;
    private final TeamRepository teamRepository;
    private final GreenSpaceRepository greenSpaceRepository;
    private final ToDoListRepository toDoListRepository;
    private final AgendaRepository agendaRepository;
    /**
     * Constructor for the Repositories class.
     * All this does is initialize every repository.
     */
    private Repositories() {
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        skillRepository = new SkillRepository();
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        vehicleRepository = new VehicleRepository();
        checkupRepository = new CheckupRepository();
        teamRepository = new TeamRepository();
        greenSpaceRepository=new GreenSpaceRepository();
        toDoListRepository=new ToDoListRepository();
        agendaRepository=new AgendaRepository();
    }

    /**
     * Gets the existing instance of the Repositories class.
     * @return The instance of Repositories.
     */
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

    /**
     * Gets the skill repository.
     * @return The SkillRepository object.
     */
    public SkillRepository getSkillRepository() {return skillRepository; }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Gets the collaborator repository.
     * @return The CollaboratorRepository object.
     */
    public CollaboratorRepository getCollaboratorRepository(){ return  collaboratorRepository;}

    /**
     * Gets the job repository.
     * @return The JobRepository object.
     */
    public JobRepository getJobRepository(){ return jobRepository;}

    /**
     * Gets the vehicle repository.
     * @return The VehicleRepository object.
     */
    public  VehicleRepository getVehicleRepository(){ return vehicleRepository;}

    /**
     * Gets the team repository.
     * @return The TeamRepository object.
     */
    public TeamRepository getTeamRepository(){ return teamRepository;}

    /**
     * Gets the checkup repository.
     * @return The CheckupRepository object.
     */
    public CheckupRepository getCheckupRepository(){ return checkupRepository;}

    public GreenSpaceRepository getGreenSpaceRepository(){
        return greenSpaceRepository;
    }

    public ToDoListRepository getToDoListRepository() {return toDoListRepository;}
    public AgendaRepository getAgendaRepository(){return agendaRepository;}
}
