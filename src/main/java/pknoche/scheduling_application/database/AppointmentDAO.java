package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class AppointmentDAO {
        public static void create(Appointment appointment) {
        try {
            String sql = "INSERT INTO client_schedule.appointments (Title, Description, Location, Type, Start, " +
                    "End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, " +
                    "Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
            ps.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
            ps.setTimestamp(7, Timestamp.valueOf(appointment.getCreate_Date()));
            ps.setString(8, appointment.getCreated_By());
            ps.setTimestamp(9, Timestamp.valueOf(appointment.getLast_Update()));
            ps.setString(10, appointment.getLast_Updated_By());
            ps.setInt(11, appointment.getCustomer_ID());
            ps.setInt(12, appointment.getUser_ID());
            ps.setInt(13, appointment.getContact_ID());
            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating new appointment");
            System.out.println(e.getMessage());
        }

    }

    public static void read(String appointmentId) {

    }

    public static void update(String appointmentId) {
    }

    public static void delete(String appointmentId) {
    }

    public static ObservableList<Appointment> getAll() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM client_schedule.appointments";
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Type = rs.getString("Type");
            LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
            LocalDateTime Create_Date = rs.getTimestamp("Create_Date").toLocalDateTime();
            String Created_By = rs.getString("Created_By");
            LocalDateTime Last_Update = rs.getTimestamp("Last_Update").toLocalDateTime();
            String Last_Updated_By = rs.getString("Last_Updated_By");
            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");
            int Contact_ID = rs.getInt("Contact_ID");
            Appointment appointment = new Appointment(Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID);
            allAppointments.add(appointment);
        }
        } catch (SQLException e) {
            System.out.println("Error retrieving appointments from database.");
        }
        return allAppointments;
    }
}
