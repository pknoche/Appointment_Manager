package pknoche.scheduling_application.helper;

import javafx.scene.control.Alert;

public abstract class DialogBox {

    public static void generateErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
