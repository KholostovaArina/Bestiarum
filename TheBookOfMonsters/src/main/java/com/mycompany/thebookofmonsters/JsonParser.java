package com.mycompany.thebookofmonsters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonParser extends AbstractParser {

    @Override
    protected void parse(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    public JsonParser() {
//        this.format = "json";
//    }
//
//    @Override
//    protected void parse(String fileName) {
//        try {
//            List<Monster> monsters = mapper.readValue(
//                new File(fileName),
//                mapper.getTypeFactory().constructCollectionType(List.class, Monster.class)
//            );
//
//            // Сохраняем в хранилище
//            MonsterStorage storage = MonsterStorage.getInstance();
//            storage.addMonsters("json", monsters);
//
//        } catch (IOException e) {
//            System.err.println("JSON parsing error: " + e.getMessage());
//        }
//    }
}
