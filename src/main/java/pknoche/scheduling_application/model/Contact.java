package pknoche.scheduling_application.model;

public class Contact {
    private int Contact_ID;
    private String Contact_Name;

    public Contact(int contact_ID, String contact_Name) {
        Contact_ID = contact_ID;
        Contact_Name = contact_Name;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    @Override
    public String toString() {
        return Contact_ID + " - " + Contact_Name;
    }
}
