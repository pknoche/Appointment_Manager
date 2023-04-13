package pknoche.scheduling_application.model;

import java.sql.Time;
import java.sql.Timestamp;

public class User {
    private int User_ID;
    private String User_Name;

    public User(int user_ID, String user_Name) {
        User_ID = user_ID;
        User_Name = user_Name;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public String getUser_Name() {
        return User_Name;
    }
}