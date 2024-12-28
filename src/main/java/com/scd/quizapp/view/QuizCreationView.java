package com.scd.quizapp.view;

import javax.swing.*;

import com.scd.quizapp.controller.QuizCreationController;

import java.awt.*;

public class QuizCreationView extends JFrame {
    private JTextField quizTitleField;
    private JButton saveButton;
    private JButton cancelButton;
    private QuizCreationController quizCreationController;

    public QuizCreationView(QuizCreationController quizCreationController) {

        this.quizCreationController = quizCreationController;

        setTitle("Create New Quiz");
        setSize(400, 300);
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

        saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(saveButton, gbc);

        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(cancelButton, gbc);

        saveButton.addActionListener(e -> this.quizCreationController.saveQuiz(quizTitleField.getText()));
        cancelButton.addActionListener(e -> dispose());

        add(panel);
    }
}
