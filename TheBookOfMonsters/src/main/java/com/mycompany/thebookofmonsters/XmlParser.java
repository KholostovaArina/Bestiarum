package com.mycompany.thebookofmonsters;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class XmlParser extends AbstractParser{
    private final XmlMapper mapper = new XmlMapper();

    public XmlParser() {
        this.format = "xml";
    }

    @Override
    protected void parse(String fileName) {
        try {
            MonsterListWrapper wrapper = mapper.readValue(
                new File(fileName), 
                MonsterListWrapper.class
            );
            System.out.println("Successfully parsed XML: " + wrapper.monsters.size() + " monsters");
        } catch (IOException e) {
            System.out.println("Error parsing XML: " + e.getMessage());
        }
    }

    // Вспомогательный класс для обертки списка
    public static class MonsterListWrapper {
        public List<Monster> monsters = Collections.emptyList();
    }
}
