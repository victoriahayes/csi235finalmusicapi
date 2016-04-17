package musicapiclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class firstFrame {
    public ButtonGroup buttonGroup1;
    public JRadioButton page1_option1;
    public JRadioButton page1_option2;
    public JLabel page1_Title;
    public JButton page1_submit;
    
    public int nextPage;
    public JPanel mPage1;
    public int whichPage;

    public firstFrame(){}
    
    public JPanel fFrame(JPanel panels)
    {
        mPage1 = new JPanel();
        buttonGroup1 = new ButtonGroup();
        page1_Title = new JLabel();
        page1_option1 = new JRadioButton();
        page1_option2 = new JRadioButton();
        page1_submit = new JButton();

        page1_Title.setText("I would like to...");

        page1_option1.setText("View top songs");
        page1_option2.setText("Generate a playlist");
        page1_option1.setSelected(true);
        buttonGroup1.add(page1_option1);
        buttonGroup1.add(page1_option2);

        page1_submit.setText("Submit");

        GridLayout page1_layout = new GridLayout(0, 1);
        mPage1.setLayout(page1_layout);
        mPage1.add(page1_Title);
        mPage1.add(page1_option1);
        mPage1.add(page1_option2);
        mPage1.add(page1_submit);
        page1_submit.addActionListener((ActionEvent ae) -> {
            CardLayout cl = (CardLayout) panels.getLayout();
            if (page1_option1.isSelected()) {
                System.out.println("Top Songs Selected");
                cl.show(panels, "TopSongs");
            } else if (page1_option2.isSelected()) {
                System.out.println("Playlist selected");
                cl.show(panels, "Playlist");
            } else {
                Alerts alert=new Alerts("please make a selection");
                nextPage = 0;
            }
        });
        return mPage1;
    }
}
