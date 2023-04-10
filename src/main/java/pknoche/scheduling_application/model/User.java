package pknoche.scheduling_application.model;

import java.sql.Time;
import java.sql.Timestamp;

public class User {
    private int User_ID;
    private String User_Name;
    private String password;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private Timestamp Last_Updated_By;

    public User(int user_ID, String user_Name, String password, Timestamp create_Date, String created_By, Timestamp last_Update, Timestamp last_Updated_By) {
        User_ID = user_ID;
        User_Name = user_Name;
        this.password = password;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(Timestamp create_Date) {
        Create_Date = create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public Timestamp getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    public Timestamp getLast_Updated_By() {
        return Last_Updated_By;
    }

    public void setLast_Updated_By(Timestamp last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }
}
