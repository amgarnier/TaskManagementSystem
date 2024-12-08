package Models;

import Models.Project;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class ProjectTableModel extends AbstractTableModel {


    private ArrayList<Project> projectList;

    public ProjectTableModel(ArrayList<Project> projectList)  {
        this.projectList = projectList;
    }

    public int getColumnCount() {
        return 4;
        // return however many columns you want
    }

    public int getRowCount() {
        return projectList.size();
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Project Title";
            case 1:
                return "Project Description";
            case 2:
                return "Start Date";
            case 3:
                return "Completion Date";
        }
        return "Null";
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Project project = projectList.get(rowIndex);

        switch (columnIndex) {
            case 0: return project.getTitle();
            case 1: return project.getDescription()==null? "": project.getDescription();
            case 2: return project.getStartDate()==null? "":project.getStartDate();
            case 3: return project.getCompletionDate()==null? "":project.getCompletionDate();
            case 4: return project.getProjectId();
        }
        return new Object();
    }
}
