package com.scd.quizapp.controller;

import com.scd.quizapp.view.TeacherView;
import com.scd.quizapp.model.Quiz;
import com.scd.quizapp.database.DatabaseManager;
import com.scd.quizapp.model.StudentScore;
import com.scd.quizapp.view.QuizScoresView;

import java.util.List;

public class TeacherController {
    private TeacherView teacherView;
    private MainViewController mainViewController;
    private QuizCreationController quizCreationController;

    public TeacherController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void displayTeacherDashboard() {
        teacherView = new TeacherView(this, 600, 400);
        teacherView.setVisible(true);
        loadPastQuizzes();
        teacherView.addQuizSelectionListener(e -> handleQuizSelection());
    }

    public void logout() {
        teacherView.dispose();
        mainViewController.displayMainView();
    }

    private void loadPastQuizzes() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        List<Quiz> quizzes = databaseManager.loadQuizzesFromDatabase();
        teacherView.setPastQuizzes(quizzes);
    }

    private void handleQuizSelection() {
        String selectedQuizTitle = teacherView.getSelectedQuizTitle();
        if (selectedQuizTitle != null) {
            for (Quiz quiz : teacherView.getPastQuizzes()) {
                if (quiz.getTitle().equals(selectedQuizTitle)) {
                    selectQuiz(quiz);
                    break;
                }
            }
        }
    }

    public void selectQuiz(Quiz quiz) {
        System.out.println("Selected quiz: " + quiz.getTitle());
        displayQuizScores(quiz.getId());
    }

    private void displayQuizScores(int quizId) {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        List<StudentScore> scores = dbManager.getQuizScores(quizId);
        QuizScoresView quizScoresView = new QuizScoresView(scores);
        quizScoresView.setVisible(true);
    }

    public void openQuizCreationView() {
        quizCreationController = new QuizCreationController(this);
        quizCreationController.displayQuizCreationView();
    }

    public void addQuiz(Quiz quiz) {
        teacherView.getPastQuizzes().add(quiz);
        teacherView.setPastQuizzes(teacherView.getPastQuizzes());
    }
}