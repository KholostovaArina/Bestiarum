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

public class FileExporter {

    public static void exportFormat(String formatName, MonsterStorage storage) {
        String directoryPath = selectDirectoryPath();
        export(formatName, storage, directoryPath);
    }
    
    public static void export(String formatName, MonsterStorage storage, String directoryPath) {
        try {
            List<Monster> monsters = storage.getMonstersByFormat(formatName);
            if (monsters == null || monsters.isEmpty()) {
                throw new IllegalArgumentException("Нет данных для экспорта в формате " + formatName);
            }

            String fileName = "monsters." + formatName.toLowerCase();
            String filePath = directoryPath + File.separator + fileName;

            switch (formatName.toLowerCase()) {
                case "json" -> exportToJson(monsters, filePath);
                case "xml" -> exportToXml(monsters, filePath);
                case "yaml" -> exportToYaml(monsters, filePath);
                default ->
                    throw new IllegalArgumentException("Неподдерживаемый формат: " + formatName);
            }

        } catch (JAXBException | IOException | IllegalArgumentException e) {
            System.err.println("Ошибка при экспорте в формате " + formatName + ": " + e.getMessage());
        }
    }

    public static void exportAllFormats(MonsterStorage storage) {
        String directoryPath = selectDirectoryPath();
        Map<String, List<Monster>> allFormats = storage.getAllFormats();
        if (allFormats == null || allFormats.isEmpty()) {
            System.err.println("Нет данных для экспорта");
            return;
        }
        
        for (String formatName : allFormats.keySet()) {
            export(formatName, storage, directoryPath);
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
    
    private static String selectDirectoryPath() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = fc.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            return null; 
        }

        return fc.getSelectedFile().getAbsolutePath();
    }
}