package com.mebr0.study;

import com.mebr0.user.type.Faculty;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Subject class contains general information
 *
 * @author A.Yergali
 * @version 1.0
 */
public class Subject implements Serializable {

    private final String id;
    private String title;
    private String description;
    private Faculty faculty;

    {
        id = UUID.randomUUID().toString();
    }

    public Subject(String title, String description, Faculty faculty) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.description = Objects.requireNonNull(description, "Description cannot be null");
        this.faculty = Objects.requireNonNull(faculty, "Faculty cannot be null");
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String text) {
        this.description = Objects.requireNonNull(text, "Description cannot be null");
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = Objects.requireNonNull(faculty, "Faculty cannot be null");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [title: " + title + ", faculty: " + faculty.getShortName() + "]";
    }
}
