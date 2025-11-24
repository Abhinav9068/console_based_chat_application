package com.chatapp.dao;

import com.chatapp.model.Message;
import com.chatapp.exception.DataAccessException;
import java.util.List;

public interface MessageRepository {
    void save(Message message) throws DataAccessException;
    List<Message> findAll() throws DataAccessException;
    List<Message> findRecent(int limit) throws DataAccessException;
}
