package edu.ics499.fumeappapi.services;

import edu.ics499.fumeappapi.domain.Message;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

@Service
public class MessagingService {


    public void sendMessage(Message message, String ip, int port) throws IOException {
//        Socket echo = new Socket(ip, port);
//        PrintWriter send = new PrintWriter(echo.getOutputStream(), true);
//        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
//
//        String message;
//        while((message = userInput.readLine()) != null){
//            send.println(message);
//        }
    }

    public void receiveMessage() throws IOException {
//        Socket echo = new Socket(conn, port);
//        BufferedReader dataReceive = new BufferedReader(new InputStreamReader(echo.getInputStream()));
//        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
//
//        String messageToReceive;
//        while((messageToReceive = userInput.readLine())!= null){
//            System.out.println(dataReceive.readLine());
//        }

    }


}
