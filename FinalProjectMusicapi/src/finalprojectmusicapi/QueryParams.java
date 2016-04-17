/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicapiclient;

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
        return this.genre;
    }
    
    public String getArtist(){
        return this.artist;
    }
    
    public String getConnectionType()
    {
        return this.connectionType;
    }
    
    public int getNumSongs(){
        return this.numSongs;
    }
}
