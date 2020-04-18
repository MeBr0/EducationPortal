package com.mebr0.user.entity;

import com.mebr0.user.base.Employee;
import com.mebr0.user.type.Faculty;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Manager))
            return false;

        if (!super.equals(o))
            return false;

        Manager manager = (Manager) o;
        return faculty == manager.faculty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculty);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + ", faculty: " + faculty.getShortName() + "]";
    }
}
