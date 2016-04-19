
package musicapiclient;
import org.json.*;

//makes simple JSON string of user input variables for server to parse
public class QueryParams {
    String connectionType;
    String artist;
    String genre;
    String numSongs;
    
    public QueryParams()
    {
        this.connectionType="";
        this.genre="null";
        this.artist="null";
        this.numSongs="10";
    }
    
    public void setConnectionType(String conn)
    {
        this.connectionType=conn;
    }
    
    public void setGenre(String genre)
    {
        this.genre=genre;
    }
    
    public void setArtist(String artist){
        this.artist=artist;
    }
    public void setNumSongs(String numSongs)
    {
        this.numSongs=numSongs;
    }
    
    public String getGenre()
    {
        String tmpGenre=null;
        tmpGenre=this.genre.trim();
        tmpGenre=tmpGenre.replaceAll(" +", "+");
        return tmpGenre;
    }
    
    public String getArtist(){
        String tmpArtist=null;
        tmpArtist=this.artist.trim();
        tmpArtist=tmpArtist.replaceAll(" +", "+");
        return tmpArtist;
    }
    
    public String getConnectionType()
    {
        return this.connectionType;
    }
    
    public String getNumSongs(){
        return this.numSongs;
    }
    
    public String toJsonString()
    {
        try
        {
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("ConnectionType", this.connectionType);
            jsonObj.put("Artist", this.artist);
            jsonObj.put("Genre", this.genre);
            jsonObj.put("Number of Songs", this.numSongs);
            return jsonObj.toString();
        }
        catch(Exception ex)
        {
            System.out.println("Error");
        }
        return null;
    }
    
}
