package com.mebr0.intranet.session;

public interface Session {

    int CONNECTION_LIMIT = 3;

    Status begin();

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
