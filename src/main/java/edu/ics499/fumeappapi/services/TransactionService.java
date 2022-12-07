package edu.ics499.fumeappapi.services;

import edu.ics499.fumeappapi.domain.*;
import edu.ics499.fumeappapi.repositories.UserRecords;
import edu.ics499.fumeappapi.requests.NewChatForm;
import edu.ics499.fumeappapi.requests.RecieveMessageForm;
import edu.ics499.fumeappapi.requests.SendMessageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

@Service
public class TransactionService {
    private static final long serialVersionUID = 1L;
    private NodeList userList;
    private Calendar date;
    private String message, pin, filePath;
    private File file;
    private Node node;
    @Value("${port}")
    private int port;

    @Autowired
    private NodeListService nodeListService;

//    @Autowired
//    private JobScheduler jobScheduler;

//    @Autowired
//    private JobService jobService;


    public Node createAccount(String username, String pin) throws Exception {

        UserRecords app = new UserRecords();
        Node user = new User(username, pin);
        if(((User) user).getUserName().length() > 10 || ((User) user).getPin().length() != 4)
        {
            ((User) user).setActive(false);
            throw new Exception("Username or pin did not fulfill the conditions");
        }else{
            app.insert(((User) user).getUserName(), ((User) user).getPin());
            ((User) user).setActive(true);
            nodeListService.setCurrentDevice((User) user);
            nodeListService.startDiscoveryClient(((User) user).getUserName());
            nodeListService.getUpdatedBlockChain();
//            jobScheduler.enqueue(() -> nodeListService.runDiscovery(((User) user).getUserName()));
//            jobScheduler.enqueue(() -> nodeListService.getUpdatedBlockChain());
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
            nodeListService.setCurrentDevice((User) user);
            ((User) user).setActive(true);
            nodeListService.startDiscoveryClient(((User) user).getUserName());
            nodeListService.getUpdatedBlockChain();
//            jobScheduler.enqueue(() -> nodeListService.runDiscovery(((User) user).getUserName()));
//            jobScheduler.enqueue(() -> nodeListService.getUpdatedBlockChain());
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
            ((User) user).setActive(false);
            nodeListService.closeDiscovery();
        }
        return found;

    }

    public boolean newChat(NewChatForm form){
        return nodeListService.searchId(form.getUsername());
    }

    public void sendMessage(SendMessageForm form) throws IOException {
        Message dataToSend = new Message(form.getFromUsername(), form.getToUsername());
        if (!form.getFilePath().equals("")) dataToSend.setFilepath(form.getFilePath());
        dataToSend.setContent(form.getContent());
        if(form.getToUsername() != null || port != 0)
            nodeListService.p2pMessageSend(dataToSend, port);
        date.getTime();
    }

    public ArrayList<Message> receiveMessages() throws IOException {
        return nodeListService.getMessages();
    }

}
