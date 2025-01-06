package com.scd.quizapp.controller;

import com.scd.quizapp.view.StudentLoginView;

import javax.swing.JOptionPane;

import com.scd.quizapp.database.DatabaseManager;
import com.scd.quizapp.model.Student;

public class StudentLoginController {
    StudentLoginView studentLoginView;
    MainViewController mainViewController;
    StudentViewController studentViewController;

    public StudentLoginController(MainViewController mainViewController) {
        this.mainViewController = mainViewController;
    }

    public void displayLoginScreen() {
        studentLoginView = new StudentLoginView(this, 400, 400);
        studentLoginView.setVisible(true);
    }

    public void handleLogin(String name, String password) {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        if (dbManager.validateStudentLogin(name, password)) {
            System.out.println("Student login successful.");
            studentViewController = new StudentViewController(mainViewController);
            Student student = dbManager.getStudentByName(name);
            studentViewController.setStudent(student);
            studentViewController.displayStudentDashboard();
            studentLoginView.dispose();
        } else {
            System.out.println("Invalid student credentials.");
            JOptionPane.showMessageDialog(null, "Invalid login credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayMainView() {
        mainViewController.displayMainView();
    }
}
