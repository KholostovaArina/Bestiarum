package com.mycompany.thebookofmonsters;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class View {

    private static Image thePhoto;

    static {
        try {
            thePhoto = ImageIO.read(View.class.getResourceAsStream("/фото.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Книга чудовищ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Оглавление");
        DefaultMutableTreeNode xmlNode = new DefaultMutableTreeNode("XML");
        DefaultMutableTreeNode yamlNode = new DefaultMutableTreeNode("YAML");
        DefaultMutableTreeNode jsonNode = new DefaultMutableTreeNode("JSON");

        root.add(xmlNode);
        root.add(yamlNode);
        root.add(jsonNode);

        JTree tree = new JTree(root);

        JPanel sheetPanel = new JPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(tree), sheetPanel);
        splitPane.setDividerLocation(150);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton buttonImport = new JButton("Иморт");
        JButton buttonExport = new JButton("Экспорт");

        bottomPanel.add(buttonImport);
        bottomPanel.add(buttonExport);

        frame.add(splitPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        makeSheetPanel();
        frame.setVisible(true);
    }

    private static void  makeSheetPanel(){
        
    }
    
    public static String chooseTxtFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
}
