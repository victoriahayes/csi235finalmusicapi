package musicapiclient;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

//arbitrary GUI page

public class finalFrame {
    public JPanel mPanel;
    public JPanel innerPanel;
    
    public JLabel title;
    public JLabel artist_title;
    public JLabel title_title;
    public JLabel index_title;
    public JTextPane indeces;
    public JTextPane artists;
    public JTextPane titles;
    public JButton again;
    public JButton exit;
   
    public finalFrame(){}
    
    public JPanel lFrame(ArrayList<Song> songs, JPanel panels)
    {
        mPanel=new JPanel();
        innerPanel=new JPanel();
        title=new JLabel("<html><b>Results:</b></html>");
        again=new JButton("Again");
        index_title=new JLabel("Index");
        artist_title=new JLabel("Artist");
        title_title=new JLabel("Title");
        exit=new JButton("Exit");
        
        indeces=new JTextPane();
        indeces.setContentType("text/html");
        //indeces.setRows(songs.size());
        indeces.setText(htmlStringBuilder_Indeces(songs));
        indeces.setEditable(false);
        
        artists=new JTextPane();
        artists.setContentType("text/html");
        //artists.setRows(songs.size());
        artists.setText(htmlStringBuilder_Artists(songs));
        
        artists.setEditable(false);
        
        titles=new JTextPane();
        titles.setContentType("text/html");
       // titles.setRows(songs.size());
        titles.setText(htmlStringBuilder_Titles(songs));
        
        titles.setEditable(false);
        
        GroupLayout mLayout=new GroupLayout(mPanel);
        mPanel.setLayout(mLayout);
        
        again.addActionListener((ActionEvent ae)-> {
            CardLayout cl=(CardLayout) panels.getLayout();
            cl.show(panels,"SelectMode");
        });
        exit.addActionListener((ActionEvent ae)-> {
            System.exit(0);
        });
        
        GroupLayout.SequentialGroup hGroup=mLayout.createSequentialGroup();
        hGroup.addGroup(mLayout.createParallelGroup().addComponent(title).addComponent(again));
        hGroup.addGroup(mLayout.createParallelGroup().addComponent(index_title).addComponent(indeces));
        hGroup.addGroup(mLayout.createParallelGroup().addComponent(title_title).addComponent(titles).addComponent(exit));
        hGroup.addGroup(mLayout.createParallelGroup().addComponent(artist_title).addComponent(artists));
        mLayout.setHorizontalGroup(hGroup);
        
        GroupLayout.SequentialGroup vGroup=mLayout.createSequentialGroup();
        vGroup.addGroup(mLayout.createParallelGroup(Alignment.BASELINE).addComponent(title));
        vGroup.addGroup(mLayout.createParallelGroup(Alignment.BASELINE).addComponent(index_title).addComponent(title_title).addComponent(artist_title));
        vGroup.addGroup(mLayout.createParallelGroup(Alignment.BASELINE).addComponent(indeces).addComponent(titles).addComponent(artists));
        vGroup.addGroup(mLayout.createParallelGroup(Alignment.BASELINE).addComponent(again).addComponent(exit));
        mLayout.setVerticalGroup(vGroup);
        return mPanel;
    }
    
    public String htmlStringBuilder_Indeces(ArrayList<Song> songs)
    {
        StringBuilder mHtml=new StringBuilder();
        String mResult=null;
        mHtml.append("<html>");
        int max=songs.size();
        
        for(int i=0; i<max; i++)
        {
            mHtml.append(i);
            mHtml.append("<br>");
        }
        mHtml.append("</html>");
        mResult=mHtml.toString();
        return mResult;
    }
    public String htmlStringBuilder_Artists(ArrayList<Song> songs)
    {
        StringBuilder mHtml=new StringBuilder();
        String mResult=null;
        mHtml.append("<html>");
        int max=songs.size();
        
        for(int i=0; i<max; i++)
        {
            mHtml.append(songs.get(i).getArtist());
            mHtml.append("<br>");
        }
        mHtml.append("</html>");
        mResult=mHtml.toString();
        return mResult;
    }
    public String htmlStringBuilder_Titles(ArrayList<Song> songs)
    {
        StringBuilder mHtml=new StringBuilder();
        String mResult=null;
        mHtml.append("<html>");
        int max=songs.size();
        
        for(int i=0; i<max; i++)
        {
            mHtml.append(songs.get(i).getTitle());
            mHtml.append("<br>");
        }
        mHtml.append("</html>");
        mResult=mHtml.toString();
        return mResult;
    }
    
}
