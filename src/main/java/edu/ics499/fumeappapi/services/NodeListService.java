package edu.ics499.fumeappapi.services;

import edu.ics499.fumeappapi.domain.User;

import java.util.Iterator;
import java.util.List;

public class NodeListService {

    private List <User> ledger;
    private List<String> blocks;
    private MessagingService messaging;

    /**
     *
     * @param dataBlock
     * Node - is added to the chain
     * @return
     */

    public boolean insert(Node device) {
        if(device != null) {
            ledger.add((User) device);
            device = head;
            setCount(getCount() + 1);
        }
        return(ledger.add((User) device));
    }

    /**
     *
     * @param dataBlock
     * Node - is removed from the chain
     * @return
     */

    public boolean remove(Node device) {
        for(int i = 0; i <= ledger.size(); i++) {
            if(ledger.contains(device)) {
                ledger.remove(device);
                setCount(getCount() - 1);
                return ledger.remove(device);
            }
        }
        return false;
    }

    public User searchId(String userId) {
        for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if (user.getUserName().equals(userId)) {
                return user;
            }

        }
        return null;
    }

    public String searchNetwork(String netId) {
        for(int i = 0; i <= ledger.size(); i++) {
            if(ledger.get(i).getNetworkID().equals(netId)){
                return netId;
            }
        }
        return null;
    }

    public void p2pMessaging(String destination, String message) {
        for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if(user.getIpAddress().equals(destination)) {
                messaging.messaging(destination, message);
            }
        }
    }

    public void p2pFileTransfer(String destination, Object data) throws Exception {
        for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if(user.getIpAddress().equals(destination)) {

            }
        }
    }


}
