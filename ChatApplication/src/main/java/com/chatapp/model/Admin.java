package com.chatapp.model;

public class Admin extends User {
    public Admin(int id, String username, String password) {
        super(id, username, password, "ADMIN");
    }

    @Override
    public boolean hasPrivileges() {
        return true;
    }
}
