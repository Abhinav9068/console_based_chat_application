USE chatapp_db;

-- Fix for "Unknown column 'role'" error
-- This adds the missing column to your existing users table
ALTER TABLE users ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'GUEST';
