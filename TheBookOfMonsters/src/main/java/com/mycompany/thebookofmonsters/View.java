package com.mycompany.thebookofmonsters;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;

public class View {
    public static JTree tree;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Книга чудовищ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        tree = new JTree(new DefaultMutableTreeNode("Чудовища"));
        tree.setOpaque(false);

        JButton importBtn = new JButton("Импорт");
        JButton exportBtn = new JButton("Экспорт");

        JPanel panel = new JPanel(new BorderLayout());

        JPanel treePanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (Design.getBookImage() != null) {
                    g.drawImage(Design.getBookImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        treePanel.add(tree);
        JScrollPane treeScrollPane = new JScrollPane(treePanel);
        treeScrollPane.setOpaque(false);
        treeScrollPane.setPreferredSize(new Dimension(200, frame.getHeight())); // Ширина 200 пикселей (примерно треть от 600)

        JPanel infoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (Design.getBookImage() != null) {
                    g.drawImage(Design.getBookImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };;
        
        // Добавляем компоненты в панель
        panel.add(treeScrollPane, BorderLayout.WEST);
        panel.add(infoPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(220,210,200));
        importBtn.setBackground(new Color(220,210,200));
        exportBtn.setBackground(new Color(220,210,200));
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
                                InfoMonsterSheet.showInfo(monster, infoPanel);
                                break;
                            }
                        }
                    }
                } else {
                    infoPanel.removeAll();
                    infoPanel.revalidate();
                    infoPanel.repaint();
                }
            }
        });
        Design.setFontForAllComponents(frame, new Color(80, 40, 0));
        
        frame.setVisible(true);
    }
}
