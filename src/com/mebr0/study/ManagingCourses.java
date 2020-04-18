package com.mebr0.study;

import java.util.List;

public interface ManagingCourses {

    List<String> getCourseIds();
    boolean addCourse(Course course);
    boolean removeCourse(Course course);
}
