package Models;

import java.sql.Date;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class Note {
    public Note(Date creationDate, int taskID, String note) {
        this.creationDate = creationDate;
        this.taskID = taskID;
        this.note = note;
    }
    public Note(int noteID,Date creationDate, int taskID, String note) {
        this.noteID = noteID;
        this.creationDate = creationDate;
        this.taskID = taskID;
        this.note = note;
    }
    public Note(){}

    private int noteID;
    private Date creationDate;
    private int taskID;
    private String note;
    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public Date getCreationDate() {
        if (creationDate == null){
            creationDate = new Date(0000,1,1);
        }
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return note;
    }
}
