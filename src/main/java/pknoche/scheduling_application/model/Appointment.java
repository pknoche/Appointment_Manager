package pknoche.scheduling_application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.helper.TimeConversion;

import java.time.LocalDateTime;

public class Appointment {
    private static final ObservableList<String> appointmentTypes = FXCollections.observableArrayList("New Client",
            "Planning Session", "Status Update", "De-Briefing");
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;
    private String Customer_Name;
    private String User_Name;
    private String Contact_Name;

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

    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public LocalDateTime getStart() {
        return Start;
    }

    public String getFormattedStart() {
        return TimeConversion.toFormattedString(getStart());
    }

    public void setStart(LocalDateTime start) {
        Start = start;
    }

    public LocalDateTime getEnd() {
        return End;
    }

    public String getFormattedEnd() {
        return TimeConversion.toFormattedString(getEnd());
    }

    public void setEnd(LocalDateTime end) {
        End = end;
    }

    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(LocalDateTime last_Update) {
        Last_Update = last_Update;
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
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
}