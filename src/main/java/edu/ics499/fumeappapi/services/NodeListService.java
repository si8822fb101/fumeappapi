package edu.ics499.fumeappapi.services;

import com.theisenp.harbor.Harbor;
import com.theisenp.harbor.Peer;
import edu.ics499.fumeappapi.domain.*;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class NodeListService {
    private List <User> ledger = new ArrayList<User>();
    private User head;
    private int count;
    private static Harbor discoveryServer;
    private static Peer discoveryClient;
    private Block headBlock;
    @Value("${port}")
    private int port;

    /**
     * @return the count
     */
    public int getCount() {
        return ledger.size();
    }
    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    public Iterator<User> iterator(){
        return ledger.iterator();
    }

    /**
     *
     * Node - is added to the chain
     * @return
     */
    public boolean insert(User device) {
        return(ledger.add(device));
    }

    public boolean setCurrentDevice(User device) {
        head = device;
        return true;
    }

    /**
     *
     * @param device
     * @return
     */
    public boolean remove(User device) {
        for(int i = 0; i <= ledger.size(); i++) {
            if(ledger.contains(device)) {
                ledger.remove(device);
                setCount(getCount() - 1);
                return ledger.remove(device);
            }
        }
        return false;
    }

    public boolean searchId(String userId) {
        for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if(user.getUserName().equals(userId)) return true;
        }
        return false;
    }

    public void p2pMessageSend(Message message, int portValue) throws IOException {
        for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if(user.getUserName().equals(message.getToUsername())) {
                Transaction transaction = new Transaction();
                transaction.setFrom(head.getUserName());
                transaction.setTo(message.getToUsername());
                transaction.setMessage(message);
                headBlock = new Block(headBlock, Hash.hashCreation(headBlock.hashCode() + transaction.hashCode()));
                headBlock.setTransaction(transaction);
            }
        }
    }

//    public void consensus(String destination, String data) {
//        double threshold = 0.95;
//        double confirm = 0;
//        boolean sentinel = false;
//        for(Iterator<String> iterator = blocks.iterator(); iterator.hasNext();) {
//            String block = iterator.next();
//            if(block.matches(data)) sentinel = true;
//            double sentinelCount = sentinel ? 1 : 0;
//
//            if(sentinelCount == 1) confirm = confirm + 1;
//            double yield = (confirm / blocks.size());
//
//            if(yield >= threshold) addToChain(convertToBlock(data));
//
//        }
//
//    }
//
//    private String convertToBlock(String data) {
//        return Hash.hashCreation(data);
//    }
//
//    private void addToChain(String block) {
//        blocks.add(block);
//    }

    public void wipeNodes() {
        ledger.clear();
    }

    private void startDiscoveryClient(String username){
        if(discoveryClient == null){
            discoveryClient = new Peer.Builder()
                    .id(UUID.randomUUID().toString())
                    .type("example")
                    .status(Peer.Status.ACTIVE)
                    .description(username)
                    .protocol("TCP", "192.168.0.1:5555")
                    .build();
        }
    }

    private void startDiscoveryServer(){
        if (discoveryServer == null){
            discoveryServer = new Harbor.Builder()
                    .address("239.255.76.67")
                    .port(7667)
                    .ttl(1)
                    .period(Duration.millis(500))
                    .timeout(Duration.standardSeconds(2))
                    .self(discoveryClient)
                    .build();
            discoveryServer.addListener(new Harbor.Listener() {
                @Override
                public void onConnected(Peer peer) {
                    System.out.println("Peer connected: " + peer.getId());
                    try {
                        ledger.add(new User(peer.getDescription(), peer.getId()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onActive(Peer peer) {
                    System.out.println("Peer active: " + peer.getId());
                    try {
                        insert(new User(peer.getDescription(), peer.getId()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onInactive(Peer peer) {
                    System.out.println("Peer inactive: " + peer.getId());
                }

                @Override
                public void onDisconnected(Peer peer) {
                    System.out.println("Peer disconnected: " + peer.getId());
                    try {
                        remove(new User(peer.getDescription(), peer.getId()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void runDiscovery(String username){
        this.startDiscoveryClient(username);
        this.startDiscoveryServer();
    }

    public void closeDiscovery(){
        this.discoveryServer.close();
    }

    public void getUpdatedBlockChain
            (){
        for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            try{
                WebClient client = WebClient.create("http://" + user.getIpAddress() + ":" + port);
                Block result = client.post().uri("/peer/syncBlockChain")
                        .body(headBlock, Block.class)
                        .retrieve()
                        .bodyToMono(Block.class).block();
                syncBlockchain(result);
                System.out.println(result);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public Block getBlocks() {
        return headBlock;
    }

    public Block syncBlockchain(Block newBlock){
        // if other blockchain has smaller height
        if(newBlock.getCurrenHeight() < headBlock.getCurrenHeight()) {
            return headBlock;
        }else if(newBlock.getCurrenHeight() > headBlock.getCurrenHeight()) {
            headBlock = newBlock;
            return newBlock;
        }else{
            return newBlock;
        }
    }

    public ArrayList<Message> getMessages(String fromUsername, String toUsername){
        ArrayList<Message> list = new ArrayList<Message>();
        Block current = headBlock;
        while (current != null){
            Message message = current.getTransaction().getMessage();
            if(message.getFromUsername().equals(fromUsername) && message.getToUsername().equals(toUsername)){
                list.add(current.getTransaction().getMessage());
            }
            current = current.getNext();
        }
        return list;
    }
}
