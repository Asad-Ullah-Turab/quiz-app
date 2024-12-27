package com.scd.quizapp.view;

import javax.swing.*;
import java.awt.*;
import com.scd.quizapp.controller.StudentLoginController;

public class StudentLoginView extends JFrame {

    public StudentLoginController studentLoginController;

    public StudentLoginView(StudentLoginController studentLoginController, int height, int width) {
        this.studentLoginController = studentLoginController;

        setTitle("Quiz App");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        JButton exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(exitButton, gbc);

        // Add action listeners for buttons
        loginButton.addActionListener(
                e -> studentLoginController.handleLogin(nameField.getText(), new String(passwordField.getPassword())));
        exitButton.addActionListener(e -> System.exit(0));

        add(panel);
    }
}
