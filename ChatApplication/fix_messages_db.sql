USE chatapp_db;

-- Drop the messages table to reset it (in case it was created incorrectly)
DROP TABLE IF EXISTS messages;

-- Recreate the messages table with the correct structure
CREATE TABLE messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    text TEXT NOT NULL,
    timestamp DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
