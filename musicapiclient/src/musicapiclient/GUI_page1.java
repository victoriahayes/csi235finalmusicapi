/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicapiclient;

import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Seaweed
 */
public class GUI_page1 extends JPanel {
    public int nextPage;
    private JPanel mPage1;//main object is here
    private ButtonGroup buttonGroup1;
    private JLabel page1_Title;
    private JRadioButton page1_option1;
    private JRadioButton page1_option2;
    private JButton page1_submit;

    public GUI_page1() {
        this.nextPage=0;
        JPanel mPage1=new JPanel();
        
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
        
        mPage1.add(page1_Title);
        mPage1.add(page1_option1);
        mPage1.add(page1_option2);
        mPage1.add(page1_submit);
        page1_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (page1_option1.isSelected()) {
                    System.out.println("Top Songs Selected");
                    nextPage=1;
                    new GUI_page2(nextPage).setVisible(true);  
                } else if (page1_option2.isSelected()) {
                    System.out.println("Playlist selected");
                    nextPage=2;
                    new GUI_page2(nextPage).setVisible(true);  
                } else {
                    System.out.println("Something is wrong");
                    nextPage=0;
                }
            }
        });
    }
}
