import Models.Employee;
import Models.Task;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Default (Template) Project -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class Main {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/taskmanagementsystem";
    public static Connection connection;
    public static MainScreen mainScreen;
    public static Employee employee;
    public static void main(String[] args) {
        employee = new Employee();
        employee.setId(0);
        try{
            connection = DriverManager.getConnection(DATABASE_URL, "root", "platinum1");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        System.out.println("Hello world!");
        mainScreen= new MainScreen();
        new LoginScreen();
        //todo documents adition
        //todo project addition and edit

    }
}