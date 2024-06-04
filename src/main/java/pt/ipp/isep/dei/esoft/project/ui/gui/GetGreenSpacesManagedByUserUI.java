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
import pt.ipp.isep.dei.esoft.project.application.controller.GetGreenSpacesManagedByUserController;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.dto.GreenSpaceDTO;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

public class GetGreenSpacesManagedByUserUI implements Initializable {
    @FXML
    private TableView greenSpaces;

    private GetGreenSpacesManagedByUserController ctrl = new GetGreenSpacesManagedByUserController();

    /**
     * Initializes this functionality and prepares and creates the appropriate columns for the
     * TableView in use. Then, gets all the green spaces managed by the user to list in the table,
     * attempts to check the sorting algorithm defined in the configuration file, and runs the
     * appropriate sort on the list of green spaces before populating the table with all the green
     * spaces.
     * Should any error arise in the process, provides feedback to the user.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        greenSpaces.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn<GreenSpace, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        greenSpaces.getColumns().add(column1);
        TableColumn<GreenSpace, String> column2 = new TableColumn<>("Address");
        column2.setCellValueFactory(new PropertyValueFactory<>("address"));
        greenSpaces.getColumns().add(column2);
        TableColumn<GreenSpace, String> column3 = new TableColumn<>("Area");
        column3.setCellValueFactory(new PropertyValueFactory<>("area"));
        greenSpaces.getColumns().add(column3);
        TableColumn<GreenSpace, String> column4 = new TableColumn<>("Type");
        column4.setCellValueFactory(new PropertyValueFactory<>("type"));
        greenSpaces.getColumns().add(column4);
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

        String sortingAlgorithm = "";
        try{
            sortingAlgorithm = ApplicationSession.getInstance().getProperties().getProperty("Sorting.Algorithm");
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on getting the sorting algorithm defined in the configuration file!"
                    , e.getMessage()).show();
            return;
        }
        if(sortingAlgorithm == null){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on getting the sorting algorithm defined in the configuration file!"
                    , "No sorting algorithm defined in the configuration file!").show();
            return;
        }
        if(sortingAlgorithm.equals("DefaultSort")){
            defaultSort(greenSpaceList.get());
        }else if(sortingAlgorithm.equals("BubbleSort")){
            greenSpaceList = Optional.of(bubbleSort(greenSpaceList.get()));
        }else{
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on getting the sorting algorithm defined in the configuration file!"
                    , "Invalid sorting algorithm defined in the configuration file!").show();
            return;
        }

        for(GreenSpaceDTO greenSpace : greenSpaceList.get()){
            greenSpaces.getItems().add(greenSpace.attachedGreenSpace);
        }
    }

    /**
     * Performs the default java collections sort method on a list of green spaces,
     * sorting them in reverse order by their area.
     * @param greenSpaces The list of green spaces to sort.
     */
    private void defaultSort(ArrayList<GreenSpaceDTO> greenSpaces){
        greenSpaces.sort(Comparator.comparingInt((GreenSpaceDTO o) -> o.area).reversed());
    }

    /**
     * Performs a reverse bubble sort by area on the provided list of green spaces.
     * @param greenSpaces The list of green spaces to sort.
     * @return The sorted list of green spaces.
     */
    private ArrayList<GreenSpaceDTO> bubbleSort(ArrayList<GreenSpaceDTO> greenSpaces){
        for(int i = 0; i < greenSpaces.size(); i++){
            for(int j = 1; j < greenSpaces.size()-i; j++){
                if(greenSpaces.get(j-1).area < greenSpaces.get(j).area){
                    GreenSpaceDTO temp = greenSpaces.get(j-1);
                    greenSpaces.set(j-1, greenSpaces.get(j));
                    greenSpaces.set(j, temp);
                }
            }
        }
        return greenSpaces;
    }

    /**
     * Switches to the RegisterGreenSpaceUI scene.
     */
    @FXML
    public void toUS20() throws IOException {
        Stage stage = (Stage) greenSpaces.getScene().getWindow();
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
        Stage stage = (Stage) greenSpaces.getScene().getWindow();
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
        Stage stage = (Stage) greenSpaces.getScene().getWindow();
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
        Stage stage = (Stage) greenSpaces.getScene().getWindow();
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
        Stage stage = (Stage) greenSpaces.getScene().getWindow();
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
        Stage stage = (Stage) greenSpaces.getScene().getWindow();
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
        Stage stage = (Stage) greenSpaces.getScene().getWindow();
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
        Stage stage = (Stage) greenSpaces.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CancelTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
