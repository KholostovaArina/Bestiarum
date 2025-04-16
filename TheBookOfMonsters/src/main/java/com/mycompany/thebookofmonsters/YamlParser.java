package com.mycompany.thebookofmonsters;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class YamlParser extends AbstractParser {
    private final MonsterStorage storage;

    public YamlParser(MonsterStorage storage) {
        this.format = "yaml";
        this.storage = storage;
    }

    @Override
    public void parse(String fileName) {
        if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) {
            try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
                
                Yaml yaml = new Yaml(new Constructor(List.class));
                
                List<Monster> monsters = yaml.load(inputStream);
                
                storage.addFormat(format, monsters);
                System.out.println("Successfully parsed YAML file: " + fileName);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + fileName);
            } catch (Exception e) {
                System.err.println("Error parsing YAML file: " + e.getMessage());
            }
        } else {
            super.parse(fileName);
        }
    }
}