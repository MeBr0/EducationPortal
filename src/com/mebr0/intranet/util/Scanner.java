package com.mebr0.intranet.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.mebr0.intranet.util.Printer.out;

/**
 * Class for getting input information from System.in stream
 * Only static methods
 *
 * Must be closed in the end of program
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Scanner {

    private static final BufferedReader INPUT;

    static {
        INPUT = new BufferedReader(new InputStreamReader(System.in));
    }

    private Scanner() {
        throw new AssertionError("No " + getClass().getSimpleName() + " instances for you!");
    }

    public static String ask(String text) {
        out(text + ": ");
        return input();
    }

    public static String input() {
        String result = "";

        try {
            while ((result = INPUT.readLine()) != null) {
                break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void close() {
        try {
            if (INPUT != null) {
                INPUT.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
