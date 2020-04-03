package com.mebr0.intranet.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class for printing information to stderr and stdout streams
 * Only static methods
 *
 * @author A.Yergali
 * @version 1.1
 */
public class Printer {

    private Printer() {
        throw new AssertionError("No " + getClass().getSimpleName() + " instances for you!");
    }

    public static void print(String... texts) {
        Arrays.stream(texts).forEach(Printer::print);
    }

    public static void options(String... texts) {
        IntStream.range(0, texts.length).
                mapToObj(i -> (i + 1) + ". " + texts[i]).
                forEach(Printer::print);
    }

    public static void print(List<?> list) {
        if (list.isEmpty()) {
            print("Empty");
            return;
        }

        list.stream().
                map(Object::toString).
                forEach(Printer::print);
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
