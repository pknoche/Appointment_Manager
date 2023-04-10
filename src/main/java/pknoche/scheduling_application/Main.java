package pknoche.scheduling_application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.database.DatabaseConnection;
import pknoche.scheduling_application.model.Appointment;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/pknoche/scheduling_application/GUI/LoginMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseConnection.openConnection();

        /*AppointmentDAO appointmentDAO = new AppointmentDAO();
        ObservableList<Appointment> allAppointments = appointmentDAO.getAll();
        System.out.println(allAppointments.size());*/

        launch();
        DatabaseConnection.closeConnection();
    }
}