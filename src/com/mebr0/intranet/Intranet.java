package com.mebr0.intranet;

import com.mebr0.intranet.database.Database;
import com.mebr0.intranet.session.AdminSession;
import com.mebr0.intranet.session.base.UserSession;
import com.mebr0.intranet.util.Scanner;
import com.mebr0.user.base.User;
import com.mebr0.user.entity.Admin;

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
 * @version 0.2
 */
public class Intranet implements UserSession {

    private static Intranet intranet = null;

    private Intranet() {

    }

    public static Intranet getInstance() {
        if (intranet == null) {
            intranet = new Intranet();
        }

        return intranet;
    }

    private final Database DB = Database.getInstance();

    @Override
    public void greet() {
        print("Intranet system launched", "Please log into system");
    }

    @Override
    public Status begin() {
        Status result = Status.ERROR;

        greet();

        int i = 0;

        while (i++ < CONNECTION_LIMIT) {
            String login = ask("Enter login");
            String password = ask("Enter password");

            User user = DB.getUser(login, password);

            if (user != null) {

                UserSession innerSession = null;

                if (user instanceof Admin) {
                    innerSession = AdminSession.getSession((Admin) user);
                }

                result = innerSession.begin();
                break;
            }
            else {
                print("Failed to log in");
            }
        }

        if (i > CONNECTION_LIMIT)
            error("Too many authentication failures");

        if (!DB.save())
            error("Could not save");

        Scanner.close();

        return result;
    }
}
