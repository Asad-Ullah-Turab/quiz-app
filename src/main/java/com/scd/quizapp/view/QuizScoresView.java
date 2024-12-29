package com.scd.quizapp.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.scd.quizapp.model.StudentScore;

public class QuizScoresView extends JFrame {
    public QuizScoresView(List<StudentScore> scores) {
        setTitle("Quiz Scores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = { "Student Name", "Score" };
        String[][] data = new String[scores.size()][2];

        for (int i = 0; i < scores.size(); i++) {
            data[i][0] = scores.get(i).getStudentName();
            data[i][1] = String.valueOf(scores.get(i).getScore());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }
}
