package pknoche.scheduling_application.reports;

import javafx.collections.transformation.FilteredList;
import pknoche.scheduling_application.database.AppointmentDAO;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.Contact;

/**
 * Generates and stores data needed for creating the Contact Schedule report.
 */
public class ContactSchedule {
    /**
     * Filtered list containing all appointments.
     */
    private static final FilteredList<Appointment> contactSchedule = new FilteredList<>(AppointmentDAO.getAll());

    /**
     * Filters appointments in the report by contact.
     * <p>
     * This method contains a lambda expression which uses the Predicate functional interface to filter the list
     * by contact.
     *
     * @param contact contact to generate report for
     * @return filtered list of appointments associated with specified contact
     */
    public static FilteredList<Appointment> getContactSchedule(Contact contact) {
        return contactSchedule.filtered(appointment -> appointment.getContact_ID() == contact.getContact_ID());
    }
}
