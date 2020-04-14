package com.mebr0.study.registration;

import com.mebr0.intranet.util.Serializer;
import com.mebr0.study.Course;
import com.mebr0.user.entity.Student;
import com.mebr0.user.entity.Student.Degree;
import com.mebr0.user.type.Faculty;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Registration {

    private static Registration registration = null;

    private Registration() {

    }

    public static Registration getInstance() {
        if (registration == null) {
            registration = new Registration();
        }

        return registration;
    }

    private Map<String, List<Course>> courses;
    private static final String file = "registration.out";

    {
        courses = new HashMap<>();
        load();
    }

    public List<Course> get(Student student) {
        String studentYear = student.getId().substring(0, 2);
        LocalDate today = LocalDate.now();

        byte yearOfStudy = (byte) (today.getYear() - Integer.parseInt("20" + studentYear));

        if (today.getMonth().getValue() > 7)
            yearOfStudy++;

        return get(yearOfStudy, student.getDegree(), student.getFaculty());
    }

    public List<Course> get(byte yearOfStudy, Degree degree, Faculty faculty) {
        String key = key(yearOfStudy, degree);

        List<Course> courses = this.courses.get(key);

        if (courses == null)
            return Collections.emptyList();

        return courses.stream().
                filter(course -> course.getSubject().getFaculty() == faculty).
                collect(Collectors.toList());
    }

    public void register(Course course, byte yearOfStudy, Degree degree) {
        String key = key(yearOfStudy, degree);

        courses.putIfAbsent(key, new ArrayList<>());

        courses.get(key).add(course);

        save();
    }

    public void unregister(Course course) {
        courses.values().forEach(courses -> courses.removeIf(c -> c == course));

        save();
    }

    // Todo: handle errors
    @SuppressWarnings("unchecked")
    public boolean load() {
        Map<String, List<Course>> courses = Serializer.deserialize(file, Map.class);

        if (courses != null) {
            this.courses = courses;
            return true;
        }
        else {
            this.courses = new HashMap<>();
            return false;
        }
    }

    private boolean save() {
        return Serializer.serialize(file, courses);
    }

    private String key(byte yearOfStudy, Degree degree) {
        return yearOfStudy + "_" + degree.getShortName();
    }
}
