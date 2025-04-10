package com.mycompany.thebookofmonsters;

import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Controller {
    private String input;
    private final String output = "src/main/resources/monsters";

    private static final MonsterStorage storage = new MonsterStorage();

    
    public void run(){
        input = chooseTxtFile();
        convert();
        View.createAndShowGUI();
        setTree();
    }
    
    public static String chooseTxtFile() {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
    
    public void convert() {
        XmlConverterFromTXT.convert(input, output + ".xml", storage);
        YamlConverterFromTXT.convert(input, output + ".yaml", storage);
        JsonConverterFromTXT.convert(input, output + ".json", storage);
    }
    
    
    public static void setTree() {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) View.tree.getModel().getRoot();
        root.removeAllChildren(); // Очищаем дерево перед заполнением

        // Получаем Map<String, List<Monster>> из storage
        for (String format : storage.getAllFormats().keySet()) {
            // Создаем узел для каждого формата (ключа)
            DefaultMutableTreeNode formatNode = new DefaultMutableTreeNode(format);
            root.add(formatNode);

            // Добавляем монстров как дочерние узлы для каждого формата
            List<Monster> monsters = storage.getMonstersByFormat(format);
            if (monsters != null) {
                for (Monster monster : monsters) {
                    DefaultMutableTreeNode monsterNode = new DefaultMutableTreeNode(monster.getName());
                    formatNode.add(monsterNode);
                }
            }
        }

        // Обновляем модель дерева
        ((DefaultTreeModel) View.tree.getModel()).reload();
    }
}