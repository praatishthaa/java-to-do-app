# Java To-Do List App

A simple and interactive desktop To-Do List application built using Java Swing. It allows users to add, delete, and mark tasks as complete — all wrapped in a clean and user-friendly GUI.

##  Features

- Add tasks using a button or by pressing Enter
- Delete selected tasks with a confirmation popup
- Mark tasks as completed
- Live task counter at the bottom
- Input field disables automatically when empty
- Scrollable task list with intuitive layout

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or above
- Any IDE (IntelliJ, Eclipse, VS Code) OR command-line setup

### Run via Command Line

javac ToDoApp.java
java ToDoApp
Run via IntelliJ IDEA
Open IntelliJ

Create a new Java project

Add ToDoApp.java to the src folder

Run the main() method

Code Structure
JFrame is used for the main window

JList with DefaultListModel holds all tasks

JTextField takes user input

JButtons handle actions: Add, Delete, Complete

JLabel shows live task count

Event listeners are used to handle input and button clicks

 Concepts Used
Java Swing GUI components

Event-driven programming with ActionListener & DocumentListener

Layout managers: BorderLayout, FlowLayout

MVC design hints (View = GUI, Controller = event handling, Model = task list)
