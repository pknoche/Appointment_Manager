package pknoche.scheduling_application.helper;

import javafx.collections.ObservableList;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Contains method for generating alert for upcoming appointments when logging into the program.
 */
public class AppointmentAlert {
    /**
     * Checks for upcoming appointments within 15 minutes of system time. Displays alert with information about
     * all appointments occurring within 15 minutes, or displays an alert if no appointments are occurring within
     * 15 minutes.
     */
    public static void execute() {
        ObservableList<Appointment> allAppointments = AppointmentDAO.getAll();
        LocalDateTime now = LocalDateTime.now();
        String messageHead;
        StringBuilder messageBody = new StringBuilder();
        int upcomingAppointments = 0;

        for (Appointment a : allAppointments) {
            if ((a.getStart().isAfter(now)) && (a.getStart().isBefore(now.plusMinutes(15)))) {
                messageBody.append(a.getTitle()).append(" (Appointment ID #").append(a.getAppointment_ID())
                        .append(") at ").append(TimeConversion.toFormattedString(a.getStart().toLocalTime())).
                        append(" on ").append(LocalDate.now()).append("\n");
                upcomingAppointments ++;
            }
        }
        if (upcomingAppointments == 0) {
            messageHead = "There are no upcoming appointments within the next 15 minutes.";
            DialogBox.generateInformationMessage(messageHead);
            return;
        } else if (upcomingAppointments == 1) {
            messageHead = "There is 1 upcoming appointment within the next 15 minutes:\n\n";
        } else {
            messageHead = "There are " + upcomingAppointments +
                    " upcoming appointments within the next 15 minutes:\n\n";
        }
        DialogBox.generateInformationMessage(messageHead + messageBody);
    }
}