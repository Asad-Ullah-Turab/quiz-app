package com.scd.quizapp.controller;

import com.scd.quizapp.model.Quiz;
import com.scd.quizapp.view.QuizCreationView;

import javax.swing.*;

public class QuizCreationController {
    private QuizCreationView quizCreationView;
    private TeacherController teacherController;

    public QuizCreationController(TeacherController teacherController) {
        this.teacherController = teacherController;
    }

    public void displayQuizCreationView() {
        quizCreationView = new QuizCreationView(this);
        quizCreationView.setVisible(true);
    }

    public void saveQuiz(Quiz quiz) {
        if (quiz.getTitle() != null && !quiz.getTitle().trim().isEmpty() && !quiz.getQuestions().isEmpty()) {
            teacherController.addQuiz(quiz);
            System.out.println(quiz);
            quizCreationView.dispose();
        } else {
            JOptionPane.showMessageDialog(quizCreationView, "Quiz title and questions cannot be empty.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
