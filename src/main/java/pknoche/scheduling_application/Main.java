package pknoche.scheduling_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pknoche.scheduling_application.database.DatabaseConnection;
import pknoche.scheduling_application.helper.DialogBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/pknoche/scheduling_application/GUI/LoginMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("pknoche.scheduling_application.localization.languages", Locale.getDefault());
            stage.setTitle(resourceBundle.getString("windowTitle"));
        } catch (MissingResourceException e) {
            DialogBox.generateErrorMessage("Could not find language properties file. Loading default language.");
            stage.setTitle("Login");
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseConnection.openConnection();
        launch();
        DatabaseConnection.closeConnection();
    }
}