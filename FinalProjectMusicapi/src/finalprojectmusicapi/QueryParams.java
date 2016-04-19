//reads json string of variables sent by client
package finalprojectmusicapi;

import org.json.*;

public class QueryParams {
    String connectionType;
    String artist;
    String genre;
    String numSongs;

    public QueryParams() {
        this.connectionType = "";
        this.genre = "null";
        this.artist = "null";
        this.numSongs = "null";
    }

    public void setConnectionType(String conn) {
        this.connectionType = conn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setNumSongs(String numSongs) {
        this.numSongs = numSongs;
    }

    public String getGenre() {
        String tmpGenre = null;
        tmpGenre = this.genre.trim();
        tmpGenre = tmpGenre.replaceAll(" +", "+");
        return tmpGenre;
    }

    public String getArtist() {
        String tmpArtist = null;
        tmpArtist = this.artist.trim();
        tmpArtist = tmpArtist.replaceAll(" +", "+");
        return tmpArtist;
    }

    public String getConnectionType() {
        return this.connectionType;
    }

    public String getNumSongs() {
        return this.numSongs;
    }

    public void fromJsonString(String mStr) {
        try
        {
            JSONObject jsonStr = new JSONObject(mStr);
            this.connectionType=jsonStr.getString("ConnectionType");
            this.artist=jsonStr.getString("Artist");
            this.genre=jsonStr.getString("Genre");
            this.numSongs=jsonStr.getString("Number of Songs");
        }
        catch(Exception ex)
        {
        }
    }
}
