package Models;

import java.sql.Date;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class Document {
    public Document(int documentId, Date creationDate, int taskId, String description,String title) {
        this.documentId=documentId;
        this.creationDate = creationDate;
        this.taskId = taskId;
        this.description = description;
        this.title=title;
    }
    public Document(Date creationDate, int taskId, String description, String title) {
        this.creationDate = creationDate;
        this.taskId = taskId;
        this.description = description;
        this.title=title;
    }
    public Document(){};

    private int documentId;
    private Date creationDate;
    private int taskId;
    private String description;
    private String title;
    public int getDocumentId() {

        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
