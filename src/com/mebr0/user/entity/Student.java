package com.mebr0.user.entity;

import com.mebr0.user.base.User;
import com.mebr0.user.type.Degree;
import com.mebr0.user.type.Faculty;
import com.mebr0.user.util.IdGenerator;

import java.io.Serializable;

public class Student extends User implements Serializable {

    private float gpa;
    private Faculty faculty;
    private Degree degree;

    public static long count = 0;

    {
        gpa = 0F;
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
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + ", faculty: " + faculty.getShortName() +
                ", degree: " + degree.getShortName() + ", gpa: " + gpa + "]";
    }
}
