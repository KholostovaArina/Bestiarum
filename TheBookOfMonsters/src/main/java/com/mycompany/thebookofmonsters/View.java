package com.mycompany.thebookofmonsters;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class View {
    public static JTree tree;
    public static JTextArea infoArea;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Книга чудовищ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Инициализация компонентов
        tree = new JTree(new DefaultMutableTreeNode("Чудовища"));
        infoArea = new JTextArea();
        infoArea.setEditable(false);

        JButton importBtn = new JButton("Импорт");
        JButton exportBtn = new JButton("Экспорт");

        // Компоновка интерфейса
        JPanel panel = new JPanel(new BorderLayout());

        // Создаем JScrollPane для JTree и задаем предпочтительный размер
        JScrollPane treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setPreferredSize(new Dimension(200, frame.getHeight())); // Ширина 200 пикселей (примерно треть от 600)

        // Добавляем компоненты в панель
        panel.add(treeScrollPane, BorderLayout.WEST);
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(importBtn);
        btnPanel.add(exportBtn);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}