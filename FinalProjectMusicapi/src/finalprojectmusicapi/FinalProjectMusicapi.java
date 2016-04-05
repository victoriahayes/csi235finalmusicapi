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
