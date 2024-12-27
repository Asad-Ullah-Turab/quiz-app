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

        JLabel label = new JLabel("Please choose your portal:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(label, gbc);

        JButton studentButton = new JButton("Student");
        studentButton.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(studentButton, gbc);

        JButton teacherButton = new JButton("Teacher");
        teacherButton.setFocusable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(teacherButton, gbc);

        // Add action listeners for buttons
        studentButton.addActionListener(e -> mainViewController.displayStudentLogin());
        teacherButton.addActionListener(e -> mainViewController.displayTeacherLogin());

        add(panel);
    }
}
