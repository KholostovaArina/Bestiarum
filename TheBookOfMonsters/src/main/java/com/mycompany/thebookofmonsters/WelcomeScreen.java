package com.mycompany.thebookofmonsters;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen {
    private static JFrame frame;
    private static JButton startButton;

    public static void showPreview() {
        frame = new JFrame("Привет пользователь!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

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

        startButton = new JButton("OK");
        startButton.setBackground(View.bezheviy);
        startButton.setFont(Design.getBigFont());

        mainPanel.add(text, BorderLayout.CENTER);
        mainPanel.add(startButton, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static JButton getStartButton() {
        return startButton;
    }
    
    public static JFrame getStartFrame(){
        return frame;
    }

    public static void close() {
        frame.dispose();
    }
}