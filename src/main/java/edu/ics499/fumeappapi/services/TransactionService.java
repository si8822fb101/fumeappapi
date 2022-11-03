package edu.ics499.fumeappapi.services;

import edu.ics499.fumeappapi.domain.Node;
import edu.ics499.fumeappapi.domain.User;
import org.springframework.web.multipart.MultipartFile;

public class TransactionService {
    private static final long serialVersionUID = 1L;
    private NodeListService userList;
    private Calendar date;
    private String message, userName, pin, networkID, Connection, filePath;
    private File file;
    private Node node;
    private int port;
    private static Transaction transaction;

//    private Transaction() {
//        userList = NodeList.getInstance();
//    }
//
//    public static Transaction getInstance() {
//        try {
//            if (transaction == null) {
//
//                return transaction = new Transaction();
//            } else {
//                return transaction;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }


    public Node createAccount(String username, String pin) throws IOException {
        // generate the network ID
        Result result = new Result();
        Node user = new User(username, pin, "networkID");
        if(userList.insert(user)) {
//            result.setResultCode(Result.USER_REGISTRATION_COMPLETED);
//            result.setUserInformation((User) user);
//            return result;
            return user;
        }
        return null;
    }

    public Result createNetwork(Request request) throws IOException {
        Result result = new Result();
        ArrayList<User> netId = new ArrayList<User>();
        if(networkTracker(request.getNetworkID())) {
            result.setResultCode(Result.NETWORK_ALREADY_EXISTS);
        } else {
            User id = new User(request.getUserName(), request.getPin(), request.getNetworkID());
            netId.add(id);
            result.setResultCode(Result.NETWORK_CREATED);
            return result;
        }

        return null;

    }

//	public Result joinNetwork(Request request) {
//		Result result = new Result();
//		String suffix = request.getNetworkID();
//		if(networkTracker(request.getNetworkID())) {
//			User id = new User(request.getUserName(), request.getPin(), request.getNetworkID()) {
//
//			}
//		}
//	}

    private boolean networkTracker(String netId) {
        return netId.equalsIgnoreCase(userList.searchNetwork(netId));
    }


    public void messaging(String message, String recipientAddress) {
        Result result = new Result();

        // sending
        if(request.setMessage(message) != null && request.getConnection() != null && request.getPort() != 0) {
            transmitMessage(recipientAddress, message);
            result.setResultCode(Result.MESSAGE_SENT);
        }
        // receiving
//        else if(request.getMessage() != null && request.getConnection() != null && request.getPort() != 0) {
//            transmitMessage(node.getMacAddress(), getMessage());
//            result.setResultCode(Result.MESSAGE_RECEIVED);
//        }
        result.setResultCode(Result.OPERATION_FAILED);

        return result;

    }


    public void fileTransfer(MultipartFile file, String recipient) throws Exception {
//        Result result = new Result();
//
//        if(request.setFile(file)!= null) {			// sending
        transmitFile(recipient, file);
//            result.setResultCode(Result.FILE_SENT);
//        }
//        else if(request.getFilePath() != null) {  // receiving
//            transmitFile(node.getMacAddress(), getFile());
//        }
//        result.setResultCode(Result.OPERATION_FAILED);
//
//        return result;
    }

    private void transmitMessage(String destination, Object data) {
        for(int i = 0; i <= userList.getCount(); i++) {
            if(node.getMacAddress() == destination) {
                userList.p2pMessaging(destination, data);

            }
        }
    }

    private void transmitFile(String destination, Object data){
        for(int i = 0; i <= userList.getCount(); i++) {
            if(node.getMacAddress() == destination) {
                userList.p2pFileTransfer(destination, data);
            }
        }
    }






}
