package DBModels;

import Models.Employee;
import Models.EmployeesTableModel;
import Models.Task;

import java.sql.*;
import java.util.ArrayList;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class TaskDBOperations implements IDatabaseActions<Task> {
    public TaskDBOperations(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;

    @Override
    public boolean createItem(Task task){

        String SQLCreate = "insert into task(team_member, description_task, creation_date, due_date, complete, project,title_task) values(?,?, ?, ?, ?, ?,?)";
        Boolean bl=false;
        try(PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setInt(1,task.getTeamMember());
            ps.setString(2,task.getDescription());
            ps.setDate(3, task.getCreationDate());
            ps.setDate(4, task.getDueDate());
            ps.setBoolean(5,false);
            ps.setInt(6,task.getProject());
            ps.setString(7, task.getTitle());
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

    //this utilizes join to get a better task array list with all values needed
    @Override
    public ArrayList retrieveArrayList( Integer employeeID){
        ArrayList list = new ArrayList();

        try {
            //Standard JDBC Code
            //todo: update the select query
            Statement stmt = connection.createStatement();
            String query = "select *\n" +
                    "from task \n" +
                    "left join project on task.project = project.project_id left join employees on task.team_member = employees.employee_id \n" +
                    "where task.team_member = " + employeeID + " order by due_date asc;";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("task_id"));
                t.setTeamMember(rs.getInt("team_member"));
                t.setDescription(rs.getString("description_task"));
                t.setCreationDate(rs.getDate("creation_date"));
                t.setEndDate(rs.getDate("end_date"));
                t.setDueDate(rs.getDate("due_date"));
                t.setComplete(rs.getBoolean("complete"));
                t.setSerialNumber(rs.getString("response_serial_number"));
                t.setProject(rs.getInt("project"));
                t.setProjectDescription(rs.getString("description"));
                t.setTitle(rs.getString("title_task"));
                t.setTeamMemberName(rs.getString("first_name") + " " + rs.getString("last_name"));

                list.add(t);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList retrieveManagerTaskList( Integer employeeID){
        ArrayList list = new ArrayList();

        try {
            //Standard JDBC Code
            //todo: update the select query
            Statement stmt = connection.createStatement();
            String query = "select *\n" +
                    "from task \n" +
                    "left join project on task.project = project.project_id left join employees on task.team_member = employees.employee_id\n" +
                    "where project.manager_id = " + employeeID + " order by due_date asc;";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("task_id"));
                t.setTeamMember(rs.getInt("team_member"));
                t.setDescription(rs.getString("description_task"));
                t.setCreationDate(rs.getDate("creation_date"));
                t.setEndDate(rs.getDate("end_date"));
                t.setDueDate(rs.getDate("due_date"));
                t.setComplete(rs.getBoolean("complete"));
                t.setSerialNumber(rs.getString("response_serial_number"));
                t.setProject(rs.getInt("project"));
                t.setProjectDescription(rs.getString("description"));
                t.setTitle(rs.getString("title_task"));
                t.setTeamMemberName(rs.getString("first_name") + " " + rs.getString("last_name"));

                list.add(t);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public Task retrieveItem(int taskId) {
        Task t = new Task();
        //Standard JDBC Code
        String SQL = "select * from task left join employees on task.team_member = employees.employee_id where task_id =?";
        try(PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1,taskId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                t.setId(rs.getInt("task_id"));
                t.setTeamMember(rs.getInt("team_member"));
                t.setDescription(rs.getString("description_task"));
                t.setCreationDate(rs.getDate("creation_date"));
                t.setEndDate(rs.getDate("end_date"));
                t.setDueDate(rs.getDate("due_date"));
                t.setComplete(rs.getBoolean("complete"));
                t.setSerialNumber(rs.getString("response_serial_number"));
                t.setProject(rs.getInt("project"));
                t.setTitle(rs.getString("title_task"));
                t.setTeamMemberName(rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public boolean updateItem(Task value) {
        String SQLCreate = " update task set team_member =?, description_task= ?, creation_date=?, end_date=?, due_date=?,complete=?,response_serial_number=?,project=?, title_task=? where task_id =?;";
        Boolean bl = false;
        try (PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setInt(1,value.getTeamMember());
            ps.setString(2,value.getDescription());
            ps.setDate(3,value.getCreationDate());
            ps.setDate(4, value.getEndDate());
            ps.setDate(5,value.getDueDate());
            ps.setBoolean(6,value.isComplete());
            ps.setString(7,value.getSerialNumber());
            ps.setInt(8,value.getProject());
            ps.setString(9,value.getTitle());
            ps.setInt(10,value.getId());
            int result = ps.executeUpdate();
            if(result!=0){
                bl=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            bl = false;
        }
        return bl;
    }

    public ArrayList<Employee> retrieveEmployeesByManager(Integer employeeID){
        ArrayList list = new ArrayList();

            //Standard JDBC Code
            String query =
                    "select * from employees where manager_id = ?";
            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1,employeeID);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    Employee e = new Employee();
                    e.setId(rs.getInt("employee_id"));
                    e.setFirstName(rs.getString("first_name"));
                    e.setLastName(rs.getString("last_name"));
                    e.setEmail(rs.getString("email"));

                    list.add(e);
                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        return list;
    }

    @Override
    public boolean deleteItem(Integer taskId) {
        String SQLCreate = "delete from task where task_id =?;";
        Boolean bl = false;
        try(PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setInt(1,taskId);
            bl = ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            bl=false;
        }

        return bl;
    }



}

