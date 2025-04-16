package com.mycompany.thebookofmonsters;

import java.io.*;
import java.util.List;

public class YamlConverterFromTXT {

    public static void convert(String inputTxtPath, MonsterStorage storage) {
        try {
            List<Monster> monsters = MonsterParser.parseFromTextFile(inputTxtPath);
            storage.addFormat("yaml", monsters);
        } catch (IOException e) {
            System.out.println("A problem converting .txt -> .yaml");
        }
    }
}