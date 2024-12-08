package DBModels;

import Models.Employee;
import Models.Note;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * FinalView -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class ManagerDBOperations implements IDatabaseActions {

    private static Connection connection;

    public ManagerDBOperations(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Employee> retrieveManagers(){
        ArrayList list = new ArrayList();
        try {
            //Standard JDBC Code
            Statement stmt = connection.createStatement();
            String query =
                    "select * from employees where is_manager = 1";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Employee employee = new Employee();
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setId(rs.getInt("manager_id"));
                list.add(employee);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public boolean createItem(Object value) {
        return false;
    }

    @Override
    public ArrayList retrieveArrayList(Integer taskId) {
        return null;
    }

    @Override
    public Object retrieveItem(int noteId) {
        return null;
    }

    @Override
    public boolean updateItem(Object value) {
        return false;
    }

    @Override
    public boolean deleteItem(Integer note_id) {
        return false;
    }
}
