package musicapiclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//simple dialogue response for errors
public class Alerts extends JFrame {
    
    public JFrame frame;
    public JLabel label;
    public JButton button;
    public GridLayout layout;
    
    public Alerts(String info)
    {
        frame=new JFrame();
        label=new JLabel(info);
        button=new JButton("OK");
        
        button.addActionListener((ActionEvent ae) -> {frame.dispose();});
        layout=new GridLayout(0,1);
        
        frame.setLayout(layout);
        
        frame.getContentPane().add(label);
        frame.getContentPane().add(button);
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setVisible(true);
        
    }
}
