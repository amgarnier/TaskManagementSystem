import DBModels.EmployeeDBOperations;
import DBModels.ManagerDBOperations;
import Models.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * FinalView -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class RegisterScreen extends  JFrame{
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfUserName;
    private JPasswordField tfPassword;
    private JPasswordField tfPassword2;
    private JTextField tfEmail;
    private JComboBox comboBox1;
    private JTextField tfPhoneNumber;
    private JButton createButton;
    private JPanel jPanel;
    private JTextField tfHireDate;

    public RegisterScreen(){

        EmployeeDBOperations employeeDBOperations = new EmployeeDBOperations(Main.connection);
        ManagerDBOperations managerDBOperations = new ManagerDBOperations(Main.connection);
        setTitle("Registration");

        List<Employee> ls = managerDBOperations.retrieveManagers();
        comboBox1.setModel(new DefaultComboBoxModel<Employee>(ls.toArray(new Employee[0])));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(jPanel);
        setVisible(true);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(tfPassword.getPassword());
                String password2 = String.valueOf(tfPassword2.getPassword());
                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Date sqlStartDate;
                Employee selectedManager = (Employee) comboBox1.getSelectedItem();
                int managerId = selectedManager.getId();
                if(password.equals(password2)){
                    try {
                        java.util.Date date = dateSimple.parse(tfHireDate.getText());
                        sqlStartDate = new Date(date.getTime());
                    } catch (ParseException ex) {
                        System.out.println("invalid Date try again");
                        throw new RuntimeException(ex);
                    }
                    Employee employee = new Employee(tfFirstName.getText(),tfLastName.getText(),sqlStartDate,tfUserName.getText(),password,tfEmail.getText(),managerId,tfPhoneNumber.getText());
                    Boolean result= employeeDBOperations.createItem(employee);
                    if (result==false){
                        JOptionPane.showMessageDialog(null, "Invalid User Creation");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Success please login");
                        dispose();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid User Creation");
                }


            }
        });
    }
}
