package com.mycompany.thebookofmonsters;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.*;

public class XmlParser extends AbstractParser {

    @Override
    protected void parse(String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//    public XmlParser() {
//        this.format = "xml";
//    }
//
//    @Override
//    protected void parse(String fileName) {
//        try {
//            XMLInputFactory factory = XMLInputFactory.newInstance();
//            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(fileName));
//
//            List<Monster> monsters = new ArrayList<>();
//            Monster currentMonster = null;
//            String currentElement = null;
//
//            while (reader.hasNext()) {
//                XMLEvent event = reader.nextEvent();
//
//                if (event.isStartElement()) {
//                    StartElement startElement = event.asStartElement();
//                    currentElement = startElement.getName().getLocalPart();
//
//                    if ("monster".equals(currentElement)) {
//                        currentMonster = new Monster();
//                    }
//                }
//
//                if (event.isCharacters()) {
//                    Characters characters = event.asCharacters();
//                    String data = characters.getData().trim();
//
//                    if (!data.isEmpty() && currentMonster != null) {
//                        switch (currentElement) {
//                            case "name": currentMonster.setName(data); break;
//                            case "description": currentMonster.setDescription(data); break;
//                            case "dangerLevel": currentMonster.setDangerLevel(Integer.parseInt(data)); break;                 
//                            case "habitat": currentMonster.setHabitat(data); break; 
//                            case "firstMention": currentMonster.setFirstMention(data); break;
//                            case "immunities": currentMonster.setImmunities(data); break;
//                            case "height": currentMonster.setHeight(Integer.parseInt(data)); break;
//                            case "weight": currentMonster.setWeight(Integer.parseInt(data)); break;
//                            case "recipe": currentMonster.setRecipe(data); break;
//                            case "time": currentMonster.setTime(data); break; 
//                        }
//                    }
//                }
//
//                if (event.isEndElement()) {
//                    EndElement endElement = event.asEndElement();
//                    String elementName = endElement.getName().getLocalPart();
//
//                    if ("monster".equals(elementName) && currentMonster != null) {
//                        monsters.add(currentMonster);
//                        currentMonster = null;
//                    }
//                }
//            }
//
//            // Сохраняем в хранилище
//            MonsterStorage storage = MonsterStorage.getInstance();
//            storage.addMonsters("xml", monsters);
//
//        } catch (XMLStreamException | IOException e) {
//            System.err.println("XML parsing error: " + e.getMessage());
//        }
//    }
}