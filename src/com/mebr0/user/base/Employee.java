package com.mebr0.user.base;

public abstract class Employee extends User {

    public static int count = 0;

    public Employee(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
