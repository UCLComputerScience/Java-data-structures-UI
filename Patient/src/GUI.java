import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class GUI {

    JFrame frame = new JFrame("Patient Viewer");
    String pathFile = "empty";
    JButton go = new JButton("Go");
    JTextField searchTextField = new JTextField(20);
    String searchedText;
    JMenuBar menuBar = new JMenuBar();
    JTextArea textArea = new JTextArea();
    JScrollPane scroll = new JScrollPane(textArea);
    Model model = new Model();
    JButton[] buttons;


    public void panelContainer()
    {
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        getInformationMenu();
        fileChoose();
        textArea.setEditable(false);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(menuBar,BorderLayout.NORTH);
        frame.getContentPane().add(scroll,BorderLayout.CENTER);
        JPanel searchBar = new JPanel();
        JLabel searchLabel = new JLabel("Search :");
        searchBar.add(searchLabel);
        searchBar.add(searchTextField);
        searchBar.add(go);
        frame.getContentPane().add(searchBar,BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public void getInformationMenu()
    {
        JMenu menu3 = new JMenu("Get Information");
        JMenuItem menu3Item1 = new JMenuItem("Patient Names");
        JMenuItem menu3Item2 = new JMenuItem("Patient IDs");
        JMenuItem menu3Item3 = new JMenuItem("Patients Information");
        menu3.add(menu3Item1);
        menu3.add(menu3Item2);
        menu3.add(menu3Item3);
        menuBar.add(menu3);

        menu3Item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    model.readFile(pathFile);
                    String output = model.getAllNames();
                    textArea.setText(output);
                }
                catch (FileNotFoundException exception){
                    JOptionPane.showMessageDialog(menu3Item1,"No Input File!");
                }
            }
        });

        menu3Item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    model.readFile(pathFile);
                    String output = model.getAllPatient();
                    textArea.setText(output);

                }
                catch (FileNotFoundException exception){
                    JOptionPane.showMessageDialog(menu3Item1,"No Input File!");
                }
            }
        });

        menu3Item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel inputIDPanel = new JPanel();
                    BoxLayout boxlayout = new BoxLayout(inputIDPanel, BoxLayout.Y_AXIS);
                    model.readFile(pathFile);
                    List<String> output = model.getAllIDs();
                    buttons = new JButton[output.size()];
                    inputIDPanel.setLayout(boxlayout);
                    for(int i = 0; i < output.size(); i++)
                    {
                        buttons[i] = new JButton(output.get(i));
                        inputIDPanel.add(buttons[i]);
                    }
                    frame.getContentPane().add(inputIDPanel);
                    frame.setVisible(true);
                }
                catch (FileNotFoundException exception){
                    JOptionPane.showMessageDialog(menu3Item1,"File not Found!");
                }
            }
        });





    }

    public void createSearchBar()
    {




        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("You searched for: " + searchTextField.getText());
                searchedText = searchTextField.getText();
            }
        });
    }

    public void fileChoose()
    {
        JMenu menu1 = new JMenu("Files");
        JMenuItem menu1Item1 = new JMenuItem("Load");
        menu1Item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(menu1Item1);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        pathFile = selectedFile.getAbsolutePath();
                        model.readFile(pathFile);
                        textArea.setText("Selected file path: " + pathFile);
                    }
                }
                catch(FileNotFoundException exception){
                        JOptionPane.showMessageDialog(menu1Item1,"File not Found!");
                    }
                }
            });
        JMenuItem menu1Item3 = new JMenuItem("Save");
        menu1.add(menu1Item1);
        menu1.add(menu1Item3);
        menuBar.add(menu1);
    }

    public void listenInputs()
    {
        JMenu menu2 = new JMenu("Mathematics");
        JMenuItem menu2Item1 = new JMenuItem("Average age");
        JMenuItem menu2Item2 = new JMenuItem("Average Birth Year");
        JMenuItem menu2Item3 = new JMenuItem("Age Distribution");
        menu2.add(menu2Item1);
        menu2.add(menu2Item2);
        menu2.add(menu2Item3);
        menuBar.add(menu2);
    }


}
