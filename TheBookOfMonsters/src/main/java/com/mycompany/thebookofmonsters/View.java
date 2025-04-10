package com.mycompany.thebookofmonsters;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
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
        infoArea.setLineWrap(true); // Включаем перенос строк
        infoArea.setWrapStyleWord(true); // Перенос по словам

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

        // Добавляем слушатель для выбора узла в дереве
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) return;

                // Проверяем, является ли узел листом (монстром) и не корневым узлом
                if (selectedNode.isLeaf() && selectedNode.getParent() != null && !selectedNode.getParent().toString().equals("Чудовища")) {
                    String monsterName = selectedNode.toString();
                    String format = selectedNode.getParent().toString(); // Формат (xml, yaml, json)

                    // Получаем список монстров для данного формата из Controller.storage
                    java.util.List<Monster> monsters = Controller.getStorage().getMonstersByFormat(format);
                    if (monsters != null) {
                        // Ищем монстра по имени
                        for (Monster monster : monsters) {
                            if (monster.getName().equals(monsterName)) {
                                // Формируем строку с характеристиками монстра
                                StringBuilder details = new StringBuilder();
                                details.append("Имя: ").append(monster.getName()).append("\n");
                                details.append("Описание: ").append(monster.getDescription()).append("\n");
                                details.append("Уровень опасности: ").append(monster.getDangerLevel()).append("\n");
                                details.append("Место обитания: ").append(monster.getHabitat()).append("\n");
                                details.append("Первое упоминание: ").append(monster.getFirstMention()).append("\n");
                                details.append("Иммунитеты: ").append(monster.getImmunities()).append("\n");
                                details.append("Активность: ").append(monster.getActivity()).append("\n");
                                details.append("Рост: ").append(monster.getHeight()).append("\n");
                                details.append("Вес: ").append(monster.getWeight()).append("\n");
                                details.append("Рецепт: ").append(monster.getRecipe()).append("\n");
                                details.append("Время приготовления: ").append(monster.getTime()).append(" мин\n");
                                details.append("Эффективность: ").append(monster.getEfficiency()).append("\n");
                                details.append("Уязвимость: ").append(monster.getVulnerability()).append("\n");

                                // Обновляем содержимое infoArea
                                infoArea.setText(details.toString());
                                break;
                            }
                        }
                    }
                } else {
                    // Если выбран не монстр (например, формат или корень), очищаем infoArea
                    infoArea.setText("");
                }
            }
        });

        frame.setVisible(true);
    }
}