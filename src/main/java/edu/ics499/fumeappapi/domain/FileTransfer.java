package edu.ics499.fumeappapi.domain;

import java.io.File;
import java.util.Random;

public class FileTransfer {
    private File digest;
    private String fileID;
    private String networkID ="";
    /**
     * @param digest
     * @param fileID
     */
    public FileTransfer(File digest, int fileID) {
        Random random = new Random();
        this.digest = digest;
        this.fileID = networkID + random.nextInt(10000);
    }
}
