package edu.ics499.fumeappapi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessagingService {
    private static String connection = "";
    private static int port;

    public static void messaging(String destination, String message) {
        try (Socket echo = new Socket(connection, port);
             PrintWriter send = new PrintWriter(echo.getOutputStream(), true);
             BufferedReader receive = new BufferedReader(new InputStreamReader(echo.getInputStream()));
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in))
        ){
            while((message = input.readLine()) != null) {
                send.println(message);
                System.out.println(receive.readLine());			}
        } catch (UnknownHostException e) {
            System.err.println();
        } catch (IOException e) {
            System.err.println();
        }

    }

}
