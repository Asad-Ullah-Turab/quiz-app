package com.scd.quizapp.view;

import javax.swing.*;
import java.awt.*;
import com.scd.quizapp.controller.QuizAttemptController;
import com.scd.quizapp.model.Question;
import java.util.List;

public class QuizAttemptView extends JFrame {
    private QuizAttemptController quizAttemptController;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton nextButton;
    private JButton submitButton;
    private int currentQuestionIndex;

    public QuizAttemptView(QuizAttemptController quizAttemptController, List<Question> questions, int width,
            int height) {
        this.quizAttemptController = quizAttemptController;
        this.currentQuestionIndex = 0;

        setTitle("Attempt Quiz");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(questionLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);

        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 2;
            panel.add(optionButtons[i], gbc);
            buttonGroup.add(optionButtons[i]);
        }

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 16));
        nextButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nextButton, gbc);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 16));
        submitButton.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(submitButton, gbc);

        nextButton.addActionListener(e -> this.quizAttemptController.nextQuestion());
        submitButton.addActionListener(e -> this.quizAttemptController.submitQuiz());

        add(panel);
    }

    public void displayQuestion(Question question) {
        questionLabel.setText(question.getQuestionText());
        List<String> options = question.getOptions();
        for (int i = 0; i < optionButtons.length; i++) {
            if (i < options.size()) {
                optionButtons[i].setText(options.get(i));
                optionButtons[i].setVisible(true);
            } else {
                optionButtons[i].setVisible(false);
            }
        }
        buttonGroup.clearSelection();
    }

    public String getSelectedOption() {
        for (JRadioButton optionButton : optionButtons) {
            if (optionButton.isSelected()) {
                return optionButton.getText();
            }
        }
        return null;
    }

    public void setNextButtonEnabled(boolean enabled) {
        nextButton.setEnabled(enabled);
    }

    public void setSubmitButtonEnabled(boolean enabled) {
        submitButton.setEnabled(enabled);
    }
}