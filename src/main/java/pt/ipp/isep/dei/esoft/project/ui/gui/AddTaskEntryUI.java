package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AddTaskEntryController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.urgencyLevel;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddTaskEntryUI implements Initializable {
    @FXML
    private TextField title;
    @FXML
    private TextField description;
    @FXML
    private TextField duration;
    @FXML
    private ListView urgencyLevels;
    @FXML
    private ListView greenSpaces;

    private ArrayList<urgencyLevel> allUrgencyLevels;
    private ArrayList<GreenSpaceDTO> allGreenSpaces;

    private AddTaskEntryController ctrl = new AddTaskEntryController();

    /**
     * Initializes this functionality and prepares and creates the appropriate columns for
     * the ListViews in use, and then populates them.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allUrgencyLevels = urgencyLevel.getAllUrgencyLevels();
        urgencyLevels.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for(urgencyLevel singleUrgencyLevel : allUrgencyLevels){
            urgencyLevels.getItems().add(singleUrgencyLevel.toString());
        }
        greenSpaces.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Optional<ArrayList<GreenSpaceDTO>> greenSpaceList = Optional.empty();
        try{
            greenSpaceList = ctrl.getGreenSpacesManagedByCurrentUser();
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get green spaces managed by you!"
                    , e.getMessage()).show();
            return;
        }
        if(greenSpaceList.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get green spaces managed by you!"
                    , "No green spaces managed by you found!").show();
            return;
        }
        allGreenSpaces = greenSpaceList.get();
        for(GreenSpaceDTO greenSpaceDTO : greenSpaceList.get()){
            greenSpaces.getItems().add(greenSpaceDTO.attachedGreenSpace.toString());
        }
    }

    /**
     * Attempts to register the green space into the system and provides the appropriate feedback
     * to the user.
     */
    @FXML
    private void addTaskEntry() {
        urgencyLevel selectedUrgencyLevel = null;
        try{
            selectedUrgencyLevel = allUrgencyLevels.get(urgencyLevels.getSelectionModel().getSelectedIndex());
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Invalid urgency level selection!"
                    , "Select an urgency level to add a task!").show();
            return;
        }
        GreenSpace selectedGreenSpace = null;
        try{
            selectedGreenSpace = allGreenSpaces.get(greenSpaces.getSelectionModel().getSelectedIndex()).attachedGreenSpace;
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Invalid green space selection!"
                    , "Select a green space to add a task!").show();
            return;
        }
        try{
            TaskEntryDTO taskEntryDTO = new TaskEntryDTO();
            taskEntryDTO.taskTitle = title.getText();
            taskEntryDTO.taskDescription = description.getText();
            try{
                taskEntryDTO.duration = Integer.parseInt(duration.getText());
            }catch(Exception e){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on adding task entry!"
                        , "The duration must be a number!").show();
                return;
            }
            taskEntryDTO.urgencyLevel = selectedUrgencyLevel;
            taskEntryDTO.greenSpace = selectedGreenSpace;
            Optional<TaskEntry> greenSpace = ctrl.addTaskEntry(taskEntryDTO);
            if(greenSpace.isEmpty()){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on adding task entry!"
                        , "Are you sure this isn't a duplicate registration?").show();
            }else{
                AlertUI.createAlert(Alert.AlertType.INFORMATION, Bootstrap.APP_TITLE, "Success!"
                        , "Task successfully added to the to-do list!").show();
            }
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on adding task entry!"
                    , e.getMessage()).show();
        }
    }

    /**
     * Switches to the PostponeTaskUI scene.
     */
    @FXML
    public void toUS24() throws IOException {
        Stage stage = (Stage) urgencyLevels.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PostponeTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the RegisterGreenSpaceUI scene.
     */
    @FXML
    public void toUS20() throws IOException {
        Stage stage = (Stage) urgencyLevels.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterGreenSpace.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the AssignTaskToAgendaUI scene.
     */
    @FXML
    public void toUS22() throws IOException {
        Stage stage = (Stage) urgencyLevels.getScene().getWindow();
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
        Stage stage = (Stage) urgencyLevels.getScene().getWindow();
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
        Stage stage = (Stage) urgencyLevels.getScene().getWindow();
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
        Stage stage = (Stage) urgencyLevels.getScene().getWindow();
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
        Stage stage = (Stage) urgencyLevels.getScene().getWindow();
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
        Stage stage = (Stage) urgencyLevels.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GetGreenSpacesManagedByUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
