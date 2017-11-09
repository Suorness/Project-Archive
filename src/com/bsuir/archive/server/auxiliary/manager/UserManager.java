package com.bsuir.archive.server.auxiliary.manager;

import com.bsuir.archive.server.domain.User;

public class UserManager {

    public UserManager(){
        this.user = new User(0,false,false,false);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
}
