package com.chatapp.controller;

import com.chatapp.dao.JdbcUserDAO;
import com.chatapp.dao.UserRepository;
import com.chatapp.model.User;
import com.chatapp.service.SessionManager;
import com.chatapp.exception.DataAccessException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() {
        userRepository = new JdbcUserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Optional<User> userOpt = userRepository.findByUsername(username);
            if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", userOpt.get());
                SessionManager.getInstance().addUser(username);
                resp.sendRedirect("chat");
            } else {
                req.setAttribute("error", "Invalid username or password");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (DataAccessException e) {
            String errorMessage = "Login error: " + e.getMessage();
            if (e.getCause() != null) {
                errorMessage += " - Reason: " + e.getCause().getMessage();
            }
            throw new ServletException(errorMessage, e);
        }
    }
}
