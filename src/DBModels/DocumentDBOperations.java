package DBModels;

import Models.Document;

import java.sql.*;
import java.util.ArrayList;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class DocumentDBOperations implements IDatabaseActions<Document> {
    private Connection connection;

    public DocumentDBOperations(Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean createItem(Document document) {

        String SQLCreate = "insert into document(creation_date, task_id, description,title) values(?,?,?,?);";
        Boolean bl = false;
        try (PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setDate(1, document.getCreationDate());
            ps.setInt(2, document.getTaskId());
            ps.setString(3, document.getDescription());
            ps.setString(4, document.getTitle());
            int result = ps.executeUpdate();
            if(result!=0){
                bl=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bl;
    }

    @Override
    public ArrayList retrieveArrayList(Integer taskId) {
        ArrayList documents = new ArrayList();
        try {
            //Standard JDBC Code
            Statement stmt = connection.createStatement();
            String query = "select * from document where task_id = " + taskId + ";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Document document = new Document();
                document.setDocumentId(rs.getInt("document_id"));
                document.setCreationDate(rs.getDate("creation_date"));
                document.setTaskId(rs.getInt("task_id"));
                document.setDescription(rs.getString("description"));
                document.setTitle(rs.getString("title"));
                documents.add(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documents;
    }

    @Override
    public Document retrieveItem(int documentId) {
        Document document = new Document();
        try {
            //Standard JDBC Code
            Statement stmt = connection.createStatement();
            String query = "select * from document where document_id = " + documentId + ";";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                document.setDocumentId(rs.getInt("document_id"));
                document.setCreationDate(rs.getDate("creation_date"));
                document.setTaskId(rs.getInt("task_id"));
                document.setDescription(rs.getString("description"));
                document.setTitle(rs.getString("title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    @Override
    public boolean updateItem(Document value) {
        String SQLCreate = "update document set creation_date=?, task_id=?,description=?, title=? where document_id =?;";
        Boolean bl = false;
        try (PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setDate(1, value.getCreationDate());
            ps.setInt(2, value.getTaskId());
            ps.setString(3, value.getDescription());
            ps.setString(4, value.getTitle());
            ps.setInt(5,value.getDocumentId());
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

    @Override
    public boolean deleteItem(Integer note_id) {
        String SQLCreate = "delete from document where document_id =?;";
        Boolean bl = false;
        try (PreparedStatement ps = connection.prepareStatement(SQLCreate)) {
            ps.setInt(1, note_id);
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
}

