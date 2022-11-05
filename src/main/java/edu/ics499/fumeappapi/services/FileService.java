package edu.ics499.fumeappapi.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileService {
    private static DataOutputStream outStream;
    private static DataInputStream inStream;

    public static void fileOut(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bufferFile = new byte[4*1024];
        outStream.writeLong(file.length());

        while((bytes = inputStream.read(bufferFile)) != -1) {
            outStream.write(bufferFile, 0, bytes);
            outStream.flush();
        }
        inputStream.close();
    }

    public static void fileIn(String file) throws Exception {
        int bytes = 0;
        FileOutputStream fileIn = new FileOutputStream(file);
        long stream = inStream.readLong();

        byte[] bufferFile = new byte[4*1024];
        while(stream > 0 && (bytes = inStream.read(bufferFile, 0, (int)Math.min(bufferFile.length, stream))) != -1) {
            outStream.write(bufferFile, 0, bytes);
            stream -= bytes;
        }
        fileIn.close();
    }
}
