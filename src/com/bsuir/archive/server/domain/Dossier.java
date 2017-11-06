package com.bsuir.archive.server.domain;

public class Dossier {

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + firstName.hashCode() + lastName.hashCode();
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
        if (!(obj instanceof Dossier)) {
            return false;
        }
        Dossier userOnCheck = (Dossier) obj;
        if (firstName == null) {
            if (userOnCheck.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(userOnCheck.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (userOnCheck.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(userOnCheck.lastName)) {
            return false;
        }
        if (groupNumber == null) {
            if (userOnCheck.groupNumber != null) {
                return false;
            }
        } else if (!groupNumber.equals(userOnCheck.groupNumber)) {
            return false;
        }

        return true;
    }
    private String firstName;
    private String lastName;
    private String groupNumber;
}
