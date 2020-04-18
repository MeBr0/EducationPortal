package com.mebr0.intranet.session.base;

import com.mebr0.study.Course;

import java.util.List;
import java.util.function.Consumer;

import static com.mebr0.intranet.util.Printer.*;
import static com.mebr0.intranet.util.Printer.print;
import static com.mebr0.intranet.util.Scanner.index;

/**
 * Interface for session classes as {@link com.mebr0.intranet.Intranet} and others
 */
public interface Session {

    int CONNECTION_LIMIT = 3;
    int BACK_OPTION = 0;
    int ERROR_OPTION = -1;

    void greet();
    Status begin();

    /**
     * Main method in subclasses for splitting options to {@link Runnable} type methods
     *
     * @param options to print
     * @param methods to execute
     */
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

    /**
     * Main method in subclasses for listing objects in list and accepting action by {@link Consumer}
     *
     * @param list of objects
     * @param consumer action with chosen object
     * @param <T> type of objects in list
     */
    default <T> void split(List<T> list, Consumer<Integer> consumer) {
        int option;

        while (true) {
            list(list);
            option = index();

            if (option == BACK_OPTION) {
                return;
            }
            else if (option == ERROR_OPTION || option > list.size()) {
                error("Invalid option");
            }
            else {
                consumer.accept(option);
//                return;
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
