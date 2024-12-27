package com.scd.quizapp.controller;

import com.scd.quizapp.view.MainView;

public class MainViewController {
    MainView mainView;
    StudentLoginController studentLoginController;

    public void DisplayMainScreen() {
        mainView = new MainView(this, 400, 400);
        mainView.setVisible(true);

        studentLoginController = new StudentLoginController();
    }

    public void displayStudentLogin() {
        studentLoginController.displayLoginScreen();
        mainView.dispose();
    }

    public void displayTeacherLogin() {
        studentLoginController.displayLoginScreen();
        mainView.dispose();
    }
}
