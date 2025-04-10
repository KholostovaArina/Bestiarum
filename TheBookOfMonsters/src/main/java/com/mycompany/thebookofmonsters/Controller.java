package com.mycompany.thebookofmonsters;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Controller {
//    private static final MonsterStorage storage = new MonsterStorage();
//    private static Parser parserChain;
//
//    public static void initialize() {
//        JsonParser jsonParser = new JsonParser();
//        XmlParser xmlParser = new XmlParser();
//        YamlParser yamlParser = new YamlParser();
//        
//        jsonParser.setNext(xmlParser);
//        xmlParser.setNext(yamlParser);
//        parserChain = jsonParser;
//    }
//
//    public static void importFiles() {
//        try {
//            String path = View.chooseTxtFile();
//            if (path != null) {
//                TxtConverter.convertFile(path);
//                storage.clear();
//                parserChain.handle("output.json");
//                parserChain.handle("output.xml");
//                parserChain.handle("output.yaml");
//                View.buildTree(storage); // Исправленный метод
//                JOptionPane.showMessageDialog(null, "Импорт завершён успешно!");
//            }
//        } catch (IOException e) {
//            showError("Ошибка импорта", e);
//        }
//    }
//
//    public static void exportFiles() {
//        try {
//            if (storage.getAllMonsters().isEmpty()) {
//                JOptionPane.showMessageDialog(null, "Нет данных для экспорта");
//                return;
//            }
//            
//            // Исправленный экспорт
//            List<Monster> monsters = storage.getAllMonsters();
//            FileExporter.exportToJson(monsters, "export.json");
//            FileExporter.exportToXml(monsters, "export.xml");
//            FileExporter.exportToYaml(monsters, "export.yaml");
//            
//            JOptionPane.showMessageDialog(null, "Экспорт завершён успешно!");
//        } catch (IOException e) {
//            showError("Ошибка экспорта", e);
//        }
//    }
//
//    private static void showError(String title, Exception e) {
//        JOptionPane.showMessageDialog(null, 
//            title + ": " + e.getMessage(),
//            "Ошибка", JOptionPane.ERROR_MESSAGE);
//    }
//
//    public static MonsterStorage getStorage() {
//        return storage;
//    }
}