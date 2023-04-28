package pknoche.scheduling_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pknoche.scheduling_application.database.DatabaseConnection;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main method called upon application launch.
 */
public class Main extends Application {

    /**
     * Gets the login screen resource path and creates new stage set to login screen. Sets language of login screen
     * to English or French based on user's system configuration.
     *
     * @param stage stage to show
     * @throws IOException if login screen resource(s) cannot be found
     */
    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pknoche.scheduling_application.localization.languages", Locale.getDefault());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/pknoche/scheduling_application/GUI/LoginMenu.fxml"), resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(resourceBundle.getString("windowTitle"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Establishes database connection, calls launch() to open JavaFX, and closes database connection upon
     * application exit.
     *
     * @param args code to execute upon application launch
     */
    public static void main(String[] args) {
        DatabaseConnection.openConnection();
        launch();
        DatabaseConnection.closeConnection();
    }
}