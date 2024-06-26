package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class GsmUI implements Runnable {
    public GsmUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Skill", new RegisterSkillUI()));
        options.add(new MenuItem("Register Job", new RegisterJobUI()));
        options.add(new MenuItem("Register Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Assign Skills To Collaborator", new AssignSkillsToCollaboratorUI()));
        options.add(new MenuItem("Generate Teams", new GenerateTeamUI()));
        options.add(new MenuItem("Register Vehicle", new RegisterVehicleUI()));
        options.add(new MenuItem("Register Vehicle Checkup", new RegisterVehicleCheckupUI()));
        options.add(new MenuItem("List Vehicles Requiring Checkup", new GenerateMaintenanceReportUI()));
        options.add(new MenuItem("Register Green Space", new RegisterGreenSpaceUI()));
        options.add(new MenuItem("List Green Spaces managed by you", new GetGreenSpacesManagedByUserUI()));
        options.add(new MenuItem("Add Task to To-Do List", new AddTaskEntryUI()));
        options.add(new MenuItem("Add Task to Agenda", new AssignTaskToAgendaUI()));
        options.add(new MenuItem("Assign Vehicles to Task", new AssignVehiclesToTaskUI()));
        options.add(new MenuItem("Assign Team to Task", new AssignTeamToTaskUI()));
        options.add(new MenuItem("Postpone Task", new PostponeTaskUI()));
        options.add(new MenuItem("Cancel Task", new CancelTaskUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}