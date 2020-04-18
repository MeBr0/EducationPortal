package com.mebr0.study.registration;

import com.mebr0.intranet.util.Serializer;
import com.mebr0.study.Course;
import com.mebr0.user.entity.Student;
import com.mebr0.user.entity.Student.Degree;
import com.mebr0.user.type.Faculty;

import java.time.LocalDate;
import java.util.*;

import static com.mebr0.intranet.util.Printer.print;

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

    private Map<String, List<String>> courses;
    private static final String file = "registration.out";

    {
        courses = new HashMap<>();
        load();
    }

    public List<String> get(Student student) {
        String studentYear = student.getId().substring(0, 2);
        LocalDate today = LocalDate.now();

        byte yearOfStudy = (byte) (today.getYear() - Integer.parseInt("20" + studentYear));

        if (today.getMonth().getValue() > 7)
            yearOfStudy++;

        return get(yearOfStudy, student.getDegree(), student.getFaculty());
    }

    public List<String> get(byte yearOfStudy, Degree degree, Faculty faculty) {
        String key = key(yearOfStudy, faculty, degree);

        List<String> courses = this.courses.get(key);

        if (courses == null)
            return Collections.emptyList();

        return courses;
    }

    public void register(Course course, byte yearOfStudy, Degree degree) {
        String key = key(yearOfStudy, course.getSubject().getFaculty(), degree);

        courses.putIfAbsent(key, new ArrayList<>());

        courses.get(key).add(course.getId());

        save();
    }

    public void unregister(Course course) {
        courses.values().forEach(courses -> courses.removeIf(c -> c.equals(course.getId())));

        save();
    }

    // Todo: handle errors
    @SuppressWarnings("unchecked")
    public boolean load() {
        Map<String, List<String>> courses = Serializer.deserialize(file, Map.class);

        if (courses != null) {
            this.courses = courses;
            this.courses.forEach((key, list) -> {
                print(key);
                print(list);
            });
            return true;
        }
        else {
            this.courses = new HashMap<>();
            return false;
        }
    }

    private boolean save() {
        this.courses.forEach((key, list) -> {
            print(key);
            print(list);
        });
        return Serializer.serialize(file, courses);
    }

    private String key(byte yearOfStudy, Faculty faculty, Degree degree) {
        return yearOfStudy + "_" + faculty.getShortName() + "_" + degree.getShortName();
    }
}
