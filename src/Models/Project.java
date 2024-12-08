package Models;

import java.sql.Date;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */

public class Project {
    private int projectId;
    private Task task;

    private Date startDate;
    private Date completionDate;
    private int managerID;
    private String description;
    private String title;

    public Project(int projectId,  Date startDate, Date completionDate, int managerID, String description, String title) {
        this.projectId = projectId;
        this.startDate = startDate;
        this.completionDate = completionDate;
        this.managerID = managerID;
        this.description = description;
        this.title=title;
    }

    public Project( Date startDate, Date completionDate, int managerID, String description, String title) {
        this.startDate = startDate;
        this.completionDate = completionDate;
        this.managerID = managerID;
        this.description = description;
        this.title=title;
    }

    public Project(){
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
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
