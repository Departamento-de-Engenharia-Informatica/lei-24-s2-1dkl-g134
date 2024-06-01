package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginUI implements Initializable {
    private final AuthenticationController ctrl = new AuthenticationController();

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void attemptLogin() {
        if(ctrl.doLogin(username.getText(), password.getText())) {
            try{
                Stage mainStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.setTitle(Bootstrap.APP_TITLE);
                mainStage.setResizable(false);
                Stage loginStage = (Stage) password.getScene().getWindow();
                loginStage.close();
                mainStage.show();
            }catch(Exception e){
                AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE, "Error on login!",
                        "Could not open the main menu!").show();
            }
        }else{
            AlertUI.createAlert(Alert.AlertType.WARNING, Bootstrap.APP_TITLE, "Invalid credentials!",
            "No account was found that matches these credentials! Please double-check and try again.").show();
        }
    }
}
