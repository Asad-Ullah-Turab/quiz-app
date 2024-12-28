package com.scd.quizapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.scd.quizapp.model.Quiz;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/quizapp";
    private static final String USER = "root";
    private static final String PASSWORD = "2004";

    private static DatabaseManager instance;
    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveQuizToDatabase(Quiz quiz) {
        try {
            connection.setAutoCommit(false);

            String quizQuery = "INSERT INTO quizzes (title) VALUES (?)";
            PreparedStatement quizStmt = connection.prepareStatement(quizQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            quizStmt.setString(1, quiz.getTitle());
            quizStmt.executeUpdate();

            var generatedKeys = quizStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int quizId = generatedKeys.getInt(1);

                String questionQuery = "INSERT INTO questions (quiz_id, question_text, correct_answer) VALUES (?, ?, ?)";
                PreparedStatement questionStmt = connection.prepareStatement(questionQuery,
                        PreparedStatement.RETURN_GENERATED_KEYS);

                for (var question : quiz.getQuestions()) {
                    questionStmt.setInt(1, quizId);
                    questionStmt.setString(2, question.getQuestionText());
                    questionStmt.setString(3, question.getCorrectAnswer());
                    questionStmt.executeUpdate();

                    var questionKeys = questionStmt.getGeneratedKeys();
                    if (questionKeys.next()) {
                        int questionId = questionKeys.getInt(1);

                        String optionQuery = "INSERT INTO options (question_id, option_text) VALUES (?, ?)";
                        PreparedStatement optionStmt = connection.prepareStatement(optionQuery);
                        for (var option : question.getOptions()) {
                            optionStmt.setInt(1, questionId);
                            optionStmt.setString(2, option);
                            optionStmt.executeUpdate();
                        }
                    }
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}