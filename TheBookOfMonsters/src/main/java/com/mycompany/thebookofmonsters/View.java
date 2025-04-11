package com.mycompany.thebookofmonsters;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;

public class View {
    public static JTree tree;
    public static Color bezheviy = new Color(220, 210, 200);
    
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Книга чудовищ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 600);

        tree = new JTree(new DefaultMutableTreeNode("Чудовища"));
        tree.setOpaque(false);

        JButton importBtn = new JButton("Импорт");
        JButton exportBtn = new JButton("Экспорт");

        JPanel panel = new JPanel(new BorderLayout());

        JPanel treePanel = new JPanel() {
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
        treePanel.setBorder(BorderFactory.createEmptyBorder(35, 20, 20, 20));
        treeScrollPane.setOpaque(false);
        treeScrollPane.setPreferredSize(new Dimension(250, frame.getHeight())); // Ширина 200 пикселей (примерно треть от 600)

        JPanel infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (Design.getBookImage() != null) {
                    g.drawImage(Design.getBookImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panel.add(treeScrollPane, BorderLayout.WEST);
        panel.add(infoPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(80, 40, 0));
        importBtn.setBackground(bezheviy);
        exportBtn.setBackground(bezheviy);
        btnPanel.add(importBtn);
        btnPanel.add(exportBtn);
        exportBtn.addActionListener(e -> {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();

            JDialog exportDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(exportBtn),
                    "Выбор формата для экспорта", true);
            exportDialog.setLayout(new BorderLayout());
            exportDialog.setSize(300, 200);

            JPanel formatsPanel = new JPanel();
            formatsPanel.setLayout(new BoxLayout(formatsPanel, BoxLayout.Y_AXIS));
            JScrollPane scrollPane = new JScrollPane(formatsPanel);

            // Добавляем только узлы-форматы (детей корневого узла)
            for (int i = 0; i < root.getChildCount(); i++) {
                DefaultMutableTreeNode formatNode = (DefaultMutableTreeNode) root.getChildAt(i);
                JButton formatButton = new JButton(formatNode.toString());
                formatButton.setAlignmentX(Component.LEFT_ALIGNMENT);
                formatButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, formatButton.getPreferredSize().height));

                formatButton.addActionListener(ev -> {
                    //TODO Здесь будет логика экспорта выбранного формата
                    JOptionPane.showMessageDialog(exportDialog,
                            "Экспорт формата: " + formatNode.toString(),
                            "Экспорт", JOptionPane.INFORMATION_MESSAGE);
                    exportDialog.dispose();
                });

                formatsPanel.add(formatButton);
            }

            // Кнопка отмены
            JButton cancelButton = new JButton("Отмена");
            cancelButton.addActionListener(ev -> exportDialog.dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cancelButton);

            exportDialog.add(scrollPane, BorderLayout.CENTER);
            exportDialog.add(buttonPanel, BorderLayout.SOUTH);
            exportDialog.setLocationRelativeTo(exportBtn);
            Design.setFontForAllComponents(exportDialog);
            buttonPanel.setForeground(bezheviy);
            exportDialog.setVisible(true);
        });

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
                    String format = selectedNode.getParent().toString();

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
        Design.setFontForAllComponents(frame);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    // Вспомогательный метод для добавления узлов в диалог
}
