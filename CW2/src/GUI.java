import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;

public class GUI {
    JFileChooser fileChooser = new JFileChooser();
    JFrame frame = new JFrame("Patient Viewer");
    String pathFile = null;
    JMenuBar menuBar = new JMenuBar();
    Model model = new Model();

    public void panelContainer()
    {
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.getContentPane().add(menuBar,BorderLayout.NORTH);
        fileLoadAndSave();
        MathematicsBar();
        getInformation();
        frame.setVisible(true);
    }

    public void fileLoadAndSave()
    {
        JMenu menu = new JMenu("Files");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem saveItem = new JMenuItem("Save as JSON");
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (pathFile != null){
                        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                        int result = fileChooser.showOpenDialog(loadItem);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            pathFile = selectedFile.getAbsolutePath();
                            model.readFile(pathFile);
                            JOptionPane.showMessageDialog(frame, "File Changed To: " + pathFile);
                        }
                    }
                    else {
                        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                        int results = fileChooser.showOpenDialog(loadItem);
                        if(results == JFileChooser.APPROVE_OPTION){
                            pathFile = fileChooser.getSelectedFile().getAbsolutePath();
                            if (pathFile.endsWith(".csv")){
                                model.readFile(pathFile);
                                JOptionPane.showMessageDialog(frame, "CSV File Selected: " + pathFile);
                            }
                            else if (pathFile.endsWith(".json")){
                                model.readJson(pathFile);
                                JOptionPane.showMessageDialog(frame, "JSON File Selected: " + pathFile);
                            }
                            else {
                                JOptionPane.showMessageDialog(frame, "File cannot be read !");
                            }
                        }
                    }
                }
                catch(FileNotFoundException exception){
                    JOptionPane.showMessageDialog(loadItem,"File not Found!");
                }
            }
        });
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String saveJson = model.getAllPatient();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile() + ".json"));
                    writer.write(saveJson);
                    writer.close();
                    JOptionPane.showMessageDialog(saveItem,"File Saved!");
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(saveItem,"Could not save item!");
                }
            }
        });
        menu.add(loadItem);
        menu.add(saveItem);
        menuBar.add(menu);
    }

    public void MathematicsBar()
    {
        JMenu Mathematics = new JMenu("Statistics");
        JMenuItem Age = new JMenuItem("Avg. Age");
        JMenuItem BirthYear = new JMenuItem("Avg. Birth Year");
        JMenuItem AgeDistribution = new JMenuItem("Age Distribution");
        Age.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int avgAge = model.getAverageAge();
                    JOptionPane.showMessageDialog(frame, "The Average Age is: " + avgAge);
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(frame, "No Entries");
                }
            }
        });
        BirthYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int birthYear = model.getAverageBirthYear();
                    JOptionPane.showMessageDialog(frame, "The Birth Year is: " + birthYear);
                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(frame, "No entries");
                }
            }
        });
        AgeDistribution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float ageDistribution = model.getAgeDistribution();
                    JOptionPane.showMessageDialog(frame, "The Age Distribution is: " + ageDistribution);
                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(frame, "No Entries" );
                }
            }
        });
        Mathematics.add(Age);
        Mathematics.add(BirthYear);
        Mathematics.add(AgeDistribution);
        menuBar.add(Mathematics);
    }

    public void getInformation(){
        JMenu Information = new JMenu("Information");
        JMenuItem Names = new JMenuItem("Patient Names");
        JMenuItem allPatient = new JMenuItem("All Patients");
        Names.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<String> inputList = model.getAllNames();
                    DefaultListModel<String> initialList = new DefaultListModel<>();
                    for (String tmp : inputList){
                        initialList.addElement(tmp);
                    }
                    JList list = new JList(initialList);
                    list.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            JList list = (JList) e.getSource();
                            if(e.getClickCount() == 2){
                                int index = list.locationToIndex(e.getPoint());
                                JOptionPane.showMessageDialog(frame,model.getSinglePatient(index));
                            }
                        }
                    });
                    JPanel searchBar = new JPanel();
                    JLabel searchLabel = new JLabel("Search :");
                    searchBar.add(searchLabel);
                    JTextField output = textField(list, inputList);
                    searchBar.add(output);
                    frame.add(new JScrollPane(list));
                    frame.add(searchBar,BorderLayout.SOUTH);
                    frame.setVisible(true);

                } catch (Exception exp){
                    JOptionPane.showMessageDialog(frame, "No Entries");
                }
            }
        });
        allPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String allPatientString = model.getAllPatient();
                    JTextArea textArea = new JTextArea(allPatientString);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(500,500));
                    JOptionPane.showMessageDialog(frame, scrollPane,"All Patient Info", JOptionPane.YES_NO_OPTION);
                }catch (Exception exp)  {
                    JOptionPane.showMessageDialog(frame, "No Entries !");
                }
            }
        });
        Information.add(Names);
        Information.add(allPatient);
        menuBar.add(Information);
    }

    public JTextField textField(JList input, List<String> inputStr){
        final JTextField searchTextField = new JTextField(30);
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) { }

            public void filter(){
                String filter = searchTextField.getText();
                filterModel((DefaultListModel<String>)input.getModel(), filter, inputStr);

            }
        });
        return searchTextField;
    }

    public void filterModel(DefaultListModel<String> model, String filterStr, List<String> inputStr) {
        for (String s : inputStr) {
            if (!s.startsWith(filterStr)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }

}

