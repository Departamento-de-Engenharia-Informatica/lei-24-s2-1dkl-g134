package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.ListTasksBetweenDatesController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListTasksBetweenDatesUI implements Initializable {
    @FXML
    private TableView taskList;
    @FXML
    private TextField firstDate;
    @FXML
    private TextField secondDate;
    @FXML
    private CheckBox planned;
    @FXML
    private CheckBox postponed;
    @FXML
    private CheckBox completed;
    @FXML
    private CheckBox cancelled;

    private ListTasksBetweenDatesController ctrl = new ListTasksBetweenDatesController();
    private Optional<ArrayList<TaskEntryDTO>> tasks = Optional.empty();

    /**
     * Initializes this functionality and prepares and creates the appropriate columns for
     * the TableView in use.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }

    /**
     * Attempts to get all tasks assigned to the current collaborator between the two dates inserted by
     * the user. Provides the appropriate feedback should anything go wrong. Calls the refreshTableView
     * method should it work.
     */
    @FXML
    private void getTaskList(){
        taskList.getItems().clear();
        try {
            tasks = ctrl.getCurrentUserTasksBetweenTwoDates(firstDate.getText(), secondDate.getText());
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get tasks!"
                    , e.getMessage()).show();
            return;
        }
        if(tasks.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get tasks!"
                    , "No tasks found between these dats for you!").show();
            return;
        }
        refreshTableView();
    }

    /**
     * Attempts to refresh the table view, sorted by dates, and applies the appropriate filters.
     */
    @FXML
    private void refreshTableView(){
        taskList.getItems().clear();
        if(tasks.isEmpty()){
            return;
        }
        tasks = Optional.of(sortTasks(tasks.get()));
        for(TaskEntryDTO taskEntry : tasks.get()){
            if(!planned.isSelected() && !postponed.isSelected() && !completed.isSelected() && !cancelled.isSelected()){
                taskList.getItems().add(taskEntry.attachedTaskEntry);
                continue;
            }
            switch(taskEntry.state){
                case PLANNED: if(planned.isSelected()){taskList.getItems().add(taskEntry.attachedTaskEntry);}
                    break;
                case POSTPONED: if(postponed.isSelected()){taskList.getItems().add(taskEntry.attachedTaskEntry);}
                    break;
                case COMPLETED: if(completed.isSelected()){taskList.getItems().add(taskEntry.attachedTaskEntry);}
                    break;
                case CANCELED: if(cancelled.isSelected()){taskList.getItems().add(taskEntry.attachedTaskEntry);}
                    break;
            }
        }
    }

    /**
     * Switches to the CompleteTaskUI scene.
     */
    @FXML
    public void goToComplete() throws IOException {
        Stage stage = (Stage) taskList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CompleteTask.fxml"));
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

    /**
     * Sorts a list of tasks by their dates.
     * @param taskEntries The list of tasks to sort.
     * @return The sorted list of tasks.
     */
    private ArrayList<TaskEntryDTO> sortTasks(ArrayList<TaskEntryDTO> taskEntries){
        for(int i = 0; i < taskEntries.size(); i++){
            for(int j = 1; j < taskEntries.size()-i; j++){
                if(taskEntries.get(j-1).startDate.isAfterDate(taskEntries.get(j).startDate)){
                    TaskEntryDTO temp = taskEntries.get(j-1);
                    taskEntries.set(j-1, taskEntries.get(j));
                    taskEntries.set(j, temp);
                }
            }
        }
        return taskEntries;
    }
}
