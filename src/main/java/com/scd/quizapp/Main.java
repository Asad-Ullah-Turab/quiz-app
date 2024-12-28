package com.scd.quizapp;

import com.scd.quizapp.controller.MainViewController;
import com.scd.quizapp.database.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        MainViewController mainViewController = new MainViewController();
        mainViewController.displayMainView();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
    }
}