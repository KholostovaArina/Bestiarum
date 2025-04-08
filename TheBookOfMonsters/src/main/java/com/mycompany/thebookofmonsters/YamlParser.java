package com.mycompany.thebookofmonsters;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class YamlParser extends AbstractParser{
    private final YAMLMapper mapper = new YAMLMapper();

    public YamlParser() {
        this.format = "yaml";
    }

    @Override
    protected void parse(String fileName) {
        try {
            List<Monster> monsters = mapper.readValue(
                new File(fileName),
                mapper.getTypeFactory().constructCollectionType(List.class, Monster.class)
            );
            System.out.println("Successfully parsed YAML: " + monsters.size() + " monsters");
        } catch (IOException e) {
            System.out.println("Error parsing YAML: " + e.getMessage());
        }
    }
}
