package com.scd.quizapp.controller;

import com.scd.quizapp.model.Quiz;
import com.scd.quizapp.model.Question;
import com.scd.quizapp.view.QuizCreationView;

import javax.swing.*;
import java.util.ArrayList;

public class QuizCreationController {
    private QuizCreationView quizCreationView;
    private TeacherController teacherController;

    public QuizCreationController(TeacherController teacherController) {
        this.teacherController = teacherController;
    }

    public void displayQuizCreationView() {
        this.quizCreationView = new QuizCreationView(this);
        quizCreationView.setVisible(true);
    }

    public void saveQuiz(String quizTitle) {
        if (quizTitle != null && !quizTitle.trim().isEmpty()) {
            Quiz newQuiz = new Quiz(quizTitle, new ArrayList<Question>());
            // Save the new quiz (add to the list of past quizzes for now)
            teacherController.addQuiz(newQuiz);
            quizCreationView.dispose();
        } else {
            JOptionPane.showMessageDialog(quizCreationView, "Quiz title cannot be empty.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
