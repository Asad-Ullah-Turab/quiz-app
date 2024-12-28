package com.scd.quizapp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import com.scd.quizapp.controller.TeacherController;

public class TeacherView extends JFrame {
    private TeacherController teacherController;
    private JList<String> pastQuizzesList;
    private DefaultListModel<String> listModel;

    public TeacherView(TeacherController teacherController, int width, int height) {
        this.teacherController = teacherController;

        setTitle("Quiz App - Teacher Dashboard");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel headingLabel = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(headingLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10); // Reset insets for other components

        JLabel pastQuizzesLabel = new JLabel("Past Quizzes:");
        pastQuizzesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(pastQuizzesLabel, gbc);

        listModel = new DefaultListModel<>();
        pastQuizzesList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(pastQuizzesList);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        JButton createQuizButton = new JButton("Create New Quiz");
        createQuizButton.setFont(new Font("Arial", Font.PLAIN, 16));
        createQuizButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(createQuizButton, gbc);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(logoutButton, gbc);

        // Add action listeners for buttons
        createQuizButton.addActionListener(e -> this.teacherController.createQuiz());
        logoutButton.addActionListener(e -> this.teacherController.logout());

        add(panel);
    }

    public void setPastQuizzes(String[] quizzes) {
        // Clear the existing list of quizzes
        listModel.clear();
        // Add each quiz from the provided array to the list model
        for (String quiz : quizzes) {
            listModel.addElement(quiz);
        }
    }

    public void addQuizSelectionListener(ActionListener listener) {
        // Add a selection listener to the list of past quizzes
        pastQuizzesList.addListSelectionListener(e -> {
            // Check if the value is still adjusting
            if (!e.getValueIsAdjusting()) {
                // Trigger the provided ActionListener's actionPerformed method
                listener.actionPerformed(null);
            }
        });
    }

    public String getSelectedQuiz() {
        // Return the currently selected quiz from the list
        return pastQuizzesList.getSelectedValue();
    }
}