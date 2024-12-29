package com.scd.quizapp.controller;

import com.scd.quizapp.view.StudentView;
import com.scd.quizapp.model.Quiz;
import com.scd.quizapp.database.DatabaseManager;
import com.scd.quizapp.model.Student;

import java.util.List;

import javax.swing.JOptionPane;

public class StudentViewController {
    private StudentView studentView;
    private MainViewController mainViewController;
    private QuizAttemptController quizAttemptController;
    private Student student;

    public StudentViewController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void displayStudentDashboard() {
        studentView = new StudentView(this, 600, 400);
        studentView.setVisible(true);
        loadAvailableQuizzes();
        studentView.addQuizSelectionListener(e -> handleQuizSelection());
    }

    public void logout() {
        studentView.dispose();
        mainViewController.displayMainView();
    }

    private void loadAvailableQuizzes() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        List<Quiz> quizzes = databaseManager.loadQuizzesFromDatabase();
        studentView.setAvailableQuizzes(quizzes);
    }

    private void handleQuizSelection() {
        String selectedQuizTitle = studentView.getSelectedQuizTitle();
        if (selectedQuizTitle != null) {
            for (Quiz quiz : studentView.getAvailableQuizzes()) {
                if (quiz.getTitle().equals(selectedQuizTitle)) {
                    selectQuiz(quiz);
                    break;
                }
            }
        }
    }

    public void selectQuiz(Quiz quiz) {
        System.out.println("Selected quiz: " + quiz.getTitle());
    }

    public void attemptQuiz() {
        String selectedQuizTitle = studentView.getSelectedQuizTitle();
        if (selectedQuizTitle != null) {
            for (Quiz quiz : studentView.getAvailableQuizzes()) {
                if (quiz.getTitle().equals(selectedQuizTitle)) {
                    quizAttemptController = new QuizAttemptController(this, quiz);
                    quizAttemptController.displayQuizAttemptView();
                    studentView.dispose();
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(studentView, "Please select a quiz to attempt.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}