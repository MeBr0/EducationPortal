package com.mebr0.user.entity;

import com.mebr0.study.Course;
import com.mebr0.study.ManagingCourses;
import com.mebr0.user.base.Employee;
import com.mebr0.user.type.Faculty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher extends Employee implements ManagingCourses, Serializable {

    private Faculty faculty;
    private Position position;
    private List<String> courses;

    {
        courses = new ArrayList<>();
    }

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
    public List<String> getCourseIds() {
        return courses;
    }

    @Override
    public boolean addCourse(Course course) {
        return courses.add(course.getId());
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Teacher))
            return false;

        if (!super.equals(o))
            return false;

        Teacher teacher = (Teacher) o;
        return faculty == teacher.faculty &&
                position == teacher.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculty, position);
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
