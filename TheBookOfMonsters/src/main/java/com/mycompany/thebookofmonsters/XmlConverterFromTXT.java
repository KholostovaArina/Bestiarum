package com.mycompany.thebookofmonsters;

import java.io.*;
import java.util.List;

public class XmlConverterFromTXT {

    public static void convert(String inputTxtPath, MonsterStorage storage) {
        try {
            List<Monster> monsters = MonsterParser.parseFromTextFile(inputTxtPath);
            storage.addFormat("xml", monsters);
        } catch (IOException e) {
            System.out.println("A problem converting .txt -> .xml");
        }
    }
}
