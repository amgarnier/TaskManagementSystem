package Models;

import Models.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class RetrieveEmployeeRecords
{
    public static ArrayList listMethod(Connection con){
        ArrayList list = new ArrayList();
        try {
            //Standard JDBC Code
            Statement stmt = con.createStatement();
            String query = "select * from employees";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("employee_id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setHireDate(rs.getDate("hire_date"));
                e.setUserName(rs.getString("user_name"));
                e.setPassword(rs.getString("employee_password"));
                e.setEmail(rs.getString("email"));
                e.setId(rs.getInt("manager_id"));
                e.setPhoneNumber(rs.getString("phone_number"));
                list.add(e);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
