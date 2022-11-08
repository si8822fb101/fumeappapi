package edu.ics499.fumeappapi.domain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {

    public static String hashCreation(Object input) {
        String encodedData = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("Sha-256");
            byte[] hashArray = digest.digest(((String) input).getBytes(StandardCharsets.UTF_8));
            encodedData = Base64.getEncoder().encodeToString(hashArray);
        } catch (NoSuchAlgorithmException e) {
        }

        return new String(encodedData);

    }

}