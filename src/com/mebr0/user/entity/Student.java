package com.mebr0.user.entity;

import com.mebr0.user.base.User;
import com.mebr0.user.type.Degree;
import com.mebr0.user.type.Faculty;

public class Student extends User {

    private float gpa;
    private Faculty faculty;
    private Degree degree;

    {
        gpa = 0;
    }

    public Student(Faculty faculty, Degree degree, String firstName, String lastName) {
        super(firstName, lastName);

        this.faculty = faculty;
        this.degree = degree;
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
}
