package com.mycompany.thebookofmonsters;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen {

    public static JButton btnStart;
    public static JFrame frameStart;

    public static void showPreview() {
        frameStart = new JFrame("Привет пользователь!");
        frameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameStart.setSize(400, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(View.brown);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(70, 30, 30, 30));

        JTextArea text = new JTextArea("Выберите, пожалуйста, текстовый файл, из которого будут импортированы чудовища");
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setFont(Design.getFont().deriveFont(22f));
        text.setOpaque(false);
        text.setForeground(Color.WHITE);

        btnStart = new JButton("OK");
        btnStart.setBackground(View.bezheviy);
        btnStart.setFont(Design.getBigFont());

        mainPanel.add(text, BorderLayout.CENTER);
        mainPanel.add(btnStart, BorderLayout.SOUTH);

        frameStart.add(mainPanel);
        frameStart.setLocationRelativeTo(null);
        frameStart.setVisible(true);
    }
}
