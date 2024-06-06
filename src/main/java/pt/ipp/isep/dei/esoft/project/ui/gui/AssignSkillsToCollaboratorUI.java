package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillsToCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignVehiclesToTaskController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
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

public class AssignSkillsToCollaboratorUI implements Initializable {
    @FXML
    private TableView collabList;
    @FXML
    private ListView skillList;

    private AssignSkillsToCollaboratorController ctrl = new AssignSkillsToCollaboratorController();
    private Optional<ArrayList<Collaborator>> collabs = Optional.empty();
    private ArrayList<Skill> skills = new ArrayList<>();

    /**
     * Initializes this functionality, creates and prepares the appropriate columns for the TableView
     * and ListView in use, and populates them.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        skillList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Optional<ArrayList<Skill>> allSkills = Optional.empty();
        try {
            allSkills = ctrl.getSkillList();
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get skills!"
                    , e.getMessage()).show();
            return;
        }
        if(allSkills.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get skills!"
                    , "No skills found!").show();
            return;
        }
        skills = allSkills.get();
        for(Skill skill : skills){
            skillList.getItems().add(skill.toString());
        }
        
        collabList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TableColumn<Collaborator, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        collabList.getColumns().add(column1);
        TableColumn<Collaborator, String> column2 = new TableColumn<>("ID Number");
        column2.setCellValueFactory(new PropertyValueFactory<>("identificationNumber"));
        collabList.getColumns().add(column2);
        TableColumn<Collaborator, String> column3 = new TableColumn<>("Email");
        column3.setCellValueFactory(new PropertyValueFactory<>("email"));
        collabList.getColumns().add(column3);

        try {
            collabs = ctrl.getCollaboratorList();
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get collaborators!"
                    , e.getMessage()).show();
            return;
        }
        if(collabs.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get collaborators!"
                    , "No collaborators found!").show();
            return;
        }
        for(Collaborator collaborator : collabs.get()){
            collabList.getItems().add(collaborator);
        }
    }

    /**
     * Attempts to assign the selected skills to the selected collaborator and provides the appropriate feedback.
     */
    @FXML
    private void assignSkillsToCollaborator() {
        ArrayList<Skill> selectedSkills = new ArrayList<>();
        if(collabList.getSelectionModel().getSelectedIndex() == -1){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to assign skills to collaborator!"
                    , "No collaborator selected!").show();
            return;
        }
        Collaborator selectedCollaborator = collabs.get().get(collabList.getSelectionModel().getSelectedIndex());
        ObservableList<Integer> selectedIndices = skillList.getSelectionModel().getSelectedIndices();
        for(Integer index : selectedIndices){
            selectedSkills.add(skills.get(index));
        }
        if(selectedSkills.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to assign skills to collaborator!"
                    , "No skills selected!").show();
            return;
        }
        try{
            Optional<ArrayList<Skill>> assignedSkills = ctrl.assignSkillsToCollaborator(selectedSkills, selectedCollaborator);
            if(assignedSkills.isEmpty()){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to assign skills to collaborator!"
                        , "Are you sure this isn't a duplicate assignment?").show();
                return;
            }
            String successMessage = "The following skills were assigned successfully: \n";
            for(Skill skill : assignedSkills.get()){
                successMessage += skill.toString()+"\n";
            }
            successMessage += "If any skill is missing from this list, it was either invalid or already present in the collaborator.";
            AlertUI.createAlert(Alert.AlertType.INFORMATION, Bootstrap.APP_TITLE, "Success!"
                    , successMessage).show();
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to assign skills to collaborator!"
                    , e.getMessage()).show();
        }
    }

    /**
     * Switches to the RegisterGreenSpaceUI scene.
     */
    @FXML
    public void toUS20() throws IOException {
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterCollaborator.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the AssignVehicleToTaskUI scene.
     */
    @FXML
    public void toUS26() throws IOException {
        Stage stage = (Stage) collabList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignVehicleToTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the GenerateTeamUI scene.
     */
    @FXML
    public void toUS5() throws IOException {
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
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
        Stage stage = (Stage) collabList.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListVehiclesRequiringCheckup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
