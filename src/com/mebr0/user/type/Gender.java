package com.mebr0.user.type;

public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    private final String TITLE;

    Gender(String title) {
        this.TITLE = title;
    }

    public String getTitle() {
        return TITLE;
    }
}
