package com.fatihdemir.javachallenge.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Hash {

        public String HashMD5(String password) throws NoSuchAlgorithmException {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            byte[] digest = md5.digest();

            return HexFormat.of().formatHex(digest).toUpperCase();
        }

}
