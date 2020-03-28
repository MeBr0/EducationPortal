package com.mebr0.user.base;

import com.mebr0.user.util.IdGenerator;

import java.io.Serializable;

public abstract class Employee extends User implements Serializable {

    public static long count = 0;

    public Employee(String firstName, String lastName) {
        super(firstName, lastName);

        String generatedId = IdGenerator.generate();

        // Todo: catch it
        try {
            setId(generatedId);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
