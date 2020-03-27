package com.mebr0.user.entity;

import com.mebr0.user.base.Employee;
import com.mebr0.user.type.Faculty;
import com.mebr0.user.type.Position;

public class Teacher extends Employee {

    private Faculty faculty;
    private Position position;

    Teacher(String firstName, String lastName, Faculty faculty, Position position) {
        super(firstName, lastName);

        this.faculty = faculty;
        this.position = position;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + ", faculty: " + faculty.getShortName() +
                ", position: " + position.getTitle() + "]";
    }
}
