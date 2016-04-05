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
public class GUI_page1 extends javax.swing.JFrame {
    public int nextPage;
    private ButtonGroup buttonGroup1;
    private JLabel page1_Title;
    private JRadioButton page1_option1;
    private JRadioButton page1_option2;
    private JButton page1_submit;

    public GUI_page1() {
        this.nextPage=0;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        buttonGroup1 = new ButtonGroup();
        page1_Title = new JLabel();
        page1_option1 = new JRadioButton();
        page1_option2 = new JRadioButton();
        page1_submit = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        page1_Title.setText("I would like to...");

        page1_option1.setText("View top songs");
        page1_option2.setText("Generate a playlist");
        page1_option1.setSelected(true);
        buttonGroup1.add(page1_option1);
        buttonGroup1.add(page1_option2);

        page1_submit.setText("Submit");
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

//the following is generated by NetBeans' interactive GUI editor.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(page1_Title))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(156, 156, 156)
                                        .addComponent(page1_submit))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(page1_option1)
                                                .addComponent(page1_option2))))
                        .addContainerGap(179, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(page1_Title)
                        .addGap(18, 18, 18)
                        .addComponent(page1_option1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(page1_option2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(page1_submit)
                        .addContainerGap(181, Short.MAX_VALUE))
        );
        pack();
    }
}