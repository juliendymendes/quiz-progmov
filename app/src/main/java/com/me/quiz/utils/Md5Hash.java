package com.me.quiz.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hash {
    public static String md5(String s) {

        // MD5 Hash
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
        byte[] digest = md.digest(s.getBytes());

        // transforma o hash em string
        StringBuilder hexString = new StringBuilder();
        for (int i=0; i<digest.length; i++)
            hexString.append(Integer.toHexString(0xFF & digest[i]));

        return hexString.toString();

    }
}
