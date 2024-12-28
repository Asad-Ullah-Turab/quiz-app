package com.scd.quizapp.controller;

import com.scd.quizapp.view.TeacherLoginView;

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
        System.out.println("Logging in the app using username: " + name + " password: " + password);
        teacherController = new TeacherController(mainViewController);
        teacherController.displayTeacherDashboard();
        teacherLoginView.dispose();
    }

    public void displayMainView() {
        mainViewController.displayMainView();
    }
}
