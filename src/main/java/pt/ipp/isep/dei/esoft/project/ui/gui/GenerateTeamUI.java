package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GenerateTeamUI implements Initializable {
    @FXML
    private TextField minSize;
    @FXML
    private TextField maxSize;
    @FXML
    private ListView allSkills;
    @FXML
    private ListView selectedSkills;
    @FXML
    private TableView teamSuggestion;

    private ArrayList<Skill> allSkillList = new ArrayList<Skill>();
    private ArrayList<Skill> selectedSkillList = new ArrayList<Skill>();
    private ArrayList<Collaborator> teamProposal = new ArrayList<Collaborator>();

    private GenerateTeamController ctrl = new GenerateTeamController();

    /**
     * Initializes this functionality, prepares and creates the ListViews and TableView in use,
     * and populates the "All Skills" ListView.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!ctrl.doCollaboratorsExist()){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Team generation impossible!"
                    , "No collaborators registered in the system!").show();
            return;
        }

        allSkills.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        selectedSkills.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        teamSuggestion.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        TableColumn<Collaborator, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        teamSuggestion.getColumns().add(column1);
        TableColumn<Collaborator, String> column2 = new TableColumn<>("ID Number");
        column2.setCellValueFactory(new PropertyValueFactory<>("identificationNumber"));
        teamSuggestion.getColumns().add(column2);
        TableColumn<Collaborator, String> column3 = new TableColumn<>("Email");
        column3.setCellValueFactory(new PropertyValueFactory<>("email"));
        teamSuggestion.getColumns().add(column3);

        Optional<ArrayList<Skill>> registeredSkills = Optional.empty();
        try {
            registeredSkills = ctrl.getSkillList();
        } catch (Exception e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to get skills!"
                    , e.getMessage()).show();
            return;
        }
        if(registeredSkills.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to get skills!"
                    , "No skills found!").show();
            return;
        }
        allSkillList = registeredSkills.get();
        for(Skill skill : allSkillList){
            allSkills.getItems().add(skill.toString());
        }
    }

    /**
     * Adds a skill to the current selection.
     */
    @FXML
    public void selectSkill(){
        if(allSkills.getSelectionModel().getSelectedIndex() == -1){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to select skill!"
                    , "No skill selected!").show();
            return;
        }
        selectedSkillList.add(allSkillList.get(allSkills.getSelectionModel().getSelectedIndex()));
        selectedSkills.getItems().add(allSkillList.get(allSkills.getSelectionModel().getSelectedIndex()).toString());
        ctrl.resetLists();
    }

    /**
     * Removes a skill from the current selection.
     */
    @FXML
    public void removeSkill(){
        if(selectedSkills.getSelectionModel().getSelectedIndex() == -1){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to remove skill!"
                    , "No skill selected!").show();
            return;
        }
        selectedSkillList.remove(selectedSkills.getSelectionModel().getSelectedIndex());
        selectedSkills.getItems().remove(selectedSkills.getSelectionModel().getSelectedIndex());
        ctrl.resetLists();
    }

    /**
     * Attempts to generate a proposal in accordance with the current inputs and provides the appropriate
     * feedback in case of an error. Otherwise, populates the TableView with the collaborators of the new
     * proposal.
     */
    @FXML
    public void generateTeam(){
        if(!teamProposal.isEmpty()){
            ctrl.blacklistTeamProposal(teamProposal);
        }
        teamProposal.clear();
        teamSuggestion.getItems().clear();
        if(selectedSkillList.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to generate team!"
                    , "No skills selected!").show();
            return;
        }
        int minSizeNumber = 0, maxSizeNumber = 0;
        try{
            minSizeNumber = Integer.parseInt(minSize.getText());
            maxSizeNumber = Integer.parseInt(maxSize.getText());
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to generate team!"
                    , "Min team size and max team size must be numbers!").show();
            return;
        }
        if(minSizeNumber > maxSizeNumber){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to generate team!"
                    , "Min team size cannot be above max team size!").show();
            return;
        }

        ArrayList<Skill> requiredSkillsNoDuplicates = new ArrayList<>();
        ArrayList<Integer> skillRepresentation = new ArrayList<>();
        for(Skill skill : selectedSkillList){
            if(requiredSkillsNoDuplicates.contains(skill)){
                skillRepresentation.set(requiredSkillsNoDuplicates.indexOf(skill), skillRepresentation.get(requiredSkillsNoDuplicates.indexOf(skill)) + 1);
            }else{
                requiredSkillsNoDuplicates.add(skill);
                skillRepresentation.add(1);
            }
        }
        Integer maxRepresentation = -1;
        for(Integer representation : skillRepresentation){
            if(representation > maxRepresentation){
                maxRepresentation = representation;
            }
        }

        if(minSizeNumber < maxRepresentation || minSizeNumber > selectedSkillList.size()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to generate team!"
                    , "Min team size impossible according to selected skills!").show();
            return;
        }

        Optional<ArrayList<Collaborator>> generatedTeam = Optional.empty();
        try{
            generatedTeam = ctrl.generateTeamProposal(minSizeNumber, maxSizeNumber, selectedSkillList);
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to generate team!"
                    , e.getMessage()).show();
            return;
        }
        if(generatedTeam.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to generate team!"
                    , "No possible team found according to the settings in place!").show();
            return;
        }
        teamProposal = generatedTeam.get();
        for(Collaborator collaborator : teamProposal){
            teamSuggestion.getItems().add(collaborator);
        }
    }

    /**
     * Attempts to register the currently proposed team into the system and provides the appropriate feedback.
     */
    @FXML
    public void acceptTeam(){
        if(teamProposal.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to register team!"
                    , "No team proposed!").show();
            return;
        }
        if(selectedSkillList.isEmpty()){
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Failed to register team!"
                    , "No skills selected!").show();
            return;
        }
        try{
            Optional<Team> createdTeam = ctrl.registerTeam(teamProposal, selectedSkillList);
            if(createdTeam.isEmpty()){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to register team!"
                        , "Are you sure this isn't a duplicate registration?").show();
            }else{
                AlertUI.createAlert(Alert.AlertType.INFORMATION, Bootstrap.APP_TITLE, "Success!"
                        , "Team registered successfully!").show();
            }
        }catch(Exception e){
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Failed to register team!"
                    , e.getMessage()).show();
        }
    }

    /**
     * Switches to the RegisterGreenSpaceUI scene.
     */
    @FXML
    public void toUS20() throws IOException {
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PostponeTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the CancelTaskUI scene.
     */
    @FXML
    public void toUS25() throws IOException {
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CancelTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the AssignVehicleToTaskUI scene.
     */
    @FXML
    public void toUS26() throws IOException {
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignSkillsToCollaborator.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the MainAdminUI scene.
     */
    @FXML
    public void toStart() throws IOException {
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuGSM.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the RegisterVehicleUI scene.
     */
    @FXML
    public void toUS6() throws IOException {
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
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
        Stage stage = (Stage) teamSuggestion.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListVehiclesRequiringCheckup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
