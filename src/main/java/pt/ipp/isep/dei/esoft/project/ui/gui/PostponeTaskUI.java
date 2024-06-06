package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CancelTaskController;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PostponeTaskUI implements Initializable {
    @FXML
    private TableView taskList;
    @FXML
    private TextField date;
    @FXML
    private TextField time;

    private PostponeTaskController ctrl = new PostponeTaskController();
    private Optional<ArrayList<TaskEntryDTO>> tasks = Optional.empty();

    /**
     * Initializes this functionality and prepares and creates the appropriate columns for
     * the TableView in use, calling refreshTableView() at the end.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn<TaskEntry, String> column1 = new TableColumn<>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
        taskList.getColumns().add(column1);
        TableColumn<TaskEntry, String> column2 = new TableColumn<>("Start Date");
        column2.setCellValueFactory(new PropertyValueFactory<>("startDateString"));
        taskList.getColumns().add(column2);
        TableColumn<TaskEntry, String> column3 = new TableColumn<>("Start Time");
        column3.setCellValueFactory(new PropertyValueFactory<>("startTimeString"));
        taskList.getColumns().add(column3);
        TableColumn<TaskEntry, String> column4 = new TableColumn<>("Duration in Hours");
        column4.setCellValueFactory(new PropertyValueFactory<>("duration"));
        taskList.getColumns().add(column4);
        refreshTableView();
    }

    /**
     * Attempts to postpone the selected task and provides the appropriate feedback.
     * Also calls refreshTableView() in the event of success.
     */
    @FXML
    private void postponeTask() {
        TaskEntryDTO selectedTask = null;
        try{
            selectedTask = tasks.get().get(taskList.getSelectionModel().getSelectedIndex());
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Invalid task selection!"
                    , "Select a task to postpone!").show();
            return;
        }
        try{
            Optional<TaskEntry> completedTask = ctrl.postponeTask(selectedTask.attachedTaskEntry, date.getText(), time.getText()+":00");
            if(completedTask.isEmpty()){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on postponing task!"
                        , "Are you sure this isn't a duplicate postponing?").show();
            }else{
                AlertUI.createAlert(Alert.AlertType.INFORMATION, Bootstrap.APP_TITLE, "Success!"
                        , "Task successfully postponed!").show();
                refreshTableView();
            }
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on postponing task!"
                    , e.getMessage()).show();
        }
    }

    /**
     * Attempts to get all tasks on a green space managed by the current user currently in a
     * planned or postponed state.
     * Provides the appropriate feedback should anything go wrong, and populates the TableView
     * if nothing is wrong.
     */
    private void refreshTableView(){
        taskList.getItems().clear();
        try {
            tasks = ctrl.getPlannedAndPostponedTask();
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
     * Switches to the AssignTeamToTaskUI scene.
     */
    @FXML
    public void toUS23() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignTeamToTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the PostponeTaskUI scene.
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
