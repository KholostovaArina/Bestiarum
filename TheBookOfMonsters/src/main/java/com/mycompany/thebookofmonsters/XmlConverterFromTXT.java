package com.mycompany.thebookofmonsters;

import jakarta.xml.bind.*;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class XmlConverterFromTXT {

    public static void convert(String inputTxtPath, String outputXmlPath, MonsterStorage storage) {
        try {
            List<Monster> monsters = MonsterParser.parseFromTextFile(inputTxtPath);
            storage.addFormat("xml", monsters);

            MonstersXmlWrapper wrapper = new MonstersXmlWrapper(monsters);

            JAXBContext context = JAXBContext.newInstance(MonstersXmlWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            try (Writer writer = Files.newBufferedWriter(Path.of(outputXmlPath))) {
                marshaller.marshal(wrapper, writer);
            }

            System.out.println("Успешно сконвертировано в XML: " + outputXmlPath);

        } catch (Exception e) {
            System.err.println("Ошибка при конвертации в XML: " + e.getMessage());
        }
    }
}
