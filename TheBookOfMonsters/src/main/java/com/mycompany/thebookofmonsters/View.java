package com.mycompany.thebookofmonsters;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;

public class View {

    public static JTree tree;
    public static Color bezheviy = new Color(220, 210, 200);
    public static JButton importBtn;
    public static JButton exportBtn;
    public static JButton exportAllBtn;
    //public static JButton dopBtn;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Книга чудовищ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 600);

        tree = new JTree(new DefaultMutableTreeNode("Чудовища"));
        tree.setOpaque(false);

        importBtn = new JButton("Импорт");
        exportBtn = new JButton("Экспорт");
        exportAllBtn = new JButton("Экспорт всех форматов");
        //dopBtn = new JButton("Добавить чудовищ");
        

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
        treePanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 0));
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
       // dopBtn.setBackground(bezheviy);
        exportAllBtn.setBackground(bezheviy);
        
        //btnPanel.add(dopBtn);
        btnPanel.add(importBtn);
        btnPanel.add(exportBtn);
        btnPanel.add(exportAllBtn);

        exportAllBtn.addActionListener(ev -> {
            JDialog exportDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(exportBtn),
                    "Выбор формата для экспорта", true);
            try {
                FileExporter.exportAllFormats(Controller.getStorage());
                JOptionPane.showMessageDialog(exportDialog,
                        "Все форматы успешно экспортированы",
                        "Экспорт завершен", JOptionPane.INFORMATION_MESSAGE);
            } catch (HeadlessException ex) {
                JOptionPane.showMessageDialog(exportDialog,
                        "Ошибка при экспорте: " + ex.getMessage(),
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            exportDialog.dispose();
        });

        exportBtn.addActionListener(e -> {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();

            JDialog exportDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(exportBtn),
                    "Выбор формата для экспорта", true);
            exportDialog.setLayout(new BorderLayout());
            exportDialog.setSize(300, 200);

            JPanel formatsPanel = new JPanel();
            formatsPanel.setLayout(new BoxLayout(formatsPanel, BoxLayout.Y_AXIS));
            JScrollPane scrollPane = new JScrollPane(formatsPanel);

            for (int i = 0; i < root.getChildCount(); i++) {
                DefaultMutableTreeNode formatNode = (DefaultMutableTreeNode) root.getChildAt(i);
                JButton formatButton = new JButton(formatNode.toString());
                formatButton.setAlignmentX(Component.LEFT_ALIGNMENT);
                formatButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, formatButton.getPreferredSize().height));

                formatButton.addActionListener(ev -> {
                    try {
                        FileExporter.exportFormat(formatNode.toString(), Controller.getStorage());
                        JOptionPane.showMessageDialog(exportDialog,
                                "Данные успешно экспортированы в формате " + formatNode.toString(),
                                "Экспорт завершен", JOptionPane.INFORMATION_MESSAGE);
                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(exportDialog,
                                "Ошибка при экспорте: " + ex.getMessage(),
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                    exportDialog.dispose();
                });

                formatsPanel.add(formatButton);
            }

            JButton cancelButton = new JButton("Отмена");
            cancelButton.addActionListener(ev -> exportDialog.dispose());

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
            buttonPanel.add(cancelButton);

            exportDialog.add(scrollPane, BorderLayout.CENTER);
            exportDialog.add(buttonPanel, BorderLayout.SOUTH);
            exportDialog.setLocationRelativeTo(exportBtn);
            Design.setFontForAllComponents(exportDialog);
            exportDialog.setVisible(true);
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);

        // Добавляем слушатель для выбора узла в дереве
        tree.addTreeSelectionListener((TreeSelectionEvent e) -> {
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
        });
        Design.setFontForAllComponents(frame);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
