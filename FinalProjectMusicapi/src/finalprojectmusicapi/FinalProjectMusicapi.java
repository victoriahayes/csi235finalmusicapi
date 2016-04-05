/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.InputStream; 
import java.net.HttpURLConnection; 
import java.net.URL;

import org.json.*;
package finalprojectmusicapi;

/**
 *
 * @author Victoria Hayes
 */
public class FinalProjectMusicapi {
final String APIKEY = "FGYIK9QZRNXZTLU1F";
final String SITEACCESS = "http://developer.echonest.com/api/v4/song/search?api_key=FGYIK9QZRNXZTLU1F";
    /**
     * @param args the command line arguments
     */

//Convert function into song object
public static Movie[] parseData(String songJsonStr, int numSongs)
{
	Movie[] songs = new Movie[numSongs];
	try{
		 JSONObject songJson = new JSONObject(songJsonStr);
		 JSONArray songJsonArray = songJson.getJSONArray("songss");
		 for(int i = 0;i<numSongs;i++)
		 {
			 JSONObject song = (JSONObject)songJsonArray.get(i);
			 Movie temp = new Movie(song.getString("artist_name"), movie.getString("title"));
			 songs[i] = temp;
		 }
	}catch(JSONException e){System.out.println(e.getMessage());}
	return songs;
}

// issue: figure out how to fetch what you're searching for from client
public static String fetchData(int year)
{
	HttpURLConnection urlConnection = null;
	BufferedReader reader = null;
	String jsonString = null;
	try
	{
		String sUrl= SITEACCESS;// + year + "&sort_by=vote_average.desc&api_key=" + APIKEY;
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
			catch (final IOException e){
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
    	
    	
    }
    
}
