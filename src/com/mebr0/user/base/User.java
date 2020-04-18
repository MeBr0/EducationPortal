package com.mebr0.user.base;

import com.mebr0.user.util.LoginGenerator;
import com.mebr0.user.util.PasswordEncoder;

import java.io.Serializable;
import java.util.Objects;

public abstract class User extends Person implements Serializable {

    private String id;
    private final String login;
    private String password;
    private String email;
    private String phoneNumber;

    public static final String DEFAULT_PASSWORD = "Kbtu111";

    {
        password = PasswordEncoder.encode(DEFAULT_PASSWORD);
    }

    public User(String firstName, String lastName) {
        super(firstName, lastName);

        login = LoginGenerator.generate(firstName, lastName);
    }

    public boolean checkCredentials(String login, String password) {
        password = Objects.requireNonNull(password, "Password cannot be null");
        String encodedPassword = PasswordEncoder.encode(password);

        return this.login.equalsIgnoreCase(login) && this.password.equals(encodedPassword);
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
        return login;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof User))
            return false;

        if (!super.equals(o))
            return false;

        User user = (User) o;
        return id.equals(user.id) &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, login, password);
    }

    @Override
    public String toString() {
        return "id: " + id + ", login: " + login + ", " + super.toString();
    }
}
