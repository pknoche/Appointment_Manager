package pknoche.scheduling_application.helper;

import javafx.collections.ObservableList;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.model.Appointment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AppointmentAlert {
    public static void execute() {
        ObservableList<Appointment> allAppointments = AppointmentDAO.getAll();
        LocalDateTime now = LocalDateTime.now();
        String messageHead;
        StringBuilder messageBody = new StringBuilder();
        int upcomingAppointments = 0;

        for (Appointment a : allAppointments) {
            if ((a.getStart().isAfter(now)) && (a.getStart().isBefore(now.plusMinutes(15)))) {
                long timeUntil = ChronoUnit.MINUTES.between(LocalDateTime.now(), a.getStart());
                messageBody.append(a.getTitle()).append(" (Appointment ID #").append(a.getAppointment_ID())
                        .append(") in ").append(timeUntil).append(" minutes\n");
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