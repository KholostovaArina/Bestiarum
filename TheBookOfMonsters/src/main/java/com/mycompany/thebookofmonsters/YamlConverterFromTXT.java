package com.mycompany.thebookofmonsters;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class YamlConverterFromTXT {

    public static void convert(String inputTxtPath, String outputYamlPath, MonsterStorage storage) {
        try {
            List<Monster> monsters = MonsterParser.parseFromTextFile(inputTxtPath);

            storage.addFormat("yaml", monsters);

            DumperOptions options = new DumperOptions();
            options.setIndent(2);//Установка отступа в 2 пробела для YAML-файла.
            options.setPrettyFlow(true);//Включение "красивого" форматирования потоков данных.
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);//Установка стиля потока по умолчанию как BLOCK (блочный стиль, более читаемый).
            Yaml yaml = new Yaml(options);

            try (Writer writer = Files.newBufferedWriter(Path.of(outputYamlPath))) {
                yaml.dump(monsters, writer);
            }
        } catch (IOException e) {}
    }
}