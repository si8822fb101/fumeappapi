package edu.ics499.fumeappapi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessagingService {
    private static String conn = "";
    private static int port;


    /**
     * @return the connection
     */
    public static String getConn() {return conn;}
    /**
     * @param conn the connection to set
     */
    public static void setConn(String conn) {MessagingService.setConn(conn);;}

    /**
     * @return the port
     */
    public static int getPort() {return port;}
    /**
     * @param port the port to set
     */
    public static void setPort(int port) {MessagingService.setPort(port);};


    public static void sendMessage() throws IOException {
        Socket echo = new Socket(conn, port);
        PrintWriter send = new PrintWriter(echo.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String message;
        while((message = userInput.readLine()) != null){
            send.println(message);
        }
    }

    public static void receiveMessage() throws IOException {
        Socket echo = new Socket(conn, port);
        BufferedReader dataReceive = new BufferedReader(new InputStreamReader(echo.getInputStream()));
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        String messageToReceive;
        while((messageToReceive = userInput.readLine())!= null){
            System.out.println(dataReceive.readLine());
        }

    }


}
