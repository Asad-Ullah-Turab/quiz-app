# Quiz Application

This is a Java Swing-based quiz application developed using the MVC architecture. The application provides two distinct portals:

- **Teacher Portal**: Allows teachers to log in, create quizzes, and view past quizzes.
- **Student Portal**: Enables students to log in, attempt available quizzes, and view their scores.

## Features

### Teacher Portal

- Secure login for teachers.
- Create new quizzes with multiple questions and options.
- View student responses and scores for completed quizzes.

### Student Portal

- Secure login for students.
- View available quizzes assigned by teachers.
- Attempt quizzes and submit answers.
- View scores and past performance.

## Prerequisites

- **Java 17** or higher
- **Maven** for build and dependency management

## Getting Started

### Building the Project

To build the project, run the following command in the project root directory:

```sh
mvn clean install
```

### Running the Application

You can run the application directly using the Java extension pack in Visual Studio Code:

1. Open the `Main.java` file located in the `src/main/java/com/scd/quizapp` directory.
2. Use the "Run" button provided by the extension to start the application.

Alternatively, you can use the following command in the terminal:

```sh
java -cp target/quiz-application-1.0-SNAPSHOT.jar com.scd.quizapp.Main
```

## Project Structure

```
quiz-app
├── .gitignore
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── scd
│   │   │           └── quizapp
│   │   │               ├── controller
│   │   │               │   ├── LoginController.java
│   │   │               │   ├── QuizController.java
│   │   │               │   ├── StudentController.java
│   │   │               │   └── TeacherController.java
│   │   │               ├── model
│   │   │               │   ├── Quiz.java
│   │   │               │   ├── Student.java
│   │   │               │   └── Teacher.java
│   │   │               └── view
│   │   │                   ├── LoginView.java
│   │   │                   ├── MainView.java
│   │   │                   ├── QuizView.java
│   │   │                   ├── StudentView.java
│   │   │                   └── TeacherView.java
│   └── test
│       └── java
└── target
```

## References

This project is referenced and inspired by:
[https://github.com/zafir100100/QuizApp-Java](https://github.com/zafir100100/QuizApp-Java)

## Future Enhancements

- **Authentication Improvements**: Add password encryption for enhanced security.
- **Reporting**: Include detailed analytics and progress reports for teachers.
- **Quiz Timer**: Implement a timer for quizzes.
- **Database Expansion**: Allow multiple-choice and true/false question types.

## Database Query

```
CREATE DATABASE if not exists quizapp;
USE quizapp;

CREATE TABLE quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL
);

CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT,
    question_text TEXT,
    correct_answer VARCHAR(255),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE RESTRICT
);

CREATE TABLE options (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    option_text VARCHAR(255),
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

CREATE TABLE teachers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE student_scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    quiz_id INT,
    score INT,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE
);
```
