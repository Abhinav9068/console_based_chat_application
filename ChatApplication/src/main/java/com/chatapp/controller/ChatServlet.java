package com.chatapp.controller;

import com.chatapp.dao.JdbcMessageDAO;
import com.chatapp.dao.MessageRepository;
import com.chatapp.model.Message;
import com.chatapp.model.User;
import com.chatapp.exception.DataAccessException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    private MessageRepository messageRepository;

    @Override
    public void init() {
        messageRepository = new JdbcMessageDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }

        try {
            List<Message> messages = messageRepository.findRecent(50);
            req.setAttribute("messages", messages);
            req.getRequestDispatcher("/chat.jsp").forward(req, resp);
        } catch (DataAccessException e) {
            throw new ServletException("Error loading messages", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login");
            return;
        }

        User user = (User) session.getAttribute("user");
        String text = req.getParameter("message");

        if (text != null && !text.trim().isEmpty()) {
            Message message = new Message(user.getId(), user.getUsername(), text);
            try {
                messageRepository.save(message);
            } catch (DataAccessException e) {
                throw new ServletException("Error saving message", e);
            }
        }

        resp.sendRedirect("chat");
    }
}
