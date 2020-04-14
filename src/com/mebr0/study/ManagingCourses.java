package com.mebr0.study;

import java.util.List;
import java.util.stream.Collectors;

public interface ManagingCourses {

    List<Course> getCourses();
    boolean addCourse(Course course);
    boolean removeCourse(Course course);

    default List<Course> getActiveCourses() {
        return getCourses().stream().
                filter(Course::isActive).
                collect(Collectors.toList());
    }

}
