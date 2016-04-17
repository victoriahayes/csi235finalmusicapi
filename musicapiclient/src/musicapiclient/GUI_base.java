package musicapiclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.GroupLayout.Alignment;

public class GUI_base extends JFrame {

    public JPanel panel2;
    public JPanel panel3;
    public JPanel mPage3;
    public JLabel Title2;
    public JLabel Description2;
    public JCheckBox specificArtist2;
    public JCheckBox specificGenre2;
    public JCheckBox specificNumSongs2;
    public JTextField artistString2;
    public JTextField genreString2;
    public JTextField numberString2;
    public JButton submitButton2;
    public int nextPage;
    public JPanel mPage1;
    public ButtonGroup buttonGroup1;
    public JLabel page1_Title;
    public JRadioButton page1_option1;
    public JRadioButton page1_option2;
    public JButton page1_submit;
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
    public JPanel panels;
    public JPanel panel1;

    public GUI_base() {
        JFrame frame = new JFrame("Music API");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public void addComponentsToPane(Container pane) {

        panel1 = firstFrame();
        panel2 = secondFrame();
        panel3 = thirdFrame();

        panels = new JPanel(new CardLayout());
        panels.add(panel1, "SelectMode");
        panels.add(panel2, "TopSongs");
        panels.add(panel3, "Playlist");

        pane.add(panels, BorderLayout.CENTER);
    }

    public JPanel firstFrame() {
        mPage1 = new JPanel();
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
                System.out.println("Something is wrong");
                nextPage = 0;
            }
        });
        return mPage1;
    }

    /**
     *
     * @return
     */
    public JPanel secondFrame() {
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
                    //alert artist field is empty
                    specificArtist.setSelected(false);
                } else {
                    mParams.setArtist(artist);
                }
            }
            if (specificGenre.isSelected()) {
                genre = genreString.getText();
                if (genre == null || genre.equals("")) {
                    //alert genre field is empty
                    specificGenre.setSelected(false);
                } else {
                    mParams.setGenre(genre);
                }
            }
            if (specificNumSongs.isSelected()) {
                value = numberString.getText();
                if (value == null || value.equals("")) {
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
                serverParams = mParams.toJsonString();
                System.out.println(serverParams);
                ServerConnection conn = new ServerConnection(serverParams);
            }
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

    public JPanel thirdFrame() {
        mPage3 = new JPanel();
        Title2 = new JLabel();
        ButtonGroup noDouble=new ButtonGroup();
        Description2 = new JLabel();
        specificArtist2 = new JCheckBox();
        specificGenre2 = new JCheckBox();
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
                        //alert artist field is empty
                        specificArtist2.setSelected(false);
                    } else {
                        mParams1.setArtist(artist);
                    }
                }
                if (specificGenre2.isSelected()) {
                    genre = genreString2.getText();
                    if (genre == null || genre.equals("")) {
                        //alert genre field is empty
                        specificGenre2.setSelected(false);
                    } else {
                        mParams1.setGenre(genre);
                    }
                }
                if (specificNumSongs2.isSelected()) {
                    value = numberString2.getText();
                    if (value == null || value.equals("")) {
                        //alert number field is empty
                        specificNumSongs2.setSelected(false);
                    } else {
                        try {
                            numSongs = Integer.parseInt(value);
                            mParams1.setNumSongs(numSongs);
                        } catch (NumberFormatException NaN) {
                            specificNumSongs2.setSelected(false);
                        }
                    }
                }
                if (whichPage == 2 && !(specificArtist2.isSelected() || specificGenre2.isSelected())) {
                    //alert equivalent
                    System.out.println("Need to have either artist or genre fields filled out.");
                } else {

                    serverParams = mParams1.toJsonString();
                    System.out.println(serverParams);
                    ServerConnection conn1 = new ServerConnection(serverParams);
                }
            }
        });
        GroupLayout page3_layout = new GroupLayout(mPage3);
        mPage3.setLayout(page3_layout);
        page3_layout.setAutoCreateContainerGaps(true);

        artistString2.addActionListener((ActionEvent e) -> {
        });
        genreString2.addActionListener((ActionEvent evt) -> {
        });
        numberString2.addActionListener((ActionEvent evt) -> {
        });
        // mPage2.add(Title);
        //mPage2.add(Description);
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
