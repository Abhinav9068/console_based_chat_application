package com.chatapp.dao;

import com.chatapp.model.User;
import com.chatapp.exception.DataAccessException;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username) throws DataAccessException;
    User save(User user) throws DataAccessException;
}
