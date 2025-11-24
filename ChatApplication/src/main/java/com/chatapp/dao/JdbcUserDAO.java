package com.chatapp.dao;

import com.chatapp.model.Admin;
import com.chatapp.model.Guest;
import com.chatapp.model.User;
import com.chatapp.exception.DataAccessException;
import com.chatapp.util.DatabaseConnectionManager;
import java.sql.*;
import java.util.Optional;

public class JdbcUserDAO implements UserRepository {

    @Override
    public Optional<User> findByUsername(String username) throws DataAccessException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToUser(rs));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error finding user", e);
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) throws DataAccessException {
        String sqlUser = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        String sqlLog = "INSERT INTO user_logs (user_id, action, timestamp) VALUES (?, 'REGISTER', NOW())";
        
        Connection conn = null;
        try {
            conn = DatabaseConnectionManager.getConnection();
            conn.setAutoCommit(false); // Start transaction

            int userId;
            try (PreparedStatement stmt = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getRole());
                stmt.executeUpdate();
                
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlLog)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            conn.commit(); // Commit transaction
            
            if ("ADMIN".equals(user.getRole())) {
                return new Admin(userId, user.getUsername(), user.getPassword());
            } else {
                return new Guest(userId, user.getUsername(), user.getPassword());
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new DataAccessException("Error saving user with transaction", e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private User mapRowToUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String role = rs.getString("role");

        if ("ADMIN".equals(role)) {
            return new Admin(id, username, password);
        } else {
            return new Guest(id, username, password);
        }
    }
}
