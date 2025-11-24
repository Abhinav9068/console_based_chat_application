package com.chatapp.model;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private int userId;
    private String username;
    private String text;
    private LocalDateTime timestamp;

    public Message(int id, int userId, String username, String text, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Message(int userId, String username, String text) {
        this(0, userId, username, text, LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
