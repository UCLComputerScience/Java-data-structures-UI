import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;

public class GUI {
    JTextArea textArea = new JTextArea();
    JFileChooser fileChooser = new JFileChooser();
    JFrame frame = new JFrame("Patient Viewer");
    String pathFile = null;
    JButton go = new JButton("Go");
    JTextField searchTextField = new JTextField(20);
    JMenuBar menuBar = new JMenuBar();
    Model model = new Model();
    JButton[] buttons;
    String searchInput;


    public void panelContainer()
    {
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.getContentPane().add(menuBar,BorderLayout.NORTH);
        fileLoadAndSave();
        searchBar();
        getInformationMenu();
        MathematicsBar();
        frame.setVisible(true);
    }

    public void fileLoadAndSave()
    {
        JMenu menu = new JMenu("Files");
        JMenuItem loadItem = new JMenuItem("Load");
        JMenuItem saveItem = new JMenuItem("Save");
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
                            model.readFile(pathFile);
                            JOptionPane.showMessageDialog(frame, "File Selected: " + pathFile);
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
                    String saveTextArea = textArea.getText();
                    FileWriter fWriter = new FileWriter(fileChooser.getSelectedFile() + ".json");
                    fWriter.write(saveTextArea);
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

    public void makeScrollForTextArea()
    {
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scroll,BorderLayout.CENTER);
    }

    public void searchBar()
    {
        JPanel searchBar = new JPanel();
        JLabel searchLabel = new JLabel("Search :");
        searchBar.add(searchLabel);
        searchBar.add(searchTextField);
        searchBar.add(go);
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   searchInput = searchTextField.getText();
                   JOptionPane.showMessageDialog(frame, "You Searched for: " + searchInput);
                }
                catch (Exception exception){}
            }
        });
        frame.add(searchBar,BorderLayout.SOUTH);
    }

    public void getInformationMenu()
    {
        JMenu menu = new JMenu("Get Information");
        JMenuItem getPatientIdList = new JMenuItem("Patients' IDs");

        getPatientIdList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel idPanel = new JPanel();
                    idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.Y_AXIS));
                    List<String> str = model.getAllNames();
                    buttons = new JButton[str.size()];
                    for(int i = 0; i < str.size(); i ++)
                    {
                        buttons[i] = new JButton(str.get(i));
                        int index = i;
                        buttons[i].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {

                                    String patientInfoPrint = model.getSinglePatient(index);
                                    textArea.setText(patientInfoPrint);
                                    frame.add(textArea);
                                    frame.setVisible(true);
                                }
                                catch (Exception exception)
                                {
                                    JOptionPane.showMessageDialog(frame,"No Information Available!");
                                }
                            }
                        });
                        idPanel.add(buttons[i]);
                    }
                    JScrollPane scroll = new JScrollPane(idPanel);
                    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    frame.add(scroll);
                    scroll.setVisible(true);
                    frame.setVisible(true);
                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(getPatientIdList,"No input File!");
                }
            }
        });

        menu.add(getPatientIdList);
        menuBar.add(menu);
    }

    public void searchFilter()
    {
        String searchInput = searchTextField.getText();
        for (JButton curButton : buttons)
        {
            String curButtonText = curButton.getText();
            if(!curButtonText.startsWith(searchInput))
            {
                if(model)
            }
        }
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


}
