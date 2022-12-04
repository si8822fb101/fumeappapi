package edu.ics499.fumeappapi.services;

import edu.ics499.fumeappapi.domain.*;
import edu.ics499.fumeappapi.repositories.UserRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@Service
public class TransactionService {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private NodeList userList;
    private Calendar date;
    private String message, userName, pin, filePath;
    private File file;
    private Node node;
    private int port;

    @Autowired
    private NodeListService nodeListService;

    public Node createAccount(String username, String pin) throws IOException, SQLException {
        UserRecords app = new UserRecords();
        Node user = new User(username, pin);
        if(((User) user).getUserName().length() > 10 || ((User) user).getPin().length() != 4)
        {
            ((User) user).setActive(false);
        }else{
            app.insert(((User) user).getUserName(), ((User) user).getPin());
            ((User) user).setActive(true);
            nodeListService.insert((User) user);

        }

        return user;
    }

    public boolean userLogon(String username, String pin) throws IOException, SQLException {
        UserRecords seek = new UserRecords();
        Node user = new User(username, pin);
        var found = false;
        String identify = (username + pin);

        if(username == null || pin == null);

        if(seek.findByUserAndPin(username, pin).equals(identify)) found = true;

        if(found == true){
            nodeListService.insert((User) user);
            ((User) user).setActive(true);
            date.getTime();
      }
        return found;
    }

    public boolean userLogout(String username) throws IOException, SQLException {
        boolean found = false;
        Node user = new User(username, null);
        String pin = null;
        if (username == null) ;

        UserRecords logout = new UserRecords();

        if (logout.findByUser(username).equals(username)) found = true;

        if (found == true) {
            nodeListService.remove((User) user);
            ((User) user).setActive(false);
            date.getTime();
        }
        return found;

    }

    public void sendMessage(String msg, String destination, int port) throws IOException {
        Message dataToSend = new Message(destination, port);
        if(destination != null || port != 0); nodeListService.p2pMessageSend(destination,port);
        date.getTime();
    }

    public void receiveMessage(String destination, int port) throws IOException {
        if(destination != null || port != 0); nodeListService.p2pMessageReceive(destination,port);
        date.getTime();
    }

    public String sendFile(String destination){
        filePath = destination;

        if(file.exists()){

        }
        return null;


    }

    public void receiveFile(){

    }


}
