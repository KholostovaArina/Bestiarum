package com.mycompany.thebookofmonsters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.xml.bind.*;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileExporter {

    public static void exportFormat(String formatName, MonsterStorage storage) throws Exception {
        // Получаем монстров по формату
        List<Monster> monsters = storage.getMonstersByFormat(formatName);
        if (monsters == null || monsters.isEmpty()) {
            throw new IllegalArgumentException("Нет данных для экспорта в формате " + formatName);
        }

        // Создаем FileChooser для выбора места сохранения
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите место для сохранения");
        fileChooser.setSelectedFile(new File("monsters." + formatName.toLowerCase()));

        // Устанавливаем фильтр для соответствующего формата
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                formatName.toUpperCase() + " файлы (*." + formatName.toLowerCase() + ")",
                formatName.toLowerCase()
        );
        fileChooser.setFileFilter(filter);

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Добавляем расширение, если его нет
            if (!filePath.toLowerCase().endsWith("." + formatName.toLowerCase())) {
                filePath += "." + formatName.toLowerCase();
                selectedFile = new File(filePath);
            }

            switch (formatName.toLowerCase()) {
                case "json" ->
                    exportToJson(monsters, filePath);
                case "xml" ->
                    exportToXml(monsters, filePath);
                case "yaml" ->
                    exportToYaml(monsters, filePath);
                default ->
                    throw new IllegalArgumentException("Неподдерживаемый формат: " + formatName);
            }
        }
    }

    public static void exportAllFormats(MonsterStorage storage) {
        Map<String, List<Monster>> allFormats = storage.getAllFormats();
        if (allFormats == null || allFormats.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Нет данных для экспорта",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Создаем FileChooser для выбора директории
        JFileChooser dirChooser = new JFileChooser();
        dirChooser.setDialogTitle("Выберите папку для сохранения всех форматов");
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (dirChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File directory = dirChooser.getSelectedFile();

            for (String formatName : allFormats.keySet()) {
                try {
                    String filePath = new File(directory, "monsters." + formatName.toLowerCase()).getAbsolutePath();
                    switch (formatName.toLowerCase()) {
                        case "json" ->
                            exportToJson(allFormats.get(formatName), filePath);
                        case "xml" ->
                            exportToXml(allFormats.get(formatName), filePath);
                        case "yaml" ->
                            exportToYaml(allFormats.get(formatName), filePath);
                    }
                } catch (JAXBException | IOException e) {
                    JOptionPane.showMessageDialog(null,
                            "Ошибка при экспорте формата " + formatName + ": " + e.getMessage(),
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private static void exportToJson(List<Monster> monsters, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filePath), monsters);
    }

    private static void exportToXml(List<Monster> monsters, String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(MonstersXmlWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        MonstersXmlWrapper wrapper = new MonstersXmlWrapper();
        wrapper.setMonsters(monsters);

        marshaller.marshal(wrapper, new File(filePath));
    }

    private static void exportToYaml(List<Monster> monsters, String filePath) throws IOException {
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(monsters, writer);
        }
    }
}