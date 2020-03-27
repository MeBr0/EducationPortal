package com.mebr0.user.util;

/**
 * Class that generates logins for {@link com.mebr0.user.base.User}
 *
 * @author A.Yergali
 * @version 1.0
 */
public class LoginGenerator {

    private final static char SEPARATOR = '_';

    // Todo: check generated login for uniqueness
    public static String generate(String name, String lastName) {
        return "" + name.toLowerCase().charAt(0) + SEPARATOR + lastName.toLowerCase();
    }
}
