package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginUI.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle(Bootstrap.APP_TITLE);
            stage.setScene(scene);

            /*stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alerta = AlertaUI.criarAlerta(Alert.AlertType.CONFIRMATION, TITULO_APLICACAO,
                            "Confirmação da ação.", "Deseja mesmo encerrar a aplicação?");

                    if (alerta.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    }
                    else {
                        AplicacaoController appController = ((JanelaPrincipalUI) loader.getController()).getAplicacaoController();
                        if (!appController.isListaTelefonicaVazia() && !appController.guardar()) {
                            AlertaUI.criarAlerta(Alert.AlertType.ERROR, TITULO_APLICACAO, "Exportar Lista.",
                                    "Problema a exportar a lista de contactos!").show();
                        }
                    }
                }
            });*/
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            AlertUI.createAlert(Alert.AlertType.ERROR, Bootstrap.APP_TITLE,
                    "Error on application start!", ex.getCause().toString()).show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
