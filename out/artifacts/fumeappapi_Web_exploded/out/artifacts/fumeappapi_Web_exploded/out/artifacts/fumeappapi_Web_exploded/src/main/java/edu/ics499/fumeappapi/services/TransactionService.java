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
    private String message, userName, pin, Connection, filePath;
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
       // seek.find(username, pin);
        if(seek.find(username, pin).equals(identify)) found = true;

        if(found == true){
            nodeListService.insert((User) user);
            ((User) user).setActive(true);
        }
        return found;
    }

    public boolean userLogout(String username) throws IOException, SQLException {
        boolean found = false;
        Node user = new User(username, null);
        String pin = null;
        if (username == null) ;

        UserRecords logout = new UserRecords();

        if (logout.find(username, null).equals(username)) found = true;

        if (found == true) {
            nodeListService.remove((User) user);
            ((User) user).setActive(false);
        }
        return found;

    }


//    public edu.ics499.fume.facade.Result messaging(Request request) {
//        edu.ics499.fume.facade.Result result = new edu.ics499.fume.facade.Result();
//
//        // sending
//        if(request.setMessage(message) != null && request.getConnection() != null && request.getPort() != 0) {
//            transmitMessage(node.getIpAddress(),getPort(), message);
//            result.setResultCode(edu.ics499.fume.facade.Result.MESSAGE_SENT);
//        }
//        // receiving
//        else if(request.getMessage() != null && request.getConnection() != null && request.getPort() != 0) {
//            transmitMessage(node.getIpAddress(), getPort(), getMessage());
//            result.setResultCode(edu.ics499.fume.facade.Result.MESSAGE_RECEIVED);
//        }
//        result.setResultCode(edu.ics499.fume.facade.Result.OPERATION_FAILED);
//
//        return result;
//
//    }
//
//
//    public edu.ics499.fume.facade.Result fileTransfer(Request request) throws Exception {
//        edu.ics499.fume.facade.Result result = new edu.ics499.fume.facade.Result();
//
//        // file to send
//        if(request.setFile(file)!= null) {			// sending
//            transmitFile(node.getMacAddress(), getPort(), file);
//            result.setResultCode(edu.ics499.fume.facade.Result.FILE_SENT);
//        }
//        //file to receive
//        else if(request.getFilePath() != null) {  // receiving
//            transmitFile(node.getMacAddress(),getPort(), getFile());
//        }
//        result.setResultCode(edu.ics499.fume.facade.Result.OPERATION_FAILED);
//
//        return result;
//    }
//
//    private void transmitMessage(String destination, int port, Object data) {
//        for(int i = 0; i <= userList.getCount(); i++) {
//            if(node.getMacAddress() == destination) {
//                userList.p2pMessaging(destination, port);
//
//            }
//        }
//    }
//
//    private void transmitFile(String destination, int port, Object data) throws Exception {
//        for(int i = 0; i <= userList.getCount(); i++) {
//            if(node.getMacAddress() == destination) {
//                userList.p2pFileTransfer(destination);
//                FileTransfer.fileSend(getFilePath());
//            }
//            FileTransfer.fileReceive(filePath);
//        }
//    }

}
