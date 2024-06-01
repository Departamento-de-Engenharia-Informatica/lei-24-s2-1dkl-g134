package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class AlertUI {

    /**
     * Creates an alert to show the user.
     * @param alertType The type of alert (in an Alert.AlertType enumerator)
     * @param title The alert window's title as a String
     * @param header The alert header as a String
     * @param message The alert message.
     * @return The Alert to show the user, in the form of a JavaFX Alert object
     */
    public static Alert createAlert(Alert.AlertType alertType, String title, String header, String message) {
        Alert alerta = new Alert(alertType);

        alerta.setTitle(title);
        alerta.setHeaderText(header);
        alerta.setContentText(message);

        if (alertType == Alert.AlertType.CONFIRMATION) {
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alerta.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        }

        return alerta;
    }
}
