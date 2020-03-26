package com.mebr0.user.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class that encode passwords for {@link com.mebr0.user.base.User}
 *
 * @author A.Yergali
 * @version 1.0
 */
public abstract class PasswordEncoder {

    private static MessageDigest digest;

    private PasswordEncoder() {
        throw new AssertionError("No " + getClass().getSimpleName() + " instances for you!");
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
