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

    public Node createAccount(String username, String pin) throws IOException {
//        edu.ics499.fume.facade.Result result = new edu.ics499.fume.facade.Result();
        Node user = new User(username, pin);
        nodeListService.insert(user);
//        if(validationService.validationCheck(username, pin)) {
//        }
        return user;
    }

//    public Result userLogon(Request request) throws IOException {
//        edu.ics499.fume.facade.Result result = new edu.ics499.fume.facade.Result();
//        Node user = new User(request.getUserName(), request.getPin());
//        for(int i = 0; i <= userList.getCount(); i++) {
//            if(userList.searchId(request.getUserName())) {
//                result.setResultCode(edu.ics499.fume.facade.Result.USER_NAME_UNAVAILABLE);
//                return result;
//            }
//            userList.insert(user);
//            result.setResultCode(edu.ics499.fume.facade.Result.USER_ONLINE);
//        }
//        return result;
//    }
//
//    public edu.ics499.fume.facade.Result userLogout(Request request) throws IOException {
//        edu.ics499.fume.facade.Result result = new edu.ics499.fume.facade.Result();
//        Node user = new User(request.getUserName(), request.getPin());
//        for(int i = 0; i <= userList.getCount(); i++) {
//            if(userList.searchId(request.getUserName())) {
//                userList.remove(user);
//                result.setResultCode(edu.ics499.fume.facade.Result.USER_OFFLINE);
//            }
//        }
//        return result;
//    }
//
//
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
