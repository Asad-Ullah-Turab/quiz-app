package com.scd.quizapp.view;

import javax.swing.*;
import java.awt.*;
import com.scd.quizapp.controller.MainViewController;

public class MainView extends JFrame {
    MainViewController mainViewController;

    public MainView(MainViewController mainViewController, int width, int height) {
        this.mainViewController = mainViewController;

        setTitle("Quiz App");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Welcome to the Quiz App", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(titleLabel, gbc);

        JLabel label = new JLabel("Please choose your portal", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        panel.add(label, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);

        JButton studentButton = new JButton("Student");
        studentButton.setFont(new Font("Arial", Font.PLAIN, 16));
        studentButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(studentButton, gbc);

        JButton teacherButton = new JButton("Teacher");
        teacherButton.setFont(new Font("Arial", Font.PLAIN, 16));
        teacherButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(teacherButton, gbc);

        studentButton.addActionListener(e -> mainViewController.displayStudentLogin());
        teacherButton.addActionListener(e -> mainViewController.displayTeacherLogin());

        add(panel);
    }
}
