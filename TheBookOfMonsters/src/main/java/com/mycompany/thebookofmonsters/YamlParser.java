package com.mycompany.thebookofmonsters;

import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.*;

public class YamlParser extends AbstractParser {
    public YamlParser() {
        this.format = "yaml";
    }

//    @Override
//    protected void parse(String fileName) {
//        try {
//            Yaml yaml = new Yaml();
//            InputStream inputStream = new FileInputStream(fileName);
//
//            // Предполагаем, что YAML файл содержит список монстров
//            List<Map<String, Object>> yamlData = yaml.load(inputStream);
//            List<Monster> monsters = new ArrayList<>();
//
//            for (Map<String, Object> data : yamlData) {
//                Monster monster = new Monster();
//                monster.setName((String) data.get("name"));
//                monster.setDescription((String) data.get("description"));
//                monster.setDangerLevel((Integer) data.get("dangerLevel"));
//                monster.setHabitat((String) data.get("habitat"));
//                monster.setFirstMention((String) data.get("firstMention"));
//                monster.setImmunities((String) data.get("immunities"));
//                monster.setHeight((Integer) data.get("height"));
//                monster.setWeight((Integer) data.get("weight"));
//                monster.setRecipe((String) data.get("recipe"));
//                monster.setTime((String) data.get("time"));
//                monster.setEfficiency((String) data.get("efficiency"));
//                monsters.add(monster);
//            }
//
//            // Сохраняем в хранилище
//            MonsterStorage storage = MonsterStorage.getInstance();
//            storage.addMonsters("yaml", monsters);
//
//        } catch (FileNotFoundException e) {
//            System.err.println("YAML parsing error: " + e.getMessage());
//        }
//    }

    @Override
    protected void parse(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}