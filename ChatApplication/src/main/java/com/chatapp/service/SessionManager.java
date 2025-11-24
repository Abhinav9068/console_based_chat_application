package com.chatapp.service;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private static SessionManager instance;
    private final Set<String> activeUsers;

    private SessionManager() {
        activeUsers = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void addUser(String username) {
        activeUsers.add(username);
    }

    public void removeUser(String username) {
        activeUsers.remove(username);
    }

    public boolean isUserActive(String username) {
        return activeUsers.contains(username);
    }
    
    public Set<String> getActiveUsers() {
        return Collections.unmodifiableSet(activeUsers);
    }
}
