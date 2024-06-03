package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainCollabUI implements Initializable {
    @FXML
    private Label title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Switches to the ListTasksBetweenDatesUI scene.
     */
    @FXML
    public void goToList() throws IOException {
        Stage stage = (Stage) title.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListTasksBetweenDates.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * Switches to the CompleteTaskUI scene.
     */
    @FXML
    public void goToComplete() throws IOException {
        Stage stage = (Stage) title.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CompleteTask.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
