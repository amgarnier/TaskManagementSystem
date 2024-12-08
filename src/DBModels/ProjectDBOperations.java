package DBModels;

import Models.Project;
import Models.Task;

import java.sql.*;
import java.util.ArrayList;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class ProjectDBOperations implements IDatabaseActions<Project>{
    private Connection connection;

    public ProjectDBOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
   public boolean createItem( Project project) {
        String SQLCreate = "insert into project(start_date, completion_date, manager_id, description,title) values(?,?,?,?,?);";
        Boolean bl= false;
        try (PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setDate(1, project.getStartDate());
            ps.setDate(2, project.getCompletionDate());
            ps.setInt(3, project.getManagerID());
            ps.setString(4, project.getDescription());
            ps.setString(5, project.getTitle());
            int result = ps.executeUpdate();
            if(result!=0){
                bl=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  bl;
    }
public ArrayList<Project> retrieveProjectByEmployee(Integer employeeID){

    ArrayList list = new ArrayList();
    try {
        //Standard JDBC Code
        Statement stmt = connection.createStatement();
        String query = "select project.project_id, project.start_date, project.completion_date, project.manager_id, project.description,project.title\n" +
                "from project\n" +
                "         left join manager on project.manager_id = manager.manager_id\n" +
                "         left join employees on manager.manager_id = employees.manager_id\n" +
                "where employees.employee_id =" + employeeID + ";";

        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            Project p = new Project();
            p.setProjectId(rs.getInt("project_id"));
            p.setStartDate(rs.getDate("start_date"));
            p.setCompletionDate(rs.getDate("completion_date"));
            p.setManagerID(rs.getInt("manager_id"));
            p.setDescription(rs.getString("description"));
            p.setTitle(rs.getString("title"));
            list.add(p);
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return list;
}
    @Override
    public ArrayList retrieveArrayList(Integer employeeID){
        ArrayList list = new ArrayList();
        try {
            //Standard JDBC Code
            Statement stmt = connection.createStatement();
            String query = "select project_id, task_id, description_task as task_description, creation_date, due_date, description as project_description,title\n" +
                    "from task\n" +
                    "left join project p on p.project_id = task.project\n" +
                    "where team_member=" + employeeID + ";";

            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("task_id"));
                task.setDescription(rs.getString("task_description"));
                task.setCreationDate(rs.getDate("creation_date"));
                task.setDueDate(rs.getDate("due_date"));
                Project p = new Project();
                p.setProjectId(rs.getInt("project_id"));
                p.setDescription(rs.getString("project_description"));
                p.setTask(task);
                p.setTitle(rs.getString("title"));
                list.add(p);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Project retrieveItem(int projectId) {
        Project pr = new Project();
        try {
            //Standard JDBC Code
            Statement stmt = connection.createStatement();
            String query = "select * from project where project_id = "+projectId +";";
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                pr.setProjectId(rs.getInt("project_id"));
                pr.setStartDate(rs.getDate("start_date"));
                pr.setCompletionDate(rs.getDate("completion_date"));
                pr.setManagerID(rs.getInt("manager_id"));
                pr.setDescription(rs.getString("description"));
                pr.setTitle(rs.getString("title"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return pr;
    }

    @Override
    public boolean updateItem(Project value) {
        String SQLCreate = "update project\n" +
                "set start_date =?, completion_date= ?, manager_id = ?, description = ?, title=?\n" +
                "where project_id =?;";
        Boolean bl=false;
        try(PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setDate(1,value.getStartDate());
            ps.setDate(2,value.getCompletionDate());
            ps.setInt(3,value.getManagerID());
            ps.setString(4,value.getDescription());
            ps.setString(5, value.getTitle());
            ps.setInt(6,value.getProjectId());

            int result = ps.executeUpdate();
            if(result!=0){
                bl=true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            bl=false;
        }
        return bl;
    }

    //todo: cannot delete if project has task associated with it
    @Override
    public boolean deleteItem(Integer projectId) {
        String SQLCreate = "delete from project where project_id =?;";
        Boolean bl = false;
        try(PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setInt(1,projectId);
            bl = ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            bl=false;
        }

        return bl;
    }

}
