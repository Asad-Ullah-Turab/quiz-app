package com.scd.quizapp.controller;

import com.scd.quizapp.view.TeacherLoginView;

import javax.swing.JOptionPane;

import com.scd.quizapp.database.DatabaseManager;

public class TeacherLoginController {
    TeacherLoginView teacherLoginView;
    MainViewController mainViewController;
    TeacherController teacherController;

    public TeacherLoginController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void displayLoginScreen() {
        teacherLoginView = new TeacherLoginView(this, 400, 400);
        teacherLoginView.setVisible(true);
    }

    public void handleLogin(String name, String password) {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        if (dbManager.validateTeacherLogin(name, password)) {
            System.out.println("Teacher login successful.");
            teacherController = new TeacherController(mainViewController);
            teacherController.displayTeacherDashboard();
            teacherLoginView.dispose();
        } else {
            System.out.println("Invalid teacher credentials.");
            JOptionPane.showMessageDialog(null, "Invalid login credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayMainView() {
        mainViewController.displayMainView();
    }
}
