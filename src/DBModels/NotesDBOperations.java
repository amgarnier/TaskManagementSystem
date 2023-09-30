package DBModels;

import Models.Note;

import java.sql.*;
import java.util.ArrayList;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class NotesDBOperations implements IDatabaseActions<Note> {
    private Connection connection;

    public NotesDBOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean createItem(Note note){

        String SQLCreate = "insert into note(creation_date, task_id, note) values(?,?,?);";
        Boolean bl=false;
        try(PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setDate(1,note.getCreationDate());
            ps.setInt(2,note.getTaskID());
            ps.setString(3,note.getNote());
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

    @Override
    public  ArrayList retrieveArrayList(Integer taskId){
        ArrayList list = new ArrayList();
        try {
            //Standard JDBC Code
            Statement stmt = connection.createStatement();
            String query = "select * from note where task_id = "+taskId +";";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                Note note = new Note();
                note.setNoteID(rs.getInt("note_id"));
                note.setCreationDate(rs.getDate("creation_date"));
                note.setTaskID(rs.getInt("task_id"));
                note.setNote(rs.getString("note"));
                list.add(note);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Note retrieveItem(int noteId){
        Note note = new Note();
        try {
            //Standard JDBC Code
            Statement stmt = connection.createStatement();
            String query = "select * from note where note_id = "+noteId +";";
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {

                note.setNoteID(rs.getInt("note_id"));
                note.setCreationDate(rs.getDate("creation_date"));
                note.setTaskID(rs.getInt("task_id"));
                note.setNote(rs.getString("note"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return note;
    }

    @Override
    public  boolean updateItem(Note note){

        String SQLCreate = "update note\n" +
                "Set creation_date =?, note =?\n" +
                "where note_id = ?;";
        Boolean bl= false;
        try(PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setDate(1,note.getCreationDate());
            ps.setString(2,note.getNote());
            ps.setInt(3,note.getNoteID());
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

    @Override
    public boolean deleteItem(Integer note_id) {
        String SQLCreate = "delete from note where note_id =?;";
        Boolean bl = false;
        try(PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setInt(1,note_id);
            bl = ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
            bl=false;
        }
        return bl;
    }
}
