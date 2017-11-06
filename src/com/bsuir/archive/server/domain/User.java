package com.bsuir.archive.server.domain;

public class User {

    private int id;
    private boolean accessSee;
    private boolean accessWrite;
    private boolean accessChange;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAccessSee() {
        return accessSee;
    }

    public void setAccessSee(boolean accessSee) {
        this.accessSee = accessSee;
    }

    public boolean isAccessWrite() {
        return accessWrite;
    }

    public void setAccessWrite(boolean accessWrite) {
        this.accessWrite = accessWrite;
    }

    public boolean isAccessChange() {
        return accessChange;
    }

    public void setAccessChange(boolean accessChange) {
        this.accessChange = accessChange;
    }
}
