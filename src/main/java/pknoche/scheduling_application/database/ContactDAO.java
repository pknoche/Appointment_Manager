package pknoche.scheduling_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pknoche.scheduling_application.helper.DialogBox;
import pknoche.scheduling_application.model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAO {
    private static final ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public static ObservableList<Contact> getAll() {
        if(allContacts.isEmpty()) {
                String sql = "SELECT Contact_ID, Contact_Name FROM client_schedule.contacts;";
                try(PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        int Contact_ID = rs.getInt("Contact_ID");
                        String Contact_Name = rs.getString("Contact_Name");
                        Contact contact = new Contact(Contact_ID, Contact_Name);
                        allContacts.add(contact);
                    }
                } catch (SQLException e) {
                    DialogBox.generateErrorMessage("Error retrieving contacts from database.");
                    System.out.println(e.getMessage());
                }
        }
        return allContacts;
    }

    public static Contact get(int contactId) {
        for(Contact c : allContacts) {
            if(c.getContact_ID() == contactId) {
                return c;
            }
        }
        return null;
    }
}
