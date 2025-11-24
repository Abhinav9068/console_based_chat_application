package com.chatapp.dao;

import com.chatapp.model.Message;
import com.chatapp.exception.DataAccessException;
import com.chatapp.util.DatabaseConnectionManager;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcMessageDAO implements MessageRepository {

    @Override
    public void save(Message message) throws DataAccessException {
        String sql = "INSERT INTO messages (user_id, text, timestamp) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, message.getUserId());
            stmt.setString(2, message.getText());
            stmt.setTimestamp(3, Timestamp.valueOf(message.getTimestamp()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error saving message", e);
        }
    }

    @Override
    public List<Message> findAll() throws DataAccessException {
        String sql = "SELECT m.id, m.user_id, u.username, m.text, m.timestamp " +
                     "FROM messages m " +
                     "JOIN users u ON m.user_id = u.id " +
                     "ORDER BY m.timestamp ASC";
        List<Message> messages = new ArrayList<>();
        try (Connection conn = DatabaseConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                messages.add(mapRowToMessage(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving messages", e);
        }
        return messages;
    }

    @Override
    public List<Message> findRecent(int limit) throws DataAccessException {
        String sql = "SELECT m.id, m.user_id, u.username, m.text, m.timestamp " +
                     "FROM messages m " +
                     "JOIN users u ON m.user_id = u.id " +
                     "ORDER BY m.timestamp DESC LIMIT ?";
        List<Message> messages = new ArrayList<>();
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    messages.add(0, mapRowToMessage(rs)); // Add to front to keep chronological order
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving recent messages", e);
        }
        return messages;
    }

    private Message mapRowToMessage(ResultSet rs) throws SQLException {
        return new Message(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getString("username"),
            rs.getString("text"),
            rs.getTimestamp("timestamp").toLocalDateTime()
        );
    }
}
