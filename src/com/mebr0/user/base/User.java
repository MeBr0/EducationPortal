package com.mebr0.user.base;

import com.mebr0.user.util.LoginGenerator;
import com.mebr0.user.util.PasswordEncoder;

import java.io.Serializable;
import java.util.Objects;

public abstract class User extends Person implements Serializable {

    private String id;
    private final String LOGIN;
    private String password;
    private String email;
    private String phoneNumber;

    public static final String DEFAULT_PASSWORD = "Kbtu111";

    {
        password = PasswordEncoder.encode(DEFAULT_PASSWORD);
    }

    public User(String firstName, String lastName) {
        super(firstName, lastName);

        LOGIN = LoginGenerator.generate(firstName, lastName);
    }

    public boolean checkCredentials(String login, String password) {
        password = Objects.requireNonNull(password, "Password cannot be null");
        String encodedPassword = PasswordEncoder.encode(password);

        return LOGIN.equalsIgnoreCase(login) && this.password.equals(encodedPassword);
    }

    public String getId() {
        return id;
    }

    // Todo: change exception to custom one
    public void setId(String id) throws IllegalAccessException {
        if (this.id != null) {
            throw new IllegalAccessException("Id already initialized");
        }

        this.id = id;
    }

    public String getLogin() {
        return LOGIN;
    }

    public void setPassword(String password) {
        password = Objects.requireNonNull(password, "Password cannot be null");
        this.password = PasswordEncoder.encode(password);
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

    @Override
    public String toString() {
        return "id: " + id + ", login: " + LOGIN + ", " + super.toString();
    }
}
