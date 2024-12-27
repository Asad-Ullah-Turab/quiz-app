package com.scd.quizapp.controller;

import com.scd.quizapp.view.StudentLoginView;

public class StudentLoginController {
    StudentLoginView studentLoginView;

    public void displayLoginScreen() {
        studentLoginView = new StudentLoginView(this, 400, 400);
        studentLoginView.setVisible(true);
    }

    public void handleLogin(String name, String password) {
        System.out.println("Logging in the app using username: " + name + " password: " + password);
    }
}
