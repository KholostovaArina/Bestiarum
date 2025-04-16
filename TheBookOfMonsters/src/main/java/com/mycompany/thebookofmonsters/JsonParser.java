package com.mycompany.thebookofmonsters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser extends AbstractParser {
    private final MonsterStorage storage;
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonParser(MonsterStorage storage) {
        this.format = "json";
        this.storage = storage;
    }

    @Override
    public void parse(String fileName) {
        if (fileName.endsWith(".json")) {
            try {
                CollectionType listType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, Monster.class);
                
                List<Monster> monsters = mapper.readValue(new File(fileName), listType);
                storage.addFormat(format, monsters);
                System.out.println("Successfully parsed JSON file: " + fileName);
            } catch (IOException e) {
                System.err.println("Error parsing JSON file: " + e.getMessage());
            }
        } else {
            super.parse(fileName);
        }
    }
}