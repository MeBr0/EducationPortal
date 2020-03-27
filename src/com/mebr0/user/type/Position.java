package com.mebr0.user.type;

public enum Position {

    TUTOR("Tutor"),
    LECTURER("Lecturer"),
    S_LECTURER("SENIOR_LECTURER"),
    PROFESSOR("PROFESSOR");

    private final String TITLE;

    Position(String title) {
        this.TITLE = title;
    }

    public String getTitle() {
        return TITLE;
    }
}
