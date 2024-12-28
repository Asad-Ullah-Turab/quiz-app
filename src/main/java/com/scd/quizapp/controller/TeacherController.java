package com.scd.quizapp.controller;

import com.scd.quizapp.view.TeacherView;
import com.scd.quizapp.model.Quiz;
import com.scd.quizapp.model.Question;

import java.util.ArrayList;
import java.util.List;

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
        teacherView.addQuizSelectionListener(e -> handleQuizSelection());
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
        List<Quiz> pastQuizzes = new ArrayList<>();
        pastQuizzes.add(new Quiz("Quiz 1 SCD", new ArrayList<Question>()));
        pastQuizzes.add(new Quiz("Quiz 2 VP", new ArrayList<Question>()));
        pastQuizzes.add(new Quiz("Quiz 3 JAVA", new ArrayList<Question>()));
        teacherView.setPastQuizzes(pastQuizzes);
    }

    public void selectQuiz(Quiz quiz) {
        // Logic to handle quiz selection
        System.out.println("Selected quiz: " + quiz.getTitle());
    }

    private void handleQuizSelection() {
        String selectedQuizTitle = teacherView.getSelectedQuizTitle();
        if (selectedQuizTitle != null) {
            // Find the selected quiz by title
            for (Quiz quiz : teacherView.getPastQuizzes()) {
                if (quiz.getTitle().equals(selectedQuizTitle)) {
                    selectQuiz(quiz);
                    break;
                }
            }
        }
    }
}