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
    String pathFile = "empty";
    JButton go = new JButton("Go");
    JTextField searchTextField = new JTextField(20);
    JMenuBar menuBar = new JMenuBar();
    Model model = new Model();


    public void panelContainer()
    {
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.getContentPane().add(menuBar,BorderLayout.NORTH);
        fileLoadAndSave();
        searchBar();
        getInformationMenu();
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
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(loadItem);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        pathFile = selectedFile.getAbsolutePath();
                        model.readFile(pathFile);
                        JOptionPane.showMessageDialog(frame,"File Selected: " + pathFile);
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
        frame.add(searchBar,BorderLayout.SOUTH);
    }

    public void getInformationMenu()
    {
        JMenu menu = new JMenu("Get Information");
        JMenuItem getAllInfo = new JMenuItem("All Information");
        JMenuItem getPatientIdList = new JMenuItem("Patients' IDs");
        getAllInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    model.readFile(pathFile);
                    String output = model.getAllPatient();
                    textArea.setText(output);
                    makeScrollForTextArea();
                    frame.setVisible(true);
                }
                catch (FileNotFoundException exception){
                    JOptionPane.showMessageDialog(getAllInfo,"No Input File!");
                }
            }
        });
        getPatientIdList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel idPanel = new JPanel();
                    idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.Y_AXIS));
                    List<String> str = model.getAllIDs();
                    JButton[] buttons = new JButton[str.size()];
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
                                    makeScrollForTextArea();
                                    frame.setVisible(true);
                                }
                                catch (Exception exception)
                                {
                                    JOptionPane.showMessageDialog(getAllInfo,"No Information Available!");
                                }
                            }
                        });
                        idPanel.add(buttons[i]);
                    }
                    JScrollPane scroll = new JScrollPane(idPanel);
                    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    frame.add(scroll);
                    frame.setVisible(true);
                }
                catch (Exception exception)
                {
                    JOptionPane.showMessageDialog(getPatientIdList,"No input File!");
                }
            }
        });
        menu.add(getPatientIdList);
        menu.add(getAllInfo);
        menuBar.add(menu);
    }


}
