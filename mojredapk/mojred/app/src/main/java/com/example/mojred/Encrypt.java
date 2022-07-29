package com.example.mojred;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    public static String SHA256(String tetx) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(tetx.getBytes());
        byte[] digesr=md.digest();


        return Base64.encodeToString(digesr,Base64.DEFAULT);
    }
}
