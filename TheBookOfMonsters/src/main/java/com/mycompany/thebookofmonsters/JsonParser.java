package com.mycompany.thebookofmonsters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser extends AbstractParser{
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonParser() {
        this.format = "json";
    }

    @Override
    protected void parse(String fileName) {
        try {
            List<Monster> monsters = mapper.readValue(
                new File(fileName),
                mapper.getTypeFactory().constructCollectionType(List.class, Monster.class)
            );
            System.out.println("Successfully parsed JSON: " + monsters.size() + " monsters");
            // Далее сохраняем monsters в хранилище
        } catch (IOException e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
    }
}
