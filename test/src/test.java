import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class test {


    public class ArrayToButtons {
        private JFrame frame;
        private JPanel pane;
        private JButton[] buttons;
        private static final String[] OPTIONS = {"Eagle", "Rabbit", "Dolphin", "Apple"};

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new ArrayToButtons()::createAndShowGui);
        }

        private void createAndShowGui() {
            frame = new JFrame(getClass().getSimpleName());
            pane = new JPanel();
            pane.setLayout(new GridLayout(2, 2));

            buttons = new JButton[OPTIONS.length];

            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JButton(OPTIONS[i]);
                pane.add(buttons[i]);
            }

            frame.add(new JLabel("Which is not an animal?"), BorderLayout.NORTH);
            frame.add(pane, BorderLayout.CENTER);

            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
