package com.mebr0.intranet.database;

import com.mebr0.user.base.User;
import com.mebr0.user.entity.Admin;

import java.util.ArrayList;
import java.util.List;

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

    {
        users = new ArrayList<>();

        users.add(Admin.from("Q", "Q"));
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(String login, String password) {
        for (User user: users) {
            if (user.checkCredentials(login, password)) {
                return user;
            }
        }

        return null;
    }
}
