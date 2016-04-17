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
import java.util.Scanner;

/**
 *
 * @author Seaweed
 */
public class ServerConnection {

    public ServerConnection(String jsonString) {
        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            try {
                Socket clientSocket = new Socket(localAddress, 6000);
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                System.out.println("here");
                System.out.println(jsonString);
                out.println(jsonString);
                String response = "";
                response = br.readLine();
                while ((response = br.readLine()) != null) {

                    if (response.equals("done")) {
                        break;
                    } else {
                        System.out.println(response);
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
