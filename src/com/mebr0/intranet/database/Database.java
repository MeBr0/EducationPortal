package com.mebr0.intranet.database;

import com.mebr0.intranet.util.Serializer;
import com.mebr0.user.base.User;
import com.mebr0.user.entity.Admin;

import java.util.ArrayList;
import java.util.List;

import static com.mebr0.intranet.database.Database.Files.USERS;
import static com.mebr0.intranet.util.Printer.error;

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

    {
        if (!load())
            error("Could not load");

        if (getUser("q_q") == null)
            this.users.add(Admin.from("Q", "Q"));
    }

    public boolean load() {
        return loadUsers();
    }

    public boolean save() {
        return saveUsers();
    }

    /* --------------------------------------------------- Users ---------------------------------------------------- */
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

    public User getUser(String login) {
        for (User user: users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        return null;
    }

    /* ---------------------------------------------------- Load ---------------------------------------------------- */
    private boolean loadUsers() {
        List<User> users = Serializer.deserializeList(USERS.title, User.class);

        this.users = users != null ? users : new ArrayList<>();

        return users != null;
    }

    /* ---------------------------------------------------- Save ---------------------------------------------------- */
    private boolean saveUsers() {
        return Serializer.serialize(USERS.title, this.users);
    }

    /* ---------------------------------------------------- Files --------------------------------------------------- */
    enum Files {
        USERS("users.out");

        private final String title;

        Files(String title) {
            this.title = title;
        }
    }
}
