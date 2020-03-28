package com.mebr0.user.type;

public enum Position {

    TUTOR("Tutor"),
    LECTURER("Lecturer"),
    SENIOR_LECTURER("Senior lecturer"),
    PROFESSOR("Professor");

    private final String TITLE;

    Position(String title) {
        this.TITLE = title;
    }

    public String getTitle() {
        return TITLE;
    }

    @Override
    public String toString() {
        return TITLE;
    }
}
