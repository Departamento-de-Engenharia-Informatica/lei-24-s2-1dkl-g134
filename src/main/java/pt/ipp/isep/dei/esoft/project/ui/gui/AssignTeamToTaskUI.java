package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignTeamToTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AssignTeamToTaskUI implements Initializable {
    @FXML
    private TableView taskList;
    @FXML
    private ListView teamList;

    private AssignTeamToTaskController ctrl = new AssignTeamToTaskController();
    private Optional<ArrayList<TaskEntryDTO>> tasks = Optional.empty();
    private ArrayList<Team> teams = new ArrayList<>();

    /**
     * Initializes this functionality, creates and prepares the appropriate columns for the TableView
     * and ListView in use, and populates the task list table. Also adds a listener for selections on
     * the task list table that triggers the refreshTeamList() function.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teamList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        taskList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn<TaskEntry, String> column1 = new TableColumn<>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
        taskList.getColumns().add(column1);
        TableColumn<TaskEntry, String> column2 = new TableColumn<>("Urgency");
        column2.setCellValueFactory(new PropertyValueFactory<>("urgencyLevel"));
        taskList.getColumns().add(column2);
        TableColumn<TaskEntry, String> column3 = new TableColumn<>("State");
        column3.setCellValueFactory(new PropertyValueFactory<>("state"));
        taskList.getColumns().add(column3);
        TableColumn<TaskEntry, String> column4 = new TableColumn<>("Green Space");
        column4.setCellValueFactory(new PropertyValueFactory<>("greenSpace"));
        taskList.getColumns().add(column4);
        TableColumn<TaskEntry, String> column5 = new TableColumn<>("Duration");
        column5.setCellValueFactory(new PropertyValueFactory<>("duration"));
        taskList.getColumns().add(column5);
        TableColumn<TaskEntry, String> column6 = new TableColumn<>("Start Date");
        column6.setCellValueFactory(new PropertyValueFactory<>("startDateString"));
        taskList.getColumns().add(column6);

        taskList.getSelectionModel().getSelectedItems().addListener((ListChangeListener) change -> refreshTeamList());

        try {
            tasks = ctrl.getPlannedAndPostponedTasks();
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get tasks on your green spaces!"
                    , e.getMessage()).show();
            return;
        }
        if(tasks.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get tasks on your green spaces!"
                    , "No tasks found!").show();
            return;
        }
        for(TaskEntryDTO taskEntry : tasks.get()){
            taskList.getItems().add(taskEntry.attachedTaskEntry);
        }
    }

    /**
     * Attempts to assign the selected team to the selected task and provides the appropriate feedback.
     */
    @FXML
    private void assignTeamToTask() {
        if(taskList.getSelectionModel().getSelectedIndex() == -1){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to assign team to task!"
                    , "No task selected!").show();
            return;
        }
        TaskEntry selectedTask = tasks.get().get(taskList.getSelectionModel().getSelectedIndex()).attachedTaskEntry;
        if(teamList.getSelectionModel().getSelectedIndex() == -1){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to assign team to task!"
                    , "No team selected!").show();
            return;
        }
        Team selectedTeam = teams.get(teamList.getSelectionModel().getSelectedIndex());
        try{
            Optional<TaskEntry> updatedTask = ctrl.assignTeamToTask(selectedTeam, selectedTask);
            if(updatedTask.isEmpty()){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to assign team to task!"
                        , "Are you sure this isn't a duplicate assignment?").show();
                return;
            }
            AlertUI.createAlert(Alert.AlertType.INFORMATION, Bootstrap.APP_TITLE, "Success!"
                    , "Team assigned successfully!").show();
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to assign team to task!"
                    , e.getMessage()).show();
        }
    }

    /**
     * Populates the list of teams with all teams available to perform the currently selected
     * task, and provides the appropriate feedback in case of an error.
     */
    private void refreshTeamList() {
        teamList.getItems().clear();
        teams.clear();
        Optional<ArrayList<TeamDTO>> allTeams = Optional.empty();
        try {
            allTeams = ctrl.getTeamList();
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get teams available for this task!"
                    , e.getMessage()).show();
            return;
        }
        if(allTeams.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get teams available for this task!"
                    , "No teams found in the system!").show();
            return;
        }
        for(TeamDTO team : allTeams.get()){
            if(ctrl.isTeamAvailable(team.attachedTeam, tasks.get().get(taskList.getSelectionModel().getSelectedIndex()).attachedTaskEntry)){
                teams.add(team.attachedTeam);
            }
        }
        if(teams.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get teams available for this task!"
                    , "No teams available for this task!").show();
            return;
        }
        for(Team team : teams){
            teamList.getItems().add(team.toString());
        }
    }

    /**
     * Switches to the RegisterGreenSpaceUI scene.
     */
    @FXML
    public void toUS20() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterGreenSpace.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the AddTaskEntryUI scene.
     */
    @FXML
    public void toUS21() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddTaskEntry.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the AssignTaskToAgendaUI scene.
     */
    @FXML
    public void toUS22() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignTaskToAgenda.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the AssignVehicleToTaskUI scene.
     */
    @FXML
    public void toUS26() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignVehicleToTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the PostponeTaskUI scene.
     */
    @FXML
    public void toUS24() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PostponeTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the MainAdminUI scene.
     */
    @FXML
    public void toStart() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuGSM.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the CancelTaskUI scene.
     */
    @FXML
    public void toUS25() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CancelTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the GetGreenSpacesManagedByUserUI scene.
     */
    @FXML
    public void toUS27() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GetGreenSpacesManagedByUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the RegisterSkillUI scene.
     */
    @FXML
    public void toUS1() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterSkill.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the RegisterJobUI scene.
     */
    @FXML
    public void toUS2() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterJob.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the RegisterCollaboratorUI scene.
     */
    @FXML
    public void toUS3() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterCollaborator.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the AssignSkillsToCollaboratorUI scene.
     */
    @FXML
    public void toUS4() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignSkillsToCollaborator.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the GenerateTeamUI scene.
     */
    @FXML
    public void toUS5() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GenerateTeam.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the RegisterVehicleUI scene.
     */
    @FXML
    public void toUS6() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterVehicle.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the RegisterCheckupUI scene.
     */
    @FXML
    public void toUS7() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterCheckup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the ListVehiclesRequiringCheckupUI scene.
     */
    @FXML
    public void toUS8() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListVehiclesRequiringCheckup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
