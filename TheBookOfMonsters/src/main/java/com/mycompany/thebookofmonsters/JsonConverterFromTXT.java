package com.mycompany.thebookofmonsters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonConverterFromTXT {

    public static void convert(String inputTxtPath, String outputJsonPath, MonsterStorage storage) {
        try {
            System.out.println(outputJsonPath+"JSON");
            List<Monster> monsters = MonsterParser.parseFromTextFile(inputTxtPath);

            storage.addFormat("json", monsters);
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(outputJsonPath), monsters);

  
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
}