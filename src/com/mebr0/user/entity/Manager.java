package com.mebr0.user.entity;

import com.mebr0.user.base.Employee;
import com.mebr0.user.type.Faculty;

public class Manager extends Employee {

    private Faculty faculty;

    Manager(String firstName, String lastName, Faculty faculty) {
        super(firstName, lastName);

        this.faculty = faculty;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + ", faculty: " + faculty.getShortName() + "]";
    }
}
