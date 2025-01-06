package com.scd.quizapp.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.scd.quizapp.controller.QuizCreationController;
import com.scd.quizapp.model.Question;
import com.scd.quizapp.model.Quiz;

public class QuizCreationView extends JFrame {
    private JTextField quizTitleField;
    private JTextField questionField;
    private JTextField correctAnswerField;
    private DefaultListModel<String> optionsListModel;
    private JList<String> optionsList;
    private JTextField optionField;
    private JButton addOptionButton;
    private JButton saveQuestionButton;
    private JButton saveQuizButton;
    private JButton cancelButton;
    private QuizCreationController quizCreationController;
    private List<Question> questions;

    public QuizCreationView(QuizCreationController quizCreationController) {
        this.quizCreationController = quizCreationController;
        this.questions = new ArrayList<>();

        setTitle("Create New Quiz");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Quiz Title:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        quizTitleField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(quizTitleField, gbc);

        JLabel questionLabel = new JLabel("Question:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(questionLabel, gbc);

        questionField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(questionField, gbc);

        JLabel correctAnswerLabel = new JLabel("Correct Answer:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(correctAnswerLabel, gbc);

        correctAnswerField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(correctAnswerField, gbc);

        JLabel optionsLabel = new JLabel("Options:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(optionsLabel, gbc);

        optionsListModel = new DefaultListModel<>();
        optionsList = new JList<>(optionsListModel);
        JScrollPane scrollPane = new JScrollPane(optionsList);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        optionField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(optionField, gbc);

        addOptionButton = new JButton("Add Option");
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(addOptionButton, gbc);

        saveQuestionButton = new JButton("Save Question");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(saveQuestionButton, gbc);

        saveQuizButton = new JButton("Save Quiz");
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(saveQuizButton, gbc);

        cancelButton = new JButton("Cancel");
        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(cancelButton, gbc);

        addOptionButton.addActionListener(e -> addOption());
        saveQuestionButton.addActionListener(e -> saveQuestion());
        saveQuizButton.addActionListener(e -> saveQuiz());
        cancelButton.addActionListener(e -> dispose());

        add(panel);
    }

    private void addOption() {
        String option = optionField.getText();
        if (option != null && !option.trim().isEmpty()) {
            optionsListModel.addElement(option);
            optionField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Option cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveQuestion() {
        String questionText = questionField.getText();
        String correctAnswer = correctAnswerField.getText();
        List<String> options = new ArrayList<>();
        for (int i = 0; i < optionsListModel.size(); i++) {
            options.add(optionsListModel.getElementAt(i));
        }

        if (questionText != null && !questionText.trim().isEmpty() && correctAnswer != null
                && !correctAnswer.trim().isEmpty() && !options.isEmpty()) {
            questions.add(new Question(questionText, options, correctAnswer));
            questionField.setText("");
            correctAnswerField.setText("");
            optionsListModel.clear();
        } else {
            JOptionPane.showMessageDialog(this, "All fields must be filled and at least one option must be added.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveQuiz() {
        String quizTitle = quizTitleField.getText();
        Quiz quiz = new Quiz(0, quizTitle, questions);
        quizCreationController.saveQuiz(quiz);
    }
}
