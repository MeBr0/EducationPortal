package com.mebr0.intranet.session;

import com.mebr0.intranet.database.Database;
import com.mebr0.intranet.session.base.Session;
import com.mebr0.intranet.session.base.UserSession;
import com.mebr0.intranet.session.mark.Level;
import com.mebr0.study.Course;
import com.mebr0.study.registration.Registration;
import com.mebr0.user.entity.Student;

import java.util.List;

import static com.mebr0.intranet.util.Printer.print;

public class StudentSession implements UserSession {

    private final Student student;

    private final Database DB = Database.getInstance();
    private final Registration REG = Registration.getInstance();

    private StudentSession(Student student) {
        this.student = student;
    }

    public static StudentSession getSession(Student student) {
        return new StudentSession(student);
    }

    @Override
    public void greet() {
        print("Logged in as " + student.getFullName() + " (" + student.getClass().getSimpleName() + ")");
    }

    @Override
    public Session.Status begin() {
        greet();

        root();

        return Session.Status.OK;
    }

    @Level(1)
    private void root() {
        String[] options = { "Registration" };
        Runnable[] methods = { this::registration };

        split(options, methods);
    }

    @Level(2)
    private void registration() {
        List<Course> courses = REG.get(student);

        print(courses);
    }
}
