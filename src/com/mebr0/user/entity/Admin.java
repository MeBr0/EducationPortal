package com.mebr0.user.entity;

import com.mebr0.user.base.User;
import com.mebr0.user.type.Degree;
import com.mebr0.user.type.Faculty;

public class Admin extends User {

    public Admin(String name, String lastName) {
        super(name, lastName);
    }

    public Student student(String firstName, String lastName, Faculty faculty, Degree degree) {
        return new Student(firstName, lastName, faculty, degree);
    }

}
