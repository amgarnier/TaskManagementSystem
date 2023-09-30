import DBModels.DocumentDBOperations;
import DBModels.NotesDBOperations;
import DBModels.ProjectDBOperations;
import DBModels.TaskDBOperations;
import Models.Document;
import Models.Note;
import Models.Project;
import Models.Task;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * FinalView -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class TaskScreen extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextArea taNote;
    private JButton btnAddNote;
    private JButton addButton;
    private JTextField tfDueDate;
    private JTextField tfStartDate;
    private JTextField tfID;
    private JTextField tfCreationDateDocument;
    private JButton btnNewDocument;
    private JButton clearButton;
    private JButton editButton;
    private JTextArea taDescriptionDocuments;
    private JComboBox cbProject;
    private JTextArea taDescription;
    private JTextField tfCreationDateNote;
    private JList listNotes;
    private JButton btnEditNote;
    private JList listDocuments;
    private JButton btnEditDocument;
    private JButton btnAddProject;
    private JButton btnRefreshProjects;
    private JTextField tfTitle;
    private JTextField tfTitleDocument;
    int _taskId;

    public TaskScreen(){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        tfStartDate.setText(timeStamp);
        tfCreationDateNote.setText(timeStamp);
        TaskDBOperations taskDBOperations = new TaskDBOperations(Main.connection);
        ProjectDBOperations projectDBOperations = new ProjectDBOperations(Main.connection);
         int empID = Main.employee.getId();
        ArrayList<Project> projects = projectDBOperations.retrieveProjectByEmployee(Main.employee.getId());
        cbProject.setModel(new DefaultComboBoxModel<Project>(projects.toArray(new Project[0])));
        setTitle("Task");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(panel1);
        tabbedPane1.setEnabledAt(1,false);
        tabbedPane1.setEnabledAt(2,false);
        editButton.setVisible(false);
        btnAddNote.setEnabled(false);
        setVisible(true);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taDescription.setText("");
                tfDueDate.setText("");
                tfStartDate.setText("");
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Project selectedProject = (Project) cbProject.getSelectedItem();
                Date sqlStartDate;
                Date sqlEndDate;
                try {
                    java.util.Date date = dateSimple.parse(tfStartDate.getText());
                    java.util.Date dateDueDate = dateSimple.parse(tfDueDate.getText());
                    sqlStartDate = new Date(date.getTime());
                    sqlEndDate = new Date(dateDueDate.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                    throw new RuntimeException(ex);
                }

                Task task = new Task(Main.employee.getId(),taDescription.getText(),sqlStartDate,sqlEndDate,selectedProject.getProjectId(),tfTitle.getText());
                if(taskDBOperations.createItem(task)){
                    Main.mainScreen.updateTables();
                    JOptionPane.showMessageDialog(null, "Successfully added task");
                    dispose();

                }
                else{
                    JOptionPane.showMessageDialog(null,"Error your task was not added please try again");
                }
            }
        });


    }
    public TaskScreen(int taskId){
        System.out.println("Task screen");
        _taskId = taskId;
        Date timeStamp = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
        tfCreationDateDocument.setText(timeStamp.toString());
        tfCreationDateNote.setText(timeStamp.toString());
        TaskDBOperations taskDBOperations = new TaskDBOperations(Main.connection);
        Task task = taskDBOperations.retrieveItem(_taskId);
        tfTitle.setText(task.getTitle());
        tfStartDate.setText(task.getCreationDate().toString());
        tfDueDate.setText(task.getDueDate().toString());
        taDescription.setText(task.getDescription());
        DocumentDBOperations documentDBOperations= new DocumentDBOperations(Main.connection);
        updateProjectList(task);
        updateNoteList();
        updateDocumentList();
        add(panel1);
        tabbedPane1.setEnabledAt(1,true);
        btnAddNote.setEnabled(true);
        addButton.setVisible(false);
        setTitle("Task");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taDescription.setText("");
                tfDueDate.setText("");
                taDescription.setText("");
                tfStartDate.setText("");
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Project selectedProject = (Project) cbProject.getSelectedItem();
                Date sqlStartDate;
                Date sqlEndDate;
                try {
                    java.util.Date date = dateSimple.parse(tfStartDate.getText());
                    java.util.Date dateDueDate = dateSimple.parse(tfDueDate.getText());
                    sqlStartDate = new Date(date.getTime());
                    sqlEndDate = new Date(dateDueDate.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                    throw new RuntimeException(ex);
                }

                Task task = new Task( _taskId, Main.employee.getId(),taDescription.getText(),sqlStartDate,sqlEndDate,selectedProject.getProjectId(),tfTitle.getText());
                if(taskDBOperations.updateItem(task)){
                    Main.mainScreen.updateTables();
                    JOptionPane.showMessageDialog(null, "Successfully updated task");
                    dispose();

                }
                else{
                    JOptionPane.showMessageDialog(null,"Error your task was not added please try again");
                }

            }
        });
        btnAddNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotesDBOperations notesDBOperations = new NotesDBOperations(Main.connection);
                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Date sqlDate;
                try {
                    java.util.Date date = dateSimple.parse(tfCreationDateDocument.getText());
                    sqlDate = new Date(date.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                    throw new RuntimeException(ex);
                }
                Note note = new Note(sqlDate,_taskId,taNote.getText());
                if(notesDBOperations.createItem(note)){
                    updateNoteList();
                    listNotes.setSelectedValue(note, true);
                    JOptionPane.showMessageDialog(null, "Successfully added note");
                }else{
                    JOptionPane.showMessageDialog(null, "Error try adding note again");
                }
            }
        });
        btnEditNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotesDBOperations notesDBOperations = new NotesDBOperations(Main.connection);
                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Date sqlDate;
                try {
                    java.util.Date date = dateSimple.parse(tfCreationDateDocument.getText());
                    sqlDate = new Date(date.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                    throw new RuntimeException(ex);
                }
                if(listNotes.getSelectedValue()==null){
                    JOptionPane.showMessageDialog(null, "Select a note from the list");
                }
                else{

                    Note selectedNote = (Note) listNotes.getSelectedValue();
                    int NoteID = selectedNote.getNoteID();
                    Note note = new Note(NoteID,sqlDate, selectedNote.getTaskID(), taNote.getText());
                    if(notesDBOperations.updateItem(note)){
                        listNotes.setSelectedValue(note, true);
                        updateNoteList();
                        JOptionPane.showMessageDialog(null, "Successfully updated note");
                    }else{
                        JOptionPane.showMessageDialog(null, "Error try updating note again");
                    }
                }

            }
        });
        listNotes.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Note note = (Note) listNotes.getSelectedValue();
                    if(note !=null) {
                        updateNoteDisplay(note.getNoteID());
                    }
                }
            }
        });
        btnNewDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Date sqlDate;
                try {
                    java.util.Date date = dateSimple.parse(tfCreationDateDocument.getText());
                    sqlDate = new Date(date.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                    throw new RuntimeException(ex);
                }

                Document document = new Document(sqlDate,_taskId,taDescriptionDocuments.getText(),tfTitleDocument.getText());
                if(documentDBOperations.createItem(document)){
                    updateDocumentList();
                    listDocuments.setSelectedValue(document, true);
                    JOptionPane.showMessageDialog(null, "Successfully added document");
                }else{
                    JOptionPane.showMessageDialog(null, "Error try adding document again");
                }
            }
        });

        btnEditDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listDocuments.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select a document to edit");
                } else {
                    SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                    Date sqlDate;
                    try {
                        java.util.Date date = dateSimple.parse(tfCreationDateDocument.getText());
                        sqlDate = new Date(date.getTime());
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                        throw new RuntimeException(ex);
                    }
                    Document getDocument = (Document) listDocuments.getSelectedValue();
                    int documentID = getDocument.getDocumentId();
                    Document document = new Document(documentID, sqlDate, _taskId, taDescriptionDocuments.getText(),tfTitleDocument.getText());
                    if (documentDBOperations.updateItem(document)) {
                        updateDocumentList();
                        listDocuments.setSelectedValue(document, true);
                        JOptionPane.showMessageDialog(null, "Successfully edited document");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error try editing document again");
                    }

                }
            }
        });
        listDocuments.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Document document = (Document) listDocuments.getSelectedValue();
                    if(document !=null) {
                        updateDocumentDisplay(document.getDocumentId());
                    }
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SimpleDateFormat dateSimple = new SimpleDateFormat("yyyy-MM-dd");
                Project selectedProject = (Project) cbProject.getSelectedItem();
                Date sqlStartDate;
                Date sqlEndDate;
                try {
                    java.util.Date date = dateSimple.parse(tfStartDate.getText());
                    java.util.Date dateDueDate = dateSimple.parse(tfDueDate.getText());
                    sqlStartDate = new Date(date.getTime());
                    sqlEndDate = new Date(dateDueDate.getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Error your entered in the dates in the wrong format\n try YYYY-MM-DD");
                    throw new RuntimeException(ex);
                }

                Task task = new Task(Main.employee.getId(),taDescription.getText(),sqlStartDate,sqlEndDate,selectedProject.getProjectId(),tfTitle.getText());
                if(taskDBOperations.createItem(task)){
                    Main.mainScreen.updateTables();
                    JOptionPane.showMessageDialog(null, "Successfully added task");
                    dispose();

                }
                else{
                    JOptionPane.showMessageDialog(null,"Error your task was not added please try again");
                }
            }
        });
        btnAddProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int xpos = getBounds().x;
                int ypos = getBounds().y;
                new ProjectScreen().setBounds(xpos, ypos, 600, 400);
            }
        });
        btnRefreshProjects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProjectList(task);
            }
        });
    }
    void updateNoteList(){
        NotesDBOperations notesDBOperations = new NotesDBOperations(Main.connection);
        ArrayList<Note> notes = notesDBOperations.retrieveArrayList(_taskId);
        DefaultListModel<Note> model = new DefaultListModel<>();
        for(Note val : notes)
            model.addElement(val);
        listNotes.setModel(model);

    }
    void updateNoteDisplay(int id){
        NotesDBOperations notesDBOperations = new NotesDBOperations(Main.connection);
        Note note = notesDBOperations.retrieveItem(id);
        tfCreationDateNote.setText(note.getCreationDate().toString());
        taNote.setText(note.getNote());

    }
    void updateDocumentList(){
        DocumentDBOperations documentDBOperations = new DocumentDBOperations(Main.connection);
        ArrayList<Document> documents = documentDBOperations.retrieveArrayList(_taskId);
        DefaultListModel<Document> model = new DefaultListModel<>();
        for(Document val: documents){
            model.addElement(val);
            listDocuments.setModel(model);
        }
    }
    void updateDocumentDisplay(int id){
        DocumentDBOperations documentDBOperations = new DocumentDBOperations(Main.connection);
        Document document = documentDBOperations.retrieveItem(id);
        tfCreationDateDocument.setText(document.getCreationDate().toString());
        taDescriptionDocuments.setText(document.getDescription());
        tfTitleDocument.setText(document.getTitle());
    }

    void updateProjectList(Task task){
        ProjectDBOperations projectDBOperations = new ProjectDBOperations(Main.connection);
        ArrayList<Project> projects = projectDBOperations.retrieveProjectByEmployee(Main.employee.getId());
        cbProject.setModel(new DefaultComboBoxModel<Project>(projects.toArray(new Project[0])));
        //int projectID = task.getProject();
        //Optional<Project> selectedProject = projects.stream().filter(p-> p.getProjectId() == projectID).findFirst();
        //cbProject.setSelectedItem(selectedProject.get());
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
