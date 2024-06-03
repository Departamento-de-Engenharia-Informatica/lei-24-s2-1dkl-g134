package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CompleteTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CompleteTaskUI implements Initializable {
    @FXML
    private TableView taskList;

    private CompleteTaskController ctrl = new CompleteTaskController();
    private Optional<ArrayList<TaskEntryDTO>> tasks = Optional.empty();

    /**
     * Initializes this functionality and prepares the creates the appropriate columns for
     * the TableView in use, calling refreshTableView() at the end.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn<TaskEntry, String> column1 = new TableColumn<>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<>("taskTitle"));
        taskList.getColumns().add(column1);
        TableColumn<TaskEntry, String> column2 = new TableColumn<>("Description");
        column2.setCellValueFactory(new PropertyValueFactory<>("taskDescription"));
        taskList.getColumns().add(column2);
        refreshTableView();
    }

    /**
     * Attempts to mark the task selected in the table as completed and provides the appropriate feedback.
     * Also calls refreshTableView() in the event of success.
     */
    @FXML
    private void completeTask() {
        TaskEntryDTO selectedTask = null;
        try{
            selectedTask = tasks.get().get(taskList.getSelectionModel().getSelectedIndex());
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Invalid task selection!"
                    , e.getMessage()).show();
            return;
        }
        try{
            Optional<TaskEntry> completedTask = ctrl.completeTask(selectedTask.attachedTaskEntry);
            if(completedTask.isEmpty()){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on completing task!"
                        , "Are you sure this isn't a duplicate completion?").show();
            }else{
                AlertUI.createAlert(Alert.AlertType.INFORMATION, Bootstrap.APP_TITLE, "Success!"
                        , "Task successfully completed!").show();
                refreshTableView();
            }
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on completing task!"
                    , e.getMessage()).show();
        }
    }

    /**
     * Attempts to get all tasks assigned to the current collaborator currently planned or postponed.
     * Provides the appropriate feedback should anything go wrong, and populates the TableView
     * if nothing is wrong.
     */
    private void refreshTableView(){
        taskList.getItems().clear();
        try {
            tasks = ctrl.getPlannedAndPostponedTasksBelongingToCurrentUser();
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get tasks belonging to you!"
                    , e.getMessage()).show();
            return;
        }
        if(tasks.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get tasks belonging to you!"
                    , "No tasks found for you!").show();
            return;
        }
        for(TaskEntryDTO taskEntry : tasks.get()){
            taskList.getItems().add(taskEntry.attachedTaskEntry);
        }
    }

    /**
     * Switches to the ListTasksBetweenDatesUI scene.
     */
    @FXML
    public void goToList() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListTasksBetweenDates.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the MainCollabUI scene.
     */
    @FXML
    public void goToStart() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuCollaborator.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
