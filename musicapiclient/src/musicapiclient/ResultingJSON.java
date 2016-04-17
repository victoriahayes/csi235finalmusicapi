
package musicapiclient;

import java.util.ArrayList;
import org.json.*;
public class ResultingJSON {
    public ResultingJSON(){}
    public ArrayList getResult_final(String mResult)
    {
        ArrayList<Song> songs = new ArrayList <>();
        try{
            JSONObject songList=new JSONObject(mResult);
            try
            {
                JSONObject responseObject=songList.getJSONObject("response");
                JSONArray songArray=responseObject.getJSONArray("songs");
                
                
                for(int i=0; i<songArray.length(); i++)
                {
                    JSONObject songObject=songArray.getJSONObject(i);
                    Song curSong=new Song();
                    
                    curSong.setArtist(songObject.getString("artist_name"));
                    curSong.setTitle(songObject.getString("title"));
                    songs.add(curSong);
                }
            }
            catch(Exception np){}
        }
        catch(Exception ex){}
        return songs;
    }
}
