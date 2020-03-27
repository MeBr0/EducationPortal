package com.mebr0.intranet;

import com.mebr0.intranet.session.Session;
import com.mebr0.intranet.util.Scanner;

import java.util.Random;

import static com.mebr0.intranet.util.Printer.error;
import static com.mebr0.intranet.util.Printer.print;
import static com.mebr0.intranet.util.Scanner.ask;

/**
 * Main class of system
 * Main source of input/output, data and sessions
 *
 * Singleton class
 *
 * @author A.Yergali
 * @version 0.1
 */
public class Intranet implements Session {

    private static Intranet intranet = null;

    private Intranet() {

    }

    public static Intranet getInstance() {
        if (intranet == null) {
            intranet = new Intranet();
        }

        return intranet;
    }

    @Override
    public Status begin() {
        print("Intranet system launched", "Please log into system");

        int i = 0;

        while (i++ < CONNECTION_LIMIT) {
            String login = ask("Enter login");
            String password = ask("Enter password");

            if (new Random().nextBoolean()) {
                print("Logged in with " + login + " " + password);
                break;
            }
            else {
                print("Failed to log in");
            }
        }

        if (i >= CONNECTION_LIMIT) {
            error("Too many authentication failures");
        }

        Scanner.close();

        return Status.OK;
    }
}
