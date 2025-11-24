<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chat Room</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; display: flex; flex-direction: column; height: 100vh; background-color: #e9ebee; }
        header { background: #4267b2; color: white; padding: 10px 20px; display: flex; justify-content: space-between; align-items: center; }
        #chat-box { flex: 1; overflow-y: auto; padding: 20px; display: flex; flex-direction: column; }
        .message { background: white; padding: 10px; margin-bottom: 10px; border-radius: 5px; max-width: 70%; box-shadow: 0 1px 2px rgba(0,0,0,0.1); }
        .message.self { align-self: flex-end; background: #dcf8c6; }
        .message .user { font-weight: bold; font-size: 0.8em; color: #555; margin-bottom: 3px; }
        .message .time { font-size: 0.7em; color: #999; text-align: right; margin-top: 5px; }
        footer { padding: 20px; background: white; border-top: 1px solid #ccc; display: flex; }
        input[type="text"] { flex: 1; padding: 10px; border: 1px solid #ccc; border-radius: 4px; margin-right: 10px; }
        button { padding: 10px 20px; background: #4267b2; color: white; border: none; border-radius: 4px; cursor: pointer; }
    </style>
    <script>
        // Auto-scroll to bottom
        window.onload = function() {
            var chatBox = document.getElementById('chat-box');
            chatBox.scrollTop = chatBox.scrollHeight;
        }
    </script>
</head>
<body>
    <header>
        <div>Chat Room</div>
        <div>
            <span>Welcome, ${sessionScope.user.username} (${sessionScope.user.role})</span>
            <a href="logout" style="color: white; margin-left: 10px; text-decoration: none; font-size: 0.9em; border: 1px solid white; padding: 5px; border-radius: 3px;">Logout</a>
        </div>
    </header>
    
    <div id="chat-box">
        <c:forEach var="msg" items="${messages}">
            <div class="message ${msg.userId == sessionScope.user.id ? 'self' : ''}">
                <div class="user">${msg.username}</div>
                <div>${msg.text}</div>
                <div class="time">${msg.timestamp}</div>
            </div>
        </c:forEach>
    </div>
    
    <footer>
        <form action="chat" method="post" style="display: flex; width: 100%;">
            <input type="text" name="message" placeholder="Type a message..." required autocomplete="off">
            <button type="submit">Send</button>
        </form>
    </footer>
</body>
</html>
