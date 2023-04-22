package pknoche.scheduling_application.helper;

import javafx.collections.ObservableList;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.model.Appointment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AppointmentAlert {
    public static void execute() {
        ObservableList<Appointment> allAppointments = AppointmentDAO.getAll();
        for(Appointment a: allAppointments) {
            long timeDifference = ChronoUnit.MINUTES.between(LocalDateTime.now(), a.getStart());
            if (timeDifference >= 0 && timeDifference <= 15) {
                DialogBox.generateInformationMessage(a.getType() + " appointment (Appointment ID #" +
                        a.getAppointment_ID() + ") starting in " + timeDifference + " minutes.");
            }
        }
    }
}
