/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojectmusicapi;
import org.json.*;
/**
 *
 * @author Victoria
 */
public class QueryParams {
    //TODO: create a json string writer to easily pass vaiables to the server
    String connectionType;
    String artist;
    String genre;
    int numSongs;
    
    public QueryParams()
    {
        this.connectionType="";
        this.genre="";
        this.artist="";
        this.numSongs=10;
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
    public void setNumSongs(int numSongs)
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
    
    public int getNumSongs(){
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
    
    public void fromJsonString(String mStr)
    {
        try
        {
            JSONObject jsonStr = new JSONObject(mStr);
            this.connectionType=jsonStr.getString("ConnectionType");
            this.artist=jsonStr.getString("Artist");
            this.genre=jsonStr.getString("Genre");
            try
            {
            this.numSongs=Integer.parseInt(jsonStr.getString("Number of Songs"));
            }
            catch(Exception Nan)
            {}
        }
        catch(Exception ex)
        {
        }
    }
}
