package com.chatapp.controller;

import com.chatapp.dao.JdbcUserDAO;
import com.chatapp.dao.UserRepository;
import com.chatapp.model.Guest;
import com.chatapp.model.User;
import com.chatapp.exception.DataAccessException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() {
        userRepository = new JdbcUserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        // Default to GUEST role for new registrations
        User newUser = new Guest(0, username, password);
        
        try {
            userRepository.save(newUser);
            resp.sendRedirect("login?registered=true");
        } catch (DataAccessException e) {
            String errorMessage = "Registration failed: " + e.getMessage();
            if (e.getCause() != null) {
                errorMessage += " - Reason: " + e.getCause().getMessage();
            }
            req.setAttribute("error", errorMessage);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
