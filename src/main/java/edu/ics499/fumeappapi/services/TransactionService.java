package edu.ics499.fumeappapi.services;

import edu.ics499.fumeappapi.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

@Service
public class TransactionService {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private NodeList userList;
    private Calendar date;
    private String message, userName, pin, networkID, Connection, filePath;
    private File file;
    private Node node;
    private int port;

    @Autowired
    private NodeListService nodeListService;

    public User createAccount(String username, String pin) throws IOException {
        User user = new User(username, pin);
        user.setActive(true);
        nodeListService.insert(user);
        return user;
    }

    public boolean userLogon(String username, String pin) throws IOException {
        User user = new User(username, pin);
        user.setActive(true);
        var found = nodeListService.searchId(username);
        if(found){
            nodeListService.insert(user);
        }
        return found;
    }

    public boolean userLogout(String username) throws IOException {
        for(int i = 0; i <= nodeListService.getCount(); i++) {
            var user = new User(username, "12345");
            if(nodeListService.searchId(username)) {
                user.setActive(false);
                return true;
            }else{
                return false;
            }
        }
        return true;
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
