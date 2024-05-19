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

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- GSM MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}