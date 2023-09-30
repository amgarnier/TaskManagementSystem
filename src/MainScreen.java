import DBModels.EmployeeDBOperations;
import DBModels.ProjectDBOperations;
import DBModels.TaskDBOperations;
import Models.Employee;
import Models.Project;
import Models.Task;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * FinalView -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class MainScreen extends JFrame{


    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable table1;
    private JTable table2;
    private JLabel jlWelcomBar;
    private JTextField tfUserName;

    public MainScreen(){
        updateTables();
        setSize(1000,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(panel1);
        setJMenuBar(createMenuBar());
        table1.setCellSelectionEnabled(true);
        setVisible(true);


        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    if(table1.getSelectedRow()!=-1){
                        int row = table1.getSelectedRow();
                        int value = (int) table1.getModel().getValueAt(row, 5);
                        System.out.println(value);
                        int xpos = getBounds().x+500-300;
                        int ypos = getBounds().y+400-200;
                        new TaskScreen(value).setBounds(xpos,ypos,600,400);
                    }

                }

            }
        });
        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    if(table2.getSelectedRow()!=-1){
                        int row = table2.getSelectedRow();
                        int value = (int) table2.getModel().getValueAt(row, 4);
                        System.out.println(value);
                        int xpos = getBounds().x+500-300;
                        int ypos = getBounds().y+400-200;
                        new ProjectScreen(value).setBounds(xpos,ypos,600,400);
                    }

                }

            }
        });

    };

    void updateTables(){
        int employeeID = Main.employee.getId();
        TableModel tableModel = new Models.TaskTableModel(updateTaskTable(employeeID));
        TableModel tableModel2 = new Models.ProjectTableModel(updateProjectTable(employeeID));
        if(employeeID != 0) {

            table1.setModel(tableModel);
            table1.getSelectedRow();

            table2.setModel(tableModel2);
            table2.getSelectedRow();
            EmployeeDBOperations emplDB = new EmployeeDBOperations(Main.connection);
            Employee employee = emplDB.retrieveItem(employeeID);
            jlWelcomBar.setText( "Welcome " + employee.getFirstName() + " " + employee.getLastName());
        }
        else{
            table1.setModel(tableModel);
            table2.setModel(tableModel2);
            jlWelcomBar.setText( "Please Login");
        }
    }

    Rectangle windowBounds(){
        Point pos = getContentPane().getLocationOnScreen();
        Rectangle rect = new Rectangle();
        rect.width = 600;
        rect.height= 400;
        rect.x = pos.x;
        rect.y = pos.y;
        return rect;
    }

    ArrayList<Task> updateTaskTable(int employeeID){
        ArrayList<Task> taskArrayList = new ArrayList();
        //this is where we update the table info for main screen
        TaskDBOperations taskDBOperations = new TaskDBOperations(Main.connection);
        taskArrayList = taskDBOperations.retrieveArrayList(employeeID);
        return taskArrayList;
    }

    ArrayList<Project> updateProjectTable(int employeeID){
        ArrayList<Project> projectArrayList = new ArrayList();
            //this is where we update the table info for main screen
            ProjectDBOperations projectDBOperations = new ProjectDBOperations(Main.connection);
            projectArrayList = projectDBOperations.retrieveProjectByEmployee(employeeID);

        return projectArrayList;
    }

    private JMenu createFileMenu()  {
        JMenu fileMenu = new JMenu("File");
        JMenuItem addItem = new JMenuItem("New Item");
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int xpos = getBounds().x+500-300;
                int ypos = getBounds().y+400-200;
                new TaskScreen().setBounds(xpos,ypos,600,400);;
            }
        });
        fileMenu.add(addItem);
        JMenuItem addProject = new JMenuItem("New Project");
        addProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int xpos = getBounds().x + 500 - 300;
                int ypos = getBounds().y + 400 - 200;
                new ProjectScreen().setBounds(xpos, ypos, 600, 400);

            }
        });
        fileMenu.add(addProject);
        JMenuItem logout = new JMenuItem("Logout");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.employee = new Employee();
                Main.employee.setId(0);
                Main.mainScreen.updateTables();
                new LoginScreen();

            }
        });
        fileMenu.add(logout);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exit);
        return fileMenu;
    }
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        return menuBar;
    }

//


}
