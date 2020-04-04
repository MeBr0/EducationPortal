package com.mebr0.user.base;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public abstract class Person implements Serializable {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;

    public Person(String firstName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");
    }

    public String getFullName() {
        return (firstName + " " + lastName).trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "name: " + firstName + ", lastName: " + lastName;
    }

    public enum Gender {

        MALE("Male"),
        FEMALE("Female");

        private final String title;

        Gender(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
