package com.mycompany.thebookofmonsters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;




public class TxtConverter {

    public static void convertFile(String inputFilePath) throws IOException {
        // Чтение содержимого файла
        String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));

        // Конвертация в JSON
        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonContent = jsonMapper.writeValueAsString(content);

        // Конвертация в XML
        XmlMapper xmlMapper = new XmlMapper();
        String xmlContent = xmlMapper.writeValueAsString(content);

        // Конвертация в YAML
        YAMLMapper yamlMapper = new YAMLMapper();
        String yamlContent = yamlMapper.writeValueAsString(content);

        // Запись результатов в файлы
        Files.write(Paths.get("output.json"), jsonContent.getBytes());
        Files.write(Paths.get("output.xml"), xmlContent.getBytes());
        Files.write(Paths.get("output.yaml"), yamlContent.getBytes());
    }
}
