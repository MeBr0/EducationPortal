package com.mebr0.intranet.session;

import com.mebr0.user.entity.Admin;

import static com.mebr0.intranet.util.Printer.*;
import static com.mebr0.intranet.util.Scanner.*;

public class AdminSession implements Session {

    private Admin user;

    private AdminSession(Admin admin) {
        this.user = admin;
    }

    public static AdminSession getSession(Admin admin) {
        return new AdminSession(admin);
    }

    @Override
    public void greet() {
        print("Logged in as " + user.getFullName() + "(" + user.getClass().getSimpleName() + ")");
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
        String[] options = { "Add admin" };
        Runnable[] methods = { this::addAdmin };

        split(options, methods);
    }

    private void addAdmin() {
        String name = ask("Enter name");
        String lastName = ask("Enter last name");

        Admin admin = user.admin(name, lastName);

        print("Created " + admin);
    }
}
