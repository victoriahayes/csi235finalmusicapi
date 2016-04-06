/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicapiclient;

import org.json.*;
/**
 *
 * @author Victoria
 */
public class jsonConverter {
    public jsonConverter()
    {}
    
    public String toJsonString(QueryParams mParams)
    {
        try
        {
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("ConnectionType", mParams.getConnectionType());
            jsonObj.put("Artist", mParams.getArtist());
            jsonObj.put("Genre", mParams.getGenre());
            jsonObj.put("Number of Songs", mParams.getNumSongs());
            return jsonObj.toString();
        }
        catch(Exception ex)
        {
            System.out.println("Error");
        }
        return null;
    }
}
