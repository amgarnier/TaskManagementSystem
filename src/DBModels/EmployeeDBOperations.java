package DBModels;

import Models.Employee;
import Models.Note;

import java.sql.*;
import java.util.ArrayList;

/**
 * FinalView -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class EmployeeDBOperations implements IDatabaseActions<Employee> {
    private static Connection connection;

    public EmployeeDBOperations(Connection connection) {
        this.connection = connection;
    }

    public int login(Employee value){
        String query ="select employee_id from employees where user_name = ? and employee_password=?;";


        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,value.getUserName());
            ps.setString(2,value.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("employee_id");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return 0;
    }

        @Override
    public boolean createItem(Employee value) {
           String SQLCreate = "insert into employees(first_name, last_name, hire_date, user_name, employee_password, email, manager_id, phone_number) VALUES (?,?,?,?,?,?,?,?);";

            ResultSet resultSet = null;
            Boolean bl = false;
            try(PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
                ps.setString(1,value.getFirstName());
                ps.setString(2, value.getLastName());
                ps.setDate(3,value.getHireDate());
                ps.setString(4,value.getUserName());
                ps.setString(5, value.getPassword());
                ps.setString(6,value.getEmail());
                ps.setInt(7,value.getManagerId());
                ps.setString(8,value.getPhoneNumber());
                int result = ps.executeUpdate();
                if(result!=0){
                    bl=true;
                }


            }
            catch (SQLException e){
                e.printStackTrace();
            }

            return bl;
    }

    @Override
    public ArrayList retrieveArrayList(Integer taskId) {
        return null;
    }

    @Override
    public Employee retrieveItem(int employeeID) {
        String query ="select * from employees where employee_id = ?;";
        Employee employee = new Employee();


        try(PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1,employeeID);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                employee.setId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setHireDate(rs.getDate("hire_date"));
                employee.setUserName(rs.getString("user_name"));
                employee.setPassword(rs.getString("employee_password"));
                employee.setEmail(rs.getString("email"));
                employee.setManagerId(rs.getInt("manager_id"));
                employee.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return employee;


    }

    @Override
    public boolean updateItem(Employee value) {
        return false;
    }

    @Override
    public boolean deleteItem(Integer note_id) {
        return false;
    }
}
