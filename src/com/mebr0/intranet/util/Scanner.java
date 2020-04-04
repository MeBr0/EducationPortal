package com.mebr0.intranet.util;

import com.mebr0.intranet.session.base.UserSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.stream.IntStream;

import static com.mebr0.intranet.util.Printer.*;

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

    private static final BufferedReader input;

    static {
        input = new BufferedReader(new InputStreamReader(System.in));
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

            String option = ask("Choose options for " + clazz.getSimpleName() + ": ");

            try {
                return T.valueOf(clazz, option);
            }
            catch (IllegalArgumentException e) {
                error("Invalid option");
            }
        }
    }

    /**
     *
     * @param classes
     * @return
     */
    public static Class<?> ask(Class<?>... classes) {
        int option;

        while (true) {
            IntStream.range(0, classes.length).
                    mapToObj(i -> (i + 1) + ". " + classes[i].getSimpleName()).
                    forEach(Printer::print);

            option = index("Choose role of user: ");

            if (option <= classes.length && option > 0)
                return classes[option - 1];
            else
                error("Invalid option");
        }
    }

    public static int index(String text) {
        out(text + ": ");
        return index();
    }

    public static int index() {
        String input = input();

        try {
            return Integer.parseInt(input);
        }
        catch (Exception e) {
            return UserSession.ERROR_OPTION;
        }
    }

    public static String input() {
        String result = "";

        try {
            while ((result = input.readLine()) != null) {
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
            if (input != null) {
                input.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
