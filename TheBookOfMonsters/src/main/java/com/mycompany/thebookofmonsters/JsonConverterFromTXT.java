package com.mycompany.thebookofmonsters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonConverterFromTXT {

    public static void convert(String inputTxtPath, String outputJsonPath, MonsterStorage storage) {
        try {
            // 1. Парсим монстров из TXT
            List<Monster> monsters = MonsterParser.parseFromTextFile(inputTxtPath);

            // 2. Запись в JSON-файл
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(outputJsonPath), monsters);

            // 3. Добавляем в хранилище
            storage.addFormat("json", monsters);

            System.out.println("Успешно сконвертировано в JSON.");

        } catch (IOException e) {
            System.err.println("Ошибка при конвертации в JSON: " + e.getMessage());
        }
    }
}
