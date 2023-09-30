package Models;

import Models.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 *
 * Citations as needed
 */
public class EmployeesTableModel extends AbstractTableModel {

    private ArrayList<Employee> employeeList;

    public EmployeesTableModel(ArrayList<Employee> contacts) {
        this.employeeList = contacts;
    }

    public int getColumnCount() {
        return 9;
        // return however many columns you want
    }

    public int getRowCount() {
        return employeeList.size();
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Models.Employee Id";
            case 1:
                return "First Name";
            case 2:
                return "Last Name";
            case 3:
                return "Hire Date";
            case 4:
                return "User Name";
            case 5:
                return "Password";
            case 6:
                return "Email";
            case 7:
                return "Manager Id";
            case 8:
                return "Phone Number";
        }
        return "Null";
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee employee = employeeList.get(rowIndex);

        switch (columnIndex) {
            case 0: return employee.getId().toString();
            case 1: return employee.getFirstName();
            case 2: return employee.getLastName();
            case 3: return employee.getHireDate().toString();
            case 4: return employee.getUserName();
            case 5: return employee.getPassword();
            case 6: return employee.getEmail();
            case 7: if(employee.getManagerId()==null){
                return "";
            } else{
                return employee.getManagerId().toString();
            }
            case 8: return employee.getPhoneNumber();
        }
        return new Object();
    }
}
