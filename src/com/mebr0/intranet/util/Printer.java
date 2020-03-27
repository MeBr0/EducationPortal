package com.mebr0.intranet.util;

import java.util.Arrays;

/**
 * Class for printing information to stderr and stdout streams
 * Only static methods
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Printer {

    private Printer() {
        throw new AssertionError("No " + getClass().getSimpleName() + " instances for you!");
    }

    public static void print(String... texts) {
        Arrays.stream(texts).forEach(Printer::print);
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static void out(String text) {
        System.out.print(text);
    }

    public static void error(String text) {
        System.err.println(text);
    }
}
