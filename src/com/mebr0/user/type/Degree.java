package com.mebr0.user.type;

public enum Degree {

    BD("Bachelor Degree"),
    MD("Master Degree"),
    PHD("Philosophy Doctor Degree");

    private final String TITLE;

    Degree(String title) {
        this.TITLE = title;
    }

    public String getTitle() {
        return TITLE;
    }
}
