package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.model.Appointment;
import pknoche.scheduling_application.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class AppointmentDAO implements DAO<Appointment>{
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    @Override
    public void Create(Appointment appointment) {

    }

    @Override
    public void Read(Appointment appointment) {

    }

    @Override
    public void Update(Appointment appointment) {

    }

    @Override
    public void Delete(Appointment appointment) {

    }

    @Override
    public ObservableList<Appointment> getAll() {
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
            Timestamp Start = rs.getTimestamp("Start");
            Timestamp End = rs.getTimestamp("End");
            Timestamp Create_Date = rs.getTimestamp("Create_Date");
            String Created_By = rs.getString("Created_By");
            Timestamp Last_Update = rs.getTimestamp("Last_Update");
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
