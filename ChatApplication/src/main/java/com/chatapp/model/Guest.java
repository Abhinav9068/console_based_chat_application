package com.chatapp.model;

public class Guest extends User {
    public Guest(int id, String username, String password) {
        super(id, username, password, "GUEST");
    }

    @Override
    public boolean hasPrivileges() {
        return false;
    }
}
