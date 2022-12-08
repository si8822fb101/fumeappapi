package edu.ics499.fumeappapi.services;

import com.sun.tools.jconsole.JConsoleContext;
import com.theisenp.harbor.Harbor;
import com.theisenp.harbor.Peer;
import de.tum.in.www1.jReto.Connection;
import de.tum.in.www1.jReto.LocalPeer;
import de.tum.in.www1.jReto.RemotePeer;
import de.tum.in.www1.jReto.module.wlan.WlanModule;
import edu.ics499.fumeappapi.domain.*;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.Executors;

@Service
public class NodeListService {
    private static List <User> ledger;
    private User head;
    private int count;
    private static Harbor discoveryServer;
    private static Peer discoveryClient;
    private static String filePath;
    private Block headBlock;
    @Value("${port}")
    private int port;
    private static WlanModule wlanModule;
    private static LocalPeer localPeer;
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
        ledger = new ArrayList<User>();
        head = device;
        filePath = System.getProperty("user.dir") + "\\" + head.getUserName();
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
        System.out.println(message);
        for(Iterator<User> iterator = ledger.iterator(); iterator.hasNext();) {
            User user = iterator.next();
            if(user.getUserName().equals(message.getToUsername())) {
                Transaction transaction = new Transaction();
                transaction.setFrom(head.getUserName());
                transaction.setTo(message.getToUsername());
                transaction.setMessage(message);
                if (!message.getFilepath().equals("")) transaction.setFile(new File(message.getFilepath()));
                headBlock = new Block(headBlock, Hash.hashCreation(headBlock.hashCode() + transaction.hashCode()));
                headBlock.setTransaction(transaction);
            }
        }
        getUpdatedBlockChain();
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

    public void startDiscoveryClient(String username) throws IOException {

        if(wlanModule == null){
            wlanModule = new WlanModule("FumeApp");
            localPeer = new LocalPeer(Arrays.asList(wlanModule), Executors.newSingleThreadExecutor());
            // 3. Starting the LocalPeer
            localPeer.start(
                    discoveredPeer -> onPeerDiscovery(discoveredPeer),
                    removedPeer -> onPeerRemoval(removedPeer),
                    (peer, incomingConnection) -> onPeerConnection(peer, incomingConnection)
            );
//            if(discoveryClient == null){
//                discoveryClient = new Peer.Builder()
//                        .id(UUID.randomUUID().toString())
//                        .type("example")
//                        .status(Peer.Status.ACTIVE)
//                        .description(username)
//                        .protocol("TCP", "192.168.0.1:5555")
//                        .build();
//            }
        }
    }

    public void closeDiscovery(){
        if (localPeer != null){
            localPeer.stop();
            localPeer = null;
            wlanModule = null;
        }
    }

    private void onPeerDiscovery(RemotePeer discoveredPeer){
        try {
            System.out.println("Sending data to remote peer: "+discoveredPeer+" from peer: "+discoveredPeer);
            Connection connection = discoveredPeer.connect();
            connection.setOnClose(conn -> System.out.println("Connection closed."));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(bos);
            out.writeObject(head);
            out.flush();
            connection.send(ByteBuffer.wrap(bos.toByteArray()));
            connection.setOnData((conn, data) -> {
                ByteArrayInputStream bis = new ByteArrayInputStream(data.array());
                ObjectInput in = null;
                try {
                    in = new ObjectInputStream(bis);
                    User user =  (User) in.readObject();
                    ledger.add(user);
                    System.out.println("Received Data from peer on connection: " + user);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    connection.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onPeerConnection(RemotePeer peer, Connection incomingConnection){
        try {
            System.out.println("Received incoming connection: "+incomingConnection+" from peer: "+peer);
            incomingConnection.setOnClose(conn -> System.out.println("Connection closed."));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = null;
            out = new ObjectOutputStream(bos);
            out.writeObject(head);
            out.flush();
            incomingConnection.send(ByteBuffer.wrap(bos.toByteArray()));
            incomingConnection.setOnData((conn, data) -> {
                ByteArrayInputStream bis = new ByteArrayInputStream(data.array());
                ObjectInput in = null;
                try {
                    in = new ObjectInputStream(bis);
                    User user =  (User) in.readObject();
                    System.out.println("Received Data from peer on connection: " + user);
                    ledger.add(user);
                    incomingConnection.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    incomingConnection.close();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            incomingConnection.close();
        }
    }

    private void onPeerRemoval(RemotePeer removedPeer){
        System.out.println(removedPeer + " was removed");
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

//    public void runDiscovery(String username){
//        this.startDiscoveryClient(username);
//        this.startDiscoveryServer();
//    }

//    public void closeDiscovery(){
//        discoveryServer.close();
//        discoveryServer = null;
//        discoveryClient = null;
//    }

    public void getUpdatedBlockChain
            (){
        for (User user : ledger) {
            try {
                WebClient client = WebClient.create("http://" + user.getIpAddress() + ":" + port);
                Block result = client.post().uri("/peer/syncBlockChain")
                        .body(headBlock, Block.class)
                        .retrieve()
                        .bodyToMono(Block.class).block();
                syncBlockchain(result);
                System.out.println(result);
            } catch (Exception e) {
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

    public ArrayList<Message> getMessages(){
        ArrayList<Message> list = new ArrayList<Message>();
        Block current = headBlock;
        while (current != null){
            Message message = current.getTransaction().getMessage();
            if(message.getFromUsername().equals(head.getUserName()) || message.getToUsername().equals(head.getUserName())){
                if (current.getTransaction().getFile() != null){
                    try {
                        File inputFile = current.getTransaction().getFile();
                        File outputFile = new File(filePath + "/" + inputFile.getName());
                        Files.copy(inputFile.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        message.setFilepath(outputFile.getAbsolutePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                list.add(message);
            }
            current = current.getNext();
        }
        return list;
    }
}
