package pknoche.scheduling_application.helper.reports;

import javafx.collections.transformation.FilteredList;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.Contact;

public class ContactSchedule {
    private static final FilteredList<Appointment> contactSchedule = new FilteredList<>(AppointmentDAO.getAll());

    public static FilteredList<Appointment> getContactSchedule(Contact contact) {
        return contactSchedule.filtered(appointment -> appointment.getContact_ID() == contact.getContact_ID());
    }
}
