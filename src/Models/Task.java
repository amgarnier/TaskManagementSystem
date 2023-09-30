package Models;

import java.sql.Date;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class Task {
    private int id;
    private int teamMember;
    private String description;
    private Date creationDate;
    private Date endDate;
    private Date dueDate;
    private boolean complete;
    private String serialNumber;
    private int project;
    private String projectDescription;
    private String title;

    public Task( int teamMember, String description, Date creationDate, Date endDate, Date dueDate, boolean complete, String serialNumber, int project, String projectDescription) {
        this.teamMember = teamMember;
        this.description = description;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.complete = complete;
        this.serialNumber = serialNumber;
        this.project = project;
        this.projectDescription = projectDescription;
    }
    public Task(int id ,int teamMember, String description, Date creationDate, Date endDate, Date dueDate, boolean complete, String serialNumber, int project, String projectDescription) {
        this.id = id;
        this.teamMember = teamMember;
        this.description = description;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
        this.complete = complete;
        this.serialNumber = serialNumber;
        this.project = project;
        this.projectDescription = projectDescription;
    }
    public Task( int teamMember, String description, Date creationDate, Date dueDate, int project, String title) {
        this.title=title;
        this.teamMember = teamMember;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.project = project;
    }
    public Task(int id, int teamMember, String description, Date creationDate, Date dueDate, int project,String title) {
        this.id = id;
        this.title = title;
        this.teamMember = teamMember;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.project = project;
    }
    public Task(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(int teamMember) {
        this.teamMember = teamMember;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;

    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
