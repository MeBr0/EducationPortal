package com.mebr0.user.util;

public class LoginGenerator {

    private final static char SEPARATOR = '_';

    // Todo: check generated login for uniqueness
    public static String generate(String name, String lastName) {
        return name.charAt(0) + SEPARATOR + lastName;
    }
}
