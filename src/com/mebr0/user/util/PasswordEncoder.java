package com.mebr0.user.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class PasswordEncoder {

    private static MessageDigest digest;

    private PasswordEncoder() {

    }

    static {
        try {
            digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encode(String string) {
        digest.update(string.getBytes());

        StringBuilder builder = new StringBuilder();

        for (byte aByte : digest.digest()) {
            builder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        return builder.toString();
    }
}
