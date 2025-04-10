package com.mycompany.thebookofmonsters;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "monsters")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonstersXmlWrapper {
    @XmlElement(name = "monster")
    private List<Monster> monsters;

    public MonstersXmlWrapper() {}

    public MonstersXmlWrapper(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }
}

