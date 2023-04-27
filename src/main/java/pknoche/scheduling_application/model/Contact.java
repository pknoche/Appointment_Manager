package pknoche.scheduling_application.model;

public class Contact {
    private final int Contact_ID;
    private final String Contact_Name;

    public Contact(int contact_ID, String contact_Name) {
        Contact_ID = contact_ID;
        Contact_Name = contact_Name;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    @Override
    public String toString() {
        return Contact_ID + " - " + Contact_Name;
    }
}