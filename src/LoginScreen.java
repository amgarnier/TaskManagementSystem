import DBModels.EmployeeDBOperations;
import Models.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * FinalView -- Brief statement as to file purpose
 * CSIS 643 -- B032023
 * Citations as needed
 */
public class LoginScreen extends JFrame {

    private JPasswordField tfPassword;
    private JTextField tfUserName;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel loginPanel;

    public LoginScreen(){


        int xpos = Main.mainScreen.getBounds().x+500-200;
        int ypos = Main.mainScreen.getBounds().y+400-125;
        setBounds(xpos,ypos,400,250);
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(loginPanel);
        tfUserName.setText("agarnier");
        tfPassword.setText("password");
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(tfPassword.getPassword());
                Employee employee = new Employee(tfUserName.getText(),password);
                EmployeeDBOperations employeeDBOperations = new EmployeeDBOperations(Main.connection);
                int empId = new EmployeeDBOperations(Main.connection).login(employee);
                if(empId !=0){
                    Main.employee = employeeDBOperations.retrieveItem(empId);
                    Main.mainScreen.updateTables();
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect user name and password");
                    tfPassword.setText("");

                }

            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int xpos = getBounds().x;
                int ypos = getBounds().y;
                new RegisterScreen().setBounds(xpos,ypos,600,400);;
            }
        });
    }
}
