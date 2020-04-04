package com.mebr0.intranet.database;

import com.mebr0.intranet.util.Serializer;
import com.mebr0.study.Course;
import com.mebr0.study.Subject;
import com.mebr0.study.time.Semester;
import com.mebr0.user.base.User;
import com.mebr0.user.entity.Admin;
import com.mebr0.user.entity.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mebr0.intranet.database.Database.Files.*;

/**
 * Class for storing and manipulating data
 * Uses java serialization and {@link Serializer}
 *
 * Have {@link #save()} and {@link #load()} methods
 *
 * @author A.Yergali
 * @version 1.1
 */
public class Database {

    private static Database database = null;

    private Database() {

    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }

        return database;
    }

    private List<User> users;
    private List<Subject> subjects;
    private List<Course> courses;

    {
        load();

        if (getUser("q_q") == null)
            this.users.add(Admin.from("Q", "Q"));
    }

    private void load() {
        this.users = load(USERS, User.class);
        this.subjects = load(SUBJECTS, Subject.class);
        this.courses = load(COURSES, Course.class);
    }

    public boolean save() {
        return save(USERS, this.users) &&
                save(SUBJECTS, this.subjects) &&
                save(COURSES, this.courses);
    }

    /* --------------------------------------------------- Users ---------------------------------------------------- */
    public List<User> getUsers() {
        return users;
    }

    public List<User> getUsers(Class<?> clazz) {
        return users.stream().
                filter(user -> user.getClass() == clazz).
                collect(Collectors.toList());
    }

    public User getUser(String login, String password) {
        return users.stream().
                filter(user -> user.checkCredentials(login, password)).
                findFirst().
                orElse(null);
    }

    public User getUser(String login) {
        return users.stream().
                filter(user -> user.getLogin().equals(login)).
                findFirst().
                orElse(null);
    }

    public <T extends User> T getUser(String fullNameOrLogin, Class<T> clazz) {
         User teacher = users.stream().
                 filter(user -> user.getLogin().equalsIgnoreCase(fullNameOrLogin) ||
                         user.getFullName().equals(fullNameOrLogin)).
                 findFirst().
                 orElse(null);

         if (teacher == null) {
             return null;
         }

        //noinspection unchecked
        return teacher.getClass() == clazz ? (T) teacher : null;
    }

    public void create(User user) {
        users.add(user);
    }

    public boolean deleteUser(String login) {
        int initSize = users.size();

        users.removeIf(user -> user.getLogin().equalsIgnoreCase(login));

        return users.size() != initSize;
    }

    /* -------------------------------------------------- Subjects -------------------------------------------------- */
    public List<Subject> getSubjects() {
        return subjects;
    }

    public Subject getSubject(String title) {
        return subjects.stream().
                filter(subject -> subject.getTitle().equalsIgnoreCase(title)).
                findFirst().
                orElse(null);
    }

    public void create(Subject subject) {
        subjects.add(subject);
    }

    /* --------------------------------------------------- Courses -------------------------------------------------- */
    public List<Course> getCourses() {
        return courses;
    }

    public Course getCourse(Subject subject, int creditNumber, Teacher teacher, Semester semester) {
        return courses.stream().
                filter(course -> course.getSubject() == subject &&
                        course.getCreditsNumber() == creditNumber &&
                        course.getTeacher() == teacher &&
                        course.getSemester().equals(semester)).
                findFirst().
                orElse(null);

    }

    public void create(Course course) {
        courses.add(course);
    }

    /* ---------------------------------------------------- Load ---------------------------------------------------- */
    private <T> List<T> load(Files file, Class<T> clazz) {
        List<T> list = Serializer.deserializeList(file.title, clazz);

        return list != null ? list : new ArrayList<>();
    }

    /* ---------------------------------------------------- Save ---------------------------------------------------- */
    private boolean save(Files file, Object object) {
        return Serializer.serialize(file.title, object);
    }

    /* ---------------------------------------------------- Files --------------------------------------------------- */
    enum Files {

        USERS("users.out"),
        SUBJECTS("subjects.out"),
        COURSES("courses.out");

        private final String title;

        Files(String title) {
            this.title = title;
        }
    }
}
