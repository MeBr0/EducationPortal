package com.mebr0.user.type;

public enum Degree {

    BD("BD", "Bachelor Degree"),
    MD("MD", "Master Degree"),
    PHD("PHD", "Philosophy Doctor Degree");

    private final String NAME;
    private final String TITLE;

    Degree(String name, String title) {
        this.NAME = name;
        this.TITLE = title;
    }

    public String getTitle() {
        return TITLE;
    }

    public String getShortName() {
        return NAME;
    }

    @Override
    public String toString() {
        return NAME + " (" + TITLE + ")";
    }
}
