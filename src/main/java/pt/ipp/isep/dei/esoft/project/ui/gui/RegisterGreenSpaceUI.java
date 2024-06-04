package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterGreenSpaceUI implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField area;
    @FXML
    private ListView types;

    private ArrayList<GreenSpaceType> allTypes;

    private RegisterGreenSpaceController ctrl = new RegisterGreenSpaceController();

    /**
     * Initializes this functionality and prepares and creates the appropriate columns for
     * the ListView in use, and then populates it.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allTypes = GreenSpaceType.getAllTypes();
        types.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        for(GreenSpaceType type : allTypes){
            types.getItems().add(type.toString());
        }
    }

    /**
     * Attempts to register the green space into the system and provides the appropriate feedback
     * to the user.
     */
    @FXML
    private void registerGreenSpace() {
        GreenSpaceType selectedType = null;
        try{
            selectedType = allTypes.get(types.getSelectionModel().getSelectedIndex());
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Invalid type selection!"
                    , "Select a green space type to register a green space!").show();
            return;
        }
        try{
            GreenSpaceDTO greenSpaceDTO = new GreenSpaceDTO();
            greenSpaceDTO.name = name.getText();
            greenSpaceDTO.address = address.getText();
            try{
                greenSpaceDTO.area = Integer.parseInt(area.getText());
            }catch(Exception e){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on registering green space!"
                        , "The area must be a number!").show();
                return;
            }
            greenSpaceDTO.type = selectedType;
            Optional<GreenSpace> greenSpace = ctrl.registerGreenSpace(greenSpaceDTO);
            if(greenSpace.isEmpty()){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on registering green space!"
                        , "Are you sure this isn't a duplicate registration?").show();
            }else{
                AlertUI.createAlert(Alert.AlertType.INFORMATION, Bootstrap.APP_TITLE, "Success!"
                        , "Green Space successfully registered!").show();
            }
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on registering green space!"
                    , e.getMessage()).show();
        }
    }

    /**
     * Switches to the PostponeTaskUI scene.
     */
    @FXML
    public void toUS24() throws IOException {
        Stage stage = (Stage) types.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PostponeTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the AddTaskEntryUI scene.
     */
    @FXML
    public void toUS21() throws IOException {
        Stage stage = (Stage) types.getScene().getWindow();
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
        Stage stage = (Stage) types.getScene().getWindow();
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
        Stage stage = (Stage) types.getScene().getWindow();
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
        Stage stage = (Stage) types.getScene().getWindow();
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
        Stage stage = (Stage) types.getScene().getWindow();
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
        Stage stage = (Stage) types.getScene().getWindow();
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
        Stage stage = (Stage) types.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GetGreenSpacesManagedByUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
