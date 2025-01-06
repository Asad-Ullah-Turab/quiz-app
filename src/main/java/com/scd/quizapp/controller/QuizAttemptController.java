package com.scd.quizapp.controller;

import com.scd.quizapp.view.QuizAttemptView;
import com.scd.quizapp.model.Question;
import com.scd.quizapp.model.Quiz;
import com.scd.quizapp.database.DatabaseManager;
import com.scd.quizapp.model.Student;

import javax.swing.*;
import java.util.List;

public class QuizAttemptController {
    private QuizAttemptView quizAttemptView;
    private StudentViewController studentViewController;
    private Quiz quiz;
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;

    public QuizAttemptController(StudentViewController studentViewController, Quiz quiz) {
        this.studentViewController = studentViewController;
        this.quiz = quiz;
        this.questions = quiz.getQuestions();
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public void displayQuizAttemptView() {
        quizAttemptView = new QuizAttemptView(this, questions, 600, 400);
        quizAttemptView.setVisible(true);
        displayCurrentQuestion();
    }

    public void nextQuestion() {
        String selectedOption = quizAttemptView.getSelectedOption();
        if (selectedOption != null) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            if (selectedOption.equals(currentQuestion.getCorrectAnswer())) {
                score++;
            }
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                displayCurrentQuestion();
            } else {
                quizAttemptView.setNextButtonEnabled(false);
                quizAttemptView.setSubmitButtonEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(quizAttemptView, "Please select an option.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void submitQuiz() {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        Student student = studentViewController.getStudent();
        if (student != null) {
            int studentId = student.getId();
            dbManager.saveStudentQuizScore(studentId, quiz.getId(), score);

            JOptionPane.showMessageDialog(quizAttemptView,
                    "Quiz submitted! Your score: " + score + "/" + questions.size(),
                    "Quiz Completed", JOptionPane.INFORMATION_MESSAGE);
            quizAttemptView.dispose();
            studentViewController.displayStudentDashboard();
        } else {
            JOptionPane.showMessageDialog(quizAttemptView, "Error retrieving student information.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        quizAttemptView.displayQuestion(currentQuestion);
        quizAttemptView.setNextButtonEnabled(true);
        quizAttemptView.setSubmitButtonEnabled(false);
    }
}