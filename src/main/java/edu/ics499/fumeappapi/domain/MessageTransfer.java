package edu.ics499.fumeappapi.domain;

import java.util.Random;

/**
 * @author marselos a. reed, qaalib farah, john quinlan, ayden sinn, mohamed mahmoud
 */
public class MessageTransfer {
    private String digest = "";
    private String messageID;
    private String networkID ="";
    /**
     * @param digest
     * @param messageID
     */
    public MessageTransfer(String digest, int messageID) {
        Random random = new Random();
        this.digest = digest;
        this.messageID = networkID + random.nextInt(10000);
    }
}
