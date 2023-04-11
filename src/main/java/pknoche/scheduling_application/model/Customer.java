package pknoche.scheduling_application.model;

import pknoche.scheduling_application.helper.TimeConversion;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Customer {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;
    private int Division_ID;

    public Customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, LocalDateTime create_Date, String created_By, LocalDateTime last_Update, String last_Updated_By, int division_ID) {
        Customer_ID = customer_ID;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Division_ID = division_ID;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPostal_Code() {
        return Postal_Code;
    }

    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    public String getFormattedCreate_Date() {
        return TimeConversion.toFormattedString(getCreate_Date());
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

    public String getFormattedLast_Update() {
        return TimeConversion.toFormattedString(getLast_Update());
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

    public int getDivision_ID() {
        return Division_ID;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }
}
