package com.mycompany.thebookofmonsters;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParser extends AbstractParser {
    private final MonsterStorage storage;
    private final JAXBContext context;

    public XmlParser(MonsterStorage storage) throws Exception {
        this.format = "xml";
        this.storage = storage;
        this.context = JAXBContext.newInstance(MonstersXmlWrapper.class); // Инициализация один раз
    }

    @Override
    public void parse(String fileName) {
        if (!fileName.endsWith(".xml")) {
            super.parse(fileName);
            return;
        }

        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MonstersXmlWrapper wrapper = (MonstersXmlWrapper) unmarshaller.unmarshal(new File(fileName));
            
            if (wrapper.getMonsters() != null) {
                storage.addFormat(format, wrapper.getMonsters());
                System.out.println("Successfully parsed JSON file: " + fileName);
            }
        } catch (Exception e) {
            System.err.println("Error parsing YAML file: " + e.getMessage());
        }
    }
}