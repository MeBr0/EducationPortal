package com.mebr0.intranet.session;

import com.mebr0.intranet.database.Database;
import com.mebr0.user.base.User;
import com.mebr0.user.entity.Admin;
import com.mebr0.user.entity.Student;
import com.mebr0.user.entity.Teacher;
import com.mebr0.user.type.Degree;
import com.mebr0.user.type.Faculty;
import com.mebr0.user.type.Position;

import java.util.List;

import static com.mebr0.intranet.util.Printer.error;
import static com.mebr0.intranet.util.Printer.print;
import static com.mebr0.intranet.util.Scanner.ask;

/**
 * Session class for {@link Admin} within {@link com.mebr0.intranet.Intranet} session
 *
 * @author A.Yergali
 * @version 1.2
 */
public class AdminSession implements Session {

    private final Admin ADMIN;

    private final Database DB = Database.getInstance();

    private AdminSession(Admin admin) {
        this.ADMIN = admin;
    }

    public static AdminSession getSession(Admin admin) {
        return new AdminSession(admin);
    }

    @Override
    public void greet() {
        print("Logged in as " + ADMIN.getFullName() + " (" + ADMIN.getClass().getSimpleName() + ")");
    }

    @Override
    public Status begin() {
        greet();

        root();

        return Status.OK;
    }

    @Level(1)
    private void root() {
        String[] options = { "Add user", "Remove user", "Show users", "Change password" };
        Runnable[] methods = { this::addUser, this::removeUser, this::showUsers, this::changePassword };

        split(options, methods);
    }

    @Level(2)
    private void addUser() {
        String[] options = { "Add admin", "Add student", "Add teacher" };
        Runnable[] methods = { this::addAdmin, this::addStudent, this::addTeacher };

        split(options, methods);
    }

    @Level(3)
    private void addAdmin() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");

        User user = ADMIN.admin(name, lastName);
        DB.createUser(user);
        print("Created " + user);
    }

    @Level(3)
    private void addStudent() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");
        Faculty faculty = ask(Faculty.class);
        Degree degree = ask(Degree.class);

        User user = ADMIN.student(name, lastName, faculty, degree);
        DB.createUser(user);
        print("Created " + user);
    }

    @Level(3)
    private void addTeacher() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");
        Faculty faculty = ask(Faculty.class);
        Position position = ask(Position.class);

        User user = ADMIN.teacher(name, lastName, faculty, position);
        DB.createUser(user);
        print("Created " + user);
    }

    @Level(2)
    private void removeUser() {
        String login = ask("Enter login of user to remove");

        boolean result = DB.deleteUser(login);

        if (result)
            print("Removed user with login " + login);
        else
            error("Could not find user with login " + login);
    }

    @Level(2)
    private void showUsers() {
        Class<?> clazz = ask(Admin.class, Student.class, Teacher.class);

        List<User> users = DB.getUsers(clazz);

        if (users.isEmpty())
            print("Empty");
        else
            print(users);
    }

    @Level(2)
    private void changePassword() {
        if (changePassword(ADMIN))
            print("Password changed");
        else
            error("Could not change password");
    }
}
