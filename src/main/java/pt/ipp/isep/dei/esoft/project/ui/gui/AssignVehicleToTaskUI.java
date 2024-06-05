package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehiclesToTaskController;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.dto.TaskEntryDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AssignVehicleToTaskUI implements Initializable {
    @FXML
    private TableView taskList;
    @FXML
    private TableView vehicleList;

    private AssignVehiclesToTaskController ctrl = new AssignVehiclesToTaskController();
    private Optional<ArrayList<TaskEntryDTO>> tasks = Optional.empty();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    /**
     * Initializes this functionality, creates and prepares the appropriate columns for the TableViews
     * in use, and populates the task list table. Also adds a listener for selections on the task list
     * table that triggers the refreshVehicleList() function.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn<Vehicle, String> vehicleColumn1 = new TableColumn<>("Plate Number");
        vehicleColumn1.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
        vehicleList.getColumns().add(vehicleColumn1);
        TableColumn<Vehicle, String> vehicleColumn2 = new TableColumn<>("Brand");
        vehicleColumn2.setCellValueFactory(new PropertyValueFactory<>("brand"));
        vehicleList.getColumns().add(vehicleColumn2);
        TableColumn<Vehicle, String> vehicleColumn3 = new TableColumn<>("Model");
        vehicleColumn3.setCellValueFactory(new PropertyValueFactory<>("model"));
        vehicleList.getColumns().add(vehicleColumn3);
        TableColumn<Vehicle, String> vehicleColumn4 = new TableColumn<>("Current KM");
        vehicleColumn4.setCellValueFactory(new PropertyValueFactory<>("currentKM"));
        vehicleList.getColumns().add(vehicleColumn4);

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

        taskList.getSelectionModel().getSelectedItems().addListener((ListChangeListener) change -> refreshVehicleList());

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
     * Attempts to assign the selected vehicles to the selected task and provides the appropriate feedback.
     */
    @FXML
    private void assignVehicleToTask() {
        ArrayList<Vehicle> selectedVehicles = new ArrayList<>();
        if(taskList.getSelectionModel().getSelectedIndex() == -1){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to assign vehicles to task!"
                    , "No task selected!").show();
            return;
        }
        TaskEntry selectedTask = tasks.get().get(taskList.getSelectionModel().getSelectedIndex()).attachedTaskEntry;
        ObservableList<Integer> selectedIndices = vehicleList.getSelectionModel().getSelectedIndices();
        for(Integer index : selectedIndices){
            selectedVehicles.add(vehicles.get(index));
        }
        if(selectedVehicles.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to assign vehicles to task!"
                    , "No vehicles selected!").show();
            return;
        }
        try{
            Optional<ArrayList<Vehicle>> assignedVehicles = ctrl.assignVehiclesToTask(selectedVehicles, selectedTask);
            if(assignedVehicles.isEmpty()){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to assign vehicles to task!"
                        , "Are you sure this isn't a duplicate assignment?").show();
                return;
            }
            String successMessage = "The following vehicles were assigned successfully: \n";
            for(Vehicle vehicle : assignedVehicles.get()){
                successMessage += vehicle.toString()+"\n";
            }
            successMessage += "If any vehicle is missing from this list, it was either invalid or already present in the task.";
            AlertUI.createAlert(Alert.AlertType.INFORMATION, Bootstrap.APP_TITLE, "Success!"
                    , successMessage).show();
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to assign vehicles to task!"
                    , e.getMessage()).show();
        }
    }

    /**
     * Populates the list of vehicles with all vehicles available to perform the currently selected
     * task, and provides the appropriate feedback in case of an error.
     */
    private void refreshVehicleList() {
        vehicleList.getItems().clear();
        vehicles.clear();
        Optional<ArrayList<VehicleDTO>> allVehicles = Optional.empty();
        try {
            allVehicles = ctrl.getVehicleList();
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get vehicles available for this task!"
                    , e.getMessage()).show();
            return;
        }
        if(allVehicles.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get vehicles available for this task!"
                    , "No vehicles found in the system!").show();
            return;
        }
        for(VehicleDTO vehicle : allVehicles.get()){
            if(ctrl.isVehicleAvailable(vehicle.attachedVehicle, tasks.get().get(taskList.getSelectionModel().getSelectedIndex()).attachedTaskEntry)){
                vehicles.add(vehicle.attachedVehicle);
            }
        }
        if(vehicles.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get vehicles available for this task!"
                    , "No vehicles available for this task!").show();
            return;
        }
        for(Vehicle vehicle : vehicles){
            vehicleList.getItems().add(vehicle);
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
}
