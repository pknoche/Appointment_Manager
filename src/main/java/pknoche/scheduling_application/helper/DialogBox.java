package pknoche.scheduling_application.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Contains methods for generating various dialog boxes and displaying them to the user.
 */
public class DialogBox {

    /**
     * Generates an error message with the specified message.
     *
     * @param message message to be displayed to user
     */
    public static void generateErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Generates a confirmation message with an OK option to continue and a cancel option that the user can select.
     *
     * @param message message to be displayed to the user
     * @return true if the user clicked "OK"
     */
    public static boolean generateConfirmationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /**
     * Generates an information message with the specified message.
     *
     * @param message message to be displayed to the user
     */
    public static void generateInformationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
