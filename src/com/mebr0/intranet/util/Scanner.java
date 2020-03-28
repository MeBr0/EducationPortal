package com.mebr0.intranet.util;

import com.mebr0.intranet.session.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;

import static com.mebr0.intranet.util.Printer.error;
import static com.mebr0.intranet.util.Printer.out;

/**
 * Class for getting input information from System.in stream
 * Only static methods
 *
 * Must be closed in the end of program
 *
 * @author A.Yergali
 * @version 1.1
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

    /**
     * Ask for one valid option of enum
     *
     * @param clazz - enum class
     * @param <T> - type of enum
     * @return chosen enum value
     */
    public static <T extends Enum<T>> T ask(Class<T> clazz) {
        while (true) {
            EnumSet.allOf(clazz).stream().map(Enum::toString).forEach(Printer::print);

            out("Choose options for " + clazz.getSimpleName() + ": ");

            String option = input();

            try {
                return T.valueOf(clazz, option);
            }
            catch (IllegalArgumentException e) {
                error("Invalid option");
            }
        }
    }

    public static int index() {
        String input = input();

        try {
            return Integer.parseInt(input);
        }
        catch (Exception e) {
            return Session.ERROR_OPTION;
        }
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
