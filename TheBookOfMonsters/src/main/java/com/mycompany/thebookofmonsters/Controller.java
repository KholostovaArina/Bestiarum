package com.mycompany.thebookofmonsters;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Controller {

    private String input;
    private final String output = "src/main/resources/monsters";
    private String fileName;

    private static final MonsterStorage storage = new MonsterStorage();

    public void run() throws Exception {
        input = chooseFile("txt");
        convert();

        View.createAndShowGUI();
        setTree();

        View.importBtn.addActionListener(e -> {
            fileName = chooseFile("yaml", "json", "xml", "yml");
            if (fileName != null) {
                try {
                    parse(fileName);
                    setTree();
                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Обработка случая, когда пользователь отменил выбор файла
                JOptionPane.showMessageDialog(null,
                        "Файл не был выбран",
                        "Информация",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        
    }

    public static void parse(String fileName) throws Exception {
        XmlParser xmlParser = new XmlParser(storage);
        YamlParser yamlParser = new YamlParser(storage);
        JsonParser jsonParser = new JsonParser(storage);
        
        xmlParser.setNext(yamlParser);
        yamlParser.setNext(jsonParser);

        xmlParser.parse(fileName);
    }

    public static String chooseFile(String... extensions) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

        // Создаем описание фильтра
        StringBuilder description = new StringBuilder("Допустимые файлы (");
        for (int i = 0; i < extensions.length; i++) {
            if (i > 0) {
                description.append(", ");
            }
            description.append("*.").append(extensions[i]);
        }
        description.append(")");

        // Создаем и устанавливаем фильтр
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                description.toString(),
                extensions
        );
        fc.setFileFilter(filter);

        // Показываем диалог выбора файла
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

    // Добавляем геттер для доступа к storage
    public static MonsterStorage getStorage() {
        return storage;
    }
}