package com.project.forupets.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by VyTKSE60964 on 5/16/2015.
 */
public class PasswordEncoderGenerator {
    public static String encodePassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(password.getBytes("UTF-8"));
        byte[] thedigest = md.digest();
        BigInteger bigInt = new BigInteger(1, thedigest);
        return bigInt.toString(16);
    }
}
