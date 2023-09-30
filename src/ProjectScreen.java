import DBModels.EmployeeDBOperations;
import DBModels.ProjectDBOperations;
import Models.Employee;
import Models.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * FinalView -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class ProjectScreen extends JFrame {
    private JTextArea taProjectDescription;
    private JTextField tfStartDate;
    private JTextField tfCompletionDate;
    private JButton btnClearProject;
    private JButton btnEditProject;
    private JButton btnNewProject;
    private JPanel jpProject;
    private JTextField tfTitleProject;

    public ProjectScreen(){
        Date timeStamp = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        tfStartDate.setText(timeStamp.toString());
        add(jpProject);
        setTitle("Project");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        btnNewProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDBOperations projectDBOperations = new ProjectDBOperations(Main.connection);
                EmployeeDBOperations employeeDBOperations = new EmployeeDBOperations(Main.connection);
                Employee employee = employeeDBOperations.retrieveItem(Main.employee.getId());
                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Date sqlDate;
                Date sqlDateComplete = null;
                try {
                    java.util.Date date = dateSimple.parse(tfStartDate.getText());
                    System.out.println(tfCompletionDate.getText().equals(""));
                    if(!tfCompletionDate.getText().equals("")){
                        java.util.Date dateCompletion = dateSimple.parse(tfCompletionDate.getText());
                        sqlDateComplete = new Date(dateCompletion.getTime());

                    }
                    sqlDate = new Date(date.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                    throw new RuntimeException(ex);
                }
                Project project = new Project(sqlDate,sqlDateComplete, employee.getManagerId(), taProjectDescription.getText(),tfTitleProject.getText());
                if(projectDBOperations.createItem(project)){
                    Main.mainScreen.updateTables();
                    JOptionPane.showMessageDialog(null, "Successfully added note");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Error try adding note again");
                }

            }
        });

    }
    public ProjectScreen(int projectId){
        add(jpProject);
        setTitle("Project");
        updateProjectData(projectId);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);


        btnClearProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfStartDate.setText("");
                tfCompletionDate.setText("");
                taProjectDescription.setText("");


            }
        });
        btnEditProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDBOperations projectDBOperations = new ProjectDBOperations(Main.connection);
                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Date sqlStartDate;
                Date sqlEndDate = null;
                try {
                    java.util.Date date = dateSimple.parse(tfStartDate.getText());
                    sqlStartDate = new Date(date.getTime());
                    if(!tfCompletionDate.getText().equals("")) {
                        java.util.Date dateDueDate = dateSimple.parse(tfCompletionDate.getText());
                        sqlEndDate = new Date(dateDueDate.getTime());
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                    throw new RuntimeException(ex);
                }
                Project project = new Project(projectId,sqlStartDate,sqlEndDate,Main.employee.getManagerId(),taProjectDescription.getText(),tfTitleProject.getText());
                if(projectDBOperations.updateItem(project)){
                    Main.mainScreen.updateTables();
                    JOptionPane.showMessageDialog(null, "Successfully updated project");
                    dispose();

                }
                else{
                    JOptionPane.showMessageDialog(null,"Error your project was not added please try again");
                }


            }
        });
        btnNewProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDBOperations projectDBOperations = new ProjectDBOperations(Main.connection);
                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Date sqlDate;
                Date sqlDateComplete = null;
                try {
                    java.util.Date date = dateSimple.parse(tfStartDate.getText());
                    if(tfCompletionDate.getText()!=""){
                        java.util.Date dateCompletion = dateSimple.parse(tfCompletionDate.getText());
                        sqlDateComplete = new Date(dateCompletion.getTime());

                    }
                    sqlDate = new Date(date.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                    throw new RuntimeException(ex);
                }
                Project project = new Project(sqlDate,sqlDateComplete, Main.employee.getManagerId(), taProjectDescription.getText(),tfTitleProject.getText());
                if(projectDBOperations.createItem(project)){
                    JOptionPane.showMessageDialog(null, "Successfully added note");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Error try adding note again");
                }

            }
        });
    }

    void updateProjectData(int projectID){
        ProjectDBOperations projectDBOperations = new ProjectDBOperations(Main.connection);
        Project project = projectDBOperations.retrieveItem(projectID);
        //tfProjectID.setText(String.valueOf(project.getProjectId()));
        tfStartDate.setText(project.getStartDate().toString());
        tfCompletionDate.setText(project.getCompletionDate()==null? "": String.valueOf(project.getCompletionDate()));
        taProjectDescription.setText(project.getDescription());
        tfTitleProject.setText(project.getTitle());
    }
}
