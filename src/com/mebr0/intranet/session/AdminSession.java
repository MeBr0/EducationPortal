package com.mebr0.intranet.session;

import com.mebr0.intranet.database.Database;
import com.mebr0.intranet.session.base.UserSession;
import com.mebr0.intranet.session.mark.Level;
import com.mebr0.user.base.User;
import com.mebr0.user.entity.Admin;
import com.mebr0.user.entity.Student;
import com.mebr0.user.entity.Student.Degree;
import com.mebr0.user.entity.Teacher;
import com.mebr0.user.entity.Teacher.Position;
import com.mebr0.user.type.Faculty;

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
public class AdminSession implements UserSession {

    private final Admin admin;

    private final Database DB = Database.getInstance();

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

    @Level(1)
    private void root() {
        String[] options = { "Users", "Profile" };
        Runnable[] methods = { this::users, this::profile };

        split(options, methods);
    }

    @Level(2)
    private void users() {
        String[] options = { "Add user", "Remove user", "Show users" };
        Runnable[] methods = { this::addUser, this::removeUser, this::showUsers };

        split(options, methods);
    }

    @Level(3)
    private void addUser() {
        String[] options = { "Add admin", "Add student", "Add teacher", "Add manager" };
        Runnable[] methods = { this::addAdmin, this::addStudent, this::addTeacher, this::addManager };

        split(options, methods);
    }

    @Level(4)
    private void addAdmin() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");

        User user = admin.admin(name, lastName);
        DB.create(user);
        print("Created " + user);
    }

    @Level(4)
    private void addStudent() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");
        Faculty faculty = ask(Faculty.class);
        Degree degree = ask(Degree.class);

        User user = admin.student(name, lastName, faculty, degree);
        DB.create(user);
        print("Created " + user);
    }

    @Level(4)
    private void addTeacher() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");
        Faculty faculty = ask(Faculty.class);
        Position position = ask(Position.class);

        User user = admin.teacher(name, lastName, faculty, position);
        DB.create(user);
        print("Created " + user);
    }

    @Level(4)
    private void addManager() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");
        Faculty faculty = ask(Faculty.class);

        User user = admin.manager(name, lastName, faculty);
        DB.create(user);
        print("Created " + user);
    }

    @Level(3)
    private void removeUser() {
        String login = ask("Enter login of user to remove");

        boolean result = DB.deleteUser(login);

        if (result)
            print("Removed user with login " + login);
        else
            error("Could not find user with login " + login);
    }

    @Level(3)
    private void showUsers() {
        Class<?> clazz = ask(Admin.class, Student.class, Teacher.class);

        List<User> users = DB.getUsers(clazz);

        print(users);
    }

    @Level(2)
    private void profile() {
        String[] options = { "Change password" };
        Runnable[] methods = { this::changePassword };

        split(options, methods);
    }

    @Level(3)
    private void changePassword() {
        if (changePassword(admin))
            print("Password changed");
        else
            error("Could not change password");
    }
}
