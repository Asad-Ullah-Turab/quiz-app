package com.scd.quizapp.view;

import javax.swing.*;
import java.awt.*;
import com.scd.quizapp.controller.StudentLoginController;

public class StudentLoginView extends JFrame {

    public StudentLoginController studentLoginController;

    public StudentLoginView(StudentLoginController studentLoginController, int height, int width) {
        this.studentLoginController = studentLoginController;

        setTitle("Quiz App - Student Login");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel headingLabel = new JLabel("Student Login", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 20, 10);
        panel.add(headingLabel, gbc);

        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        JButton exitButton = new JButton("Back");
        exitButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(exitButton, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(loginButton, gbc);

        loginButton.addActionListener(
                e -> this.studentLoginController.handleLogin(nameField.getText(),
                        new String(passwordField.getPassword())));
        exitButton.addActionListener(e -> {
            this.studentLoginController.displayMainView();
            dispose();
        });

        add(panel);
    }
}
