package com.scd.quizapp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import com.scd.quizapp.controller.StudentViewController;
import com.scd.quizapp.model.Quiz;
import java.util.List;

public class StudentView extends JFrame {
    private StudentViewController studentViewController;
    private JList<String> availableQuizzesList;
    private DefaultListModel<String> listModel;
    private List<Quiz> availableQuizzes;

    public StudentView(StudentViewController studentViewController, int width, int height) {
        this.studentViewController = studentViewController;

        setTitle("Quiz App - Student Dashboard");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel headingLabel = new JLabel("Student Dashboard", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(headingLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel availableQuizzesLabel = new JLabel("Available Quizzes:");
        availableQuizzesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(availableQuizzesLabel, gbc);

        listModel = new DefaultListModel<>();
        availableQuizzesList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(availableQuizzesList);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        JButton attemptQuizButton = new JButton("Attempt Quiz");
        attemptQuizButton.setFont(new Font("Arial", Font.PLAIN, 16));
        attemptQuizButton.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(attemptQuizButton, gbc);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(logoutButton, gbc);

        attemptQuizButton.addActionListener(e -> this.studentViewController.attemptQuiz());
        logoutButton.addActionListener(e -> this.studentViewController.logout());

        add(panel);
    }

    public void setAvailableQuizzes(List<Quiz> quizzes) {
        listModel.clear();
        this.availableQuizzes = quizzes;
        for (Quiz quiz : quizzes) {
            listModel.addElement(quiz.getTitle());
        }
    }

    public List<Quiz> getAvailableQuizzes() {
        return availableQuizzes;
    }

    public void addQuizSelectionListener(ActionListener listener) {
        availableQuizzesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listener.actionPerformed(null);
            }
        });
    }

    public String getSelectedQuizTitle() {
        return availableQuizzesList.getSelectedValue();
    }
}