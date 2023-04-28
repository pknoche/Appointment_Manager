# Scheduling Application for C195 - Software II - Advanced Java Concepts
Author: Philipp Knoche  <br>
Version: 1.0 <br>
Date: April 28, 2023

### About:
This is a scheduling application designed to work with a globally distributed consulting organization. It provides
functionality for appointment and customer management. The application communicates with a database server that can be
remotely hosted and accessed by multiple users. It allows for user authentication and create, read, update, delete 
operations on the database, including data validation. The application is designed to work across multiple time zones
and is accessible in English and French.

#### Technical Information:
IDE: IntelliJ IDEA 2023.1 (Community Edition) <br>
SDK: Java 17.0.6 <br>
JavaFX Version: 17.0.2 <br>
MySQL Connector Version: mysql-connector-j-8.0.31

### Instructions:
#### Login Screen:
Upon launching the program, you will be greeted with the login screen. This screen is automatically translated
to English or French based on your system configuration. It contains text fields for your database username and 
password. Your time zone is shown at the bottom of the window. After entering your username and password, click the 
"Login" button. If the username and password were correct, the main menu will automatically launch. If the username
and password were incorrect, you will be prompted to try again.

#### Main Menu:
After logging in, a dialog box will be generated that displays whether there are any upcoming appointments
within the next 15 minutes. If there are upcoming appointments within the next 15 minutes, a summary of these 
appointments will be displayed. After reviewing this information, click "OK" to close the dialog box. <br>

The main menu contains three tabs at the top that are used to navigate between the Appointments, Customers, and Reports
section of the application. Clicking any of these tabs will take you to the corresponding section of the program.

##### Appointments Tab:
The appointments tab is shown by default when first logging in. It contains radio buttons at the top of the screen for
filtering the table to show all appointments, appointments in the current week, and appointments in the current month.
Clicking any of these appointments will filter the list accordingly. <br>

The table shows a list of appointments. It is sorted by default according to the appointment start time, but can be
sorted by any column by clicking on the column header. <br>

###### Creating an appointment:
To create a new appointment, click on the "Create New Appointment" button at the bottom of the window. This will
launch the create appointment form. Fill out the fields in the form and click the save button at the bottom to 
create a new appointment. Please note that the appointment ID is automatically generated and cannot be overridden.
After filling out the fields, click the "Save" button. Please note that data validation is performed on the values 
entered. This validation checks to ensure that no fields are left blank, the date is not in the past, the start time
is not set to occur after the end time, and the appointment time does not overlap with another existing appointment 
for the customer the appointment is being created for. If the data passes validation, a success message will be 
displayed to confirm that the appointment was created. If the data does not pass validation, a message will be 
displayed indicating what needs to be corrected.

###### Modifying an appointment:
Modifying an appointment is identical to creating an appointment, except the fields are pre-filled using the
information from the appointment being modified. To modify an appointment, select an appointment in the table by
clicking on it, and then click the "Modify Appointment" button at the bottom of the screen.

###### Deleting an appointment:
To delete an appointment, select the appointment to be deleted by clicking on it in the table and then click the
"Delete Appointment" button at the bottom of the screen. A confirmation dialog box will be displayed. Click the "OK"
button to proceed with deleting the appointment. After deletion, a message will be displayed indicating that the
appointment was successfully deleted.

##### Customers Tab:
The customers tab contains identical functionality to the appointments tab, and the steps are the same for creating,
modifying, and deleting customers as they are appointments. The only functional difference from appointments is that,
when deleting a customer, appointments associated with that customer will be deleted in addition to the customer.

##### Reports Tab:
The reports tab contains three different reports that display information about appointments and customers in the
database. These reports are automatically updated whenever appointments or customers are changed.

###### Appointments By Month Report:
This report shows a summary of all appointment types by month for the year that is selected. The year can be 
toggled between the current year, the last year, and the next year by selecting an option from the "Year" combo box at
the top of the report. The current year is displayed by default when initially viewing the report.

###### Contact Schedule Report:
The contact schedule report shows a list of all appointments associated with the contact specified in the combo box
at the top of the report.

###### Customers By Division Report:
The customers by division report displays a summation of all customers in the database by division. Only divisions
that have at least one customer assigned to them are displayed.

#### Exiting the program:
To exit the program, simply click the X button at the top of the window. There is no need to manually save anything 
before exiting since data is automatically synchronized with the database.