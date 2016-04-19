//song class for output
package musicapiclient;

public class Song {
    public String songTitle;
    public String songArtist;
    
    public Song()
    {
        this.songTitle=null;
        this.songArtist=null;
    }
    
    public void setArtist(String value)
    {
        this.songArtist=value;
    }
    public void setTitle(String value)
    {
        this.songTitle=value;
    }
    public String getArtist()
    {
        return this.songArtist;
    }
    public String getTitle()
    {
        return this.songTitle;
    }
}
