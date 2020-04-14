package com.mebr0.user.entity;

import com.mebr0.study.Course;
import com.mebr0.study.ManagingCourses;
import com.mebr0.user.base.User;
import com.mebr0.user.type.Faculty;
import com.mebr0.user.util.IdGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Serializable, ManagingCourses {

    private float gpa;
    private Faculty faculty;
    private Degree degree;
    private List<Course> courses;

    public static long count = 0;

    {
        gpa = 0F;
        courses = new ArrayList<>();
    }

    Student(String firstName, String lastName, Faculty faculty, Degree degree) {
        super(firstName, lastName);

        this.faculty = faculty;
        this.degree = degree;

        String generatedId = IdGenerator.generate(degree);

        // Todo: catch it
        try {
            setId(generatedId);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public float getGpa() {
        return gpa;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @Override
    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public boolean addCourse(Course course) {
        return courses.add(course);
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + ", faculty: " + faculty.getShortName() +
                ", degree: " + degree.getShortName() + ", gpa: " + gpa + "]";
    }

    public enum Degree {

        BD("BD", "Bachelor Degree"),
        MD("MD", "Master Degree"),
        PHD("PHD", "Philosophy Doctor Degree");

        private final String name;
        private final String title;

        Degree(String name, String title) {
            this.name = name;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public String getShortName() {
            return name;
        }

        @Override
        public String toString() {
            return name + " (" + title + ")";
        }
    }
}
