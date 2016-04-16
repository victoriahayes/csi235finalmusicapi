/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package finalprojectmusicapi;

import java.io.IOException;
import java.io.InputStream; 
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
/**
 *
 * @author Victoria Hayes Alec Rulev
 */
public class FinalProjectMusicapi {
final String APIKEY = "FGYIK9QZRNXZTLU1F";
final String STATICPLAYLIST = "http://developer.echonest.com/api/v4/playlist/static?api_key=";
final String SEARCHBYARTIST = "http://developer.echonest.com/api/v4/song/search?api_key=";
/**
     * @param args the command line arguments
     */

//Convert function into song object
public static Song[] parseData(String songJsonStr, int numSongs)
{
	Song[] songs = new Song[numSongs];
	try{
		 JSONObject songJson = new JSONObject(songJsonStr);
		 JSONArray songJsonArray = songJson.getJSONArray("songs");
		 for(int i = 0;i<numSongs;i++)
		 {
			 JSONObject song = (JSONObject)songJsonArray.get(i);
			 Song temp = new Song(song.getString("artist_name"), song.getString("title"));
			 songs[i] = temp;
		 }
	}catch(JSONException e){System.out.println(e.getMessage());}
	return songs;
}

// issue: figure out how to fetch what you're searching for from client
public  String fetchData(String connectionType, String artist, int numSongs)
{
	HttpURLConnection urlConnection = null;
	BufferedReader reader = null;
	String jsonString = null;
	String sUrl = null;
	try
	{	
            if(connectionType == "top songs")
		{
			sUrl = SEARCHBYARTIST + APIKEY + "&artist=" + artist + "&results=" + numSongs;
		}
		else if(connectionType == "playlist")
		{
			sUrl = STATICPLAYLIST + APIKEY + "&artist=" + artist + "&results=" + numSongs;
		}
		URL url = new URL(sUrl);
		urlConnection = (HttpURLConnection) url.openConnection();   
		urlConnection.setRequestMethod("GET"); 
		urlConnection.connect();
	  
	  InputStream inputStream = urlConnection.getInputStream();
	  
	  reader = new BufferedReader(new InputStreamReader(inputStream));
	  String line;    
	  StringBuilder buffer = new StringBuilder();       
	  while ((line = reader.readLine()) != null) 
	  { 
		 buffer.append(line).append("\n");  
	  }
	  jsonString = buffer.toString();
	}catch(IOException e)
	{
		System.out.println(e.getMessage());
	}
	finally
	{
		if (urlConnection != null)
		{ 
		 urlConnection.disconnect();     //disconnect  
		}  
		if (reader != null)
		{                
			try       

			 {            
				 reader.close();    //close the input stream  
			 }                
			catch (final Exception e){
				 System.out.println(e.getMessage()); }
		}
		else
			return jsonString;
		 }
	return jsonString;
	}
	
    public static void main(String[] args) {
        // TODO build this to be the server side
        /* Goals:
        * read form sent by client
        * parse the client's input into strings that can be built into the URL
        * read the JSON string sent by api
        * give response to client--possibly formatted back into a simplified JSON string or as an entire web page.
        */
    	
    	Socket sSocket = null;   
    	//client connection 
    	try{
    		ServerSocket serverSocket = new ServerSocket(6000);
    		System.out.println("Waiting for connection....");
    		sSocket = serverSocket.accept();
    		System.out.println("Connected to client");
    	}catch(IOException ex){
    		System.out.println(ex.getMessage());
    	}
    	
    	/*
    	 *     public QueryParams()
    {
        this.connectionType="";
        this.genre="";
        this.artist="";
        this.numSongs=10;
    }
    	 */
    	try{
    		BufferedReader br = new BufferedReader(new InputStreamReader(sSocket.getInputStream()));
    		
    		String inputLine;
    		while((inputLine = br.readLine()) != null){
    			System.out.println("Client request: " + inputLine);
    			//String[] parameters = inputLine.S
    		}
    	}
        catch(Exception ex)
        {}
    }
    
}
