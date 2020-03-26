package com.mebr0.user.base;

public abstract class User extends Person {

    private final String ID;
    private final String LOGIN;
    private String password;
    private String email;
    private String phoneNumber;

    public static final String DEFAULT_PASSWORD = "Kbtu111";

    {
        // Todo: Id generator
        ID = "";
        // Todo: login generator
        LOGIN = "";
        // Todo: hash password
        password = DEFAULT_PASSWORD;
    }

    public User(String firstName, String lastName) {
        super(firstName, lastName);
    }

    // Todo: compare to hash
    public boolean checkCredentials(String login, String password) {
        return LOGIN.equalsIgnoreCase(login) && this.password.equals(password);
    }

    public String getId() {
        return ID;
    }

    public String getLogin() {
        return LOGIN;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
