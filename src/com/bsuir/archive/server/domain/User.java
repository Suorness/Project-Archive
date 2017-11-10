package com.bsuir.archive.server.domain;

public class User {

    private String login;

    public String getLogin() {
        return login;
    }

    private String hash;

    private Boolean accessSee;
    private Boolean accessWrite;
    private Boolean accessChange;


    private Boolean accessAdmin;

    public User() {
    }

    public User(String login, String hash, Boolean accessSee, Boolean accessWrite, Boolean accessChange, Boolean accessAdmin) {
        this.login = login;
        this.hash = hash;
        this.accessSee = accessSee;
        this.accessWrite = accessWrite;
        this.accessChange = accessChange;
        this.accessAdmin = accessAdmin;
    }


    public Boolean isAccessSee() {
        return accessSee;
    }

    public void setAccessSee(Boolean accessSee) {
        this.accessSee = accessSee;
    }

    public boolean isAccessWrite() {
        return accessWrite;
    }

    public void setAccessWrite(Boolean accessWrite) {
        this.accessWrite = accessWrite;
    }

    public Boolean isAccessChange() {
        return accessChange;
    }

    public void setAccessChange(Boolean accessChange) {
        this.accessChange = accessChange;
    }

    public Boolean getAccessAdmin() {
        return accessAdmin;
    }

    public void setAccessAdmin(Boolean accessAdmin) {
        this.accessAdmin = accessAdmin;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + login.hashCode() + hash.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User userOnCheck = (User) obj;
        if (login == null) {
            if (userOnCheck.login != null) {
                return false;
            }
        } else if (!login.equals(userOnCheck.login)) {
            return false;
        }

        if (!(accessSee == userOnCheck.accessSee)) {
            return false;
        }
        if (!(accessWrite == userOnCheck.accessWrite)) {
            return false;
        }
        if (!(accessChange == userOnCheck.accessChange)) {
            return false;
        }

        return true;
    }
}
