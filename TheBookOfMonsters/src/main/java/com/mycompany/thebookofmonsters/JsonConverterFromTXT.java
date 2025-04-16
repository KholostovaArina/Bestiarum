package com.mycompany.thebookofmonsters;

import java.io.IOException;
import java.util.List;

public class JsonConverterFromTXT {

    public static void convert(String inputTxtPath, MonsterStorage storage) {
        try {
            List<Monster> monsters = MonsterParser.parseFromTextFile(inputTxtPath);
            storage.addFormat("json", monsters);
        } catch (IOException e) {
            System.out.println("A problem converting .txt -> .json");
        }
    }
}