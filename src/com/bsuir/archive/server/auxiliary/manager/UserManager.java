package com.bsuir.archive.server.auxiliary.manager;

import com.bsuir.archive.server.domain.User;

public class UserManager {

    public UserManager() {
        this.user = startUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void logOut() {
        user = startUser;
    }

    private User user;
    private User startUser = new User("login", "pas", false, false, false, false);
}
