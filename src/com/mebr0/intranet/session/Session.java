package com.mebr0.intranet.session;

import static com.mebr0.intranet.util.Printer.error;
import static com.mebr0.intranet.util.Printer.options;
import static com.mebr0.intranet.util.Scanner.index;

/**
 * Interface for session classes as {@link com.mebr0.intranet.Intranet} and others
 *
 * @author A.Yergali
 * @version 1.1
 */
public interface Session {

    int CONNECTION_LIMIT = 3;
    int BACK_OPTION = 0;
    int ERROR_OPTION = -1;

    void greet();
    Status begin();

    default void split(String[] options, Runnable[] methods) {
        int option;

        while (true) {
            options(options);
            option = index();

            if (option == BACK_OPTION) {
                return;
            }
            else if (option == ERROR_OPTION || option > options.length) {
                error("Invalid option");
            }
            else {
                methods[option - 1].run();
            }
        }
    }

    enum Status {

        OK(0, "OK"),
        ERROR(-1, "ERROR");

        private final int code;
        private final String text;

        Status(int code, String text) {
            this.code = code;
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "Session finished with code " + code + " (" + text + ")";
        }
    }
}
