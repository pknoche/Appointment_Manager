package pknoche.scheduling_application.model;

/**
 * Object model for users.
 */
public class User {
    /**
     * ID of the user.
     */
    private final int User_ID;
    /**
     * Name of the user.
     */
    private final String User_Name;

    public User(int user_ID, String user_Name) {
        User_ID = user_ID;
        User_Name = user_Name;
    }

    /**
     * @return user ID
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     * @return username
     */
    public String getUser_Name() {
        return User_Name;
    }

    /**
     * Displays users as a concatenation of the user ID and the username.
     *
     * @return concatenation of user ID and username
     */
    @Override
    public String toString() {
        return User_ID + " - " + User_Name;
    }
}