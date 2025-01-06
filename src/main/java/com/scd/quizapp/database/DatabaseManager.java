package com.scd.quizapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import com.scd.quizapp.model.Quiz;
import com.scd.quizapp.model.Question;
import com.scd.quizapp.model.Student;
import com.scd.quizapp.model.StudentScore;

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

    public List<Quiz> loadQuizzesFromDatabase() {
        List<Quiz> quizzes = new ArrayList<>();
        String quizQuery = "SELECT * FROM quizzes";
        String questionQuery = "SELECT * FROM questions WHERE quiz_id = ?";
        String optionQuery = "SELECT * FROM options WHERE question_id = ?";

        try (Statement quizStmt = connection.createStatement();
                ResultSet quizRs = quizStmt.executeQuery(quizQuery)) {

            while (quizRs.next()) {
                int quizId = quizRs.getInt("id");
                String quizTitle = quizRs.getString("title");

                List<Question> questions = new ArrayList<>();
                try (PreparedStatement questionStmt = connection.prepareStatement(questionQuery)) {
                    questionStmt.setInt(1, quizId);
                    try (ResultSet questionRs = questionStmt.executeQuery()) {
                        while (questionRs.next()) {
                            int questionId = questionRs.getInt("id");
                            String questionText = questionRs.getString("question_text");
                            String correctAnswer = questionRs.getString("correct_answer");

                            List<String> options = new ArrayList<>();
                            try (PreparedStatement optionStmt = connection.prepareStatement(optionQuery)) {
                                optionStmt.setInt(1, questionId);
                                try (ResultSet optionRs = optionStmt.executeQuery()) {
                                    while (optionRs.next()) {
                                        String optionText = optionRs.getString("option_text");
                                        options.add(optionText);
                                    }
                                }
                            }

                            questions.add(new Question(questionText, options, correctAnswer));
                        }
                    }
                }

                quizzes.add(new Quiz(quizId, quizTitle, questions));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quizzes;
    }

    public boolean validateTeacherLogin(String name, String password) {
        String query = "SELECT * FROM teachers WHERE name = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateStudentLogin(String name, String password) {
        String query = "SELECT * FROM students WHERE name = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveStudentQuizScore(int studentId, int quizId, int score) {
        String query = "INSERT INTO student_scores (student_id, quiz_id, score) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, quizId);
            stmt.setInt(3, score);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StudentScore> getQuizScores(int quizId) {
        List<StudentScore> scores = new ArrayList<>();
        String query = "SELECT s.name, ss.score FROM student_scores ss JOIN students s ON ss.student_id = s.id WHERE ss.quiz_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quizId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String studentName = rs.getString("name");
                    int score = rs.getInt("score");
                    scores.add(new StudentScore(studentName, score));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public Student getStudentByName(String name) {
        String query = "SELECT * FROM students WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String password = rs.getString("password");
                    return new Student(id, name, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}