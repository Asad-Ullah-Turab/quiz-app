package com.scd.quizapp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import com.scd.quizapp.controller.TeacherController;
import com.scd.quizapp.model.Quiz;
import java.awt.event.ActionEvent;

import java.util.List;

public class TeacherView extends JFrame {
    private TeacherController teacherController;
    private JList<String> pastQuizzesList;
    private DefaultListModel<String> listModel;
    private List<Quiz> pastQuizzes;

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

        gbc.insets = new Insets(10, 10, 10, 10);

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
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(createQuizButton, gbc);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(logoutButton, gbc);

        createQuizButton.addActionListener(e -> this.teacherController.openQuizCreationView());
        logoutButton.addActionListener(e -> this.teacherController.logout());

        add(panel);
    }

    public void setPastQuizzes(List<Quiz> quizzes) {
        listModel.clear();
        this.pastQuizzes = quizzes;
        for (Quiz quiz : quizzes) {
            listModel.addElement(quiz.getTitle());
        }
    }

    public List<Quiz> getPastQuizzes() {
        return pastQuizzes;
    }

    public void addQuizSelectionListener(ActionListener listener) {
        pastQuizzesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                listener.actionPerformed(null);
            }
        });
    }

    public String getSelectedQuizTitle() {
        return pastQuizzesList.getSelectedValue();
    }
}