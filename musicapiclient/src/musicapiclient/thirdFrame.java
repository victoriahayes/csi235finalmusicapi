//arbitraru GUI page

package musicapiclient;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.GroupLayout.Alignment;

public class thirdFrame {
    
    public JPanel mPage3;
    public JLabel Title2;
    public JLabel Description2;
    public JRadioButton specificArtist2;
    public JRadioButton specificGenre2;
    public JCheckBox specificNumSongs2;
    public JTextField artistString2;
    public JTextField genreString2;
    public JTextField numberString2;
    public JButton submitButton2;
    public String artist;
    public String genre;
    public String value;
    public int numSongs;
    public String serverParams;
    public int whichPage;
    public JPanel panel1;
    
    public thirdFrame(){}
    
    public JPanel tFrame(JPanel panels)
    {
         mPage3 = new JPanel();
        Title2 = new JLabel();
        ButtonGroup noDouble=new ButtonGroup();
        Description2 = new JLabel();
        specificArtist2 = new JRadioButton();
        specificGenre2 = new JRadioButton();
        specificNumSongs2 = new JCheckBox();
        artistString2 = new JTextField(25);
        genreString2 = new JTextField(25);
        numberString2 = new JTextField(4);
        submitButton2 = new JButton();
        QueryParams mParams1 = new QueryParams();
        JPanel mPage3_inset = new JPanel();

        mParams1.setConnectionType("playlist");
        Title2.setText("Playlist Generation Menu");
        Description2.setText("<html>In order to generate a playlist, you need to input traits that you'd like the songs to have.<br>This includes the size of the playlist, artist the songs sound like, or the genre of the songs</html>");
        noDouble.add(specificArtist2);
        noDouble.add(specificGenre2);
        specificArtist2.setText("Artist");
        specificGenre2.setText("Genre");
        specificNumSongs2.setText("Number of Songs");
        submitButton2.setText("Submit");
        
        submitButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (specificArtist2.isSelected()) {
                    artist = artistString2.getText();
                    if (artist == null || artist.equals("")) {
                        specificArtist2.setSelected(false);
                    } else {
                        mParams1.setArtist(artist);
                    }
                }
                if (specificGenre2.isSelected()) {
                    genre = genreString2.getText();
                    if (genre == null || genre.equals("")) {
                        specificGenre2.setSelected(false);
                    } else {
                        mParams1.setGenre(genre);
                    }
                }
                if (specificNumSongs2.isSelected()) {
                    value = numberString2.getText();
                    if (value == null || value.equals("")) {
                        Alerts alert=new Alerts("Number of songs field empty. Ignoring...");
                        specificNumSongs2.setSelected(false);
                    } else {
                        try {
                            numSongs = Integer.parseInt(value);
                            mParams1.setNumSongs(value);
                        } catch (NumberFormatException NaN) {
                            Alerts alert=new Alerts("Number of songs field not a number");
                            specificNumSongs2.setSelected(false);
                        }
                    }
                }
                if (!(specificArtist2.isSelected() || specificGenre2.isSelected())) {
                    Alerts alert=new Alerts("Need to have either artist or genre fields filled out.");
                } 
                else if(specificArtist2.isSelected() && artistString2.getText().trim().isEmpty())
                {
                    Alerts alert=new Alerts("Need to have some value in Artist field");
                }
                else if(specificGenre2.isSelected() && genreString2.getText().trim().isEmpty())
                {
                    Alerts alert=new Alerts("Need to have some value in Genre field");
                }
                else {

                serverParams = mParams1.toJsonString();
                String conn1 = new ServerConnection().getServerResponse(serverParams);
                ArrayList<Song> songs= new ResultingJSON().getResult_final(conn1);
                JPanel panel4=new finalFrame().lFrame(songs, panels);
                panels.add(panel4,"Results");
                CardLayout cl =(CardLayout) panels.getLayout();
                cl.show(panels,"Results");
                }
            }
        });
        GroupLayout page3_layout = new GroupLayout(mPage3);
        mPage3.setLayout(page3_layout);
        
        page3_layout.setAutoCreateContainerGaps(true);
      
        GridLayout inset_layout = new GridLayout(0, 2);
        mPage3_inset.setLayout(inset_layout);
        mPage3_inset.add(specificArtist2);
        mPage3_inset.add(artistString2);
        mPage3_inset.add(specificGenre2);
        mPage3_inset.add(genreString2);
        mPage3_inset.add(specificNumSongs2);
        mPage3_inset.add(numberString2);
        mPage3_inset.add(submitButton2);

        GroupLayout.SequentialGroup hGroup = page3_layout.createSequentialGroup();
        hGroup.addGroup(page3_layout.createParallelGroup().addComponent(Title2).addComponent(Description2).addComponent(mPage3_inset));
        page3_layout.setHorizontalGroup(hGroup);
        GroupLayout.SequentialGroup vGroup = page3_layout.createSequentialGroup();
        vGroup.addGroup(page3_layout.createParallelGroup(Alignment.BASELINE).addComponent(Title2));
        vGroup.addGroup(page3_layout.createParallelGroup(Alignment.BASELINE).addComponent(Description2));
        vGroup.addGroup(page3_layout.createParallelGroup(Alignment.BASELINE).addComponent(mPage3_inset));
        page3_layout.setVerticalGroup(vGroup);

        return mPage3;
    }
}
