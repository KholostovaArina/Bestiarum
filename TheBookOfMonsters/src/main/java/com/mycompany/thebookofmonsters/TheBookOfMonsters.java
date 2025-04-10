package com.mycompany.thebookofmonsters;

import java.io.IOException;
import java.util.List;

public class TheBookOfMonsters {

    public static void main(String[] args) throws IOException, Exception {
        String input = "C:\\Users\\GOSPOGA\\OneDrive\\Документы\\GitHub\\Bestiarum\\TheBookOfMonsters\\Bestiarum скучное.txt";
        String jsonoutput = "src/output_monsters.json";
        String yamlOutput = "src/main/resources/monsters.yaml";
       String xmlOutput = "src/main/resources/monsters.xml";
       
        MonsterStorage storage = new MonsterStorage();

        XmlConverterFromTXT.convert(input, xmlOutput, storage);
//        YamlConverterFromTXT.convert(input, yamlOutput, storage);
//        JsonConverterFromTXT.convert(input, jsonoutput, storage);

    }
}
