package com.mebr0.user.entity;

import com.mebr0.user.base.Employee;
import com.mebr0.user.type.Degree;
import com.mebr0.user.type.Faculty;
import com.mebr0.user.type.Position;

public class Admin extends Employee {

    public Admin(String name, String lastName) {
        super(name, lastName);
    }

    public Student student(String firstName, String lastName, Faculty faculty, Degree degree) {
        return new Student(firstName, lastName, faculty, degree);
    }

    public Teacher teacher(String firstName, String lastName, Faculty faculty, Position position) {
        return new Teacher(firstName, lastName, faculty, position);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + "]";
    }
}
