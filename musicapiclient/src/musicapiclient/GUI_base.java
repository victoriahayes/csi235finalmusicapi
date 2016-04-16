package musicapiclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.GroupLayout.Alignment;

public class GUI_base extends JFrame {

    public int nextPage;
    private JPanel mPage1;
    private ButtonGroup buttonGroup1;
    private JLabel page1_Title;
    private JRadioButton page1_option1;
    private JRadioButton page1_option2;
    private JButton page1_submit;
    private JLabel Description;
    private JTextField genreString;
    private JTextField numberString;
    private JCheckBox specificNumSongs;
    private JLabel Title;
    private JPanel mPage2;
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
    public int whichPage;
    public JPanel panels;

    public GUI_base() {
        JFrame frame = new JFrame("Music API");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        
        JPanel panel1=firstFrame();
        JPanel panel2=nextFrames(1);
        JPanel panel3=nextFrames(2);
        
        panels=new JPanel(new CardLayout());
        panels.add(panel1,"SelectMode");
        panels.add(panel2,"TopSongs");
        panels.add(panel3, "Playlist");
     
        pane.add(panels, BorderLayout.CENTER);
    }
    public JPanel firstFrame()
    {
        JPanel mPage1 = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
       
        GridLayout page1_layout= new GridLayout(0,1);
        mPage1.setLayout(page1_layout);
        mPage1.add( page1_Title);
        mPage1.add(page1_option1);
        mPage1.add(page1_option2);
        mPage1.add(page1_submit);
        
        
        page1_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                CardLayout cl = (CardLayout) panels.getLayout();
                if (page1_option1.isSelected()) {
                    System.out.println("Top Songs Selected");
                    cl.show(panels, "TopSongs");
                } else if (page1_option2.isSelected()) {
                    System.out.println("Playlist selected");
                    cl.show(panels, "Playlist");
                } else {
                    System.out.println("Something is wrong");
                    nextPage = 0;
                }
            }
        });
        return mPage1;
    }

    public JPanel nextFrames(int whichPage) {
        mPage2 = new JPanel();
        Title = new JLabel();
        Description = new JLabel();
        specificArtist = new JCheckBox();
        specificGenre = new JCheckBox();
        specificNumSongs = new JCheckBox();
        artistString = new JTextField(15);
        genreString = new JTextField(15);
        numberString = new JTextField(4);
        submitButton = new JButton();
        mParams = new QueryParams();
        JPanel mPage2_inset=new JPanel();

        if (whichPage == 1) {
            mParams.setConnectionType("top songs");
            Title.setText("Top Songs Menu");
            Description.setText("<html>By default, the Tops Songs menu will output the 10 most popular songs at current.<br>However, this output can be modified by specifying an Artist, Genre, and Number of Songs.<br>Just select which values you'd like to change, then enter the values in the text boxes.</html>");
        } else if (whichPage == 2) {
            mParams.setConnectionType("playlist");
            Title.setText("Playlist Generation Menu");
            Description.setText("<html>In order to generate a playlist, you need to input traits that you'd like the songs to have.<br>This includes the size of the playlist, artist the songs sound like, or the genre of the songs</html>");
        }
        
        
        specificArtist.setText("Artist");
        specificGenre.setText("Genre");
        specificNumSongs.setText("Number of Songs");
        submitButton.setText("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (specificArtist.isSelected()) {
                    artist = artistString.getText();
                    if (artist == null || artist == "") {
                        //alert artist field is empty
                        specificArtist.setSelected(false);
                    } else {
                        mParams.setArtist(artist);
                    }
                }
                if (specificGenre.isSelected()) {
                    genre = genreString.getText();
                    if (genre == null || genre == "") {
                        //alert genre field is empty
                        specificGenre.setSelected(false);
                    } else {
                        mParams.setGenre(genre);
                    }
                }
                if (specificNumSongs.isSelected()) {
                    value = numberString.getText();
                    if (value == null || value == "") {
                        //alert number field is empty
                        specificNumSongs.setSelected(false);
                    } else {
                        try {
                            numSongs = Integer.parseInt(value);
                            mParams.setNumSongs(numSongs);
                        } catch (NumberFormatException NaN) {
                            specificNumSongs.setSelected(false);
                        }
                    }
                }
                if (whichPage == 2 && !(specificArtist.isSelected() || specificGenre.isSelected())) {
                    //alert equivalent
                    System.out.println("Need to have either artist or genre fields filled out.");
                } else {
                    jsonConv = new jsonConverter();
                    serverParams = jsonConv.toJsonString(mParams);
                    conn = new ServerConnection(serverParams);
                }
            }
        });
        GroupLayout page2_layout=new GroupLayout(mPage2);
        mPage2.setLayout(page2_layout);
        page2_layout.setAutoCreateContainerGaps(true);
       
       // mPage2.add(Title);
        //mPage2.add(Description);
        
        GridLayout inset_layout=new GridLayout(0,2);
        mPage2_inset.setLayout(inset_layout);
        mPage2_inset.add(specificArtist);
        mPage2_inset.add(artistString);
        mPage2_inset.add(specificGenre);
        mPage2_inset.add(genreString);
        mPage2_inset.add(specificNumSongs);
        mPage2_inset.add(numberString);
        mPage2_inset.add(submitButton);
        
        GroupLayout.SequentialGroup hGroup = page2_layout.createSequentialGroup();
        hGroup.addGroup(page2_layout.createParallelGroup().
            addComponent(Title).addComponent(Description).addComponent(mPage2_inset));
        page2_layout.setHorizontalGroup(hGroup);
         GroupLayout.SequentialGroup vGroup = page2_layout.createSequentialGroup();
         vGroup.addGroup(page2_layout.createParallelGroup(Alignment.BASELINE).addComponent(Title));
         vGroup.addGroup(page2_layout.createParallelGroup(Alignment.BASELINE).addComponent(Description));
         vGroup.addGroup(page2_layout.createParallelGroup(Alignment.BASELINE).addComponent(mPage2_inset));
         page2_layout.setVerticalGroup(vGroup);
         
         
        
        return mPage2;
    }
}
