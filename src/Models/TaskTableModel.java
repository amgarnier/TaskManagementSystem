package Models;

import Models.Task;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class TaskTableModel extends AbstractTableModel {


    private ArrayList<Task> taskList;

    public TaskTableModel(ArrayList<Task> task) {
        this.taskList = task;
    }

    public int getColumnCount() {
        return 6;
        // return however many columns you want
    }

    public int getRowCount() {
        return taskList.size();
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Due Date";
            case 1:
                return "Task Title";
            case 2:
                return "Task Description";
            case 3:
                return "Creation Date";
            case 4:
                return "Project Description";
                case 5:
                    return "Employee Name";

        }
        return "Null";
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = taskList.get(rowIndex);

        switch (columnIndex) {
            case 0: return task.getDueDate()==null ? "":task.getDueDate();
            case 1: return task.getTitle();
            case 2: return task.getDescription()==null ? "":task.getDescription();
            case 3: return task.getCreationDate()==null ? "":task.getCreationDate();
            case 4: return task.getProjectDescription()==null ? "":task.getProjectDescription();
            case 5: return task.getTeamMemberName()==null ? "":task.getTeamMemberName();
            case 6: return task.getId();
        }
        return new Object();
    }
}
