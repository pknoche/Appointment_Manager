package pknoche.scheduling_application.model;

import pknoche.scheduling_application.helper.TimeConversion;

import java.time.LocalDateTime;

public class Customer {
    private final int Customer_ID;
    private final String Customer_Name;
    private final String Address;
    private final String Postal_Code;
    private final String Phone;
    private final LocalDateTime Create_Date;
    private final String Created_By;
    private final LocalDateTime Last_Update;
    private final String Last_Updated_By;
    private final int Division_ID;

    public Customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone,
                    LocalDateTime create_Date, String created_By, LocalDateTime last_Update, String last_Updated_By,
                    int division_ID) {
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

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPostal_Code() {
        return Postal_Code;
    }

    public String getPhone() {
        return Phone;
    }

    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    public String getFormattedCreate_Date() {
        return TimeConversion.toFormattedString(getCreate_Date());
    }

    public String getCreated_By() {
        return Created_By;
    }

    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    public String getFormattedLast_Update() {
        return TimeConversion.toFormattedString(getLast_Update());
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public int getDivision_ID() {
        return Division_ID;
    }

    @Override
    public String toString() {
        return Customer_ID + " - " + Customer_Name;
    }
}
