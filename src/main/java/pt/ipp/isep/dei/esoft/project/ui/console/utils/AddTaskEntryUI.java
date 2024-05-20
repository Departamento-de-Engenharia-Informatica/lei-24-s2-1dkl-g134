package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AddTaskEntryController;
import pt.ipp.isep.dei.esoft.project.domain.State;
import pt.ipp.isep.dei.esoft.project.domain.urgencyLevel;

public class AddTaskEntryUI implements Runnable {

    private final AddTaskEntryController controller;
    private String taskTitle;
    private String taskDescription;
    private urgencyLevel urgencyLevel;
    private State state;
    private int duration;

    public AddTaskEntryUI() {controller = new AddTaskEntryController();}

    public void run() {
        System.out.println("\n >>>>>>>>>> ADD TASK ENTRY <<<<<<<<<< \n");

        taskCategoryDescription = displayAndSelectTaskCategory();

        requestData();

        submitData();
    }


}
