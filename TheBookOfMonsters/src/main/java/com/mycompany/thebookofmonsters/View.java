package com.mycompany.thebookofmonsters;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View {

    public static JTree tree;
    public static Color bezheviy = new Color(220, 210, 200);
    public static Color brown = new Color(103,79,60);
    public static JButton importBtn;
    public static JButton exportBtn;
    public static JButton exportAllBtn;
    public static JButton btnStart;
    public static JFrame frameStart;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Книга чудовищ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 600);

        tree = new JTree(new DefaultMutableTreeNode("Чудовища"));
        tree.setOpaque(false);

        importBtn = new JButton("Импорт");
        exportBtn = new JButton("Экспорт");
        exportAllBtn = new JButton("Экспорт всех форматов");

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
        btnPanel.setBackground(brown);
        importBtn.setBackground(bezheviy);
        exportBtn.setBackground(bezheviy);
        exportAllBtn.setBackground(bezheviy);
        
        btnPanel.add(importBtn);
        btnPanel.add(exportBtn);
        btnPanel.add(exportAllBtn);

        exportAllBtn.addActionListener(ev -> {
            JDialog exportDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(exportBtn),
                    "Выбор формата для экспорта", true);
            try {
                FileExporter.exportAllFormats(Controller.getStorage());
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
                    
                    } catch (HeadlessException ex) {
                        JOptionPane.showMessageDialog(exportDialog,
                                "Ошибка при экспорте: " + ex.getMessage(),
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
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
                            try {
                                InfoMonsterSheet.showInfo(monster, infoPanel);
                            } catch (IOException ex) {
                                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                            }
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

    public static void showPreview() {
        frameStart = new JFrame("Привет пользователь!");
        frameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameStart.setSize(400, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(brown);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(70, 30, 30, 30));

        JTextArea text = new JTextArea("Выберите, пожалуйста, текстовый файл, из которого будут импортированы чудовища");
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setFont(Design.getFont().deriveFont(22f));
        text.setOpaque(false);
        text.setForeground(Color.WHITE);

        btnStart = new JButton("OK");
        btnStart.setBackground(bezheviy);
        btnStart.setFont(Design.getBigFont());

        mainPanel.add(text, BorderLayout.CENTER);
        mainPanel.add(btnStart, BorderLayout.SOUTH);

        frameStart.add(mainPanel);
        frameStart.setLocationRelativeTo(null);
        frameStart.setVisible(true);
    }
}
