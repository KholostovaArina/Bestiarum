package com.mycompany.thebookofmonsters;

import java.util.*;

public class MonsterStorage {
    private Map<String, List<Monster>> formatStorage;

    public MonsterStorage() {
        this.formatStorage = new HashMap<>();
    }

    public void addFormat(String formatName, List<Monster> monsters) {
        formatStorage.put(formatName, new ArrayList<>(monsters));
    }

    public List<Monster> getMonstersByFormat(String formatName) {
        return formatStorage.get(formatName);
    }

    public Map<String, List<Monster>> getAllFormats() {
        return formatStorage;
    }
}