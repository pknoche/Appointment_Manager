package pknoche.scheduling_application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.helper.TimeConversion;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Appointment implements Comparable<Appointment> {
    private static final ObservableList<String> appointmentTypes = FXCollections.observableArrayList("New Client",
            "Planning Session", "Status Update", "De-Briefing");
    private final int Appointment_ID;
    private final String Title;
    private final String Description;
    private final String Location;
    private final String Type;
    private final LocalDateTime Start;
    private final LocalDateTime End;
    private final LocalDateTime Create_Date;
    private final String Created_By;
    private final LocalDateTime Last_Update;
    private final String Last_Updated_By;
    private final int Customer_ID;
    private final int User_ID;
    private final int Contact_ID;
    private final String Customer_Name;
    private final String User_Name;
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

    public static ObservableList<String> getAppointmentTypes() {
        return FXCollections.observableArrayList(appointmentTypes);
    }

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getLocation() {
        return Location;
    }

    public String getType() {
        return Type;
    }

    public LocalDateTime getStart() {
        return Start;
    }

    public String getFormattedStart() {
        return TimeConversion.toFormattedString(getStart());
    }

    public LocalDateTime getEnd() {
        return End;
    }

    public String getFormattedEnd() {
        return TimeConversion.toFormattedString(getEnd());
    }

    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public String getCustomer_IDAndName() {
        return Customer_ID + " - " + Customer_Name;
    }

    public String getUser_IDAndName() {
        return User_ID + " - " + User_Name;
     }

    public String getContact_IDAndName() {
        return Contact_ID + " - " + Contact_Name;
    }

    @Override
    public int compareTo(Appointment compareAppointment) {
        LocalDateTime now = LocalDateTime.now();
        long timeUntilCompare = ChronoUnit.MINUTES.between(now, compareAppointment.getStart());
        long timeUntilThis = ChronoUnit.MINUTES.between(now, this.getStart());
        return (int) (timeUntilThis - timeUntilCompare);
    }
}