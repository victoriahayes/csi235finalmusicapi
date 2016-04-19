
package finalprojectmusicapi;

import java.net.*;
import java.io.*;
import org.json.*;

public class FinalProjectMusicapi {

    final String APIKEY = "FGYIK9QZRNXZTLU1F";
    final String STATICPLAYLIST = "http://developer.echonest.com/api/v4/playlist/static?api_key=";
    final String SEARCHBYARTIST = "http://developer.echonest.com/api/v4/song/search?api_key=";

  //connects to client and sends response back after reading the request
    public static void main(String[] args) {
        QueryParams qp = new QueryParams();
        Socket sSocket = null;
        //client connection 
        while(true)
        {
        try {
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("Waiting for connection....");
            sSocket = serverSocket.accept();
            System.out.println("Connected to Music Client");
        } catch (IOException ex) {
            //System.out.println(ex.getMessage());
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(sSocket.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                System.out.println("Client request: " + inputLine + "... Parsing ...");
                qp.fromJsonString(inputLine);
                String response = fetchData(qp);
                System.out.println(response);
                try {
                    PrintWriter out = new PrintWriter(sSocket.getOutputStream(), true);
                    out.println(response);
                    System.out.println("Sent");
                    out.close();
                    br.close();
                    sSocket.close();
                } catch (Exception pr) {
                    System.out.println("no pr");
                }

            }

        } catch (Exception Ex) {
        }
        }
    }

    //makes the url to connect to
    public static String buildURL(QueryParams qp) {
        String mStr = null;
        if (qp.getConnectionType().equals("top songs")) {
            mStr = "http://developer.echonest.com/api/v4/song/search?api_key=";
        } else {
            mStr = "http://developer.echonest.com/api/v4/playlist/static?api_key=";
        }
        mStr += "FGYIK9QZRNXZTLU1F";

        if (!qp.getArtist().equals("null")) {
            mStr += "&artist=";
            mStr += qp.getArtist();
            if (qp.getConnectionType().equals("playlist")) {
                mStr += "&type=artist-radio";
            }
        }
        if (!qp.getGenre().equals("null")) {
            mStr += "&style=";
            mStr += qp.getGenre();
            if (qp.getConnectionType().equals("playlist")) {
                mStr += "&type=artist-description";
            }
        }
        System.out.println(qp.getNumSongs());
            mStr += "&results=" + qp.getNumSongs();
          
        mStr += "&sort=song_hotttnesss-desc";

        System.out.println(mStr);
        return mStr;
    }

    //connects to API
    public static String fetchData(QueryParams qp) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonString = null;
        String sURL = null;

        sURL = buildURL(qp);

        try {
            URL url = new URL(sURL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder buffer = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
                line = reader.readLine();
            }
            jsonString = buffer.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();     //disconnect  
            }
            if (reader != null) {
                try {
                    reader.close();    //close the input stream  
                } catch (final IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
            }
        }
        return jsonString;
    }
}
