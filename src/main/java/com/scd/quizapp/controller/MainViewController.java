package com.scd.quizapp.controller;

import com.scd.quizapp.view.MainView;

public class MainViewController {
    MainView mainView;
    StudentLoginController studentLoginController;
    TeacherLoginController teacherLoginController;

    public void displayMainView() {
        mainView = new MainView(this, 400, 400);
        mainView.setVisible(true);
    }

    public void displayStudentLogin() {
        studentLoginController = new StudentLoginController(this);
        studentLoginController.displayLoginScreen();
        mainView.dispose();
    }

    public void displayTeacherLogin() {
        teacherLoginController = new TeacherLoginController(this);
        teacherLoginController.displayLoginScreen();
        mainView.dispose();
    }
}
