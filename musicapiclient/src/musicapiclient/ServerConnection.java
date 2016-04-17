/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicapiclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ServerConnection {

    public ServerConnection() {
    }

    public String getServerResponse(String jsonString) {
        String serverResponse = null;
        try {
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), 6000);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(jsonString);
            try {
                StringBuilder buffer=new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;
                inputLine=br.readLine();
                System.out.println("Server Response: " + inputLine + "... Parsing ...");
                serverResponse=inputLine;
            } catch (Exception brf) {
            };
        } catch (Exception conn) {
        }
        System.out.println(serverResponse);
        return serverResponse;
    }
}
