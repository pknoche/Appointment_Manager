package pknoche.scheduling_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pknoche.scheduling_application.database.DatabaseConnection;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pknoche.scheduling_application.localization.languages", Locale.getDefault());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/pknoche/scheduling_application/GUI/LoginMenu.fxml"), resourceBundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(resourceBundle.getString("windowTitle"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseConnection.openConnection();
        launch();
        DatabaseConnection.closeConnection();
    }
}