package com.mebr0.intranet.session;

import com.mebr0.intranet.database.Database;
import com.mebr0.user.base.User;
import com.mebr0.user.entity.Admin;
import com.mebr0.user.type.Degree;
import com.mebr0.user.type.Faculty;
import com.mebr0.user.type.Position;

import static com.mebr0.intranet.util.Printer.print;
import static com.mebr0.intranet.util.Scanner.ask;

public class AdminSession implements Session {

    private Admin admin;

    private Database database = Database.getInstance();

    private AdminSession(Admin admin) {
        this.admin = admin;
    }

    public static AdminSession getSession(Admin admin) {
        return new AdminSession(admin);
    }

    @Override
    public void greet() {
        print("Logged in as " + admin.getFullName() + " (" + admin.getClass().getSimpleName() + ")");
    }

    @Override
    public Status begin() {
        greet();

        root();

        return Status.OK;
    }

    private void root() {
        String[] options = { "Add user" };
        Runnable[] methods = { this::addUser };

        split(options, methods);
    }

    private void addUser() {
        String[] options = { "Add admin", "Add student", "Add teacher" };
        Runnable[] methods = { this::addAdmin, this::addStudent, this::addTeacher };

        split(options, methods);
    }

    private void addAdmin() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");

        User user = admin.admin(name, lastName);
        database.createUser(user);
        print("Created " + user);
    }

    private void addStudent() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");
        Faculty faculty = ask(Faculty.class);
        Degree degree = ask(Degree.class);

        User user = admin.student(name, lastName, faculty, degree);
        database.createUser(user);
        print("Created " + user);
    }

    private void addTeacher() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");
        Faculty faculty = ask(Faculty.class);
        Position position = ask(Position.class);

        User user = admin.teacher(name, lastName, faculty, position);
        database.createUser(user);
        print("Created " + user);
    }
}
