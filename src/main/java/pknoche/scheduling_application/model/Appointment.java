package pknoche.scheduling_application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.helper.TimeConversion;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Object model for appointments.
 */
public class Appointment implements Comparable<Appointment> {
    /**
     * List of possible appointment types.
     */
    private static final ObservableList<String> appointmentTypes = FXCollections.observableArrayList("New Client",
            "Planning Session", "Status Update", "De-Briefing");
    /**
     * ID of the appointment.
     */
    private final int Appointment_ID;
    /**
     * Title of the appointment.
     */
    private final String Title;
    /**
     * Description of the appointment.
     */
    private final String Description;
    /**
     * Location of the appointment.
     */
    private final String Location;
    /**
     * Type of the appointment.
     */
    private final String Type;
    /**
     * Start time and date of the appointment.
     */
    private final LocalDateTime Start;
    /**
     * End time and date of the appointment.
     */
    private final LocalDateTime End;
    /**
     * Create date of the appointment.
     */
    private final LocalDateTime Create_Date;
    /**
     * Username of the user who created the appointment.
     */
    private final String Created_By;
    /**
     * Time and date of the last update to the appointment.
     */
    private final LocalDateTime Last_Update;
    /**
     * Username of the last user who updated the appointment.
     */
    private final String Last_Updated_By;
    /**
     * Customer ID associated with the appointment.
     */
    private final int Customer_ID;
    /**
     * User ID associated with the appointment.
     */
    private final int User_ID;
    /**
     * Contact ID associated with the appointment.
     */
    private final int Contact_ID;
    /**
     * Customer name associated with the appointment.
     */
    private final String Customer_Name;
    /**
     * Username associated with the appointment.
     */
    private final String User_Name;
    /**
     * Contact name associated with the appointment.
     */
    private final String Contact_Name;


    public Appointment(int appointment_ID, String title, String description, String location, String type, LocalDateTime start,
                       LocalDateTime end, LocalDateTime create_Date, String created_By, LocalDateTime last_Update,
                       String last_Updated_By, int customer_ID, int user_ID, int contact_ID, String customer_Name,
                       String user_Name, String contact_Name) {
        Appointment_ID = appointment_ID;
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Customer_ID = customer_ID;
        User_ID = user_ID;
        Contact_ID = contact_ID;
        Customer_Name = customer_Name;
        User_Name = user_Name;
        Contact_Name = contact_Name;
    }

    /**
     * @return list of appointment types
     */
    public static ObservableList<String> getAppointmentTypes() {
        return FXCollections.observableArrayList(appointmentTypes);
    }

    /**
     * @return appointment ID
     */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     * @return appointment title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @return appointment description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @return appointment location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * @return appointment type
     */
    public String getType() {
        return Type;
    }

    /**
     * @return appointment start time/date
     */
    public LocalDateTime getStart() {
        return Start;
    }

    /**
     * @return formatted string of appointment start time/date
     */
    public String getFormattedStart() {
        return TimeConversion.toFormattedString(getStart());
    }

    /**
     * @return appointment end time/date
     */
    public LocalDateTime getEnd() {
        return End;
    }

    /**
     * @return formatted string of appointment end time/date
     */
    public String getFormattedEnd() {
        return TimeConversion.toFormattedString(getEnd());
    }

    /**
     * @return appointment create time/date
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * @return appointment created by username
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * @return appointment last update time/date
     */
    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    /**
     * @return appointment last updated by username
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * @return appointment customer ID
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * @return appointment user ID
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     * @return appointment contact ID
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * @return concatenation of appointment customer ID and customer name
     */
    public String getCustomer_IDAndName() {
        return Customer_ID + " - " + Customer_Name;
    }

    /**
     * @return concatenation of appointment user ID and username
     */
    public String getUser_IDAndName() {
        return User_ID + " - " + User_Name;
     }

    /**
     * @return concatenation of appointment contact ID and contact name
     */
    public String getContact_IDAndName() {
        return Contact_ID + " - " + Contact_Name;
    }

    /**
     * Sorts appointments by start time/date
     * @param compareAppointment the appointment to be compared to this appointment
     * @return negative integer if Appointment is less than compareAppointment, zero if appointments are equal, and
     * positive integer if Appointment is greater than compareAppointment
     */
    @Override
    public int compareTo(Appointment compareAppointment) {
        LocalDateTime now = LocalDateTime.now();
        long timeUntilCompare = ChronoUnit.MINUTES.between(now, compareAppointment.getStart());
        long timeUntilThis = ChronoUnit.MINUTES.between(now, this.getStart());
        return (int) (timeUntilThis - timeUntilCompare);
    }
}