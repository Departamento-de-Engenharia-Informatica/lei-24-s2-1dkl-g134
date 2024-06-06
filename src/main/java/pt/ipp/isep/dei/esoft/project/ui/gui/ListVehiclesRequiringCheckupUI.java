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
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateMaintenanceReportController;
import pt.ipp.isep.dei.esoft.project.domain.CheckUp;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListVehiclesRequiringCheckupUI implements Initializable {
    @FXML
    private TableView vehicles;
    @FXML
    private TableView prev;

    private GenerateMaintenanceReportController ctrl = new GenerateMaintenanceReportController();

    /**
     * Initializes this functionality and prepares and creates the appropriate columns for the
     * TableViews in use. Then, gets all the vehicles currently requiring a checkup, and populates
     * the TableView with them.
     * Should any error arise in the process, provides feedback to the user.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prev.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn<CheckUp, String> prevColumn = new TableColumn<>("Previous");
        prevColumn.setCellValueFactory(new PropertyValueFactory<>("currentKM"));
        prev.getColumns().add(prevColumn);
        TableColumn<CheckUp, String> nextColumn = new TableColumn<>("Next");
        nextColumn.setCellValueFactory(new PropertyValueFactory<>("nextCheckupKM"));
        prev.getColumns().add(nextColumn);

        vehicles.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn<Vehicle, String> vehicleColumn1 = new TableColumn<>("Plate Number");
        vehicleColumn1.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
        vehicles.getColumns().add(vehicleColumn1);
        TableColumn<Vehicle, String> vehicleColumn2 = new TableColumn<>("Brand");
        vehicleColumn2.setCellValueFactory(new PropertyValueFactory<>("brand"));
        vehicles.getColumns().add(vehicleColumn2);
        TableColumn<Vehicle, String> vehicleColumn3 = new TableColumn<>("Model");
        vehicleColumn3.setCellValueFactory(new PropertyValueFactory<>("model"));
        vehicles.getColumns().add(vehicleColumn3);
        TableColumn<Vehicle, String> vehicleColumn4 = new TableColumn<>("Current KM");
        vehicleColumn4.setCellValueFactory(new PropertyValueFactory<>("currentKM"));
        vehicles.getColumns().add(vehicleColumn4);
        TableColumn<Vehicle, String> vehicleColumn5 = new TableColumn<>("Checkup Frequency");
        vehicleColumn5.setCellValueFactory(new PropertyValueFactory<>("checkUpFrequency"));
        vehicles.getColumns().add(vehicleColumn5);
        Optional<ArrayList<Vehicle>> vehicleList = Optional.empty();
        try{
            vehicleList = ctrl.getVehiclesRequiringCheckup();
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get vehicles requiring checkup!"
                    , e.getMessage()).show();
            return;
        }
        if(vehicleList.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get vehicles requiring checkup!"
                    , "No vehicles requiring checkup found!").show();
            return;
        }

        for(Vehicle vehicle : vehicleList.get()){
            Optional<CheckUp> latestCheckup = ctrl.getLatestCheckUpOfVehicle(vehicle);
            if(latestCheckup.isEmpty()){
                prev.getItems().add(new CheckUp(vehicle, 0, "0001/01/01"));
            }else{
                prev.getItems().add(latestCheckup.get());
            }
            vehicles.getItems().add(vehicle);
        }
    }

    /**
     * Switches to the RegisterGreenSpaceUI scene.
     */
    @FXML
    public void toUS20() throws IOException {
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignTeamToTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the PostponeTaskUI scene.
     */
    @FXML
    public void toUS24() throws IOException {
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignVehicleToTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the CancelTaskUI scene.
     */
    @FXML
    public void toUS25() throws IOException {
        Stage stage = (Stage) vehicles.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CancelTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the RegisterSkillUI scene.
     */
    @FXML
    public void toUS1() throws IOException {
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
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
        Stage stage = (Stage) vehicles.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterCheckup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the GetGreenSpacesManagedByUserUI scene.
     */
    @FXML
    public void toUS27() throws IOException {
        Stage stage = (Stage) vehicles.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GetGreenSpacesManagedByUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
