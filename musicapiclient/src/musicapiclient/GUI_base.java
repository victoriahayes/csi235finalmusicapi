package musicapiclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI_base extends JFrame {
    public JPanel panel1;
    public JPanel panel2;
    public JPanel panel3;
    public JPanel panels;
 

    public GUI_base() {
        JFrame frame = new JFrame("Music API");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        panels = new JPanel(new CardLayout());
        panel1 = new firstFrame().fFrame(panels);
        panel2 = new secondFrame().sFrame(panels);
        panel3 = new thirdFrame().tFrame(panels);

        
        panels.add(panel1, "SelectMode");
        panels.add(panel2, "TopSongs");
        panels.add(panel3, "Playlist");

        pane.add(panels, BorderLayout.CENTER);
    }
}
