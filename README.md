# Console Chat Application

A simple console-based chat application that simulates a conversation with a bot. The application provides a WhatsApp-like interface directly in the terminal and maintains chat history for future reference.
Overview

This project offers an engaging and interactive messaging experience through the console. It includes message timestamps, persistent storage, and a clean display format that makes chatting smooth and intuitive.

## Features

*Interactive console-based chat interface

*Built-in bot that responds to user messages

*Persistent chat history storage

*Timestamp for every message

*Clean and organized message formatting

*Command support for managing chat history

*Simple and user-friendly interface

## Technical Stack

*Java 11 or higher

*Maven (project build and dependency management)

*Gson (JSON handling)

## Setup Instructions

*Ensure Java 11 or above is installed

*Install Maven

*Clone the repository

*Build the project using Maven:
mvn clean install

*Run the application:
mvn exec:java -Dexec.mainClass="com.chatapp.ui.ChatUI"

## Usage

*Launch the application and start chatting with the bot.

## Available commands:

*Type exit to end the chat session

*Type clear history to delete stored chat history


*Type help to display available commands

## Project Structure

src/ — Application source code

dao/ — Handles message storage and retrieval

service/ — Core chat logic

ui/ — Console user interface

lib/ — External libraries

target/ — Build outputs

pom.xml — Maven configuration

## Data Storage

Chat history is stored at:
~/.chatapp/chat_history.txt

Bot responses are loaded from:
~/.chatapp/messages.json
For any queries or support, please contact:

GitHub: https://github.com/

Email: abhinavmaurya9c@gmail.com
