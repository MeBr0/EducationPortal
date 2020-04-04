package com.mebr0.user.entity;

import com.mebr0.user.base.Employee;
import com.mebr0.user.type.Faculty;

import java.io.Serializable;

public class Teacher extends Employee implements Serializable {

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

    public enum Position {

        TUTOR("Tutor"),
        LECTURER("Lecturer"),
        SENIOR_LECTURER("Senior lecturer"),
        PROFESSOR("Professor");

        private final String title;

        Position(String title) {
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
