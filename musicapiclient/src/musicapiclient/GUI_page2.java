package musicapiclient;

import javax.swing.*;
import java.awt.event.*;

public class GUI_page2 extends javax.swing.JFrame {
    private int whichPage;
    private JLabel Description;
    private JTextField genreString;
    private JTextField numberString;
    private JCheckBox specificNumSongs;
    private JLabel Title;
    private JTextField artistString;
    private JCheckBox specificArtist;
    private JCheckBox specificGenre;
    private JButton submitButton;
    private jsonConverter jsonConv;
    public String artist;
    public String genre;
    public String value;
    public int numSongs;
    public String serverParams;
    public ServerConnection conn;
    public QueryParams mParams;
    
   
    public GUI_page2(int whichPage) {
        this.whichPage=whichPage;
        
        Title = new JLabel();
        Description = new JLabel();
        specificArtist = new JCheckBox();
        specificGenre = new JCheckBox();
        specificNumSongs = new JCheckBox();
        artistString = new JTextField(15);
        genreString = new JTextField(15);
        numberString = new JTextField(4);
        submitButton = new JButton();
        mParams=new QueryParams();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        if(whichPage==1)
        {
            mParams.setConnectionType("top songs");
            Title.setText("Top Songs Menu");
            Description.setText("<html>By default, the Tops Songs menu will output the 10 most popular songs at current.<br>However, this output can be modified by specifying an Artist, Genre, and Number of Songs.<br>Just select which values you'd like to change, then enter the values in the text boxes.</html>");
        }
        else if(whichPage==2)
        {
            mParams.setConnectionType("playlist");
            Title.setText("Playlist Generation Menu");
            Description.setText("<html>In order to generate a playlist, you need to input traits that you'd like the songs to have.<br>This includes the size of the playlist, artist the songs sound like, or the genre of the songs</html>");
        }
        specificArtist.setText("Artist");
        specificArtist.addActionListener((ActionEvent evt) -> {
        });

        specificGenre.setText("Genre");

        specificNumSongs.setText("Number of Songs");
        specificNumSongs.addActionListener((ActionEvent evt) -> {
        });
        
        genreString.addActionListener((ActionEvent evt) -> {
        });
        numberString.addActionListener((ActionEvent evt) -> {
        });

        submitButton.setText("Submit");
        submitButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent evt)
        {
            if(specificArtist.isSelected())
            {
                artist=artistString.getText();
                if(artist==null||artist=="")
                {
                    //alert artist field is empty
                    specificArtist.setSelected(false);
                }
                else
                {
                    mParams.setArtist(artist);
                }
            }
            if(specificGenre.isSelected()){
             genre=genreString.getText();
                if(genre==null||genre=="")
                {
                    //alert genre field is empty
                    specificGenre.setSelected(false);
                }
                else
                {
                    mParams.setGenre(genre);
                }
            }
            if(specificNumSongs.isSelected()){
                value=numberString.getText();
                if(value==null||value=="")
                {
                    //alert number field is empty
                    specificNumSongs.setSelected(false);
                }
                else
                {
                    try{
                          numSongs=Integer.parseInt(value);
                          mParams.setNumSongs(numSongs);
                    }
                    catch(NumberFormatException NaN)
                    {
                        specificNumSongs.setSelected(false);
                    }
                }
            }
            if(whichPage==2 && !(specificArtist.isSelected()||specificGenre.isSelected()))
            {
                //alert equivalent
                System.out.println("Need to have either artist or genre fields filled out.");
            }
            else
            {
                jsonConv=new jsonConverter();
                serverParams=jsonConv.toJsonString(mParams);
                conn=new ServerConnection(serverParams);
            }
        }
        });
        
        //created by netbean's formatter. Will change later.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Title)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Description)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(specificArtist)
                                    .addComponent(specificGenre)
                                    .addComponent(specificNumSongs))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(genreString, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(artistString, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numberString))))))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitButton)
                .addGap(136, 136, 136))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Description)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(specificArtist)
                    .addComponent(artistString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(specificGenre)
                    .addComponent(genreString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(specificNumSongs)
                    .addComponent(numberString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }                      
}
