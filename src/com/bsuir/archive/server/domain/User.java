package com.bsuir.archive.server.domain;

public class User {

    private Integer id;
    private Boolean accessSee;
    private Boolean accessWrite;
    private Boolean accessChange;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + id;
        return result;
    }
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
        if ( !(id==userOnCheck.id)){
            return false;
        }
        if (!(accessSee==userOnCheck.accessSee)) {
            return false;
        }
        if (!(accessWrite==userOnCheck.accessWrite)) {
            return false;
        }
        if (!(accessChange==userOnCheck.accessChange)) {
            return false;
        }

        return true;
    }
}
