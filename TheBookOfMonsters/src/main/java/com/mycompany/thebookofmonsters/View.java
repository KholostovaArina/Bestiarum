package com.mycompany.thebookofmonsters;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;

public class View {
//    private static JTree tree;
//    private static JTextArea infoArea;
//
//    public static void createAndShowGUI() {
//        JFrame frame = new JFrame("Книга чудовищ");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//
//        // Инициализация компонентов
//        tree = new JTree(new DefaultMutableTreeNode("Чудовища"));
//        infoArea = new JTextArea();
//        infoArea.setEditable(false);
//
//        JButton importBtn = new JButton("Импорт");
//        JButton exportBtn = new JButton("Экспорт");
////        importBtn.addActionListener(e -> Controller.importFiles());
////        exportBtn.addActionListener(e -> Controller.exportFiles());
//
//        // Компоновка интерфейса
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.add(new JScrollPane(tree), BorderLayout.WEST);
//        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
//
//        JPanel btnPanel = new JPanel();
//        btnPanel.add(importBtn);
//        btnPanel.add(exportBtn);
//
//        frame.add(panel, BorderLayout.CENTER);
//        frame.add(btnPanel, BorderLayout.SOUTH);
//        frame.setVisible(true);
//    }
//
//    public static String chooseTxtFile() {
//        JFileChooser fc = new JFileChooser();
//        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
//        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//            return fc.getSelectedFile().getAbsolutePath();
//        }
//        return null;
//    }
//
//    public static void updateTree() {
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Чудовища");
////        MonsterStorage storage = Controller.getStorage();
//        
//        DefaultMutableTreeNode jsonNode = new DefaultMutableTreeNode("JSON");
////        storage.getMonstersByType("json").forEach(m -> jsonNode.add(new DefaultMutableTreeNode(m.getName())));
//        root.add(jsonNode);
//
//        DefaultMutableTreeNode xmlNode = new DefaultMutableTreeNode("XML");
////        storage.getMonstersByType("xml").forEach(m -> xmlNode.add(new DefaultMutableTreeNode(m.getName())));
//        root.add(xmlNode);
//
//        DefaultMutableTreeNode yamlNode = new DefaultMutableTreeNode("YAML");
////        storage.getMonstersByType("yaml").forEach(m -> yamlNode.add(new DefaultMutableTreeNode(m.getName())));
//        root.add(yamlNode);
//
//        tree.setModel(new DefaultTreeModel(root));
//    }
//    
//    public static void buildTree(MonsterStorage storage) {
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Чудовища");
//        
//        // Для JSON
//        DefaultMutableTreeNode jsonNode = new DefaultMutableTreeNode("JSON");
//        storage.getMonstersByType("json").forEach(m -> 
//            jsonNode.add(new DefaultMutableTreeNode(m.getName())));
//        root.add(jsonNode);
//        
//        // Для XML
//        DefaultMutableTreeNode xmlNode = new DefaultMutableTreeNode("XML");
//        storage.getMonstersByType("xml").forEach(m -> 
//            xmlNode.add(new DefaultMutableTreeNode(m.getName())));
//        root.add(xmlNode);
//        
//        // Для YAML
//        DefaultMutableTreeNode yamlNode = new DefaultMutableTreeNode("YAML");
//        storage.getMonstersByType("yaml").forEach(m -> 
//            yamlNode.add(new DefaultMutableTreeNode(m.getName())));
//        root.add(yamlNode);
//        
//        tree.setModel(new DefaultTreeModel(root));
//    }
}