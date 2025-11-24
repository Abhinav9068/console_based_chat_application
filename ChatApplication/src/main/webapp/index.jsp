<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chat App - Login</title>
    <style>
        body { font-family: Arial, sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f0f0f0; }
        .container { background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); width: 300px; }
        input { display: block; width: 100%; margin-bottom: 10px; padding: 8px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background: #0056b3; }
        .error { color: red; margin-bottom: 10px; font-size: 0.9em; }
        .switch { margin-top: 10px; text-align: center; font-size: 0.9em; cursor: pointer; color: blue; }
    </style>
    <script>
        function toggleForm() {
            var loginForm = document.getElementById('login-form');
            var registerForm = document.getElementById('register-form');
            var title = document.getElementById('form-title');
            if (loginForm.style.display === 'none') {
                loginForm.style.display = 'block';
                registerForm.style.display = 'none';
                title.innerText = 'Login';
            } else {
                loginForm.style.display = 'none';
                registerForm.style.display = 'block';
                title.innerText = 'Register';
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h2 id="form-title">Login</h2>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${param.registered}">
            <div style="color: green; margin-bottom: 10px;">Registration successful! Please login.</div>
        </c:if>

        <form id="login-form" action="login" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
            <div class="switch" onclick="toggleForm()">Need an account? Register</div>
        </form>

        <form id="register-form" action="register" method="post" style="display: none;">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit" style="background-color: #28a745;">Register</button>
            <div class="switch" onclick="toggleForm()">Have an account? Login</div>
        </form>
    </div>
</body>
</html>
