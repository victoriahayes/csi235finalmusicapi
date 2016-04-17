package musicapiclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.GroupLayout.Alignment;

public class secondFrame {
    
    public JLabel Description;
    public JTextField genreString;
    public JTextField numberString;
    public JCheckBox specificNumSongs;
    public JLabel Title;
    public JPanel mPage2;
    public JTextField artistString;
    public JCheckBox specificArtist;
    public JCheckBox specificGenre;
    public JButton submitButton; 
    public String artist;
    public String genre;
    public String value;
    public int numSongs;
    public String serverParams;
    public int whichPage;
    
    public secondFrame(){}
    
    public JPanel sFrame(JPanel panels)
    {
        mPage2 = new JPanel();
        Title = new JLabel();
        Description = new JLabel();
        specificArtist = new JCheckBox();
        specificGenre = new JCheckBox();
        specificNumSongs = new JCheckBox();
        artistString = new JTextField(25);
        genreString = new JTextField(25);
        numberString = new JTextField(4);
        submitButton = new JButton();
        QueryParams mParams = new QueryParams();
        JPanel mPage2_inset = new JPanel();

        mParams.setConnectionType("top songs");
        Title.setText("Top Songs Menu");
        Description.setText("<html>By default, the Tops Songs menu will output the 10 most popular songs at current.<br>However, this output can be modified by specifying an Artist, Genre, and Number of Songs.<br>Just select which values you'd like to change, then enter the values in the text boxes.</html>");
        specificArtist.setText("Artist");
        specificGenre.setText("Genre");
        specificNumSongs.setText("Number of Songs");
        submitButton.setText("Submit");
        submitButton.addActionListener((ActionEvent evt) -> {
            if (specificArtist.isSelected()) {
                artist = artistString.getText();
                if (artist == null || artist.equals("")) {
                    Alerts alert=new Alerts("Artist field empty, ignoring...");
                    specificArtist.setSelected(false);
                } else {
                    mParams.setArtist(artist);
                }
            }
            if (specificGenre.isSelected()) {
                genre = genreString.getText();
                if (genre == null || genre.equals("")) {
                    Alerts alert=new Alerts("Genre field empty, ignoring...");
                    specificGenre.setSelected(false);
                } else {
                    mParams.setGenre(genre);
                }
            }
            if (specificNumSongs.isSelected()) {
                value = numberString.getText();
                if (value == null || value.equals("")) {
                    Alerts alert=new Alerts("Number of songs field empty, ignoring...");
                    specificNumSongs.setSelected(false);
                } else {
                    try {
                        numSongs = Integer.parseInt(value);
                        mParams.setNumSongs(numSongs);
                    } catch (NumberFormatException NaN) {
                        Alerts alert=new Alerts("Number of songs field not a number.");
                        specificNumSongs.setSelected(false);
                    }
                }
            }
                serverParams = mParams.toJsonString();
                System.out.println(serverParams);
                String conn = new ServerConnection().getServerResponse(serverParams);
                ArrayList<Song> songs= new ResultingJSON().getResult_final(conn);
                JPanel panel4=new finalFrame().lFrame(songs,panels);
                panels.add(panel4,"Results");
                CardLayout cl =(CardLayout) panels.getLayout();
                cl.show(panels,"Results");
        });
        GroupLayout page2_layout = new GroupLayout(mPage2);
        mPage2.setLayout(page2_layout);
        page2_layout.setAutoCreateContainerGaps(true);

        artistString.addActionListener((ActionEvent e) -> {
        });
        genreString.addActionListener((ActionEvent evt) -> {
        });
        numberString.addActionListener((ActionEvent evt) -> {
        });
        // mPage2.add(Title);
        //mPage2.add(Description);
        GridLayout inset_layout = new GridLayout(0, 2);
        mPage2_inset.setLayout(inset_layout);
        mPage2_inset.add(specificArtist);
        mPage2_inset.add(artistString);
        mPage2_inset.add(specificGenre);
        mPage2_inset.add(genreString);
        mPage2_inset.add(specificNumSongs);
        mPage2_inset.add(numberString);
        mPage2_inset.add(submitButton);

        GroupLayout.SequentialGroup hGroup = page2_layout.createSequentialGroup();
        hGroup.addGroup(page2_layout.createParallelGroup().addComponent(Title).addComponent(Description).addComponent(mPage2_inset));
        page2_layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = page2_layout.createSequentialGroup();
        vGroup.addGroup(page2_layout.createParallelGroup(Alignment.BASELINE).addComponent(Title));
        vGroup.addGroup(page2_layout.createParallelGroup(Alignment.BASELINE).addComponent(Description));
        vGroup.addGroup(page2_layout.createParallelGroup(Alignment.BASELINE).addComponent(mPage2_inset));
        page2_layout.setVerticalGroup(vGroup);

        return mPage2;
    }
}
