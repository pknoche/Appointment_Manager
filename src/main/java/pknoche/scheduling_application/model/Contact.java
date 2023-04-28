package pknoche.scheduling_application.model;

/**
 * Object model for contacts.
 */
public class Contact {
    /**
     * ID of contact.
     */
    private final int Contact_ID;
    /**
     * Name of contact.
     */
    private final String Contact_Name;

    public Contact(int contact_ID, String contact_Name) {
        Contact_ID = contact_ID;
        Contact_Name = contact_Name;
    }

    /**
     * @return contact ID
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * @return contact name
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     * Displays Contact as concatenation of contact ID and contact name.
     *
     * @return concatenation of contact ID and contact name
     */
    @Override
    public String toString() {
        return Contact_ID + " - " + Contact_Name;
    }
}