package com.mycompany.thebookofmonsters;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class YamlConverterFromTXT {

    public static void convert(String inputTxtPath, String outputYamlPath, MonsterStorage storage) {
        try {
            // 1. Получаем список монстров
            List<Monster> monsters = MonsterParser.parseFromTextFile(inputTxtPath);

            // 2. Добавляем в хранилище
            storage.addFormat("yaml", monsters);

            // 3. Настройка красивого YAML
            DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);

            // 4. Сохраняем в файл
            try (Writer writer = Files.newBufferedWriter(Path.of(outputYamlPath))) {
                yaml.dump(monsters, writer);
            }

            System.out.println("Успешно сконвертировано в YAML: " + outputYamlPath);

        } catch (IOException e) {
            System.err.println("Ошибка при конвертации в YAML: " + e.getMessage());
        }
    }
}