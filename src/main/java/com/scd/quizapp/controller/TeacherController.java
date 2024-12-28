package com.scd.quizapp.controller;

import com.scd.quizapp.view.TeacherView;

public class TeacherController {
    private TeacherView teacherView;
    private MainViewController mainViewController;

    public TeacherController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void displayTeacherDashboard() {
        teacherView = new TeacherView(this, 600, 400);
        teacherView.setVisible(true);
        loadPastQuizzes();
    }

    public void createQuiz() {
        // Logic to create a new quiz
        System.out.println("Creating a new quiz...");
    }

    public void logout() {
        teacherView.dispose();
        mainViewController.displayMainView();
    }

    private void loadPastQuizzes() {
        // Load past quizzes from the model (dummy data for now)
        String[] pastQuizzes = { "Quiz 1", "Quiz 2", "Quiz 3" };
        teacherView.setPastQuizzes(pastQuizzes);
    }
}