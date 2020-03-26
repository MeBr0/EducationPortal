package com.mebr0.user.entity;

import com.mebr0.user.base.User;
import com.mebr0.user.type.Degree;
import com.mebr0.user.type.Faculty;
import com.mebr0.user.util.IdGenerator;

public class Student extends User {

    private float gpa;
    private Faculty faculty;
    private Degree degree;

    public static int count = 0;

    {
        gpa = 0F;
    }

    public Student(Faculty faculty, Degree degree, String firstName, String lastName) {
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
}
